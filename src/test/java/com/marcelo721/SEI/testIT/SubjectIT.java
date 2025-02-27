package com.marcelo721.SEI.testIT;

import com.marcelo721.SEI.entities.enums.Semester;
import com.marcelo721.SEI.web.dto.SubjectDto.SubjectCreateDto;
import com.marcelo721.SEI.web.dto.SubjectDto.SubjectResponseDto;
import com.marcelo721.SEI.web.dto.UserDto.UserResponseDto;
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
public class SubjectIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void createSubject_withValidData_returnStatus201() {

        SubjectCreateDto subjectCreateDto = SubjectCreateDto.builder()
                .name("Cálculo")
                .semester(Semester.FIRST)
                .build();

        testClient
                .post()
                .uri("/api/v1/subjects")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subjectCreateDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void createSubject_withInvalidData_returnStatus422() {

        SubjectCreateDto subjectCreateDto = SubjectCreateDto.builder()
                .name("")
                .semester(Semester.FIRST)
                .build();

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/subjects")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subjectCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        subjectCreateDto = SubjectCreateDto.builder()
                .name("Cálculo1")
                .semester(null)
                .build();

        response = testClient
                .post()
                .uri("/api/v1/subjects")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subjectCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);


    }

    @Test
    public void crateSubject_notAuthenticated_returnStatus401() {
        SubjectCreateDto subjectCreateDto = SubjectCreateDto.builder()
                .name("Fisica 2")
                .semester(Semester.FIRST)
                .build();

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/subjects")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subjectCreateDto)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(401);
    }

    @Test
    public void createSubject_withoutPermissions_returnStatus403() {
        SubjectCreateDto subjectCreateDto = SubjectCreateDto.builder()
                .name("Fisica 2")
                .semester(Semester.FIRST)
                .build();

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/subjects")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(subjectCreateDto)
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(403);
    }

    @Test
    public void findById_withValidId_returnStatus200(){
        SubjectResponseDto responseBody = testClient
                .get()
                .uri("/api/v1/subjects/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(SubjectResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.id()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.name()).isEqualTo("Cálculo I");

    }//

    @Test
    public void findById_withInvalidId_returnStatus404(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/subjects/0")
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
                .uri("/api/v1/subjects/1")
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
                .uri("/api/v1/subjects/2")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }//

    @Test
    public void findAllSubjects_withoutParameters_returnStatus200(){

        List<SubjectResponseDto> responseBody = testClient
                .get()
                .uri("/api/v1/subjects")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SubjectResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(2);
    }//

    @Test
    public void findAllSubjects_notAuthenticated_returnStatus401(){

        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/subjects")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }//

    @Test
    public void findAllSubjectsByUserId_withoutParameters_returnStatus200(){
        List<SubjectResponseDto> responseBody = testClient
                .get()
                .uri("/api/v1/subjects/idUser/2")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SubjectResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(1);
    }

    @Test
    public void findAllSubjectsByUserId_withoutPermission_returnStatus404(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/subjects/idUser/0")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }

    @Test
    public void findAllSubjectsByUserId_notAuthenticated_returnStatus403(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/subjects/idUser/1")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }

    @Test
    public void findAllSubjectsBySemester_withoutParameters_returnStatus200(){
        List<SubjectResponseDto> responseBody = testClient
                .get()
                .uri("/api/v1/subjects//by-semester/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SubjectResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(2);
    }

    @Test
    public void findAllSubjectsBySemester_notAuthenticated_returnStatus403(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/subjects/by-semester/1")
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }
}
