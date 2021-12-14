package com.objectway.idp.controller;

import com.objectway.idp.DTO.GeneratedTokenResponse;
import com.objectway.idp.DTO.UserMTDTO;
import com.objectway.idp.DTO.UsernameFromTokenResponse;
import com.objectway.idp.model.User;
import com.objectway.idp.model.UserMT;
import com.objectway.idp.repository.UserMTRepository;
import com.objectway.idp.repository.UserRepository;
import com.objectway.idp.service.UserService;
import com.objectway.idp.service.mt.UserMTServiceImpl;
import com.objectway.idp.service.mt.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("mt")
public class UserMTController {
    private UserServiceInterface userMTService;
    private UserMTRepository userMTRepository;

    public UserMTController(UserMTServiceImpl userMTServiceImpl, UserMTRepository userMTRepository) {
        this.userMTService = userMTServiceImpl;
        this.userMTRepository = userMTRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<GeneratedTokenResponse> login(@RequestBody UserMTDTO body) {
        UserMT user = userMTService.checkLoginCredentials(body);
        if (user != null) {
            String generatedToken = userMTService.addToken(user);
            return ResponseEntity.ok(new GeneratedTokenResponse(generatedToken));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/check/{token}")
    public ResponseEntity<UsernameFromTokenResponse> getUserFromToken(@PathVariable String token) {
        UserMT user = userMTRepository.findUserByToken(token);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new UsernameFromTokenResponse(user.getUsername()));
    }
}
