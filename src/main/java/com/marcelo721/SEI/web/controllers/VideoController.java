package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Video;
import com.marcelo721.SEI.services.VideoService;
import com.marcelo721.SEI.web.dto.VideoDto.VideoCreateDto;
import com.marcelo721.SEI.web.dto.VideoDto.VideoResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/videos")
@Slf4j
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<VideoResponseDto> save(@RequestBody @Valid VideoCreateDto videoCreateDto){

        Video saved = videoService.save(videoCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(VideoResponseDto.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoResponseDto> getById(@PathVariable Long id){
        Video video = videoService.findById(id);
        return ResponseEntity.ok(VideoResponseDto.toDto(video));
    }

    @GetMapping
    public ResponseEntity<List<VideoResponseDto>> getAll(){
        List<Video> all = videoService.findAll();

        return ResponseEntity.ok(VideoResponseDto.toDto(all));
    }
}
