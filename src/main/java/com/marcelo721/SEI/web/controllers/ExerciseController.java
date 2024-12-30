package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Exercise;
import com.marcelo721.SEI.services.ExerciseService;
import com.marcelo721.SEI.web.dto.ExerciseDto.ExerciseCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<Exercise> save(@RequestBody ExerciseCreateDto exercise) {
        Exercise saved = exerciseService.save(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> findAll() {
        List<Exercise> all = exerciseService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> findById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        return ResponseEntity.ok(exercise);
    }
}

