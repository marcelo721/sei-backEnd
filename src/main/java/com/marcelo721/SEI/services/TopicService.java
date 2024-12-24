package com.marcelo721.SEI.services;


import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.repositories.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;

    @Transactional
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    @Transactional(readOnly = true)
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Topic findById(Long id) {
        return topicRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Topic Not Found"));
    }
}
