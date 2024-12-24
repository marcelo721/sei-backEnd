package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.Subject;
import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.services.UserService;
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
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User obj = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User obj = userService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/{userId}/subjects/{subjectId}")
    public ResponseEntity<User> addSubject(@PathVariable Long userId, @PathVariable Long subjectId) {
        User student = userService.addSubject(userId, subjectId);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> findAllSubjectsByUserId(@PathVariable Long id) {
        List<Subject> subjects = userService.getSubjects(id);
        return ResponseEntity.ok(subjects);
    }
}
