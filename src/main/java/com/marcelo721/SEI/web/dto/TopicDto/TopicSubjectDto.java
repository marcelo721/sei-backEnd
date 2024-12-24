package com.marcelo721.SEI.web.dto.TopicDto;


import com.marcelo721.SEI.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public record TopicSubjectDto(String name) {

    public static TopicSubjectDto toDto(Topic topic){
        return new TopicSubjectDto(topic.getName());
    }

    public static List<TopicSubjectDto> toListDto(List<Topic> Topics){
        List<TopicSubjectDto> dtos = new ArrayList<>();

        for(Topic topic : Topics){
            dtos.add(toDto(topic));
        }
        return dtos;
    }
}
