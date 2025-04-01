package com.marcelo721.SEI.services;


import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.repositories.TopicRepository;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.TopicDto.TopicCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final SubjectService subjectService;
    @Transactional

    public Topic save(TopicCreateDto topic) {
        Subject subject = subjectService
                .findById(topic.idSubject());

        Topic topicEntity = new Topic();
        topicEntity.setName(topic.name());
        topicEntity.setDescription(topic.description());
        topicEntity.setSubject(subject);

        return topicRepository.save(topicEntity);
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

    @Transactional(readOnly = true)
    public List<Topic> getTopicsBySubject(Long idSubject) {

        Subject subject = subjectService.findById(idSubject);
        if (subject == null) throw new EntityNotFoundException("Subject Not Found");
        return topicRepository.findTopicsBySubject(idSubject);
    }
    @Transactional()
    public void updateTopic(Topic topic) {
        topicRepository.save(topic);
    }
}
