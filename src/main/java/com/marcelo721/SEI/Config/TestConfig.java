package com.marcelo721.SEI.Config;

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
public class TestConfig implements CommandLineRunner {

    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final ResumeRepository  resumeRepository;
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
        resumolimites.setText("O limite é um dos conceitos mais fundamentais da matemática, especialmente no campo do cálculo. Ele descreve" +
                " o comportamento de uma função ou sequência à medida que a variável independente se aproxima de um determinado valor." +
                " Em muitos casos, o limite nos permite entender o que ocorre com uma função quando ela se aproxima de um ponto, " +
                "mesmo quando não há um valor definido nesse ponto. Esse conceito é crucial para compreender a continuidade das funções," +
                " bem como para a definição de derivadas e integrais.\n" +
                "\n" +
                "De maneira mais precisa, o limite nos ajuda a estudar o comportamento assintótico de uma" +
                " função — como ela se comporta em direção a um valor específico, seja ele finito ou infinito. No caso das derivadas," +
                " por exemplo, o limite é usado para calcular taxas de variação, que descrevem a inclinação de uma curva em um ponto específico." +
                " Da mesma forma, o conceito de limite é essencial para a definição de integrais, permitindo calcular áreas sob curvas e somar infinitos " +
                "valores em intervalos pequenos.\n" +
                "\n" +
                "Portanto, o limite não é apenas um valor que se alcança, mas uma ferramenta poderosa" +
                " para a análise do comportamento de funções em uma escala infinitesimal, permitindo que problemas complexos" +
                " sejam resolvidos de forma precisa e rigorosa.");
        resumolimites.setTopic(limites);
        resumeRepository.save(resumolimites);

        //criando lista de videos para o tópico limites
        Video aulaLimites01 = new Video();
        aulaLimites01.setUrl("https://www.youtube.com/watch?v=QdPTWhACIwY");
        aulaLimites01.setTopic(limites);
        aulaLimites01.setTopic(limites);
        videoRepository.save(aulaLimites01);

        Video aulaLimites02 = new Video();
        aulaLimites02.setUrl("https://www.youtube.com/watch?v=voBexx2V7gw");
        aulaLimites02.setTopic(limites);
        videoRepository.save(aulaLimites02);

        Video aulaLimites03 = new Video();
        aulaLimites03.setUrl("https://www.youtube.com/watch?v=2wolMQImLpY");
        aulaLimites03.setTopic(limites);
        videoRepository.save(aulaLimites03);

        Video aulaLimites04 = new Video();
        aulaLimites04.setUrl("https://www.youtube.com/watch?v=s3j69Fd3GWM");
        aulaLimites04.setTopic(limites);
        videoRepository.save(aulaLimites04);

        Video aulaLimites05 = new Video();
        aulaLimites05.setUrl("https://www.youtube.com/watch?v=68EDBZwlSuA");
        aulaLimites05.setTopic(limites);
        videoRepository.save(aulaLimites05);

        Video aulaLimites06 = new Video();
        aulaLimites06.setUrl("https://www.youtube.com/watch?v=HwHO_w6V_No");
        aulaLimites06.setTopic(limites);
        videoRepository.save(aulaLimites06);

        Video aulaLimites07 = new Video();
        aulaLimites07.setUrl("https://www.youtube.com/watch?v=ChB77_9RJOM");
        aulaLimites07.setTopic(limites);
        videoRepository.save(aulaLimites07);

        Video aulaLimites08 = new Video();
        aulaLimites08.setUrl("https://www.youtube.com/watch?v=8uWVkjgzB7A");
        aulaLimites08.setTopic(limites);
        videoRepository.save(aulaLimites08);

        Video aulaLimites09 = new Video();
        aulaLimites09.setUrl("https://www.youtube.com/watch?v=nX6XtwX5dV4");
        aulaLimites09.setTopic(limites);
        videoRepository.save(aulaLimites09);

        Video aulaLimites10 = new Video();
        aulaLimites10.setUrl("https://www.youtube.com/watch?v=u6wAIs0_lnw");
        aulaLimites10.setTopic(limites);
        videoRepository.save(aulaLimites10);

        Video aulaLimites11 = new Video();
        aulaLimites11.setUrl("https://www.youtube.com/watch?v=nxpW6EqN2Jc");
        aulaLimites11.setTopic(limites);
        videoRepository.save(aulaLimites11);

        Video aulaLimites12 = new Video();
        aulaLimites12.setUrl("https://www.youtube.com/watch?v=8uWVkjgzB7A");
        aulaLimites12.setTopic(limites);
        videoRepository.save(aulaLimites12);

        Exercise limitesExercise01 = new Exercise();
        limitesExercise01.setUrl("");
        limitesExercise01.setTopic(limites);
        exerciseRepository.save(limitesExercise01);

        Exercise limitesExercise02 = new Exercise();
        limitesExercise02.setUrl("");
        limitesExercise02.setTopic(limites);
        exerciseRepository.save(limitesExercise02);

        Exercise limitesExercise03 = new Exercise();
        limitesExercise03.setUrl("");
        limitesExercise03.setTopic(limites);
        exerciseRepository.save(limitesExercise03);

        Exercise limitesExercise04 = new Exercise();
        limitesExercise04.setUrl("");
        limitesExercise04.setTopic(limites);
        exerciseRepository.save(limitesExercise04);

        Exercise limitesExercise05 = new Exercise();
        limitesExercise05.setUrl("");
        limitesExercise05.setTopic(limites);
        exerciseRepository.save(limitesExercise05);

        Topic funcoesContinuas = new Topic();
        funcoesContinuas.setName("Funções contínuas ");
        funcoesContinuas.setDescription("Nesse tópico, Serão abordados os conceitos e as propriedades das funções contínuas, " +
                "explorando como elas se comportam em diferentes intervalos" +
                " e como a continuidade influencia o comportamento das funções em cálculos e teoremas importantes");
        funcoesContinuas.setSubject(calculoI);
        topicRepository.save(funcoesContinuas);

        Resume resumeFuncoesContinuas = new Resume();
        resumeFuncoesContinuas.setTitle("Breve resumo sobre funções contínuas");
        resumeFuncoesContinuas.setText("Uma função contínua é uma função matemática em que pequenas variações nas entradas causam pequenas variações " +
                "nas saídas. Em outras palavras, a função não apresenta saltos ou descontinuidades. Para que uma função seja contínua, " +
                "ela deve estar bem definida em todo o seu domínio, e o valor da função em qualquer ponto deve ser igual ao limite da função à medida" +
                " que se aproxima desse ponto. Isso implica que, para qualquer ponto dentro de seu domínio, a função deve ser capaz de ser desenhada s" +
                "em levantar o lápis do papel.");
        resumeFuncoesContinuas.setTopic(funcoesContinuas);
        resumeRepository.save(resumeFuncoesContinuas);

        Video aulafuncoesContinuas01 = new Video();
        aulafuncoesContinuas01.setUrl("https://www.youtube.com/watch?v=PW_Y2pvJg4s");
        aulafuncoesContinuas01.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas01);

        Video aulafuncoesContinuas02 = new Video();
        aulafuncoesContinuas02.setUrl("https://www.youtube.com/watch?v=4L4E_N3EaJs");
        aulafuncoesContinuas02.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas02);



        Video aulafuncoesContinuas04 = new Video();
        aulafuncoesContinuas04.setUrl("https://www.youtube.com/watch?v=y2NeuWYexLc");
        aulafuncoesContinuas04.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas04);


        Video aulafuncoesContinuas05 = new Video();
        aulafuncoesContinuas05.setUrl("https://www.youtube.com/watch?v=nB_359EriG4");
        aulafuncoesContinuas05.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas05);


        Video aulafuncoesContinuas06 = new Video();
        aulafuncoesContinuas06.setUrl("https://www.youtube.com/watch?v=iiOrtGZVqnk");
        aulafuncoesContinuas06.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas06);

        Exercise funcoesContinuas_Exercise01 = new Exercise();
        funcoesContinuas_Exercise01.setUrl("");
        funcoesContinuas_Exercise01.setTopic(funcoesContinuas);
        exerciseRepository.save(funcoesContinuas_Exercise01);

        Exercise funcoesContinuas_Exercise02 = new Exercise();
        funcoesContinuas_Exercise02.setUrl("");
        funcoesContinuas_Exercise02.setTopic(funcoesContinuas);
        exerciseRepository.save(funcoesContinuas_Exercise02);

        Topic derivadas = new Topic();
        derivadas.setName("Derivadas");
        derivadas.setDescription("Neste tópico, serão abordados os conceitos e propriedades das derivadas," +
                " explorando como elas descrevem a taxa de variação das funções. Serão discutidos os comportamentos das " +
                "derivadas em diferentes " +
                "intervalos e como a continuidade das funções influencia seu cálculo e os teoremas fundamentais relacionados.");
        derivadas.setSubject(calculoI);
        topicRepository.save(derivadas);

        // Criando lista de vídeos para o tópico derivadas
        Video aulaDerivadas01 = new Video();
        aulaDerivadas01.setUrl("https://www.youtube.com/watch?v=cWBEMN75IMc");
        aulaDerivadas01.setTopic(derivadas);
        videoRepository.save(aulaDerivadas01);

        Video aulaDerivadas02 = new Video();
        aulaDerivadas02.setUrl("https://www.youtube.com/watch?v=pGSRpeapO2w");
        aulaDerivadas02.setTopic(derivadas);
        videoRepository.save(aulaDerivadas02);

        Video aulaDerivadas03 = new Video();
        aulaDerivadas03.setUrl("https://www.youtube.com/watch?v=2NBVVW3XvZc");
        aulaDerivadas03.setTopic(derivadas);
        videoRepository.save(aulaDerivadas03);

        Video aulaDerivadas04 = new Video();
        aulaDerivadas04.setUrl("https://www.youtube.com/watch?v=b5m864fIKvA");
        aulaDerivadas04.setTopic(derivadas);
        videoRepository.save(aulaDerivadas04);

        Video aulaDerivadas05 = new Video();
        aulaDerivadas05.setUrl("https://www.youtube.com/watch?v=tmlPchI--gw");
        aulaDerivadas05.setTopic(derivadas);
        videoRepository.save(aulaDerivadas05);

        Video aulaDerivadas06 = new Video();
        aulaDerivadas06.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY");
        aulaDerivadas06.setTopic(derivadas);
        videoRepository.save(aulaDerivadas06);

        Video aulaDerivadas07 = new Video();
        aulaDerivadas07.setUrl("https://www.youtube.com/watch?v=OY4RHvyrRsI");
        aulaDerivadas07.setTopic(derivadas);
        videoRepository.save(aulaDerivadas07);

        Video aulaDerivadas08 = new Video();
        aulaDerivadas08.setUrl("https://www.youtube.com/watch?v=vN5dIxDx18I");
        aulaDerivadas08.setTopic(derivadas);
        videoRepository.save(aulaDerivadas08);

        Video aulaDerivadas09 = new Video();
        aulaDerivadas09.setUrl("https://www.youtube.com/watch?v=nsKAHLGAYw0");
        aulaDerivadas09.setTopic(derivadas);
        videoRepository.save(aulaDerivadas09);

        Video aulaDerivadas10 = new Video();
        aulaDerivadas10.setUrl("https://www.youtube.com/watch?v=mTx6pUqWDYs");
        aulaDerivadas10.setTopic(derivadas);
        videoRepository.save(aulaDerivadas10);

        Video aulaDerivadas11 = new Video();
        aulaDerivadas11.setUrl("https://www.youtube.com/watch?v=TIe5E_TqrvQ");
        aulaDerivadas11.setTopic(derivadas);
        videoRepository.save(aulaDerivadas11);

        Video aulaDerivadas12 = new Video();
        aulaDerivadas12.setUrl("https://www.youtube.com/watch?v=IpIOhPdI9_Y");
        aulaDerivadas12.setTopic(derivadas);
        videoRepository.save(aulaDerivadas12);

        Video aulaDerivadas13 = new Video();
        aulaDerivadas13.setUrl("https://www.youtube.com/watch?v=qIemCSbTz9c");
        aulaDerivadas13.setTopic(derivadas);
        videoRepository.save(aulaDerivadas13);

        Video aulaDerivadas14 = new Video();
        aulaDerivadas14.setUrl("https://www.youtube.com/watch?v=NviwT9nvJEc");
        aulaDerivadas14.setTopic(derivadas);
        videoRepository.save(aulaDerivadas14);
        Video aulaDerivadas15 = new Video();

        aulaDerivadas15.setUrl("https://www.youtube.com/watch?v=2NWtEezNE48");
        aulaDerivadas15.setTopic(derivadas);
        videoRepository.save(aulaDerivadas15);

        Video aulaDerivadas16 = new Video();
        aulaDerivadas16.setUrl("https://www.youtube.com/watch?v=WH4-5xDujpo");
        aulaDerivadas16.setTopic(derivadas);
        videoRepository.save(aulaDerivadas16);

        Video aulaDerivadas17 = new Video();
        aulaDerivadas17.setUrl("https://www.youtube.com/watch?v=i06swUAOyc4");
        aulaDerivadas17.setTopic(derivadas);
        videoRepository.save(aulaDerivadas17);

        Video aulaDerivadas18 = new Video();
        aulaDerivadas18.setUrl("https://www.youtube.com/watch?v=Q_smhHkGi70");
        aulaDerivadas18.setTopic(derivadas);
        videoRepository.save(aulaDerivadas18);

        Video aulaDerivadas19 = new Video();
        aulaDerivadas19.setUrl("https://www.youtube.com/watch?v=dDwWaoJlSlA");
        aulaDerivadas19.setTopic(derivadas);
        videoRepository.save(aulaDerivadas19);

        Video aulaDerivadas20 = new Video();
        aulaDerivadas20.setUrl("https://www.youtube.com/watch?v=FMp4klLrREo");
        aulaDerivadas20.setTopic(derivadas);
        videoRepository.save(aulaDerivadas20);

        Video aulaDerivadas21 = new Video();
        aulaDerivadas21.setUrl("https://www.youtube.com/watch?v=PMOEMs00Jz4");
        aulaDerivadas21.setTopic(derivadas);
        videoRepository.save(aulaDerivadas21);

        Video aulaDerivadas22 = new Video();
        aulaDerivadas22.setUrl("https://www.youtube.com/watch?v=vrG-627wBMI");
        aulaDerivadas22.setTopic(derivadas);
        videoRepository.save(aulaDerivadas22);

        Video aulaDerivadas23 = new Video();
        aulaDerivadas23.setUrl("https://www.youtube.com/watch?v=X3_FkHr3loQ");
        aulaDerivadas23.setTopic(derivadas);
        videoRepository.save(aulaDerivadas23);


        Exercise derivadasExcesice01 = new Exercise();
        derivadasExcesice01.setUrl("");
        derivadasExcesice01.setTopic(derivadas);
        exerciseRepository.save(derivadasExcesice01);

        Exercise derivadasExcesice02 = new Exercise();
        derivadasExcesice02.setUrl("");
        funcoesContinuas_Exercise02.setTopic(derivadas);
        exerciseRepository.save(derivadasExcesice02);

        Exercise derivadasExcesice03 = new Exercise();
        derivadasExcesice03.setUrl("");
        funcoesContinuas_Exercise02.setTopic(derivadas);
        exerciseRepository.save(derivadasExcesice03);

        Exercise derivadasExcesice04 = new Exercise();
        derivadasExcesice04.setUrl("");
        derivadasExcesice04.setTopic(derivadas);
        exerciseRepository.save(derivadasExcesice04);

        Exercise derivadasExcesice05 = new Exercise();
        derivadasExcesice05.setUrl("");
        derivadasExcesice05.setTopic(derivadas);
        exerciseRepository.save(derivadasExcesice05);

        Exercise derivadasExercise06 = new Exercise();
        derivadasExcesice03.setUrl("");
        derivadasExercise06.setTopic(derivadas);

        Exercise derivadasExcesice07 = new Exercise();
        derivadasExcesice07.setUrl("");
        derivadasExcesice07.setTopic(derivadas);
        exerciseRepository.save(derivadasExcesice07);

        Exercise derivadasExercise08 = new Exercise();
        derivadasExcesice03.setUrl("");
        derivadasExercise06.setTopic(derivadas);
        exerciseRepository.save(derivadasExercise06);

        Exercise derivadasExercise09 = new Exercise();
        derivadasExcesice03.setUrl("");
        derivadasExercise06.setTopic(derivadas);
        exerciseRepository.save(derivadasExercise09);

        Topic integrais = new Topic();
        integrais.setSubject(calculoI);
        integrais.setName("integrais");
        integrais.setDescription("Nesse tópico, iremos abordar o conceito de integrais, que são ferramentas fundamentais do cálculo utilizadas " +
                "para calcular áreas, volumes, comprimentos de curvas e diversas outras grandezas. Exploraremos a definição de integral definida e" +
                "" + " indefinida, suas propriedades, técnicas de integração e aplicações em problemas práticos." +
                " Além disso, veremos como as integrais estão relacionadas à derivação pelo Teorema Fundamental do Cálculo.");

        topicRepository.save(integrais);
        Resume resumoIntegrais = new Resume();
        resumoIntegrais.setTitle("O que é uma integral ?");
        resumoIntegrais.setText("As integrais são um conceito fundamental do cálculo e possuem diversas aplicações em áreas como física, " +
                "engenharia e economia. Elas são utilizadas para calcular áreas sob curvas, volumes de sólidos, comprimentos de arcos e até mesmo quantidades acumuladas ao longo do tempo.\n" +
                "\n" +
                "Existem dois tipos principais de integrais: a indefinida e a definida. A integral indefinida representa uma família de funções cuja derivada resulta na " +
                "função original, sendo usada para encontrar expressões gerais antes de determinar valores específicos. Já a integral definida calcula a área sob uma curva dentro de " +
                "um intervalo determinado, sendo muito aplicada na resolução de problemas que envolvem acumulação de grandezas.\n" +
                "\n" +
                "Um dos conceitos mais importantes relacionados às integrais é o Teorema Fundamental do Cálculo, que estabelece a relação entre integração e derivação. " +
                "Esse teorema permite calcular integrais definidas de forma eficiente, utilizando antiderivadas.\n" +
                "\n" +
                "Para resolver integrais, existem diversas técnicas, como substituição, integração por partes e frações parciais. Cada método é aplicado conforme a complexidade da " +
                "função envolvida. Além disso, as integrais possuem grande importância prática, sendo usadas para modelar fenômenos naturais, calcular probabilidades, descrever movimentos e analisar crescimento populacional. ");
        resumoIntegrais.setTopic(integrais);
        resumeRepository.save(resumoIntegrais);

        Video integrais01 = new Video();
        integrais01.setUrl("https://www.youtube.com/watch?v=M_xCxHcBdBo");
        integrais01.setTopic(integrais);
        videoRepository.save(integrais01);

        Video integrais02 = new Video();
        integrais02.setUrl("https://www.youtube.com/watch?v=fHom2rFJGjg");
        integrais02.setTopic(integrais);
        videoRepository.save(integrais02);

        Video integrais03 = new Video();
        integrais03.setUrl("https://www.youtube.com/watch?v=E3ZILV7ER54");
        integrais03.setTopic(integrais);
        videoRepository.save(integrais03);

        Video integrais04 = new Video();
        integrais04.setUrl("https://www.youtube.com/watch?v=CWWbjoOjYOg");
        integrais04.setTopic(integrais);
        videoRepository.save(integrais04);

        Video integrais05 = new Video();
        integrais05.setUrl("https://www.youtube.com/watch?v=qRMvrwLt96s");
        integrais05.setTopic(integrais);
        videoRepository.save(integrais05);

        Video integrais06 = new Video();
        integrais06.setUrl("https://www.youtube.com/watch?v=wv23YefHGJA");
        integrais06.setTopic(integrais);
        videoRepository.save(integrais06);

        Video integrais07 = new Video();
        integrais07.setUrl("https://www.youtube.com/watch?v=SIjRu1Xw1QM");
        integrais07.setTopic(integrais);
        videoRepository.save(integrais07);

        Video integrais08 = new Video();
        integrais08.setUrl("https://www.youtube.com/watch?v=nCbocso-Pbw");
        integrais08.setTopic(integrais);
        videoRepository.save(integrais08);

        Video integrais09 = new Video();
        integrais09.setUrl("https://www.youtube.com/watch?v=2kyjGsqPlko");
        integrais09.setTopic(integrais);
        videoRepository.save(integrais09);

        Video integrais10 = new Video();
        integrais10.setUrl("https://www.youtube.com/watch?v=nKQvdg4v8HM");
        integrais10.setTopic(integrais);
        videoRepository.save(integrais10);

        Video integrais11 = new Video();
        integrais11.setUrl("https://www.youtube.com/watch?v=7vEIru6Qp0U");
        integrais11.setTopic(integrais);
        videoRepository.save(integrais11);

        Video integrais12 = new Video();
        integrais12.setUrl("https://www.youtube.com/watch?v=o9M-28RNaRI");
        integrais12.setTopic(integrais);
        videoRepository.save(integrais12);

        Video integrais13 = new Video();
        integrais13.setUrl("https://www.youtube.com/watch?v=NC3kdjHXmks");
        integrais13.setTopic(integrais);
        videoRepository.save(integrais13);

        Video integrais14 = new Video();
        integrais14.setUrl("https://www.youtube.com/watch?v=HqtKJbNMm6Y");
        integrais14.setTopic(integrais);
        videoRepository.save(integrais14);

        Video integrais15 = new Video();
        integrais15.setUrl("https://www.youtube.com/watch?v=ScrZj1iCp6c");
        integrais15.setTopic(integrais);
        videoRepository.save(integrais15);

        Subject fisicaI = new Subject();
        fisicaI.setName("Física I");
        fisicaI.setSemester(Semester.FIRST);
        fisicaI.getUsers().add(user);
        subjectRepository.save(fisicaI);

        user.getSubjects().add(fisicaI);
        userService.save(user);


        //tópicos para calculo 1
        Topic cinematica = new Topic();
        cinematica.setName("Limites1");
        cinematica.setDescription("Noção intuitiva de limites");
        cinematica.setSubject(calculoI);
        topicRepository.save(cinematica);
    }}
