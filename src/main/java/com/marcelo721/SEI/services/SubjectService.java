package com.marcelo721.SEI.services;


import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.repositories.SubjectRepository;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.services.exceptions.SemesterInvalidException;
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
        return subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("subject not Found"));
    }

    @Transactional(readOnly = true)
    public List<Subject> getSubjectsByUserId(Long idUser) {
        return subjectRepository.findAllSubjectsByUserId(idUser);
    }

    @Transactional(readOnly = true)
    public List<Subject> getSubjectsBySemesterNumber(int semesterNumber) {

        if (semesterNumber > 10)
            throw new SemesterInvalidException("The semester must be between 1 and 10.\n");

        Semester semester = Semester.fromValue(semesterNumber);
        return subjectRepository.findSubjectsBySemester(semester);
    }
}

