package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.services.SubjectService;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.web.dto.SubjectDto.SubjectCreateDto;
import com.marcelo721.SEI.web.dto.SubjectDto.SubjectResponseDto;
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
@RequestMapping("api/v1/subjects")
@RequiredArgsConstructor
@Tag(name = "subjects", description = "Endpoints to manage academic subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final UserService userService;

    @Operation(
            summary = "Create a new subject",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to create a new academic subject with all necessary validations.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Subject created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict: subject already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectResponseDto> save(@RequestBody @Valid SubjectCreateDto subject) {
        Subject obj = subjectService.save(subject.toSubject());
        return ResponseEntity.status(HttpStatus.CREATED).body(SubjectResponseDto.toDto(obj));
    }

    @Operation(
            summary = "Retrieve a subject by ID",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve details of a specific subject using its unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the subject",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SubjectResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Subject not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectResponseDto> getSubjectById(@PathVariable Long id) {
        Subject obj = subjectService.findById(id);
        return ResponseEntity.ok(SubjectResponseDto.toDto(obj));
    }

    @Operation(
            summary = "Retrieve all subjects",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve a list of all academic subjects stored in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of subjects",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SubjectResponseDto.class)))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SubjectResponseDto>> getAll() {
        List<Subject> subjects = subjectService.findAll();
        return ResponseEntity.ok(SubjectResponseDto.toListDto(subjects));
    }


    @Operation(
            summary = "Retrieve subjects by user ID",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve all subjects associated with a specific user based on their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the subjects",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Subject.class)))),
                    @ApiResponse(responseCode = "404", description = "User not found or no subjects associated",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/idUser/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #id == authentication.principal.id)")
    public ResponseEntity<List<Subject>> findAllSubjectsByUserId(@PathVariable Long id) {
        List<Subject> subjects = subjectService.getSubjectsByUserId(id);
        return ResponseEntity.ok(subjects);
    }

    @Operation(
            summary = "Retrieve subjects by semester number",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve subjects for a specific semester based on the semester number.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the subjects for the specified semester",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SubjectResponseDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Invalid semester number provided",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/by-semester/{semesterNumber}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SubjectResponseDto>> getSubjectsBySemester(@PathVariable int semesterNumber) {
        try {
            List<Subject> subjects = subjectService.getSubjectsBySemesterNumber(semesterNumber);
            return ResponseEntity.ok(SubjectResponseDto.toListDto(subjects));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
