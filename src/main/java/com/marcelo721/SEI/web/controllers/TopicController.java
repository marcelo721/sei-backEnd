package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.services.TopicService;
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
    public ResponseEntity<Topic> save(@RequestBody Topic topic) {
        Topic savedTopic = topicService.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTopic);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> findAll() {
        List<Topic> topics = topicService.findAll();
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> findById(@PathVariable Long id) {
        Topic topic = topicService.findById(id);
        return ResponseEntity.ok(topic);
    }
}
