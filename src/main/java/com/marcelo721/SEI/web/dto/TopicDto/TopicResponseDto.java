package com.marcelo721.SEI.web.dto.TopicDto;

import com.marcelo721.SEI.entities.Resume;
import com.marcelo721.SEI.entities.Topic;


import java.util.ArrayList;
import java.util.List;

public record TopicResponseDto(
        String name ,Long id, String subject, Resume resume
) {

    public static TopicResponseDto toDto(Topic topic){
        return new TopicResponseDto(topic.getName(),topic.getId(), topic.getSubject().getName(), topic.getResume());
    }

    public static List<TopicResponseDto> toListDto(List<Topic> Topics){
        List<TopicResponseDto> dtos = new ArrayList<>();

        for(Topic topic : Topics){
            dtos.add(toDto(topic));
        }
        return dtos;
    }
}
