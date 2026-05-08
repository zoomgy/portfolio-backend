package com.zoomgy.portfolio.service;

import com.zoomgy.portfolio.dto.UserDTO;
import com.zoomgy.portfolio.entity.User;
import com.zoomgy.portfolio.exception.InvalidEmailException;
import com.zoomgy.portfolio.exception.InvalidPasswordException;
import com.zoomgy.portfolio.exception.UserNotFoundException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmailId(String email) throws UserNotFoundException;
    UserDTO getUserById(Long id) throws UserNotFoundException;
    UserDTO updateUser(Long id,User user) throws UserNotFoundException;
    List<User> getAllUsers();
    String loginUser(@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email, @NotBlank(message = "Password is required") @Size(min = 6,
            message = "Password must contain at least 6 characters") String password) throws InvalidPasswordException, InvalidEmailException;
}
