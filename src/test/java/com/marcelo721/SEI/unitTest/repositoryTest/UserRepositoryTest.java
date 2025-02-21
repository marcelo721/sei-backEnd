package com.marcelo721.SEI.unitTest.repositoryTest;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.entities.enums.Role;
import com.marcelo721.SEI.entities.enums.StatusAccount;
import com.marcelo721.SEI.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Marcelo");
        user.setEmail("marcelo@example.com");
        user.setPassword("securepassword");
        user.setVerificationCode("123456");
        user.setStatusAccount(StatusAccount.DISABLED);
        user.setRole(Role.STUDENT);

        userRepository.save(user);
    }

    @Test
    void testFindByEmail_ShouldReturnUser() {
        Optional<User> foundUser = userRepository.findByEmail("marcelo@example.com");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void testFindByEmail_ShouldReturnEmpty() {
        Optional<User> foundUser = userRepository.findByEmail("notfound@example.com");

        assertThat(foundUser).isEmpty();
    }

    @Test
    void testFindByVerificationCode_ShouldReturnUser() {
        User foundUser = userRepository.findByVerificationCode("123456");

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getVerificationCode()).isEqualTo(user.getVerificationCode());
    }

    @Test
    void testFindByVerificationCode_ShouldReturnNull() {
        User foundUser = userRepository.findByVerificationCode("invalid_code");

        assertThat(foundUser).isNull();
    }

    @Test
    void testSaveUser_ShouldPersistUser() {
        User newUser = new User();
        newUser.setName("Test User");
        newUser.setEmail("test@example.com");
        newUser.setPassword("password123");
        newUser.setVerificationCode("654321");
        newUser.setStatusAccount(StatusAccount.DISABLED);
        newUser.setRole(Role.STUDENT);

        User savedUser = userRepository.save(newUser);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void testSaveUser_DuplicateEmail_ShouldThrowException() {
        User duplicateUser = new User();
        duplicateUser.setName("Marcelo 2");
        duplicateUser.setEmail("marcelo@example.com");
        duplicateUser.setPassword("anotherpassword");
        duplicateUser.setVerificationCode("789101");
        duplicateUser.setStatusAccount(StatusAccount.DISABLED);
        duplicateUser.setRole(Role.STUDENT);

        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(duplicateUser));
    }

    @Test
    void testFindById_ShouldReturnUser() {
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertThat(foundUser).isPresent();
    }

    @Test
    void testFindById_ShouldReturnEmpty() {
        Optional<User> foundUser = userRepository.findById(999L);
        assertThat(foundUser).isEmpty();
    }
}