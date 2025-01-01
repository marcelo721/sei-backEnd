package com.marcelo721.SEI.web.dto.ExerciseDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ExerciseCreateDto(
        @NotBlank
        String url,

        @NotNull
        Long topicId
) {
}
