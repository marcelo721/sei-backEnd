package com.marcelo721.SEI.web.dto;

import com.marcelo721.SEI.entities.User;

public record UserCreateDto(
        String name,
        String password,
        String email
) {
    public User toUser() {

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }
}
