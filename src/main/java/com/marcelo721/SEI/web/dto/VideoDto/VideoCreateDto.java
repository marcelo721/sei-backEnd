package com.marcelo721.SEI.web.dto.VideoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VideoCreateDto(

        @NotBlank
        String url,

        @NotNull
        Long topicId
) {
}
