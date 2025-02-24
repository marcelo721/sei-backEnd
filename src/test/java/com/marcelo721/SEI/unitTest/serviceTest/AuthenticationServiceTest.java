package com.marcelo721.SEI.unitTest.serviceTest;


import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.entities.enums.StatusAccount;
import com.marcelo721.SEI.jwt.JwtToken;
import com.marcelo721.SEI.jwt.JwtUtils;
import com.marcelo721.SEI.services.AuthenticationService;
import com.marcelo721.SEI.services.UserService;
import com.marcelo721.SEI.services.exceptions.AccountNotEnabledException;
import com.marcelo721.SEI.web.dto.UserDto.UserLoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationService authenticationService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setStatusAccount(StatusAccount.ENABLED);
    }

    @Test
    void testLoadUserByUsernameSuccess() {
        when(userService.findByEmail("test@example.com")).thenReturn(user);
        assertNotNull(authenticationService.loadUserByUsername("test@example.com"));
    }

    @Test
    void testLoadUserByUsernameNotFound() {
        when(userService.findByEmail("unknown@example.com")).thenThrow(new UsernameNotFoundException("User not found"));
        assertThrows(UsernameNotFoundException.class, () -> authenticationService.loadUserByUsername("unknown@example.com"));
    }

    @Test
    void testGetTokenAuthenticatedSuccess() {
        UserLoginDto loginDto = new UserLoginDto("test@example.com", "password");
        when(userService.findByEmail("test@example.com")).thenReturn(user);
        when(JwtUtils.createToken(user)).thenReturn(new JwtToken("mockToken"));

        JwtToken token = authenticationService.getTokenAuthenticated(loginDto);
        assertNotNull(token);
        assertEquals("mockToken", token.getToken());
    }

    @Test
    void testGetTokenAuthenticatedAccountDisabled() {
        UserLoginDto loginDto = new UserLoginDto("test@example.com", "password");
        user.setStatusAccount(StatusAccount.DISABLED);
        when(userService.findByEmail("test@example.com")).thenReturn(user);

        assertThrows(AccountNotEnabledException.class, () -> authenticationService.getTokenAuthenticated(loginDto));
    }
}

