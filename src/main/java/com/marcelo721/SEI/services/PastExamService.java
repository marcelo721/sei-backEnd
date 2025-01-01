package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.PastExam;
import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.repositories.PastExamRepository;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.PastExam.PastExamCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PastExamService {

    private final PastExamRepository pastExamRepository;
    private final SubjectService subjectService;

    @Transactional
    public PastExam save(PastExamCreateDto pastExam) {
        Subject subject = subjectService.findById(pastExam.subjectId());
        PastExam pastExamEntity = new PastExam();
        pastExamEntity.setSubject(subject);
        pastExamEntity.setUrl(pastExam.url());
        pastExamEntity.setTitle(pastExam.title());

        return pastExamRepository.save(pastExamEntity);
    }

    @Transactional(readOnly = true)
    public PastExam findById(Long id) {
        return pastExamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PastExam not found"));
    }

    @Transactional(readOnly = true)
    public List<PastExam> findAll() {
        return pastExamRepository.findAll();
    }
}
