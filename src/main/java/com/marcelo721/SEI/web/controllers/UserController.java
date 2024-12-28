package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.web.dto.UserDto.UserCreateDto;
import com.marcelo721.SEI.web.dto.UserDto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserCreateDto user) {
        User obj = user.toUser();
        userService.save(obj);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDto.toDto(obj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        User obj = userService.findById(id);

        return ResponseEntity.ok(UserResponseDto.toDto(obj));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(UserResponseDto.toListDto(users));
    }

    @PostMapping("/{userId}/subjects/{subjectId}")
    public ResponseEntity<User> addSubject(@PathVariable Long userId, @PathVariable Long subjectId) {
        User student = userService.addSubject(userId, subjectId);
        return ResponseEntity.ok(student);
    }

}
