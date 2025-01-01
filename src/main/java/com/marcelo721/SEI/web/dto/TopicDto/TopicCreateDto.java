package com.marcelo721.SEI.web.dto.TopicDto;

import com.marcelo721.SEI.entities.Topic;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TopicCreateDto(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotNull
        Long idSubject
) {
    public Topic toTopic(){
       Topic topic = new Topic();
       topic.setName(name);
       topic.setDescription(description);

       return topic;
    }
}
