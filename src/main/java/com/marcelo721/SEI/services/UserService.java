package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.repositories.SubjectRepository;
import com.marcelo721.SEI.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Transactional()
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User Not Found"));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User addSubject(Long userId, Long subjectId) {
        User user = userRepository.findById(userId).get();
        Subject subject = subjectRepository.findById(subjectId).get();
        user.getSubjects().add(subject);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Subject> getSubjects(Long idUser) {
        return subjectRepository.findAllSubjectsByUserId(idUser);
    }
}
