package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.services.TopicService;
import com.marcelo721.SEI.web.dto.TopicDto.TopicCreateDto;
import com.marcelo721.SEI.web.dto.TopicDto.TopicResponseDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/topics")
@Tag(name = "topics", description = "Endpoints to manage topics within subjects")
public class TopicController {

    private final TopicService topicService;

    @Operation(
            summary = "Create a new topic",
            description = "Endpoint to create a new topic for a specific subject. All input data must be valid and meet the system's requirements.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Topic created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict: topic already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TopicResponseDto> save(@RequestBody @Valid TopicCreateDto topic) {
        Topic savedTopic = topicService.save(topic);

        return ResponseEntity.status(HttpStatus.CREATED).body(TopicResponseDto.toDto(savedTopic));
    }

    @Operation(
            summary = "Retrieve all topics",
            description = "Endpoint to retrieve a list of all topics available in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of topics",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TopicResponseDto.class)))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TopicResponseDto>> findAll() {
        List<Topic> topics = topicService.findAll();
        return ResponseEntity.ok(TopicResponseDto.toListDto(topics));
    }

    @Operation(
            summary = "Retrieve a topic by ID",
            description = "Endpoint to retrieve details of a specific topic using its unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the topic",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TopicResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Topic not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TopicResponseDto> findById(@PathVariable Long id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok(TopicResponseDto.toDto(topic));
    }

    @Operation(
            summary = "Retrieve topics by subject ID",
            description = "Endpoint to retrieve all topics associated with a specific subject based on its unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the topics for the subject",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = TopicResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "Subject not found or no topics associated",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )

    @GetMapping("/subjectId/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TopicResponseDto>> findBySubjectId(@PathVariable Long id) {
        List<Topic> topics = topicService.getTopicsBySubject(id);
        return ResponseEntity.ok(TopicResponseDto.toListDto(topics));
    }
}
