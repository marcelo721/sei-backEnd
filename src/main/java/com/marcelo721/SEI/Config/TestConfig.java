package com.marcelo721.SEI.Config;

import com.marcelo721.SEI.entities.*;
import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
//@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final ResumeRepository  resumeRepository;
    private final VideoRepository videoRepository;
    private final ExerciseRepository exerciseRepository;
    @Override
    public void run(String... args) throws Exception {

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
        aulaLimites01.setUrl("https://www.youtube.com/watch?v=QdPTWhACIwY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=2");
        aulaLimites01.setTopic(limites);
        aulaLimites01.setTopic(limites);
        videoRepository.save(aulaLimites01);

        Video aulaLimites02 = new Video();
        aulaLimites02.setUrl("https://www.youtube.com/watch?v=voBexx2V7gw&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=3");
        aulaLimites02.setTopic(limites);
        videoRepository.save(aulaLimites02);

        Video aulaLimites03 = new Video();
        aulaLimites03.setUrl("https://www.youtube.com/watch?v=2wolMQImLpY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=4");
        aulaLimites03.setTopic(limites);
        videoRepository.save(aulaLimites03);

        Video aulaLimites04 = new Video();
        aulaLimites04.setUrl("https://www.youtube.com/watch?v=s3j69Fd3GWM&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=5");
        aulaLimites04.setTopic(limites);
        videoRepository.save(aulaLimites04);

        Video aulaLimites05 = new Video();
        aulaLimites05.setUrl("https://www.youtube.com/watch?v=68EDBZwlSuA&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=6");
        aulaLimites05.setTopic(limites);
        videoRepository.save(aulaLimites05);

        Video aulaLimites06 = new Video();
        aulaLimites06.setUrl("https://www.youtube.com/watch?v=HwHO_w6V_No&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=7");
        aulaLimites06.setTopic(limites);
        videoRepository.save(aulaLimites06);

        Video aulaLimites07 = new Video();
        aulaLimites07.setUrl("https://www.youtube.com/watch?v=ChB77_9RJOM&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=8");
        aulaLimites07.setTopic(limites);
        videoRepository.save(aulaLimites07);

        Video aulaLimites08 = new Video();
        aulaLimites08.setUrl("https://www.youtube.com/watch?v=8uWVkjgzB7A&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=9");
        aulaLimites08.setTopic(limites);
        videoRepository.save(aulaLimites08);

        Video aulaLimites09 = new Video();
        aulaLimites09.setUrl("https://www.youtube.com/watch?v=nX6XtwX5dV4&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=10");
        aulaLimites09.setTopic(limites);
        videoRepository.save(aulaLimites09);

        Video aulaLimites10 = new Video();
        aulaLimites10.setUrl("https://www.youtube.com/watch?v=8uWVkjgzB7A&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=11");
        aulaLimites10.setTopic(limites);
        videoRepository.save(aulaLimites10);

        Video aulaLimites11 = new Video();
        aulaLimites11.setUrl("https://www.youtube.com/watch?v=8uWVkjgzB7A&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=12");
        aulaLimites11.setTopic(limites);
        videoRepository.save(aulaLimites11);

        Video aulaLimites12 = new Video();
        aulaLimites12.setUrl("https://www.youtube.com/watch?v=8uWVkjgzB7A&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=13");
        aulaLimites12.setTopic(limites);
        videoRepository.save(aulaLimites12);

        Exercise limitesExercise01 = new Exercise();
        limitesExercise01.setUrl("");
        limitesExercise01.setTopic(limites);

        Exercise limitesExercise02 = new Exercise();
        limitesExercise02.setUrl("");
        limitesExercise02.setTopic(limites);

        Exercise limitesExercise03 = new Exercise();
        limitesExercise03.setUrl("");
        limitesExercise03.setTopic(limites);

        Exercise limitesExercise04 = new Exercise();
        limitesExercise04.setUrl("");
        limitesExercise04.setTopic(limites);

        Exercise limitesExercise05 = new Exercise();
        limitesExercise05.setUrl("");
        limitesExercise05.setTopic(limites);

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
        aulafuncoesContinuas01.setUrl("https://www.youtube.com/watch?v=PW_Y2pvJg4s&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=14");
        aulafuncoesContinuas01.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas01);

        Video aulafuncoesContinuas02 = new Video();
        aulafuncoesContinuas02.setUrl("https://www.youtube.com/watch?v=PW_Y2pvJg4s&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=15");
        aulafuncoesContinuas02.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas02);


        Video aulafuncoesContinuas03 = new Video();
        aulafuncoesContinuas03.setUrl("https://www.youtube.com/watch?v=PW_Y2pvJg4s&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=16");
        aulafuncoesContinuas03.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas03);


        Video aulafuncoesContinuas04 = new Video();
        aulafuncoesContinuas04.setUrl("https://www.youtube.com/watch?v=y2NeuWYexLc&list=PLe82WKsecrpxRkX578EifI2QkAfhL0TEk");
        aulafuncoesContinuas04.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas04);


        Video aulafuncoesContinuas05 = new Video();
        aulafuncoesContinuas05.setUrl("https://www.youtube.com/watch?v=nB_359EriG4&list=PLe82WKsecrpxRkX578EifI2QkAfhL0TEk&index=2");
        aulafuncoesContinuas05.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas05);


        Video aulafuncoesContinuas06 = new Video();
        aulafuncoesContinuas06.setUrl("https://www.youtube.com/watch?v=iiOrtGZVqnk");
        aulafuncoesContinuas06.setTopic(funcoesContinuas);
        videoRepository.save(aulafuncoesContinuas06);

        Exercise funcoesContinuas_Exercise01 = new Exercise();
        funcoesContinuas_Exercise01.setUrl("");
        funcoesContinuas_Exercise01.setTopic(funcoesContinuas);

        Exercise funcoesContinuas_Exercise02 = new Exercise();
        funcoesContinuas_Exercise02.setUrl("");
        funcoesContinuas_Exercise02.setTopic(funcoesContinuas);

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
        aulaDerivadas01.setUrl("https://www.youtube.com/watch?v=cWBEMN75IMc&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=17");
        aulaDerivadas01.setTopic(derivadas);
        videoRepository.save(aulaDerivadas01);

        Video aulaDerivadas02 = new Video();
        aulaDerivadas02.setUrl("https://www.youtube.com/watch?v=cWBEMN75IMc&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=18");
        aulaDerivadas02.setTopic(derivadas);
        videoRepository.save(aulaDerivadas02);

        Video aulaDerivadas03 = new Video();
        aulaDerivadas03.setUrl("https://www.youtube.com/watch?v=cWBEMN75IMc&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=19");
        aulaDerivadas03.setTopic(derivadas);
        videoRepository.save(aulaDerivadas03);

        Video aulaDerivadas04 = new Video();
        aulaDerivadas04.setUrl("https://www.youtube.com/watch?v=cWBEMN75IMc&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=20");
        aulaDerivadas04.setTopic(derivadas);
        videoRepository.save(aulaDerivadas04);

        Video aulaDerivadas05 = new Video();
        aulaDerivadas05.setUrl("https://www.youtube.com/watch?v=cWBEMN75IMc&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=21");
        aulaDerivadas05.setTopic(derivadas);
        videoRepository.save(aulaDerivadas05);

        Video aulaDerivadas06 = new Video();
        aulaDerivadas06.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=23");
        aulaDerivadas06.setTopic(derivadas);
        videoRepository.save(aulaDerivadas06);

        Video aulaDerivadas07 = new Video();
        aulaDerivadas07.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=25");
        aulaDerivadas07.setTopic(derivadas);
        videoRepository.save(aulaDerivadas07);

        Video aulaDerivadas08 = new Video();
        aulaDerivadas08.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=23");
        aulaDerivadas08.setTopic(derivadas);
        videoRepository.save(aulaDerivadas08);

        Video aulaDerivadas09 = new Video();
        aulaDerivadas09.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=25");
        aulaDerivadas09.setTopic(derivadas);
        videoRepository.save(aulaDerivadas09);

        Video aulaDerivadas10 = new Video();
        aulaDerivadas10.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=26");
        aulaDerivadas10.setTopic(derivadas);
        videoRepository.save(aulaDerivadas10);

        Video aulaDerivadas11 = new Video();
        aulaDerivadas11.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=27");
        aulaDerivadas11.setTopic(derivadas);
        videoRepository.save(aulaDerivadas11);

        Video aulaDerivadas12 = new Video();
        aulaDerivadas12.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=28");
        aulaDerivadas12.setTopic(derivadas);
        videoRepository.save(aulaDerivadas12);

        Video aulaDerivadas13 = new Video();
        aulaDerivadas13.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=29");
        aulaDerivadas13.setTopic(derivadas);
        videoRepository.save(aulaDerivadas13);

        Video aulaDerivadas14 = new Video();
        aulaDerivadas14.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=30");
        aulaDerivadas14.setTopic(derivadas);
        videoRepository.save(aulaDerivadas14);
        Video aulaDerivadas15 = new Video();

        aulaDerivadas15.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=31");
        aulaDerivadas15.setTopic(derivadas);
        videoRepository.save(aulaDerivadas15);

        Video aulaDerivadas16 = new Video();
        aulaDerivadas16.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=32");
        aulaDerivadas16.setTopic(derivadas);
        videoRepository.save(aulaDerivadas16);

        Video aulaDerivadas17 = new Video();
        aulaDerivadas17.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=33");
        aulaDerivadas17.setTopic(derivadas);
        videoRepository.save(aulaDerivadas17);

        Video aulaDerivadas18 = new Video();
        aulaDerivadas18.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=34");
        aulaDerivadas18.setTopic(derivadas);
        videoRepository.save(aulaDerivadas18);

        Video aulaDerivadas19 = new Video();
        aulaDerivadas19.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=35");
        aulaDerivadas19.setTopic(derivadas);
        videoRepository.save(aulaDerivadas19);

        Video aulaDerivadas20 = new Video();
        aulaDerivadas20.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=36");
        aulaDerivadas20.setTopic(derivadas);
        videoRepository.save(aulaDerivadas20);

        Video aulaDerivadas21 = new Video();
        aulaDerivadas21.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=37");
        aulaDerivadas21.setTopic(derivadas);
        videoRepository.save(aulaDerivadas21);

        Video aulaDerivadas22 = new Video();
        aulaDerivadas22.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=38");
        aulaDerivadas22.setTopic(derivadas);
        videoRepository.save(aulaDerivadas22);

        Video aulaDerivadas23 = new Video();
        aulaDerivadas23.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=39");
        aulaDerivadas23.setTopic(derivadas);
        videoRepository.save(aulaDerivadas23);

        Video aulaDerivadas24 = new Video();
        aulaDerivadas24.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=40");
        aulaDerivadas24.setTopic(derivadas);
        videoRepository.save(aulaDerivadas24);

        Video aulaDerivadas25 = new Video();
        aulaDerivadas25.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=41");
        aulaDerivadas25.setTopic(derivadas);
        videoRepository.save(aulaDerivadas25);

        Video aulaDerivadas26 = new Video();
        aulaDerivadas26.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=42");
        aulaDerivadas26.setTopic(derivadas);
        videoRepository.save(aulaDerivadas26);

        Video aulaDerivadas27 = new Video();
        aulaDerivadas27.setUrl("https://www.youtube.com/watch?v=91UN2cbzBGY&list=PLEfwqyY2ox86LhxKybOY3_IG-7R5herLC&index=43");
        aulaDerivadas27.setTopic(derivadas);
        videoRepository.save(aulaDerivadas27);
    }}
