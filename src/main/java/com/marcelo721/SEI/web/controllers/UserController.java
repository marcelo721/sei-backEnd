package com.marcelo721.SEI.web.controllers;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.web.dto.UserDto.UserCreateDto;
import com.marcelo721.SEI.web.dto.UserDto.UserResponseDto;
import com.marcelo721.SEI.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Tag(name = "users", description = "contains all user resources")
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
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateDto user) {
        User obj = user.toUser();
        userService.save(obj);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponseDto.toDto(obj));
    }

    @Operation(
            summary = "find User by id", description = "resource to find User by id ",
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
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #id == authentication.principal.id)")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        User obj = userService.findById(id);
        return ResponseEntity.ok(UserResponseDto.toDto(obj));
    }

    @Operation(
            summary = "Find all users", description = "Resource to find all users",
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(UserResponseDto.toListDto(users));
    }

    @Operation(
            summary = "add new subject", description = "This feature will add a new subject to the user's subject list.",
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
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('STUDENT') AND #id == authentication.principal.id)")
    public ResponseEntity<User> addSubject(@PathVariable Long userId, @PathVariable Long subjectId) {
        User student = userService.addSubject(userId, subjectId);
        return ResponseEntity.ok(student);
    }

}
