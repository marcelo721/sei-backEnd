package com.marcelo721.SEI.unitTest.serviceTest;

import com.marcelo721.SEI.entities.PastExam;
import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.repositories.PastExamRepository;
import com.marcelo721.SEI.services.PastExamService;
import com.marcelo721.SEI.services.SubjectService;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.PastExam.PastExamCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PastExamServiceTest {

    @Mock
    private PastExamRepository pastExamRepository;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private PastExamService pastExamService;

    private PastExamCreateDto pastExamCreateDto;
    private Subject subject;
    private PastExam pastExam;

    @BeforeEach
    void setUp() {
        subject = new Subject();
        subject.setId(1L);
        subject.setName("Mathematics");

        pastExamCreateDto = new PastExamCreateDto("Math Exam 2023", "http://example.com/exam.pdf", 1L);

        pastExam = new PastExam();
        pastExam.setId(1L);
        pastExam.setTitle("Math Exam 2023");
        pastExam.setUrl("http://example.com/exam.pdf");
        pastExam.setSubject(subject);
    }

    @Test
    void save_ShouldReturnSavedPastExam() {
        when(subjectService.findById(1L)).thenReturn(subject);
        when(pastExamRepository.save(any(PastExam.class))).thenReturn(pastExam);

        PastExam savedPastExam = pastExamService.save(pastExamCreateDto);

        assertNotNull(savedPastExam);
        assertEquals("Math Exam 2023", savedPastExam.getTitle());
        assertEquals("http://example.com/exam.pdf", savedPastExam.getUrl());
        assertEquals(subject, savedPastExam.getSubject());

        verify(subjectService, times(1)).findById(1L);
        verify(pastExamRepository, times(1)).save(any(PastExam.class));
    }

    @Test
    void findById_ShouldReturnPastExam() {
        when(pastExamRepository.findById(1L)).thenReturn(Optional.of(pastExam));

        PastExam foundPastExam = pastExamService.findById(1L);

        assertNotNull(foundPastExam);
        assertEquals(pastExam, foundPastExam);

        verify(pastExamRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowEntityNotFoundException() {
        when(pastExamRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            pastExamService.findById(1L);
        });

        assertEquals("PastExam not found", exception.getMessage());

        verify(pastExamRepository, times(1)).findById(1L);
    }

    @Test
    void findAll_ShouldReturnListOfPastExams() {
        when(pastExamRepository.findAll()).thenReturn(Collections.singletonList(pastExam));

        List<PastExam> pastExams = pastExamService.findAll();

        assertNotNull(pastExams);
        assertEquals(1, pastExams.size());
        assertEquals(pastExam, pastExams.get(0));

        verify(pastExamRepository, times(1)).findAll();
    }
}
