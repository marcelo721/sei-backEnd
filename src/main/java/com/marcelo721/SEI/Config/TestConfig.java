package com.marcelo721.SEI.Config;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.entities.enums.Role;
import com.marcelo721.SEI.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final ExerciseService exerciseService;
    private final UserService userService;
    private final PastExamService  pastExamService;
    private final ResumeService resumeService;
    private final SubjectService subjectService;
    private final TopicService topicService;
    private final VideoService  videoService;

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setRole(Role.STUDENT);
        user.setEmail("marcelo@gmail.com");
        user.setPassword("12345678");
    }
}
