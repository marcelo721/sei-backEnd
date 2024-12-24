package com.marcelo721.SEI.web.dto.SubjectDto;

import com.marcelo721.SEI.entities.Subject;

public record SubjectCreateDto(String name) {

    public Subject toSubject(){
        Subject subject = new Subject();
        subject.setName(name);
        return subject;
    }
}
