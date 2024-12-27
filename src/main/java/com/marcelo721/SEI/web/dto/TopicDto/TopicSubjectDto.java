package com.marcelo721.SEI.web.dto.TopicDto;


import com.marcelo721.SEI.entities.Resume;
import com.marcelo721.SEI.entities.Topic;
import com.marcelo721.SEI.entities.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record TopicSubjectDto(String name, Resume resume, Set<Video> videos) {

    public static TopicSubjectDto toDto(Topic topic){
        return new TopicSubjectDto(topic.getName(), topic.getResume(), topic.getVideos());
    }

    public static List<TopicSubjectDto> toListDto(List<Topic> Topics){
        List<TopicSubjectDto> dtos = new ArrayList<>();

        for(Topic topic : Topics){
            dtos.add(toDto(topic));
        }
        return dtos;
    }
}
