package com.marcelo721.SEI.web.dto.UserDto;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record UserResponseDto(String name, String email, Long id, Set<Subject> subjects) {

    public static UserResponseDto toDto(User user){
        return new UserResponseDto(user.getName(), user.getEmail(), user.getId(), user.getSubjects());
    }

    public static List<UserResponseDto> toListDto(List<User> users){
        List<UserResponseDto> dtos = new ArrayList<>();

        for(User user : users){
            dtos.add(toDto(user));
        }
        return dtos;
    }
}
