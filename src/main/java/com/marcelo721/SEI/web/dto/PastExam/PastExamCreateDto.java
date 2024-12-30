package com.marcelo721.SEI.web.dto.PastExam;

public record PastExamCreateDto(
        String title,
        String url,
        Long subjectId

) {
}
