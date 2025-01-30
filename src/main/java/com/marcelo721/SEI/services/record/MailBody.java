package com.marcelo721.SEI.services.record;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record MailBody(
        @NotBlank
        String to,
        @NotBlank
        String subject,
        @NotBlank
        String text
) {
}
