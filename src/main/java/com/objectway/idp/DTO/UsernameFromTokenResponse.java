package com.objectway.idp.DTO;

public class UsernameFromTokenResponse {
    private String username;

    public UsernameFromTokenResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
