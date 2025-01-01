package com.marcelo721.SEI.web.dto.resumeDto;

import com.marcelo721.SEI.entities.Resume;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ResumeCreateDto(
        @NotBlank
        String title,

        @NotBlank
        String text,

        @NotNull
        Long idTopic
) {

    public Resume toResume(){
        Resume resume = new Resume();
        resume.setTitle(title);
        resume.setText(text);
        return resume;
    }

}
