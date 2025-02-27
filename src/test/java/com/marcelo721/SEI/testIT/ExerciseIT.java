package com.marcelo721.SEI.testIT;

import com.marcelo721.SEI.entities.Exercise;
import com.marcelo721.SEI.web.dto.ExerciseDto.ExerciseCreateDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ExerciseIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void creatExercise_withValidData_returnStatus201() {

        ExerciseCreateDto exerciseCreateDto = new ExerciseCreateDto("url dos exercicios", 1L);
        testClient
                .post()
                .uri("/api/v1/exercises")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(exerciseCreateDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void creatExercise_withInvalidData_returnStatus422() {

        ExerciseCreateDto exerciseCreateDto = new ExerciseCreateDto("", 1L);

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/exercises")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(exerciseCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

    }

    @Test
    public void creatPastExam_withoutPermission_returnStatus403() {

        ExerciseCreateDto exerciseCreateDto = new ExerciseCreateDto("url dos exercicios", 1L);
        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/exercises")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(exerciseCreateDto)
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(403);
    }

    @Test
    public void creatPastExam_notAuthenticated_returnStatus401() {

        ExerciseCreateDto exerciseCreateDto = new ExerciseCreateDto("url dos exercicios", 1L);
        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/exercises")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(exerciseCreateDto)
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(401);
    }

    @Test
    public void findById_withValidId_returnStatus200(){
        Exercise responseBody = testClient
                .get()
                .uri("/api/v1/exercises/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Exercise.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(1);

    }//

    @Test
    public void findById_withInvalidId_returnStatus404(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/exercises/0")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }//

    @Test
    public void findById_NotAuthenticated_returnStatus401(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/exercises/1")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }//

    @Test
    public void findById_withoutPermission_returnStatus403(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/exercises/2")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }//

    @Test
    public void findAll_withoutParameters_returnStatus200(){

        List<Exercise> responseBody = testClient
                .get()
                .uri("/api/v1/exercises")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Exercise.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    }//

    @Test
    public void findAll_withoutPermission_returnStatus403(){

        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/exercises")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }//

    @Test
    public void findAll_notAuthenticated_returnStatus401(){

        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/exercises")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }//
}
