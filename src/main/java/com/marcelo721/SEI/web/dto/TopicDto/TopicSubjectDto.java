package com.marcelo721.SEI.web.dto.TopicDto;


import com.marcelo721.SEI.entities.Resume;
import com.marcelo721.SEI.entities.Topic;

import java.util.ArrayList;
import java.util.List;

public record TopicSubjectDto(String name, Resume resume) {

    public static TopicSubjectDto toDto(Topic topic){
        return new TopicSubjectDto(topic.getName(), topic.getResume());
    }

    public static List<TopicSubjectDto> toListDto(List<Topic> Topics){
        List<TopicSubjectDto> dtos = new ArrayList<>();

        for(Topic topic : Topics){
            dtos.add(toDto(topic));
        }
        return dtos;
    }
}
