package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Resume;
import com.marcelo721.SEI.services.ResumeService;
import com.marcelo721.SEI.web.dto.resumeDto.ResumeCreateDto;
import com.marcelo721.SEI.web.dto.resumeDto.ResumeResponseDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/resumes")
@RequiredArgsConstructor
@Tag(name = "resumes", description = "Endpoints to manage resumes in the system")
public class ResumeController {

    private final ResumeService resumeService;

    @Operation(
            summary = "Create a new resume",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to create a new resume. The input data must be valid and adhere to the system's requirements.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Resume created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResumeResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict: resume already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResumeResponseDto> save(@RequestBody @Valid ResumeCreateDto resume) {
        Resume saved = resumeService.save(resume);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResumeResponseDto.toDto(saved));
    }

    @Operation(
            summary = "Retrieve all resumes",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve a list of all resumes stored in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of resumes",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResumeResponseDto.class)))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ResumeResponseDto>> findAll() {
        List<Resume> resumes = resumeService.findAll();

        return ResponseEntity.ok(ResumeResponseDto.toListDto(resumes));
    }

    @Operation(
            summary = "Retrieve a resume by ID",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve a specific resume by its unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the resume",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResumeResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Resume not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResumeResponseDto> findById(@PathVariable Long id) {
        Resume resume = resumeService.findById(id);
        return ResponseEntity.ok(ResumeResponseDto.toDto(resume));
    }
}

