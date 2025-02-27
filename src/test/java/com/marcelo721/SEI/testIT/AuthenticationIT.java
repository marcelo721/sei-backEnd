package com.marcelo721.SEI.testIT;

import com.marcelo721.SEI.jwt.JwtToken;
import com.marcelo721.SEI.web.dto.UserDto.UserLoginDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AuthenticationIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void authenticationWithValidCredentials_returnStatus200(){

        JwtToken responseBody = testClient
                .post()
                .uri("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserLoginDto("marcelo@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    }

    @Test
    public void authentication_WithInvalidCredentials_returnStatus401(){

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserLoginDto("marcelo@alu.ufc.br", "M@rcelinho232323"))
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);

    }

    @Test void authentication_withInvalidEmail_returnStatus404(){

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserLoginDto("qeq@alu.ufc.br", "M@rcelinho232323"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }

    @Test
    public void authenticationWithInvalidUsernameAndInvalidPassword_returnStatus422(){

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserLoginDto("", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    }
}
