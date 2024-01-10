package com.project.zeroyamoyo.domain.user.service;

import com.project.zeroyamoyo.domain.user.api.model.UserJoin;
import com.project.zeroyamoyo.domain.user.entity.User;
import com.project.zeroyamoyo.domain.user.entity.UserInterest;
import com.project.zeroyamoyo.domain.user.repository.UserInterestRepository;
import com.project.zeroyamoyo.domain.user.repository.UserRepository;
import com.project.zeroyamoyo.global.exception.GlobalException;
import com.project.zeroyamoyo.global.exception.ResultType;
import com.project.zeroyamoyo.global.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserInterestRepository userInterestRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void join(UserJoin.Request request) {
        validateUserJoinRequest(request);
        createUser(request);
    }

    private void createUser(UserJoin.Request request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .gender(request.getGender())
                .regionCode(request.getRegionCode())
                .description(request.getDescription())
                .registeredAt(LocalDateTime.now())
                .build();
        User saved = userRepository.save(user);

        List<UserInterest> userInterestList = request.getInterests().stream()
                .map(v -> UserInterest.builder()
                        .user(saved)
                        .interestId(v.getId().longValue())
                        .interestCategory(String.join(",", v.getCategory()))
                        .build()
                ).collect(Collectors.toList());
        userInterestRepository.saveAll(userInterestList);
    }

    private void validateUserJoinRequest(UserJoin.Request request) {
        if (!ValidationUtil.isValidEmail(request.getEmail())) {
            throw new GlobalException(ResultType.EMAIL_NOT_VALID);
        }
        if (!ValidationUtil.isValidPassword(request.getPassword())) {
            throw new GlobalException(ResultType.PASSWORD_NOT_VALID);
        }
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new GlobalException(ResultType.USER_ALREADY_EXISTS);
        }
    }
}
