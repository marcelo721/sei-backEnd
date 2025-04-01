package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Video;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.services.VideoService;
import com.marcelo721.SEI.web.dto.VideoDto.VideoCreateDto;
import com.marcelo721.SEI.web.dto.VideoDto.VideoResponseDto;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/videos")
@Slf4j
@Tag(name = "videos", description = "Endpoints to manage educational videos")
public class VideoController {

    private final VideoService videoService;
    private final UserService userService;

    @Operation(
            summary = "Create a new video resource",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to create a new video in the system. The video details must be provided in the request body and comply with validation rules.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Video created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VideoResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict: video already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")//tested
    public ResponseEntity<Void> save(@RequestBody @Valid VideoCreateDto videoCreateDto){
        videoService.save(videoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Retrieve a video by its ID",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve the details of a specific video using its unique identifier.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Video retrieved successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VideoResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Video not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")//tested
    public ResponseEntity<VideoResponseDto> getById(@PathVariable Long id){
        Video video = videoService.findById(id);
        return ResponseEntity.ok(VideoResponseDto.toDto(video));
    }

    @Operation(
            summary = "Retrieve all videos",
            security = @SecurityRequirement(name = "security"),
            description = "Endpoint to retrieve a list of all videos available in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of videos",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = VideoResponseDto.class)))),
                    @ApiResponse(responseCode = "403", description = "Access denied",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized access",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")//tested
    public ResponseEntity<List<VideoResponseDto>> getAll(){
        List<Video> all = videoService.findAll();
        return ResponseEntity.ok(VideoResponseDto.toDto(all));
    }

    @GetMapping("/by-topic/{idTopic}")
    public ResponseEntity<List<VideoResponseDto>> getByTopic(@PathVariable  Long idTopic){
        List<VideoResponseDto> listVideos = VideoResponseDto.toDto(videoService.findByTopicId(idTopic));
        return ResponseEntity.ok().body(listVideos);
    }

    @DeleteMapping("/{id}/topic-id/{idTopic}")
    public ResponseEntity<Void> deleteVideoById(@PathVariable Long id, @PathVariable Long idTopic){
        log.info("deleting video with id {}", id);
        videoService.delete(id, idTopic);
        return ResponseEntity.noContent().build();
    }
}
