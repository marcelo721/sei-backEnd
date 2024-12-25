package com.marcelo721.SEI.web.dto.resumeDto;

import com.marcelo721.SEI.entities.Resume;

public record ResumeCreateDto(
        String title,
        String text,
        Long idTopic
) {

    public Resume toResume(){
        Resume resume = new Resume();
        resume.setTitle(title);
        resume.setText(text);
        return resume;
    }

}
