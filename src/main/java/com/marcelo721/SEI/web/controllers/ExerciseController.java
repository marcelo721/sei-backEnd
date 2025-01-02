package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Exercise;
import com.marcelo721.SEI.services.ExerciseService;
import com.marcelo721.SEI.web.dto.ExerciseDto.ExerciseCreateDto;
import com.marcelo721.SEI.web.dto.UserDto.UserResponseDto;
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
@RequestMapping("api/v1/exercises")
@RequiredArgsConstructor
@Tag(name = "exercises", description = "contains all exercise resources")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Operation(
            summary = "create a new exercise", description = "resource to create a exercise",
            responses = {
                    @ApiResponse(responseCode = "201", description = "resource created successfully",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "409", description = "exercise is already registered",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "422", description = "Invalid Data",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = ErrorMessage.class)))

            }
    )
    @PostMapping
    public ResponseEntity<Exercise> save(@RequestBody @Valid ExerciseCreateDto exercise) {
        Exercise saved = exerciseService.save(exercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(
            summary = "Find all exercises", description = "Resource to find all exercises",
            responses = {
                    @ApiResponse(responseCode = "200",description = "List of all exercises",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<Exercise>> findAll() {
        List<Exercise> all = exerciseService.findAll();
        return ResponseEntity.ok(all);
    }


    @Operation(
            summary = "find exercise by id", description = "resource to find exercise by id ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "exercise Found Successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "404", description = "exercise not found !",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> findById(@PathVariable Long id) {
        Exercise exercise = exerciseService.findById(id);
        return ResponseEntity.ok(exercise);
    }
}

