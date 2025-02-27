package com.marcelo721.SEI.testIT;

import com.marcelo721.SEI.entities.enums.Course;
import com.marcelo721.SEI.web.dto.UserDto.UpdatePasswordDto;
import com.marcelo721.SEI.web.dto.UserDto.UserCreateDto;
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
public class UserIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void createUser_withValidData_returnStatus201(){

        UserCreateDto userCreateDto = UserCreateDto.builder()
                .name("joao henrique de sousa")
                .password("M@rcelinho121")
                .email("marcelo@gmail.com")
                .course(Course.COMPUTER_ENGINEERING)
                .build();

        testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    public void createUser_withInvalidData_returnStatus422(){

        //test with empty name
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .name("")
                .password("12345678")
                .email("marcelo@gmail.com").build();

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        //test with empty password
        userCreateDto = UserCreateDto.builder()
                .name("Marcelo henrique de sousa")
                .password("")
                .email("marcelo@gmail.com").build();

        response = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        //test with empty email
        userCreateDto = UserCreateDto.builder()
                .name("Marcelo henrique de sousa")
                .password("12345678")
                .email("").build();

        response = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        //test with invalid password
        userCreateDto = UserCreateDto.builder()
                .name("Marcelo henrique de sousa")
                .password("1234")
                .email("marcelo@gmail.com").build();

        response = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        //test with invalid password
        userCreateDto = UserCreateDto.builder()
                .name("Marcelo henrique de sousa")
                .password("1234567890")
                .email("marcelo@gmail.com").build();

        response = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);
    }//

    @Test
    public void createUser_withAlreadyEmailRegistered_returnStatus409(){
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .name("Marcelo henrique de sousa")
                .password("M@rcelinho121")
                .email("marcelo@alu.ufc.br")
                .course(Course.COMPUTER_ENGINEERING).build();

        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userCreateDto)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(409);
    }//

    @Test
    public void updatePassword_withValidData_returnStatus200(){

        UpdatePasswordDto userPasswordDto = UpdatePasswordDto.builder()
                .pastPassword("M@rcelo222")
                .newPassword("M@rcelinho212")
                .confirmPassword("M@rcelinho212")
                .build();

        testClient
                .put()
                .uri("/api/v1/users/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userPasswordDto)
                .exchange()
                .expectStatus().isNoContent();
    }//

    @Test
    public void updatePassword_withInvalidData_returnStatus422(){

        UpdatePasswordDto userPasswordDto = UpdatePasswordDto.builder()
                .pastPassword("12345678")
                .newPassword("123")
                .confirmPassword("123")
                .build();

        ErrorMessage response = testClient
                .put()
                .uri("/api/v1/users/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userPasswordDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        userPasswordDto = UpdatePasswordDto.builder()
                .pastPassword("")
                .newPassword("87654321")
                .confirmPassword("87654321")
                .build();

        response = testClient
                .put()
                .uri("/api/v1/users/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userPasswordDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        userPasswordDto = UpdatePasswordDto.builder()
                .pastPassword("12345678")
                .newPassword("123456789")
                .confirmPassword("123456789")
                .build();

        response = testClient
                .put()
                .uri("/api/v1/users/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userPasswordDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        userPasswordDto = UpdatePasswordDto.builder()
                .pastPassword("12345678")
                .newPassword("12345678")
                .confirmPassword("87654321")
                .build();

        response = testClient
                .put()
                .uri("/api/v1/users/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userPasswordDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);
    }//

    @Test
    public void updatePassword_NotAuthenticated_returnStatus401(){
        UpdatePasswordDto userPasswordDto = UpdatePasswordDto.builder()
                .pastPassword("12345678")
                .newPassword("123")
                .confirmPassword("123")
                .build();

        ErrorMessage response = testClient
                .put()
                .uri("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userPasswordDto)
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(401);
    }//

    @Test
    public void findById_withValidId_returnStatus200(){
        UserResponseDto responseBody = testClient
                .get()
                .uri("/api/v1/users/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.id()).isEqualTo(1);
        org.assertj.core.api.Assertions.assertThat(responseBody.email()).isEqualTo("marcelo@alu.ufc.br");
        org.assertj.core.api.Assertions.assertThat(responseBody.name()).isEqualTo("marcelinho22");
        org.assertj.core.api.Assertions.assertThat(responseBody.course()).isEqualTo("COMPUTER_ENGINEERING");

    }//

    @Test
    public void findById_withInvalidId_returnStatus404(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/users/0")
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
                .uri("/api/v1/users/1")
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
                .uri("/api/v1/users/2")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }//

    @Test
    public void findAllUsers_withoutParameters_returnStatus200(){

        List<UserResponseDto> responseBody = testClient
                .get()
                .uri("/api/v1/users")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(1);
    }//

    @Test
    public void findAllUsers_notAuthenticated_returnStatus401(){

        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/users")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }//

    @Test
    public void findAllUsers_withoutPermission_returnStatus403(){

        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/users")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }//

    @Test
    public void addSubject_withValidId_returnStatus200(){
         testClient
                .post()
                .uri("/api/v1/users/1/subjects/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

    }

    @Test
    public void addSubject_withInvalidId_returnStatus404(){
        ErrorMessage responseBody =  testClient
                .post()
                .uri("/api/v1/users/1/subjects/0")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);

    }

    @Test
    public void addSubject_notAuthenticated_returnStatus401(){
        ErrorMessage responseBody =  testClient
                .post()
                .uri("/api/v1/users/1/subjects/0")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);

    }

    @Test
    public void addSubject_withoutPermission_returnStatus403(){
        ErrorMessage responseBody =  testClient
                .post()
                .uri("/api/v1/users/2/subjects/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }

    @Test
    public void removeSubject_withValidId_returnStatus200(){
        testClient
                .delete()
                .uri("/api/v1/users/1/subjects/2")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();
    }

    @Test
    public void removeSubject_withInvalidId_returnStatus404(){
        ErrorMessage responseBody =  testClient
                .delete()
                .uri("/api/v1/users/1/subjects/0")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }

    @Test
    public void removeSubject_notAuthenticated_returnStatus401(){
        ErrorMessage responseBody =  testClient
                .delete()
                .uri("/api/v1/users/1/subjects/0")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);

    }

    @Test
    public void removeSubject_withoutPermission_returnStatus403(){
        ErrorMessage responseBody =  testClient
                .post()
                .uri("/api/v1/users/2/subjects/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }

    @Test
    public void getUsrData_withoutParams_returnStatus200(){
        UserResponseDto responseBody =  testClient
                .get()
                .uri("/api/v1/users/me")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.course()).isEqualTo("COMPUTER_ENGINEERING");
        org.assertj.core.api.Assertions.assertThat(responseBody.email()).isEqualTo("marcelo@alu.ufc.br");
    }

    @Test
    public void getUsrData_notAuthenticated_returnStatus401(){
        ErrorMessage responseBody =  testClient
                .get()
                .uri("/api/v1/users/me")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);

    }
}
