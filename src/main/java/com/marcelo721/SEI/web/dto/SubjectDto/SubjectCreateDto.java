package com.marcelo721.SEI.web.dto.SubjectDto;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.enums.Semester;

public record SubjectCreateDto(String name, Semester semester) {

    public Subject toSubject(){
        Subject subject = new Subject();
        subject.setName(name);
        subject.setSemester(semester);
        return subject;
    }
}
