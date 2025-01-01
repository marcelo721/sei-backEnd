package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.repositories.SubjectRepository;
import com.marcelo721.SEI.repositories.UserRepository;
import com.marcelo721.SEI.services.exceptions.EmailUniqueViolationException;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubjectService subjectService;

    @Transactional()
    public User save(User user) {

        try {
            return userRepository.save(user);

        }catch (DataIntegrityViolationException e){
            throw new EmailUniqueViolationException(String.format("Email {%s} already registered ", user.getEmail()));
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

}
