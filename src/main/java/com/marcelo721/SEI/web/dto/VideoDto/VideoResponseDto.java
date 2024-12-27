package com.marcelo721.SEI.web.dto.VideoDto;

import com.marcelo721.SEI.entities.Video;

import java.util.ArrayList;
import java.util.List;

public record VideoResponseDto(
        String url,
        Long id,
        String TopicName
) {

    public static VideoResponseDto toDto(Video video) {
        return new VideoResponseDto(video.getUrl(), video.getId(), video.getTopic().getName());
    }

    public static List<VideoResponseDto> toDto(List<Video> videos) {
        List<VideoResponseDto> dtos = new ArrayList<>();
        for (Video video : videos){
            dtos.add(toDto(video));
        }

        return dtos;
    }
}
