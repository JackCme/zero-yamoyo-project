package com.project.zeroyamoyo.domain.somoim.service;

import com.project.zeroyamoyo.domain.interest.entity.Interest;
import com.project.zeroyamoyo.domain.interest.repository.InterestRepository;
import com.project.zeroyamoyo.domain.somoim.api.model.*;
import com.project.zeroyamoyo.domain.somoim.entity.MemberRole;
import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimInterest;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimMember;
import com.project.zeroyamoyo.domain.somoim.repository.SomoimInterestRepository;
import com.project.zeroyamoyo.domain.somoim.repository.SomoimMemberRepository;
import com.project.zeroyamoyo.domain.somoim.repository.SomoimRepository;
import com.project.zeroyamoyo.domain.user.entity.User;
import com.project.zeroyamoyo.domain.user.repository.UserRepository;
import com.project.zeroyamoyo.global.auth.AuthenticationFacade;
import com.project.zeroyamoyo.global.exception.GlobalException;
import com.project.zeroyamoyo.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SomoimService {
    private final AuthenticationFacade auth;
    private final SomoimRepository somoimRepository;
    private final SomoimInterestRepository somoimInterestRepository;
    private final SomoimMemberRepository somoimMemberRepository;
    private final InterestRepository interestRepository;
    private final UserRepository userRepository;

    @Transactional
    public SomoimCreate.Response create(SomoimCreate.Request request) {
        Somoim newSomoim = createNewSomoim(request);
        return new SomoimCreate.Response(newSomoim);
    }

    public SomoimGet.Response getSomoim(Long somoimId) {
        Somoim somoim = findSomoim(somoimId);
        return new SomoimGet.Response(somoim);
    }

    @Transactional
    public SomoimModify.Response modifySomoim(Long somoimId, SomoimModify.Request request) {
        Somoim somoim = checkCurrentUserIsOwnerOfSomoim(somoimId);
        Somoim updatedSomoim = somoimRepository.saveAndFlush(
                somoim.updatedSomoim(request.getName(), request.getRegionCode(), request.getDescription(), request.getLimit())
        );
        return new SomoimModify.Response(updatedSomoim);
    }

    @Transactional
    public SomoimInterestModify.Response modifySomoimInterest(Long somoimId, SomoimInterestModify.Request request) {
        Somoim somoim = checkCurrentUserIsOwnerOfSomoim(somoimId);
        SomoimInterest currentSomoimInterest = somoim.getSomoimInterest();
        Interest requestedInterest = findInterest(request.getInterestId().longValue());

        validateInterestCategory(request.getCategory(), requestedInterest.getInterestCategoryList());

        return new SomoimInterestModify.Response(somoimInterestRepository.saveAndFlush(
                currentSomoimInterest.updatedSomoimInterest(requestedInterest, String.join(",", request.getCategory()))
        ));
    }

    public CursorResult<SomoimGetList.Response> getSomoimList(Long cursorId, Pageable page) {
        List<Somoim> somoimList = Optional.ofNullable(cursorId)
                .map(id -> this.somoimRepository.findByIdLessThanOrderByIdDesc(id, page))
                .orElseGet(() -> this.somoimRepository.findAllByOrderByIdDesc(page));
        Long lastIdOfList = Optional.of(somoimList.get(somoimList.size() - 1))
                .map(Somoim::getId)
                .orElse(null);
        List<SomoimGetList.Response> collect = somoimList.stream()
                .map(SomoimGetList.Response::new)
                .collect(Collectors.toList());
        return new CursorResult<>(collect, hasNext(lastIdOfList));
    }

    @Transactional
    public void applyToSomoim(Long somoimId) {
        Somoim somoim = findSomoim(somoimId);
        User user = (User) auth.getAuthentication().getPrincipal();
        validateSomoimApply(user, somoim);
        somoimMemberRepository.save(SomoimMember.newTempMember(user, somoim));
    }

    @Transactional
    public void acceptMember(Long somoimId, Long userId) {
        SomoimMember applyingMember = findApplyingMember(somoimId, userId);
        applyingMember.acceptMember();
    }

    @Transactional
    public void declineMember(Long somoimId, Long userId) {
        SomoimMember applyingMember = findApplyingMember(somoimId, userId);
        somoimMemberRepository.delete(applyingMember);
    }

    private Somoim createNewSomoim(SomoimCreate.Request request) {
        User user = (User) auth.getAuthentication().getPrincipal();
        Interest interest = findInterest(request.getInterestId().longValue());

        Somoim savedSomoim = somoimRepository.save(Somoim.builder()
                .name(request.getName())
                .regionCode(request.getRegionCode())
                .description(request.getDescription())
                .limit(request.getLimit())
                .creator(user)
                .build()
        );

        somoimInterestRepository.save(SomoimInterest.builder()
                .somoim(savedSomoim)
                .interest(interest)
                .build()
        );

        somoimMemberRepository.save(SomoimMember.builder()
                .user(user).role(MemberRole.OWNER)
                .enrolledAt(LocalDateTime.now())
                .appliedAt(LocalDateTime.now())
                .somoim(savedSomoim)
                .build()
        );

        return savedSomoim;
    }

    private Interest findInterest(Long interestId) {
        return interestRepository.findById(interestId)
                .orElseThrow(() -> new GlobalException(ResultType.INVALID_REQUEST_PARAMETER));
    }

    private Somoim findSomoim(Long somoimId) {
        return somoimRepository.findById(somoimId)
                .orElseThrow(() -> new GlobalException(ResultType.SOMOIM_NOT_FOUND));
    }

    private Somoim checkCurrentUserIsOwnerOfSomoim(Long somoimId) {
        User user = (User) auth.getAuthentication().getPrincipal();
        Somoim somoim = findSomoim(somoimId);
        SomoimMember somoimMember = somoimMemberRepository.findBySomoimAndUser(somoim, user)
                .orElseThrow(() -> new GlobalException(ResultType.ACCESS_DENIED));

        if (!MemberRole.OWNER.equals(somoimMember.getRole())) {
            throw new GlobalException(ResultType.ACCESS_DENIED);
        }

        return somoim;
    }

    private Boolean hasNext(Long id) {
        return Optional.ofNullable(id)
                .map(this.somoimRepository::existsByIdLessThan)
                .orElse(false);
    }

    private void validateSomoimApply(User user, Somoim somoim) {
        // 이미 존재하면 Error
        somoimMemberRepository.findBySomoimAndUser(somoim, user).ifPresent(somoimMember -> {
            throw new GlobalException(ResultType.MEMBER_ALREADY_EXISTS);
        });
    }

    private SomoimMember findApplyingMember(Long somoimId, Long userId) {
        Somoim somoim = checkCurrentUserIsOwnerOfSomoim(somoimId);
        User user = userRepository.findById(userId).orElseThrow(() -> new GlobalException(ResultType.USER_NOT_FOUND));
        SomoimMember somoimMember = somoimMemberRepository.findBySomoimAndUser(somoim, user).orElseThrow(() -> new GlobalException(ResultType.MEMBER_NOT_FOUND));
        if (!MemberRole.TEMP.equals(somoimMember.getRole())) {
            throw new GlobalException(ResultType.NOT_TEMP_MEMBER);
        }
        return somoimMember;
    }

    private void validateInterestCategory(List<String> categoryList, List<InterestCategory> requestedInterestCategories) {
        boolean isValidCategory = categoryList
                .stream()
                .allMatch(category -> requestedInterestCategories.stream().anyMatch(interestCategory -> category.equals(interestCategory.getName())));
        if (!isValidCategory) {
            throw new GlobalException(ResultType.INVALID_INTEREST_CATEGORY);
        }
    }
}
