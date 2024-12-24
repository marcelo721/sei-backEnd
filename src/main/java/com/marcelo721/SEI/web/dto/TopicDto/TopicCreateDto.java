package com.marcelo721.SEI.web.dto.TopicDto;

import com.marcelo721.SEI.entities.Topic;

public record TopicCreateDto(
        String name,
        String description,
        Long idSubject
) {
    public Topic toTopic(){
       Topic topic = new Topic();
       topic.setName(name);
       topic.setDescription(description);

       return topic;
    }
}
