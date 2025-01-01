package com.marcelo721.SEI.web.dto.PastExam;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PastExamCreateDto(
        @NotBlank
        String title,

        @NotBlank
        String url,

        @NotNull
        Long subjectId

) {
}
