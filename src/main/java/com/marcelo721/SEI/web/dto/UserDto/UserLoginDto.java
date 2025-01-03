package com.marcelo721.SEI.web.dto.UserDto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
