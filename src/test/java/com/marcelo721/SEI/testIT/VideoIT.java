package com.marcelo721.SEI.testIT;

import com.marcelo721.SEI.web.dto.VideoDto.VideoCreateDto;
import com.marcelo721.SEI.web.dto.VideoDto.VideoResponseDto;
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
public class VideoIT {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void creatVideo_withValidData_returnStatus201() {
        VideoCreateDto videoCreateDto = new VideoCreateDto("linkDovideo", 1L);

        testClient
                .post()
                .uri("/api/v1/videos")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(videoCreateDto)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void creatVideo_withInvalidData_returnStatus422() {
        VideoCreateDto videoCreateDto = new VideoCreateDto("", 1L);

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/videos")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        videoCreateDto = new VideoCreateDto("linkdovideo", null);

        responseBody = testClient
                .post()
                .uri("/api/v1/videos")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

    }

    @Test
    public void creatVideo_notAuthenticated_returnStatus401() {
        VideoCreateDto videoCreateDto = new VideoCreateDto("linkdovideo", 1L);

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/videos")
                .exchange()
                .expectStatus().isEqualTo(401)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(401);
    }

    @Test
    public void creatVideo_withoutPermissions_returnStatus403() {
        VideoCreateDto videoCreateDto = new VideoCreateDto("https://www.youtube.com/watch?v=kL0eDQ-w_5s&t=3873s", 1L);

        ErrorMessage responseBody = testClient
                .post()
                .uri("/api/v1/videos")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelo@alu.ufc.br", "M@rcelo222"))
                .bodyValue(videoCreateDto)
                .exchange()
                .expectStatus().isEqualTo(403)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }

    @Test
    public void findById_withValidId_returnStatus200(){
        VideoResponseDto responseBody = testClient
                .get()
                .uri("/api/v1/videos/1")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(VideoResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.id()).isEqualTo(1);

    }//

    @Test
    public void findById_withInvalidId_returnStatus404(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/videos/0")
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
                .uri("/api/v1/videos/1")
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
                .uri("/api/v1/videos/2")
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

        List<VideoResponseDto> responseBody = testClient
                .get()
                .uri("/api/v1/videos")
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "marcelin@alu.ufc.br", "M@rcelo222"))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(VideoResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
    }//

    @Test
    public void findAll_withoutPermission_returnStatus403(){

        ErrorMessage responseBody = testClient
                .get()
                .uri("/api/v1/videos")
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
