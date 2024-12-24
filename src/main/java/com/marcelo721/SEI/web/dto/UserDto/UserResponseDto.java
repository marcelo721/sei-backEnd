package com.marcelo721.SEI.web.dto.UserDto;

import com.marcelo721.SEI.entities.User;

import java.util.ArrayList;
import java.util.List;

public record UserResponseDto(String name, String email, Long id) {

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getName(), user.getEmail(), user.getId());
    }

    public static List<UserResponseDto> toListDto(List<User> users){
        List<UserResponseDto> dtos = new ArrayList<>();

        for(User user : users){
            dtos.add(toDto(user));
        }
        return dtos;
    }
}
