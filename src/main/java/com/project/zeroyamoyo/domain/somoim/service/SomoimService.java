package com.project.zeroyamoyo.domain.somoim.service;

import com.project.zeroyamoyo.domain.interest.entity.Interest;
import com.project.zeroyamoyo.domain.interest.repository.InterestRepository;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimCreate;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimGet;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimInterestModify;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimModify;
import com.project.zeroyamoyo.domain.somoim.entity.MemberRole;
import com.project.zeroyamoyo.domain.somoim.entity.Somoim;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimInterest;
import com.project.zeroyamoyo.domain.somoim.entity.SomoimMember;
import com.project.zeroyamoyo.domain.somoim.repository.SomoimInterestRepository;
import com.project.zeroyamoyo.domain.somoim.repository.SomoimMemberRepository;
import com.project.zeroyamoyo.domain.somoim.repository.SomoimRepository;
import com.project.zeroyamoyo.domain.user.entity.User;
import com.project.zeroyamoyo.global.auth.AuthenticationFacade;
import com.project.zeroyamoyo.global.exception.GlobalException;
import com.project.zeroyamoyo.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SomoimService {
    private final AuthenticationFacade auth;
    private final SomoimRepository somoimRepository;
    private final SomoimInterestRepository somoimInterestRepository;
    private final SomoimMemberRepository somoimMemberRepository;
    private final InterestRepository interestRepository;

    @Transactional
    public SomoimCreate.Response create(SomoimCreate.Request request) {
        Somoim newSomoim = createNewSomoim(request);
        return new SomoimCreate.Response(newSomoim);
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

    public SomoimGet.Response getSomoim(Long somoimId) {
        Somoim somoim = findSomoim(somoimId);
        return new SomoimGet.Response(somoim);
    }

    private Somoim findSomoim(Long somoimId) {
        return somoimRepository.findById(somoimId)
                .orElseThrow(() -> new GlobalException(ResultType.SOMOIM_NOT_FOUND));
    }

    @Transactional
    public SomoimModify.Response modifySomoim(Long somoimId, SomoimModify.Request request) {
        Somoim somoim = findSomoim(somoimId);
        checkIsUserOwnerOfSomoim(somoim);
        Somoim updatedSomoim = somoimRepository.save(
                somoim.updatedSomoim(request.getName(), request.getRegionCode(), request.getDescription(), request.getLimit())
        );
        return new SomoimModify.Response(updatedSomoim);
    }

    private void checkIsUserOwnerOfSomoim(Somoim somoim) {
        User user = (User) auth.getAuthentication().getPrincipal();
        SomoimMember somoimMember = somoimMemberRepository.findBySomoimAndUser(somoim, user)
                .orElseThrow(() -> new GlobalException(ResultType.ACCESS_DENIED));

        if (somoimMember.getRole() != MemberRole.OWNER) {
            throw new GlobalException(ResultType.ACCESS_DENIED);
        }
    }

    @Transactional
    public SomoimInterestModify.Response modifySomoimInterest(Long somoimId, SomoimInterestModify.Request request) {
        Somoim somoim = findSomoim(somoimId);
        Interest interest = findInterest(request.getInterestId().longValue());
        checkIsUserOwnerOfSomoim(somoim);

        SomoimInterest somoimInterest = somoim.getSomoimInterest();
        SomoimInterest updatedSomoimInterest = somoimInterestRepository.saveAndFlush(
                somoimInterest.updatedSomoimInterest(interest, String.join(",", request.getCategory()))
        );

        return new SomoimInterestModify.Response(updatedSomoimInterest);
    }
}
