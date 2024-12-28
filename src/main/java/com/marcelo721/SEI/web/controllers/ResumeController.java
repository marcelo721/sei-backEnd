package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Resume;
import com.marcelo721.SEI.services.ResumeService;
import com.marcelo721.SEI.web.dto.resumeDto.ResumeCreateDto;
import com.marcelo721.SEI.web.dto.resumeDto.ResumeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResponseEntity<ResumeResponseDto> save(@RequestBody ResumeCreateDto resume) {
        Resume saved = resumeService.save(resume);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResumeResponseDto.toDto(saved));
    }

    @GetMapping
    public ResponseEntity<List<ResumeResponseDto>> findAll() {
        List<Resume> resumes = resumeService.findAll();

        return ResponseEntity.ok(ResumeResponseDto.toListDto(resumes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeResponseDto> findById(@PathVariable Long id) {
        Resume resume = resumeService.findById(id);
        return ResponseEntity.ok(ResumeResponseDto.toDto(resume));
    }
}

