package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.services.SubjectService;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.web.dto.SubjectDto.SubjectCreateDto;
import com.marcelo721.SEI.web.dto.SubjectDto.SubjectResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<SubjectResponseDto> save(@RequestBody SubjectCreateDto subject) {
        Subject obj = subjectService.save(subject.toSubject());
        return ResponseEntity.status(HttpStatus.CREATED).body(SubjectResponseDto.toDto(obj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectResponseDto> getSubject(@PathVariable Long id) {
        Subject obj = subjectService.findById(id);
        return ResponseEntity.ok(SubjectResponseDto.toDto(obj));
    }

    @GetMapping()
    public ResponseEntity<List<SubjectResponseDto>> getAll() {
        List<Subject> subjects = subjectService.findAll();
        return ResponseEntity.ok(SubjectResponseDto.toListDto(subjects));
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> findAllSubjectsByUserId(@PathVariable Long id) {
        List<Subject> subjects = subjectService.getSubjectsByUserId(id);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/by-semester/{semesterNumber}")
    public ResponseEntity<List<SubjectResponseDto>> getSubjectsBySemester(@PathVariable int semesterNumber) {
        try {
            List<Subject> subjects = subjectService.getSubjectsBySemesterNumber(semesterNumber);
            return ResponseEntity.ok(SubjectResponseDto.toListDto(subjects));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
