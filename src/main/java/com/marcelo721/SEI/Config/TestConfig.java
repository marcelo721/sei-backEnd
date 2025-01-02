package com.marcelo721.SEI.Config;

import com.marcelo721.SEI.entities.*;
import com.marcelo721.SEI.entities.enums.Role;
import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final UserService userService;
    private final SubjectService subjectService;

    @Override
    public void run(String... args) throws Exception {
        // Criando disciplina
        Subject subject1 = new Subject();
        subject1.setName("Arquitetura de Computadores");
        subject1.setSemester(Semester.FIRST);// Semestre

        PastExam pastExam1 = new PastExam();
        pastExam1.setUrl("https://www.youtube.com/watch?v=video101");
        pastExam1.setTitle("2022.1");
        pastExam1.setSubject(subject1);

        PastExam pastExam2 = new PastExam();
        pastExam2.setUrl("https://www.SEFSEyoutube.com/watch?v=video1");
        pastExam2.setTitle("2022.2");
        pastExam2.setSubject(subject1);

        subject1.getPastExams().add(pastExam1);
        subject1.getPastExams().add(pastExam2);

// Criando tópico
        Topic topic1 = new Topic();
        topic1.setName("Processadores e Arquitetura");
        topic1.setDescription("Estudo dos principais componentes de um processador e sua arquitetura");

// Associando o tópico à disciplina
        topic1.setSubject(subject1);  // Associa o tópico à disciplina
        subject1.getTopics().add(topic1);  // Adiciona o tópico à lista de tópicos da disciplina

// Adicionando vídeos ao tópico
        Video video1_1 = new Video();
        video1_1.setUrl("https://www.youtube.com/watch?v=QdPTWhACIwY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=2");
        video1_1.setTopic(topic1);  // Associando o vídeo ao tópico
        topic1.getVideos().add(video1_1);

        Video video1_2 = new Video();
        video1_2.setUrl("https://www.youtube.com/watch?v=voBexx2V7gw&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=3");
        video1_2.setTopic(topic1);  // Associando o vídeo ao tópico
        topic1.getVideos().add(video1_2);

// Adicionando exercícios ao tópico
        Exercise exercise1_1 = new Exercise();
        exercise1_1.setUrl("https://drive.google.com/drive/folders/exercise101");
        exercise1_1.setTopic(topic1);  // Associando o exercício ao tópico
        topic1.getExercises().add(exercise1_1);

        Exercise exercise1_2 = new Exercise();
        exercise1_2.setUrl("https://drive.google.com/drive/folders/exercise102");
        exercise1_2.setTopic(topic1);  // Associando o exercício ao tópico
        topic1.getExercises().add(exercise1_2);

// Criando resumo
        Resume resume1 = new Resume();
        resume1.setTitle("Resumo sobre Processadores e Arquitetura");
        resume1.setText("Este resumo aborda os principais componentes de um processador e o funcionamento de uma arquitetura de computador.");
        resume1.setTopic(topic1);  // Associando o resumo ao tópico
        topic1.setResume(resume1);  // Adicionando o resumo ao tópico

// Salvando no banco
        subjectService.save(subject1);

        // Criando disciplina
        Subject subject2 = new Subject();
        subject2.setName("Sistemas Operacionais");
        subject2.setSemester(Semester.SECOND);  // Semestre II

        PastExam pastExam3 = new PastExam();
        pastExam3.setUrl("https://www.youtube.comtch?v=video101");
        pastExam3.setTitle("2022.1");
        pastExam3.setSubject(subject2);

        PastExam pastExam4 = new PastExam();
        pastExam4.setUrl("https://www.youtube.com/watch?v=vide");
        pastExam4.setTitle("2022.2");
        pastExam4.setSubject(subject2);

        subject2.getPastExams().add(pastExam3);
        subject2.getPastExams().add(pastExam4);

// Criando tópico
        Topic topic2 = new Topic();
        topic2.setName("Processos e Escalonamento");
        topic2.setDescription("Estudo dos processos no sistema operacional e técnicas de escalonamento");

// Associando o tópico à disciplina
        topic2.setSubject(subject2);  // Associa o tópico à disciplina
        subject2.getTopics().add(topic2);  // Adiciona o tópico à lista de tópicos da disciplina

// Adicionando vídeos ao tópico
        Video video2_1 = new Video();
        video2_1.setUrl("https://www.youtube.com/watch?v=2wolMQImLpY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=4");
        video2_1.setTopic(topic2);  // Associando o vídeo ao tópico
        topic2.getVideos().add(video2_1);

        Video video2_2 = new Video();
        video2_2.setUrl("https://www.youtube.com/watch?v=s3j69Fd3GWM&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=5");
        video2_2.setTopic(topic2);  // Associando o vídeo ao tópico
        topic2.getVideos().add(video2_2);

// Adicionando exercícios ao tópico
        Exercise exercise2_1 = new Exercise();
        exercise2_1.setUrl("https://drive.google.com/drive/folders/exercise201");
        exercise2_1.setTopic(topic2);  // Associando o exercício ao tópico
        topic2.getExercises().add(exercise2_1);

        Exercise exercise2_2 = new Exercise();
        exercise2_2.setUrl("https://drive.google.com/drive/folders/exercise202");
        exercise2_2.setTopic(topic2);  // Associando o exercício ao tópico
        topic2.getExercises().add(exercise2_2);

// Criando resumo
        Resume resume2 = new Resume();
        resume2.setTitle("Resumo sobre Processos e Escalonamento");
        resume2.setText("Este resumo aborda o gerenciamento de processos e as estratégias de escalonamento em sistemas operacionais.");
        resume2.setTopic(topic2);  // Associando o resumo ao tópico
        topic2.setResume(resume2);  // Adicionando o resumo ao tópico

// Salvando no banco
        subjectService.save(subject2);

        // Criando disciplina
        Subject subject3 = new Subject();
        subject3.setName("Redes de Computadores");
        subject3.setSemester(Semester.THIRD);  // Semestre III

        PastExam pastExam5 = new PastExam();
        pastExam5.setUrl("hps://www.youtube.356563635653comtch?v=video101");
        pastExam5.setTitle("2022.1");
        pastExam5.setSubject(subject3);

        PastExam pastExam6 = new PastExam();
        pastExam6.setUrl("https://www.6356356356356536?v=vide");
        pastExam6.setTitle("2022.2");
        pastExam6.setSubject(subject3);

        subject3.getPastExams().add(pastExam5);
        subject3.getPastExams().add(pastExam6);

// Criando tópico
        Topic topic3 = new Topic();
        topic3.setName("Modelo OSI e TCP/IP");
        topic3.setDescription("Estudo dos modelos de referência OSI e TCP/IP e sua aplicação em redes");

// Associando o tópico à disciplina
        topic3.setSubject(subject3);  // Associa o tópico à disciplina
        subject3.getTopics().add(topic3);  // Adiciona o tópico à lista de tópicos da disciplina

// Adicionando vídeos ao tópico
        Video video3_1 = new Video();
        video3_1.setUrl("https://www.youtube.com/watch?v=68EDBZwlSuA&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=6");
        video3_1.setTopic(topic3);  // Associando o vídeo ao tópico
        topic3.getVideos().add(video3_1);

        Video video3_2 = new Video();
        video3_2.setUrl("https://www.youtube.com/watch?v=HwHO_w6V_No&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=7");
        video3_2.setTopic(topic3);  // Associando o vídeo ao tópico
        topic3.getVideos().add(video3_2);

// Adicionando exercícios ao tópico
        Exercise exercise3_1 = new Exercise();
        exercise3_1.setUrl("https://drive.google.com/drive/folders/exercise301");
        exercise3_1.setTopic(topic3);  // Associando o exercício ao tópico
        topic3.getExercises().add(exercise3_1);

        Exercise exercise3_2 = new Exercise();
        exercise3_2.setUrl("https://drive.google.com/drive/folders/exercise302");
        exercise3_2.setTopic(topic3);  // Associando o exercício ao tópico
        topic3.getExercises().add(exercise3_2);

// Criando resumo
        Resume resume3 = new Resume();
        resume3.setTitle("Resumo sobre Modelo OSI e TCP/IP");
        resume3.setText("Este resumo detalha os principais conceitos do modelo OSI e TCP/IP usados para comunicação em redes.");
        resume3.setTopic(topic3);  // Associando o resumo ao tópico
        topic3.setResume(resume3);  // Adicionando o resumo ao tópico

// Salvando no banco
        subjectService.save(subject3);

        // Criando disciplina
        Subject subject4 = new Subject();
        subject4.setName("Engenharia de Software");
        subject4.setSemester(Semester.FOURTH);  // Semestre IV

        PastExam pastExam7 = new PastExam();
        pastExam7.setUrl("hps://www.youtube.356comtch?v=video101");
        pastExam7.setTitle("2022.1");
        pastExam7.setSubject(subject4);

        PastExam pastExam8 = new PastExam();
        pastExam8.setUrl("https://www.5565363?v=vide");
        pastExam8.setTitle("2022.2");
        pastExam8.setSubject(subject4);

        subject4.getPastExams().add(pastExam7);
        subject4.getPastExams().add(pastExam8);

// Criando tópico
        Topic topic4 = new Topic();
        topic4.setName("Ciclo de Vida de Software");
        topic4.setDescription("Estudo do ciclo de vida de desenvolvimento de software, incluindo modelos e práticas");

// Associando o tópico à disciplina
        topic4.setSubject(subject4);  // Associa o tópico à disciplina
        subject4.getTopics().add(topic4);  // Adiciona o tópico à lista de tópicos da disciplina

// Adicionando vídeos ao tópico
        Video video4_1 = new Video();
        video4_1.setUrl("https://www.youtube.com/watch?v=ChB77_9RJOM&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=8");
        video4_1.setTopic(topic4);  // Associando o vídeo ao tópico
        topic4.getVideos().add(video4_1);

        Video video4_2 = new Video();
        video4_2.setUrl("https://www.youtube.com/watch?v=8uWVkjgzB7A&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=9");
        video4_2.setTopic(topic4);  // Associando o vídeo ao tópico
        topic4.getVideos().add(video4_2);

// Adicionando exercícios ao tópico
        Exercise exercise4_1 = new Exercise();
        exercise4_1.setUrl("https://drive.google.com/drive/folders/exercise401");
        exercise4_1.setTopic(topic4);  // Associando o exercício ao tópico
        topic4.getExercises().add(exercise4_1);

        Exercise exercise4_2 = new Exercise();
        exercise4_2.setUrl("https://drive.google.com/drive/folders/exercise402");
        exercise4_2.setTopic(topic4);  // Associando o exercício ao tópico
        topic4.getExercises().add(exercise4_2);

// Criando resumo
        Resume resume4 = new Resume();
        resume4.setTitle("Resumo sobre Ciclo de Vida de Software");
        resume4.setText("Este resumo aborda as principais fases do ciclo de vida de software e os modelos de desenvolvimento usados.");
        resume4.setTopic(topic4);  // Associando o resumo ao tópico
        topic4.setResume(resume4);  // Adicionando o resumo ao tópico

// Salvando no banco
        subjectService.save(subject4);

        User user = new User();
        user.setEmail("marcelo@alu.ufc.br");
        user.setPassword("123456");
        user.setRole(Role.STUDENT);
        user.setName("marcelinho");

        user.getSubjects().add(subject1);
        user.getSubjects().add(subject2);
        user.getSubjects().add(subject3);
        user.getSubjects().add(subject4);

        userService.save(user);
    }
}
