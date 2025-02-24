package com.marcelo721.SEI.unitTest.serviceTest;


import com.marcelo721.SEI.entities.Resume;
import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.repositories.ResumeRepository;
import com.marcelo721.SEI.services.ResumeService;
import com.marcelo721.SEI.services.TopicService;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.resumeDto.ResumeCreateDto;
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
public class ResumeServiceTest {

    @Mock
    private ResumeRepository resumeRepository;

    @Mock
    private TopicService topicService;

    @InjectMocks
    private ResumeService resumeService;

    private ResumeCreateDto resumeCreateDto;
    private Topic topic;
    private Resume resume;

    @BeforeEach
    void setUp() {
        topic = new Topic();
        topic.setId(1L);
        topic.setName("Java");

        resumeCreateDto = new ResumeCreateDto("Java Basics", "Introduction to Java", 1L);

        resume = new Resume();
        resume.setId(1L);
        resume.setTitle("Java Basics");
        resume.setText("Introduction to Java");
        resume.setTopic(topic);
    }

    @Test
    void save_ShouldReturnSavedResume() {
        when(topicService.findById(1L)).thenReturn(topic);
        when(resumeRepository.save(any(Resume.class))).thenReturn(resume);

        Resume savedResume = resumeService.save(resumeCreateDto);

        assertNotNull(savedResume);
        assertEquals("Java Basics", savedResume.getTitle());
        assertEquals("Introduction to Java", savedResume.getText());
        assertEquals(topic, savedResume.getTopic());

        verify(topicService, times(1)).findById(1L);
        verify(resumeRepository, times(1)).save(any(Resume.class));
    }

    @Test
    void findAll_ShouldReturnListOfResumes() {
        when(resumeRepository.findAll()).thenReturn(Collections.singletonList(resume));

        List<Resume> resumes = resumeService.findAll();

        assertNotNull(resumes);
        assertEquals(1, resumes.size());
        assertEquals(resume, resumes.get(0));

        verify(resumeRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnResume() {
        when(resumeRepository.findById(1L)).thenReturn(Optional.of(resume));

        Resume foundResume = resumeService.findById(1L);

        assertNotNull(foundResume);
        assertEquals(resume, foundResume);

        verify(resumeRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowEntityNotFoundException() {
        when(resumeRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            resumeService.findById(1L);
        });

        assertEquals("Resume not found", exception.getMessage());

        verify(resumeRepository, times(1)).findById(1L);
    }
}
