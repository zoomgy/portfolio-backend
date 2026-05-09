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

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Bucket loginBucket = Bucket.builder()
            .addLimit(Bandwidth.classic(5, io.github.bucket4j.Refill.intervally(5, Duration.ofMinutes(1))))
            .build();

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(
            @RequestBody User user
    ) throws InvalidPasswordException, InvalidEmailException {

        if (!loginBucket.tryConsume(1)) {
            return new ResponseEntity<>(
                CustomResponseBuilder.buildError(
                    "Too many login attempts. Try again later.",
                    HttpStatus.TOO_MANY_REQUESTS.value()
                ),
                HttpStatus.TOO_MANY_REQUESTS
            );
        }

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

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(
            @Valid @RequestBody User user
    ) {
        return new ResponseEntity<>(
                CustomResponseBuilder.buildError(
                        "Registration is disabled",
                        HttpStatus.FORBIDDEN.value()
                ),
                HttpStatus.FORBIDDEN
        );
    }
}