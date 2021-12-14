package com.objectway.idp.service.mt;

import com.objectway.idp.DTO.UserMTDTO;
import com.objectway.idp.model.User;
import com.objectway.idp.model.UserMT;
import com.objectway.idp.repository.UserMTRepository;
import com.objectway.idp.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMTServiceImpl implements UserServiceInterface {
    @Autowired
    private UserMTRepository userMTRepository;

    public String generateToken() {
        String token = RandomStringUtils.randomAlphanumeric(20);
        return token;
    }

    public UserMT checkLoginCredentials(UserMTDTO userMTDTO) {
        UserMT user = userMTRepository.findUserByUsername(userMTDTO.getUsername());
        if (user == null) {
            return null;
        }
        if (userMTDTO.getPassword().equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    public String addToken(UserMT user) {
        String token = generateToken();
        List<String> userTokens = user.getTokens();
        if (userTokens == null) {
            user.setTokens(new ArrayList<>());
        }
        user.getTokens().add(token);
        userMTRepository.save(user);
        return token;
    }
}
