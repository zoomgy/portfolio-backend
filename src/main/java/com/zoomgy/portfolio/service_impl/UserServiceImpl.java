package com.zoomgy.portfolio.service_impl;

import com.zoomgy.portfolio.dto.UserDTO;
import com.zoomgy.portfolio.entity.User;
import com.zoomgy.portfolio.exception.InvalidEmailException;
import com.zoomgy.portfolio.exception.InvalidPasswordException;
import com.zoomgy.portfolio.exception.UserNotFoundException;
import com.zoomgy.portfolio.repository.UserRepository;
import com.zoomgy.portfolio.security.JwtUtil;
import com.zoomgy.portfolio.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDTO createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        User createdUser = userRepository.save(user);
        UserDTO payload = new UserDTO();
        payload.setName(createdUser.getName());
        payload.setEmail(createdUser.getEmail());
        payload.setCreatedAt(createdUser.getCreatedAt());
        return payload;
    }

    @Override
    public UserDTO getUserByEmailId(String email) throws UserNotFoundException {
        User createdUser = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Email Not Found"));
        UserDTO payload = new UserDTO();
        payload.setName(createdUser.getName());
        payload.setEmail(createdUser.getEmail());
        payload.setCreatedAt(createdUser.getCreatedAt());
        return payload;
    }

    @Override
    public UserDTO getUserById(Long id) throws UserNotFoundException {
        User createdUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        UserDTO payload = new UserDTO();
        payload.setName(createdUser.getName());
        payload.setEmail(createdUser.getEmail());
        payload.setCreatedAt(createdUser.getCreatedAt());
        return payload;
    }

    @Override
    public UserDTO updateUser(Long id, User user)
            throws UserNotFoundException {
        User u = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found"
                        ));
        if (user.getName() != null &&
                !user.getName().isBlank()) {
            u.setName(user.getName());
        }
        if (user.getEmail() != null &&
                !user.getEmail().isBlank()) {

            u.setEmail(user.getEmail());
        }
        if (user.getPassword() != null &&
                !user.getPassword().isBlank()) {
            u.setPassword(
                    passwordEncoder.encode(
                            user.getPassword()
                    )
            );
        }
        u.setUpdatedAt(LocalDateTime.now());
        User createdUser = userRepository.save(u);
        UserDTO payload = new UserDTO();
        payload.setName(createdUser.getName());
        payload.setEmail(createdUser.getEmail());
        payload.setCreatedAt(createdUser.getCreatedAt());
        return payload;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String loginUser(String email, String password) throws InvalidPasswordException, InvalidEmailException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new InvalidEmailException("Invalid Email"));
        boolean isMatch = passwordEncoder.matches(
                password,
                user.getPassword()
        );
        if (!isMatch) {
            throw new InvalidPasswordException("Invalid Password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail()
        );

        return token;
    }
}
