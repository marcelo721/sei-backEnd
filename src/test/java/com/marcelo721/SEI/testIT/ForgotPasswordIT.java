package com.marcelo721.SEI.testIT;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.services.ForgotPasswordService;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.web.dto.forgotPasswordDto.OtpDto;
import com.marcelo721.SEI.web.dto.forgotPasswordDto.ResetPasswordDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ForgotPasswordIT {

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private UserService userService;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Test
    public void verifyMail_withValidMail_returns200() {

        String response = testClient
                .post()
                .uri("/api/v1/forgot-password/verifyMail/marcelo@alu.ufc.br")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response).isEqualTo("OTP sent");
    }

    @Test
    public void verifyMail_withInvalidMail_returns404() {
        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/forgot-password/verifyMail/test@alu.ufc.br")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void verifyOtp_withValidOtp_returns200() {

        forgotPasswordService.verifyEmail("marcelo@alu.ufc.br");
        User user = userService.findByEmail("marcelo@alu.ufc.br");

        OtpDto otpDto = new OtpDto(user.getForgotPassword().getOtp());

        String response = testClient
                .post()
                .uri("/api/v1/forgot-password/verifyOtp/marcelo@alu.ufc.br")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(otpDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response).isEqualTo("OTP verified");
    }

    @Test
    public void verifyOtp_withInvalidOtp_returns200() {

        forgotPasswordService.verifyEmail("marcelo@alu.ufc.br");
        User user = userService.findByEmail("marcelo@alu.ufc.br");

        OtpDto otpDto = new OtpDto(user.getForgotPassword().getOtp());

        OtpDto wrongOtp = new OtpDto(user.getForgotPassword().getOtp() + 1);


        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/forgot-password/verifyOtp/marcelo@alu.ufc.br")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(wrongOtp)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void resetPassword_withValidPassword_returns200() {

        forgotPasswordService.verifyEmail("marcelo@alu.ufc.br");
        User user = userService.findByEmail("marcelo@alu.ufc.br");

        OtpDto otpDto = new OtpDto(user.getForgotPassword().getOtp());

        forgotPasswordService.verifyOTP(otpDto.OTP(),"marcelo@alu.ufc.br");

        ResetPasswordDto resetPasswordDto = new ResetPasswordDto("M@rcelin12121", "M@rcelin12121");
        String response = testClient
                .post()
                .uri("/api/v1/forgot-password/changePassword/marcelo@alu.ufc.br")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resetPasswordDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response).isEqualTo("password has been changed");
    }

    @Test
    public void resetPassword_withInvalidPassword_returns422() {

        forgotPasswordService.verifyEmail("marcelo@alu.ufc.br");
        User user = userService.findByEmail("marcelo@alu.ufc.br");

        OtpDto otpDto = new OtpDto(user.getForgotPassword().getOtp());

        forgotPasswordService.verifyOTP(otpDto.OTP(),"marcelo@alu.ufc.br");

        ResetPasswordDto resetPasswordDto = new ResetPasswordDto("M@qwqw", "M@rcelin12121");
        ErrorMessage response = testClient
                .post()
                .uri("/api/v1/forgot-password/changePassword/marcelo@alu.ufc.br")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resetPasswordDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        resetPasswordDto = new ResetPasswordDto("", "M@rcelin12121");
        response = testClient
                .post()
                .uri("/api/v1/forgot-password/changePassword/marcelo@alu.ufc.br")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(resetPasswordDto)
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);
    }
}
