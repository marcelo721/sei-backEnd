package com.marcelo721.SEI.unitTest.serviceTest;

import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.entities.Video;
import com.marcelo721.SEI.repositories.VideoRepository;
import com.marcelo721.SEI.services.TopicService;
import com.marcelo721.SEI.services.VideoService;
import com.marcelo721.SEI.services.exceptions.EntityNotFoundException;
import com.marcelo721.SEI.web.dto.VideoDto.VideoCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private TopicService topicService;

    @InjectMocks
    private VideoService videoService;

    private Video video;
    private Topic topic;
    private VideoCreateDto videoCreateDto;

    @BeforeEach
    void setUp() {
        topic = new Topic();
        topic.setId(1L);

        video = new Video();
        video.setId(1L);
        video.setUrl("http://example.com");
        video.setTopic(topic);

        videoCreateDto = new VideoCreateDto("http://example.com", 1L);
    }

    @Test
    void save_ShouldSaveVideoSuccessfully() {
        when(topicService.findById(1L)).thenReturn(topic);
        when(videoRepository.save(any(Video.class))).thenReturn(video);

        Video savedVideo = videoService.save(videoCreateDto);

        assertNotNull(savedVideo);
        assertEquals("http://example.com", savedVideo.getUrl());
        assertEquals(1L, savedVideo.getTopic().getId());

        verify(topicService, times(1)).findById(1L);
        verify(videoRepository, times(1)).save(any(Video.class));
    }

    @Test
    void save_ShouldThrowException_WhenTopicNotFound() {
        when(topicService.findById(1L)).thenThrow(new EntityNotFoundException("Topic not found"));

        assertThrows(EntityNotFoundException.class, () -> videoService.save(videoCreateDto));

        verify(topicService, times(1)).findById(1L);
        verify(videoRepository, never()).save(any(Video.class));
    }

    @Test
    void findAll_ShouldReturnAllVideos() {
        when(videoRepository.findAll()).thenReturn(List.of(video));

        List<Video> videos = videoService.findAll();

        assertFalse(videos.isEmpty());
        assertEquals(1, videos.size());
        assertEquals(video, videos.get(0));

        verify(videoRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnVideo_WhenVideoExists() {
        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));

        Video foundVideo = videoService.findById(1L);

        assertNotNull(foundVideo);
        assertEquals(1L, foundVideo.getId());

        verify(videoRepository, times(1)).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenVideoNotFound() {
        when(videoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> videoService.findById(1L));

        verify(videoRepository, times(1)).findById(1L);
    }
}

