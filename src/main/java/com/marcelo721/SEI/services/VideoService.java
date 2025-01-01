package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.entities.Video;
import com.marcelo721.SEI.repositories.TopicRepository;
import com.marcelo721.SEI.repositories.VideoRepository;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.VideoDto.VideoCreateDto;
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
    private final TopicService topicService;

    @Transactional
    public Video save(VideoCreateDto video) {

        Topic topic = topicService.findById(video.topicId());

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
