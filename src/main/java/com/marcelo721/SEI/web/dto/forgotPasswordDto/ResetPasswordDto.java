package com.marcelo721.SEI.web.dto.forgotPasswordDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ResetPasswordDto(

        @NotBlank
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&_]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, incluindo pelo menos uma letra maiúscula, " +
                        "uma letra minúscula, um número e um caractere especial."
        )
        String password,

        @NotBlank
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&_]{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, incluindo pelo menos uma letra maiúscula, " +
                        "uma letra minúscula, um número e um caractere especial."
        )
        String repeatPassword
) {
}
