package com.marcelo721.SEI.services;


import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

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

    @Transactional(readOnly = true)
    public List<Subject> getSubjectsByUserId(Long idUser) {
        return subjectRepository.findAllSubjectsByUserId(idUser);
    }

    @Transactional(readOnly = true)
    public List<Subject> getSubjectsBySemesterNumber(int semesterNumber) {
        Semester semester = Semester.fromValue(semesterNumber);
        return subjectRepository.findSubjectsBySemester(semester);
    }
}
