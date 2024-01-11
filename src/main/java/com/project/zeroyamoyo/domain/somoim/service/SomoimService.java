package com.project.zeroyamoyo.domain.somoim.service;

import com.project.zeroyamoyo.domain.interest.entity.Interest;
import com.project.zeroyamoyo.domain.interest.repository.InterestRepository;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimCreate;
import com.project.zeroyamoyo.domain.somoim.api.model.SomoimGet;
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
        Interest interest = interestRepository.findById(request.getInterestId().longValue())
                .orElseThrow(() -> new GlobalException(ResultType.INVALID_REQUEST_PARAMETER));

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

    public SomoimGet.Response getSomoim(Long somoimId) {
        Somoim somoim = somoimRepository.findById(somoimId)
                .orElseThrow(() -> new GlobalException(ResultType.SOMOIM_NOT_FOUND));
        return new SomoimGet.Response(somoim);
    }
}
