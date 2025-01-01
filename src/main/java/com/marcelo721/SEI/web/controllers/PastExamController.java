package com.marcelo721.SEI.web.controllers;


import com.marcelo721.SEI.entities.PastExam;
import com.marcelo721.SEI.services.PastExamService;
import com.marcelo721.SEI.web.dto.PastExam.PastExamCreateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pastExams")
@RequiredArgsConstructor
public class PastExamController {

    private final PastExamService pastExamService;

    @PostMapping
    public ResponseEntity<PastExam> createPastExam(@RequestBody @Valid PastExamCreateDto pastExam) {
        PastExam response = pastExamService.save(pastExam);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PastExam>> getAllPastExams() {
        List<PastExam> response = pastExamService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PastExam> getPastExamById(@PathVariable Long id) {
        PastExam response = pastExamService.findById(id);
        return ResponseEntity.ok(response);
    }
}
