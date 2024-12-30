package com.marcelo721.SEI.web.dto.PastExam;

import com.marcelo721.SEI.entities.PastExam;

import java.util.ArrayList;
import java.util.List;

public record PastExamResponseDto (
        String url,
        String title
){

    public static PastExamResponseDto tDto(PastExam pastExam) {
        return   new PastExamResponseDto(pastExam.getUrl(), pastExam.getTitle());
    }

    public static List<PastExamResponseDto> toListDto(List<PastExam> pastExams) {

        List<PastExamResponseDto> pastExamResponseDtos = new ArrayList<>();
        for (PastExam pastExam : pastExams) {
            pastExamResponseDtos.add(tDto(pastExam));
        }
        return pastExamResponseDtos;
    }
}
