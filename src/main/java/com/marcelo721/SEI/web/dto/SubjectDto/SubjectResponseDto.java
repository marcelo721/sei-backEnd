package com.marcelo721.SEI.web.dto.SubjectDto;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.web.dto.PastExam.PastExamResponseDto;
import com.marcelo721.SEI.web.dto.TopicDto.TopicSubjectDto;

import java.util.ArrayList;
import java.util.List;

public record SubjectResponseDto(
        String name,
        Long id,
        List<TopicSubjectDto> topics,
        Semester semester,
        List<PastExamResponseDto> pastExams
) {

    public static SubjectResponseDto toDto(Subject subject) {
        List<TopicSubjectDto> topics = TopicSubjectDto.toListDto(subject.getTopics());
        List<PastExamResponseDto> pastExams = PastExamResponseDto.toListDto(subject.getPastExams());

        return new SubjectResponseDto(subject.getName(), subject.getId(),
                topics, subject.getSemester(), pastExams);
    }

    public static List<SubjectResponseDto> toListDto(List<Subject> subjects) {
        List<SubjectResponseDto> topics = new ArrayList<>();
        for (Subject subject : subjects) {
            topics.add(toDto(subject));
        }
        return topics;
    }
}
