package com.marcelo721.SEI.unitTest.serviceTest;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.repositories.TopicRepository;
import com.marcelo721.SEI.services.SubjectService;
import com.marcelo721.SEI.services.TopicService;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.TopicDto.TopicCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private TopicService topicService;

    private Topic topic;
    private Subject subject;
    private TopicCreateDto topicCreateDto;

    @BeforeEach
    void setUp() {
        subject = new Subject();
        subject.setId(1L);

        topic = new Topic();
        topic.setId(1L);
        topic.setName("Math");
        topic.setDescription("Algebra and Geometry");
        topic.setSubject(subject);

        topicCreateDto = new TopicCreateDto("Math", "Algebra and Geometry", 1L);
    }

    @Test
    void save_ShouldSaveTopicSuccessfully() {
        when(subjectService.findById(1L)).thenReturn(subject);
        when(topicRepository.save(any(Topic.class))).thenReturn(topic);

        Topic savedTopic = topicService.save(topicCreateDto);

        assertNotNull(savedTopic);
        assertEquals("Math", savedTopic.getName());
        assertEquals("Algebra and Geometry", savedTopic.getDescription());
        assertEquals(1L, savedTopic.getSubject().getId());

        verify(subjectService, times(1)).findById(1L);
        verify(topicRepository, times(1)).save(any(Topic.class));
    }

    @Test
    void save_ShouldThrowException_WhenSubjectNotFound() {
        when(subjectService.findById(1L)).thenThrow(new EntityNotFoundException("Subject Not Found"));

        assertThrows(EntityNotFoundException.class, () -> topicService.save(topicCreateDto));

        verify(subjectService, times(1)).findById(1L);
        verify(topicRepository, never()).save(any(Topic.class));
    }

    @Test
    void findAll_ShouldReturnAllTopics() {
        when(topicRepository.findAll()).thenReturn(List.of(topic));

        List<Topic> topics = topicService.findAll();

        assertFalse(topics.isEmpty());
        assertEquals(1, topics.size());
        assertEquals(topic, topics.get(0));

        verify(topicRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnTopic_WhenTopicExists() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        Topic foundTopic = topicService.findById(1L);

        assertNotNull(foundTopic);
        assertEquals(1L, foundTopic.getId());

        verify(topicRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenTopicNotFound() {
        when(topicRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> topicService.findById(1L));

        verify(topicRepository, times(1)).findById(1L);
    }

    @Test
    void getTopicsBySubject_ShouldReturnTopics_WhenSubjectExists() {
        when(subjectService.findById(1L)).thenReturn(subject);
        when(topicRepository.findTopicsBySubject(1L)).thenReturn(List.of(topic));

        List<Topic> topics = topicService.getTopicsBySubject(1L);

        assertFalse(topics.isEmpty());
        assertEquals(1, topics.size());
        assertEquals(topic, topics.get(0));

        verify(subjectService, times(1)).findById(1L);
        verify(topicRepository, times(1)).findTopicsBySubject(1L);
    }

    @Test
    void getTopicsBySubject_ShouldThrowException_WhenSubjectNotFound() {
        when(subjectService.findById(1L)).thenThrow(new EntityNotFoundException("Subject Not Found"));

        assertThrows(EntityNotFoundException.class, () -> topicService.getTopicsBySubject(1L));

        verify(subjectService, times(1)).findById(1L);
        verify(topicRepository, never()).findTopicsBySubject(anyLong());
    }
}

