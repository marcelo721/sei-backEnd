package com.marcelo721.SEI.web.dto.SubjectDto;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.enums.Semester;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SubjectCreateDto(
        @NotBlank
        String name,

        @NotNull
        Semester semester) {

    public Subject toSubject(){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setSemester(semester);
        return subject;
    }
}
