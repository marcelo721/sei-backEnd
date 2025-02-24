package com.marcelo721.SEI.unitTest.serviceTest;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.repositories.SubjectRepository;
import com.marcelo721.SEI.services.SubjectService;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.services.exceptions.SemesterInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    private Subject subject;

    @BeforeEach
    void setUp() {
        subject = new Subject();
        subject.setId(1L);
        subject.setName("Mathematics");
    }

    @Test
    void testSave() {
        when(subjectRepository.save(subject)).thenReturn(subject);
        Subject savedSubject = subjectService.save(subject);
        assertNotNull(savedSubject);
        assertEquals(subject.getId(), savedSubject.getId());
    }

    @Test
    void testFindAll() {
        when(subjectRepository.findAll()).thenReturn(Arrays.asList(subject));
        List<Subject> subjects = subjectService.findAll();
        assertFalse(subjects.isEmpty());
        assertEquals(1, subjects.size());
    }

    @Test
    void testFindByIdSuccess() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        Subject foundSubject = subjectService.findById(1L);
        assertNotNull(foundSubject);
        assertEquals(1L, foundSubject.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> subjectService.findById(1L));
    }

    @Test
    void testGetSubjectsByUserId() {
        when(subjectRepository.findAllSubjectsByUserId(1L)).thenReturn(Arrays.asList(subject));
        List<Subject> subjects = subjectService.getSubjectsByUserId(1L);
        assertFalse(subjects.isEmpty());
        assertEquals(1, subjects.size());
    }

    @Test
    void testGetSubjectsBySemesterNumberValid() {
        when(subjectRepository.findSubjectsBySemester(Semester.FIRST)).thenReturn(Arrays.asList(subject));
        List<Subject> subjects = subjectService.getSubjectsBySemesterNumber(1);
        assertFalse(subjects.isEmpty());
        assertEquals(1, subjects.size());
    }

    @Test
    void testGetSubjectsBySemesterNumberInvalid() {
        assertThrows(SemesterInvalidException.class, () -> subjectService.getSubjectsBySemesterNumber(11));
    }
}

