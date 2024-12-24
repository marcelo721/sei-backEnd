package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.services.TopicService;
import com.marcelo721.SEI.web.dto.TopicDto.TopicCreateDto;
import com.marcelo721.SEI.web.dto.TopicDto.TopicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/topics")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponseDto> save(@RequestBody TopicCreateDto topic) {
        Topic savedTopic = topicService.save(topic);

        return ResponseEntity.status(HttpStatus.CREATED).body(TopicResponseDto.toDto(savedTopic));
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDto>> findAll() {
        List<Topic> topics = topicService.findAll();
        return ResponseEntity.ok(TopicResponseDto.toListDto(topics));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDto> findById(@PathVariable Long id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok(TopicResponseDto.toDto(topic));
    }
}
