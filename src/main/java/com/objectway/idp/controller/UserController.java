package com.objectway.idp.controller;

import com.objectway.idp.DTO.GeneratedTokenResponse;
import com.objectway.idp.DTO.UsernameFromTokenResponse;
import com.objectway.idp.model.User;
import com.objectway.idp.repository.UserRepository;
import com.objectway.idp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneratedTokenResponse> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (username == null || password == null || body.size() > 2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userService.checkLoginCredentials(username, password);
        if (user != null) {
            String generatedToken = userService.addToken(user);
            return ResponseEntity.ok(new GeneratedTokenResponse(generatedToken));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/check/{token}")
    public ResponseEntity<UsernameFromTokenResponse> getUserFromToken(@PathVariable String token) {
        User user = userRepository.findUserByToken(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new UsernameFromTokenResponse(user.getUsername()));
    }

}