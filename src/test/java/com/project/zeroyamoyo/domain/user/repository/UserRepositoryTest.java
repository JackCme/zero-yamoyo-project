package com.project.zeroyamoyo.domain.user.repository;

import com.project.zeroyamoyo.domain.user.entity.Gender;
import com.project.zeroyamoyo.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.yml")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUser() {
        // Given
        User user = User.builder()
                .id(1L).email("jack@jack.com")
                .password("PassW@rd!1")
                .nickname("jack")
                .regionCode(1)
                .gender(Gender.MALE)
                .description("this is a description")
                .registeredAt(LocalDateTime.now())
                .build();
        // When
        User saved = userRepository.save(user);
        // Then
        assertThat(saved).isEqualTo(user);
    }
}