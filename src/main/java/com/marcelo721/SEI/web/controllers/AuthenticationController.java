package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.jwt.JwtToken;
import com.marcelo721.SEI.repositories.UserRepository;
import com.marcelo721.SEI.services.AuthenticationService;
import com.marcelo721.SEI.web.dto.UserDto.UserLoginDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/login")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "resource to proceed with API authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Operation(
            summary = "authenticate to api",
            description = "authentication feature",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = " authentication created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtToken.class))),

                    @ApiResponse(responseCode = "400",
                            description = "Invalid credentials",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "422",
                            description = "invalid  data",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping
    public ResponseEntity<?> auth(@RequestBody @Valid UserLoginDto userLoginDto, HttpServletRequest request){

        User user = userRepository.findByEmail(userLoginDto.email()).get();


        try {
            var userAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.email(), userLoginDto.password());
            authenticationManager.authenticate(userAuthenticationToken);

            JwtToken token = authenticationService.getTokenAuthenticated(userLoginDto);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e){
            log.warn("Bad credentials");
        }
        return ResponseEntity.badRequest().body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Invalid Credentials"));
    }
}
