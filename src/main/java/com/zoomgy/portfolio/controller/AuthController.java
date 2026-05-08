package com.zoomgy.portfolio.controller;

import com.zoomgy.portfolio.dto.UserDTO;
import com.zoomgy.portfolio.entity.User;
import com.zoomgy.portfolio.exception.InvalidEmailException;
import com.zoomgy.portfolio.exception.InvalidPasswordException;
import com.zoomgy.portfolio.security.JwtUtil;
import com.zoomgy.portfolio.service.UserService;
import com.zoomgy.portfolio.utils.CustomResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(
            @Valid @RequestBody User user
    ) {
        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        userService.createUser(user),
                        HttpStatus.CREATED.value()
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(
            @RequestBody User user
    ) throws InvalidPasswordException, InvalidEmailException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        userService.loginUser(
                                user.getEmail(),
                                user.getPassword()
                        ),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }
}