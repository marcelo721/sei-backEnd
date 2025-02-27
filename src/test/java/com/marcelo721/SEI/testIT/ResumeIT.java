package com.marcelo721.SEI.testIT;


import com.marcelo721.SEI.web.dto.TopicDto.TopicResponseDto;
import com.marcelo721.SEI.web.dto.resumeDto.ResumeCreateDto;
import com.marcelo721.SEI.web.dto.resumeDto.ResumeResponseDto;
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
public class ResumeIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void creatResume_withValidData_returnStatus201() {

        ResumeCreateDto resumeCreateDto = new ResumeCreateDto("resumo de calculo", "isso é um resumo de calculo", 5L);
        testClient
                .post()
                .uri("/api/v1/resumes")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resumeCreateDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void createResume_withInvalidData_returnStatus422() {
        ResumeCreateDto resumeCreateDto = new ResumeCreateDto("", "isso é um resumo de calculo", 5L);

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/resumes")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resumeCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        resumeCreateDto = new ResumeCreateDto("hqth", "", 5L);
        response = testClient
                .post()
                .uri("/api/v1/subjects")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resumeCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        resumeCreateDto = new ResumeCreateDto("hqth", "raggrgrg", null);
        response = testClient
                .post()
                .uri("/api/v1/subjects")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resumeCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

    }

    @Test
    public void crateResume_notAuthenticated_returnStatus401() {

        ResumeCreateDto resumeCreateDto = new ResumeCreateDto("resumo de calculo", "isso é um resumo de calculo", 5L);

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/resumes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resumeCreateDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(401);
    }

    @Test
    public void createResume_withoutPermissions_returnStatus403() {
        ResumeCreateDto resumeCreateDto = new ResumeCreateDto("resumo de calculo", "isso é um resumo de calculo", 5L);

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/resumes")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resumeCreateDto)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(403);
    }

    @Test
    public void createSubject_AlreadyAssociated_returnStatus409() {
        ResumeCreateDto resumeCreateDto = new ResumeCreateDto("resumo de calculo", "isso é um resumo de calculo", 4L);

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/resumes")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resumeCreateDto)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(409);
    }

    @Test
    public void findById_withValidId_returnStatus200(){
        ResumeResponseDto responseBody = testClient
                .get()
                .uri("/api/v1/resumes/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResumeResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.id()).isEqualTo(1);

    }//

    @Test
    public void findById_withInvalidId_returnStatus404(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/resumes/0")
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
                .uri("/api/v1/resumes/1")
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
                .uri("/api/v1/resumes/2")
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

        List<ResumeResponseDto> responseBody = testClient
                .get()
                .uri("/api/v1/resumes")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ResumeResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        //org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(4);
    }//

    @Test
    public void findAll_withoutPermission_returnStatus403(){

        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/resumes")
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
                .uri("/api/v1/resumes")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }//

}
