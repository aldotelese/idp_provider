package com.objectway.idp.service.mt;

import com.objectway.idp.DTO.UserMTDTO;
import com.objectway.idp.model.UserMT;

public interface UserServiceInterface {
    String generateToken();
    UserMT checkLoginCredentials(UserMTDTO userMTDTO);
    String addToken(UserMT user);
}
