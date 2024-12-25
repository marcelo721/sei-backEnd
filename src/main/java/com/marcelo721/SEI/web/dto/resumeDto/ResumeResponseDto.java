package com.marcelo721.SEI.web.dto.resumeDto;


import com.marcelo721.SEI.entities.Resume;

import java.util.ArrayList;
import java.util.List;

public record ResumeResponseDto(
        String title,
        String text,
        Long id,
        String TopicName
) {

    public static ResumeResponseDto toDto(Resume resume){
        ResumeResponseDto dto = new ResumeResponseDto(resume.getTitle(),
                resume.getText(),
                resume.getId(),
                resume.getTopic().getName());

        return dto;
    }

    public static List<ResumeResponseDto> toListDto(List<Resume> resumes){
        List<ResumeResponseDto> dtos = new ArrayList<>();

        for (Resume resume : resumes) {
            dtos.add(toDto(resume));
        }
        return dtos;
    }
}
