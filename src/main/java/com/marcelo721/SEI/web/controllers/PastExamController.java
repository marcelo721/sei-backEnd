package com.marcelo721.SEI.web.controllers;


import com.marcelo721.SEI.entities.PastExam;
import com.marcelo721.SEI.services.PastExamService;
import com.marcelo721.SEI.web.dto.PastExam.PastExamCreateDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pastExams")
@RequiredArgsConstructor
@Tag(name = "pastExams", description = "Endpoints to manage past exam records")
public class PastExamController {

    private final PastExamService pastExamService;

    @Operation(
            summary = "Create a new past exam record",
            description = "Endpoint to create a new past exam record in the system, with all necessary validations.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Past exam created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PastExam.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict: past exam already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    public ResponseEntity<PastExam> createPastExam(@RequestBody @Valid PastExamCreateDto pastExam) {
        PastExam response = pastExamService.save(pastExam);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Retrieve all past exams",
            description = "Endpoint to retrieve a list of all past exam records available in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of past exams",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = PastExam.class)))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<PastExam>> getAllPastExams() {
        List<PastExam> response = pastExamService.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Retrieve a past exam by ID",
            description = "Endpoint to retrieve a specific past exam record by its unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the past exam",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PastExam.class))),
                    @ApiResponse(responseCode = "404", description = "Past exam not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PastExam> getPastExamById(@PathVariable Long id) {
        PastExam response = pastExamService.findById(id);
        return ResponseEntity.ok(response);
    }
}
