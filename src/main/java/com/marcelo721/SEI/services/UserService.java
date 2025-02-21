package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.entities.enums.StatusAccount;
import com.marcelo721.SEI.repositories.UserRepository;
import com.marcelo721.SEI.services.exceptions.EmailUniqueViolationException;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.services.exceptions.PasswordInvalidException;
import com.marcelo721.SEI.utils.UserUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubjectService subjectService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional()
    public User save(User user) {

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            String randomCode = UserUtils.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setStatusAccount(StatusAccount.ENABLED);
            emailService.sendVerifyEmail(user);

            return userRepository.save(user);

        }catch (DataIntegrityViolationException e){
            throw new EmailUniqueViolationException(String.format("Email {%s} already registered ", user.getEmail()));
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User Not Found"));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User addSubject(Long userId, Long subjectId) {
        User user = findById(userId);
        Subject subject = subjectService.findById(subjectId);
        user.getSubjects().add(subject);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundException("user not found"));
    }

    @Transactional
    public User updatePassword(String currentPassword, String newPassword, String confirmNewPassword, long id){

        if (!newPassword.equals(confirmNewPassword)){
            throw new PasswordInvalidException("the confirmation passwords are different");
        }

        User user = findById(id);

        if (!passwordEncoder.matches(currentPassword, user.getPassword())){
            throw  new PasswordInvalidException("The password is wrong");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return user;
    }

    public StatusAccount verify(String code) {
        User user = userRepository.findByVerificationCode(code);

        if (user == null || user.getStatusAccount().equals(StatusAccount.ENABLED)) {
            return StatusAccount.ALREADY_ENABLED;

        } else{
            user.setVerificationCode(null);
            user.setStatusAccount(StatusAccount.ENABLED);
            userRepository.save(user);
            return StatusAccount.ENABLED ;
        }
    }
}
