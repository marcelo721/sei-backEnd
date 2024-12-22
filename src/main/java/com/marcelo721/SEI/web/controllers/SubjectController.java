package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private  SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject obj = subjectService.save(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        Subject obj = subjectService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping()
    public ResponseEntity<List<Subject>> getAll() {
        List<Subject> subjects = subjectService.findAll();
        return ResponseEntity.ok(subjects);
    }
}
