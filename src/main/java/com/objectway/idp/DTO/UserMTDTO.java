package com.objectway.idp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserMTDTO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public UserMTDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
