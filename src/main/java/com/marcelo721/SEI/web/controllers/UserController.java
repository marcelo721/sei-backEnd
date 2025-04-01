package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.entities.enums.StatusAccount;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.utils.EmailUtils;
import com.marcelo721.SEI.web.dto.UserDto.UpdatePasswordDto;
import com.marcelo721.SEI.web.dto.UserDto.UserCreateDto;
import com.marcelo721.SEI.web.dto.UserDto.UserResponseDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Tag(name = "users", description = "contains all user resources")
@Slf4j
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "create a new user", description = "resource to create a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "resource created successfully",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "409", description = "User email is already registered",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "422", description = "Invalid Data",
                            content = @Content(mediaType= "application/json", schema = @Schema(implementation = ErrorMessage.class)))

            }
    )
    @PostMapping//tested
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateDto user) {
        User obj = user.toUser();
        userService.save(obj);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "find User by id", description = "resource to find User by id ",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User Found Successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "404", description = "User not found !",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #id == authentication.principal.id)")//tested
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        User obj = userService.findById(id);
        return ResponseEntity.ok(UserResponseDto.toDto(obj));
    }

    @Operation(
            summary = "Find all users", description = "Resource to find all users",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200",description = "List of all registered users",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")//tested
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(UserResponseDto.toListDto(users));
    }

    @Operation(
            summary = "add new subject", description = "This feature will add a new subject to the user's subject list.",
            security = @SecurityRequirement(name = "security"),

            responses = {
                    @ApiResponse(responseCode = "200", description = "subject added",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "404", description = "User not found or subject not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PostMapping("/{userId}/subjects/{subjectId}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #userId == authentication.principal.id)")//tested
    public ResponseEntity<Void> addSubject(@PathVariable Long userId, @PathVariable Long subjectId) {
        User student = userService.addSubject(userId, subjectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "remove subject", description = "This feature will remove a  subject to the user's subject list.",
            security = @SecurityRequirement(name = "security"),

            responses = {
                    @ApiResponse(responseCode = "200", description = "subject removed",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "404", description = "User not found or subject not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @DeleteMapping("/{userId}/subjects/{subjectId}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #userId == authentication.principal.id)")//tested
    public ResponseEntity<Void> removeSubject(@PathVariable Long userId, @PathVariable Long subjectId) {
        userService.removeSubject(userId, subjectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(
            summary = "update password", description = "resource to update password",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "password updated successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),

                    @ApiResponse(responseCode = "404", description = "User Not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "422", description = "Invalid data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #id == authentication.principal.id)")//tested
    public ResponseEntity<Void> updatePassword( @PathVariable Long id,  @Valid @RequestBody UpdatePasswordDto user){

        log.info("Updating password for user with id: {}", id);
        User obj = userService.updatePassword(user.pastPassword(),user.newPassword(), user.confirmPassword(), id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/verify")
    public String verifyCode(@Param("code") String code){

        if (userService.verify(code) == StatusAccount.ENABLED){
            return EmailUtils.verifyEnabledAccount();
        }else if (userService.verify(code) == StatusAccount.ALREADY_ENABLED){
            return EmailUtils.verifyAccountAlreadyEnabled();
        } else {
            return EmailUtils.reportAccountNotEnabled();
        }
    }

    @Operation(
            summary = "resource to get user data", description = "resource to get user data by authentication token ",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User authenticated Successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),

                    @ApiResponse(responseCode = "403",
                            description = "This user does not have permission to access this resource",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(responseCode = "401",
                            description = "This user is not authenticated",
                            content =  @Content(mediaType = "application/json",schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/me")//tested
    public ResponseEntity<UserResponseDto> getUserData(@AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDto user = UserResponseDto.toDto(userService.findByEmail(userDetails.getUsername()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{id}/upload-profile-picture")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #id == authentication.principal.id)")//tested
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
            userService.saveProfilePicture(id, file);
            return ResponseEntity.ok("Imagem salva com sucesso!");
    }


    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #id == authentication.principal.id)")//tested
    @GetMapping("/{id}/profile-picture")
    public ResponseEntity<?> getProfilePicture(@PathVariable Long id) {
        User user = userService.findById(id);

        if (user.getProfilePicture() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(user.getProfilePicture());
    }
}

