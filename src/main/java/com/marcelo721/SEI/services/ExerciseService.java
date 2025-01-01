package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.Exercise;
import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.repositories.ExerciseRepository;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.ExerciseDto.ExerciseCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final TopicService topicService;

    @Transactional
    public Exercise save(ExerciseCreateDto exercise) {
        Topic topic = topicService.findById(exercise.topicId());
        Exercise saved = new Exercise();

        saved.setUrl(exercise.url());
        saved.setTopic(topic);

        return exerciseRepository.save(saved);
    }

    @Transactional(readOnly = true)
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Transactional
    public Exercise findById(Long id) {
        return exerciseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
    }
}

