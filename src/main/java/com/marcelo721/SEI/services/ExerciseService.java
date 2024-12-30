package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.Exercise;
import com.marcelo721.SEI.repositories.ExerciseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Transactional
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
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

