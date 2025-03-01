package com.marcelo721.SEI.utils;

import com.marcelo721.SEI.entities.*;
import com.marcelo721.SEI.entities.enums.Course;
import com.marcelo721.SEI.entities.enums.Role;
import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.entities.enums.StatusAccount;
import com.marcelo721.SEI.repositories.*;
import com.marcelo721.SEI.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile("test")
//classe destinada para perfis de testes
public class Factor implements CommandLineRunner {

    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final ResumeRepository resumeRepository;
    private final VideoRepository videoRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {

        //salvar usuario para teste
        User user = new User();
        user.setName("marcelinho22");
        user.setEmail("marcelo@alu.ufc.br");
        user.setPassword("M@rcelo222");
        user.setStatusAccount(StatusAccount.ENABLED);
        user.setCourse(Course.COMPUTER_ENGINEERING);
        user.setRole(Role.STUDENT);

        User user2 = new User();
        user2.setName("marcelinho221");
        user2.setEmail("marcelin@alu.ufc.br");
        user2.setPassword("M@rcelo222");
        user2.setStatusAccount(StatusAccount.ENABLED);
        user2.setCourse(Course.COMPUTER_ENGINEERING);
        user2.setRole(Role.ADMIN);
        userService.save(user2);
        User userResponse = userRepository.findByEmail("marcelin@alu.ufc.br").get();
        userResponse.setStatusAccount(StatusAccount.ENABLED);
        userRepository.save(userResponse);


        //disciplinas primeiro semestre

        //calculo 1
        Subject calculoI = new Subject();
        calculoI.setName("Cálculo I");
        calculoI.setSemester(Semester.FIRST);
        subjectRepository.save(calculoI);

        //tópicos para calculo 1
        Topic limites = new Topic();
        limites.setName("Limites");
        limites.setDescription("Noção intuitiva de limites");
        limites.setSubject(calculoI);
        topicRepository.save(limites);

        //resumo de limites
        Resume resumolimites = new Resume();
        resumolimites.setTitle("O que são limites ?");
        resumolimites.setText("texto de limites");
        resumolimites.setTopic(limites);
        resumeRepository.save(resumolimites);

        //criando lista de videos para o tópico limites
        Video aulaLimites01 = new Video();
        aulaLimites01.setUrl("https://www.youtube.com/watch?v=QdPTWhACIwY");
        aulaLimites01.setTopic(limites);
        aulaLimites01.setTopic(limites);
        videoRepository.save(aulaLimites01);


        Exercise limitesExercise01 = new Exercise();
        limitesExercise01.setUrl("");
        limitesExercise01.setTopic(limites);
        exerciseRepository.save(limitesExercise01);



        Subject fisicaI = new Subject();
        fisicaI.setName("Física I");
        fisicaI.setSemester(Semester.FIRST);
        fisicaI.getUsers().add(user);
        subjectRepository.save(fisicaI);

        user.getSubjects().add(fisicaI);
        userService.save(user);

        Topic cinematica = new Topic();
        cinematica.setName("Limites1");
        cinematica.setDescription("Noção intuitiva de limites");
        cinematica.setSubject(calculoI);
        topicRepository.save(cinematica);
        User userResponse2 = userRepository.findByEmail("marcelo@alu.ufc.br").get();
        userResponse2.setStatusAccount(StatusAccount.ENABLED);
        userRepository.save(userResponse2);

        Subject subject = new Subject();
        subject.setSemester(Semester.FIRST);
        subject.setName("materia");
        subjectRepository.save(subject);

        Topic materia = new Topic();
        materia.setName("Materia");
        materia.setDescription("Texto de materia");
        materia.setSubject(subject);
        topicRepository.save(materia);


        User user3 = new User();
        user3.setName("marcelinho22");
        user3.setEmail("marcelao@alu.ufc.br");
        user3.setPassword("M@rcelo222");
        user3.setStatusAccount(StatusAccount.ENABLED);
        user3.setCourse(Course.COMPUTER_ENGINEERING);
        user3.setRole(Role.STUDENT);
        user3.getSubjects().add(calculoI);
        userService.save(user3);
        user3 = userRepository.findByEmail("marcelao@alu.ufc.br").get();
        user3.setStatusAccount(StatusAccount.ENABLED);
        userRepository.save(user3);

        User user4 = new User();
        user4.setName("marcelinho22");
        user4.setEmail("test@alu.ufc.br");
        user4.setPassword("M@rcelo222");
        user4.setStatusAccount(StatusAccount.ENABLED);
        user4.setCourse(Course.COMPUTER_ENGINEERING);
        user4.setRole(Role.STUDENT);
        user4.getSubjects().add(calculoI);
        userService.save(user4);
        user4 = userRepository.findByEmail("test@alu.ufc.br").get();
        user4.setStatusAccount(StatusAccount.ENABLED);
        userRepository.save(user4);

        User user5 = new User();
        user5.setName("marcelinho22");
        user5.setEmail("reset@alu.ufc.br");
        user5.setPassword("M@rcelo222");
        user5.setStatusAccount(StatusAccount.ENABLED);
        user5.setCourse(Course.COMPUTER_ENGINEERING);
        user5.setRole(Role.STUDENT);
        user5.getSubjects().add(calculoI);
        userService.save(user5);
        user5 = userRepository.findByEmail("reset@alu.ufc.br").get();
        user5.setStatusAccount(StatusAccount.ENABLED);
        userRepository.save(user5);

    }
}
