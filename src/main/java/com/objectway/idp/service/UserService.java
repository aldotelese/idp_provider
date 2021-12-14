package com.objectway.idp.service;

import com.objectway.idp.model.User;
import com.objectway.idp.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String generateToken() {
        String token = RandomStringUtils.randomAlphanumeric(20);
        return token;
    }

    public User checkLoginCredentials(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        String dbPassword = user.getPassword();
        if (password.equals(dbPassword)) {
            return user;
        } else {
            return null;
        }
    }

    public String addToken(User user) {
        String token = generateToken();
        List<String> userTokens = user.getTokens();
        if (userTokens == null) {
            user.setTokens(new ArrayList<>());
        }
        user.getTokens().add(token);
        userRepository.save(user);
        return token;
    }

}








