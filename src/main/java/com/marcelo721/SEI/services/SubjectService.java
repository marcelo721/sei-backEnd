package com.marcelo721.SEI.services;


import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.repositories.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Transactional()
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }
}
