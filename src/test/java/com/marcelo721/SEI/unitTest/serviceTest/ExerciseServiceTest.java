package com.marcelo721.SEI.unitTest.serviceTest;


import com.marcelo721.SEI.entities.Exercise;
import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.repositories.ExerciseRepository;
import com.marcelo721.SEI.services.ExerciseService;
import com.marcelo721.SEI.services.TopicService;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.ExerciseDto.ExerciseCreateDto;
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
public class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private TopicService topicService;

    @InjectMocks
    private ExerciseService exerciseService;

    private ExerciseCreateDto exerciseCreateDto;
    private Topic topic;
    private Exercise exercise;

    @BeforeEach
    void setUp() {
        topic = new Topic();
        topic.setId(1L);
        topic.setName("Mathematics");

        exerciseCreateDto = new ExerciseCreateDto("http://example.com/exercise.pdf", 1L);

        exercise = new Exercise();
        exercise.setId(1L);
        exercise.setUrl("http://example.com/exercise.pdf");
        exercise.setTopic(topic);
    }

    @Test
    void save_ShouldReturnSavedExercise() {
        when(topicService.findById(1L)).thenReturn(topic);
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(exercise);

        Exercise savedExercise = exerciseService.save(exerciseCreateDto);

        assertNotNull(savedExercise);
        assertEquals("http://example.com/exercise.pdf", savedExercise.getUrl());
        assertEquals(topic, savedExercise.getTopic());

        verify(topicService, times(1)).findById(1L);
        verify(exerciseRepository, times(1)).save(any(Exercise.class));
    }

    @Test
    void findAll_ShouldReturnListOfExercises() {
        when(exerciseRepository.findAll()).thenReturn(Collections.singletonList(exercise));

        List<Exercise> exercises = exerciseService.findAll();

        assertNotNull(exercises);
        assertEquals(1, exercises.size());
        assertEquals(exercise, exercises.get(0));

        verify(exerciseRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnExercise() {
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        Exercise foundExercise = exerciseService.findById(1L);

        assertNotNull(foundExercise);
        assertEquals(exercise, foundExercise);

        verify(exerciseRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowEntityNotFoundException() {
        when(exerciseRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            exerciseService.findById(1L);
        });

        assertEquals("not found", exception.getMessage());

        verify(exerciseRepository, times(1)).findById(1L);
    }
}
