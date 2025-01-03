package com.marcelo721.SEI.services;

import com.marcelo721.SEI.entities.User;
import com.marcelo721.SEI.jwt.JwtToken;
import com.marcelo721.SEI.jwt.JwtUtils;
import com.marcelo721.SEI.web.dto.UserDto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByEmail(username);
    }
    public JwtToken getTokenAuthenticated(UserLoginDto userLoginDto){
        User user = userService.findByEmail(userLoginDto.email());
        return JwtUtils.createToken(user);
    }
}
