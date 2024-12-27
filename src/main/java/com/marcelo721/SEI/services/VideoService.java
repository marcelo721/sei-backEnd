package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.entities.Video;
import com.marcelo721.SEI.repositories.TopicRepository;
import com.marcelo721.SEI.repositories.VideoRepository;
import com.marcelo721.SEI.web.dto.VideoDto.VideoCreateDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoService {

    private final VideoRepository videoRepository;
    private final TopicRepository topicRepository;

    @Transactional
    public Video save(VideoCreateDto video) {

        Topic topic = topicRepository.findById(video.topicId()).
                orElseThrow(() -> new EntityNotFoundException("topic not found"));

        Video videoEntity = new Video();
        videoEntity.setUrl(video.url());
        videoEntity.setTopic(topic);

        return videoRepository.save(videoEntity);
    }

    @Transactional(readOnly = true)
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Video findById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Video not Found"));
    }
}
