package com.zoomgy.portfolio.controller;

import com.zoomgy.portfolio.entity.User;
import com.zoomgy.portfolio.exception.UserNotFoundException;
import com.zoomgy.portfolio.service.UserService;
import com.zoomgy.portfolio.utils.CustomResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllUsers(){
        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        userService.getAllUsers(),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserProfile(
            @PathVariable Long id
    ) throws UserNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        userService.getUserById(id),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUserProfile(
            @PathVariable Long id,
            @Valid @RequestBody User user
    ) throws UserNotFoundException {

        return new ResponseEntity<>(
                CustomResponseBuilder.buildResponse(
                        userService.updateUser(id, user),
                        HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );
    }
}