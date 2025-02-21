package com.marcelo721.SEI.unitTest.serviceTest;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.entities.enums.StatusAccount;
import com.marcelo721.SEI.repositories.UserRepository;
import com.marcelo721.SEI.services.EmailService;
import com.marcelo721.SEI.services.SubjectService;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.services.exceptions.EmailUniqueViolationException;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.services.exceptions.PasswordInvalidException;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SubjectService subjectService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setStatusAccount(StatusAccount.DISABLED);
    }

    @Test
    void testSaveUser_Success() throws MessagingException, UnsupportedEncodingException {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        doNothing().when(emailService).sendVerifyEmail(any(User.class));

        User savedUser = userService.save(user);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSaveUser_EmailAlreadyExists() {
        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(EmailUniqueViolationException.class, () -> userService.save(user));
    }

    @Test
    void testFindById_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(1L);

        assertNotNull(foundUser);
        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    void testFindById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findById(1L));
    }

    @Test
    void testFindAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.findAll();

        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
    }

    @Test
    void testAddSubject() {
        Subject subject = new Subject();
        subject.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(subjectService.findById(1L)).thenReturn(subject);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.addSubject(1L, 1L);

        assertFalse(updatedUser.getSubjects().isEmpty());
        assertTrue(updatedUser.getSubjects().contains(subject));
    }

    @Test
    void testUpdatePassword_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");

        User updatedUser = userService.updatePassword("password", "newPassword", "newPassword", 1L);

        assertNotNull(updatedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdatePassword_PasswordMismatch() {
        assertThrows(PasswordInvalidException.class, () ->
                userService.updatePassword("password", "newPass", "wrongConfirm", 1L));
    }

    @Test
    void testVerifyAccount_ValidCode() {
        when(userRepository.findByVerificationCode("validCode")).thenReturn(user);

        StatusAccount status = userService.verify("validCode");

        assertEquals(StatusAccount.ENABLED, status);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testVerifyAccount_InvalidCode() {
        when(userRepository.findByVerificationCode("invalidCode")).thenReturn(null);

        StatusAccount status = userService.verify("invalidCode");

        assertEquals(StatusAccount.ALREADY_ENABLED, status);
    }
}
