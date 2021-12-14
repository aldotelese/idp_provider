package com.objectway.idp.model;

import com.objectway.idp.DTO.UserMTDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Accessors(chain = true)
@Document("users_mt")
@NoArgsConstructor
public class UserMT {

    @Id
    private String id;
    @NotEmpty
    @Indexed(unique=true)
    private String username;
    @NotEmpty
    private String password;

    private List<String> tokens;

    public UserMT(UserMTDTO userDTO){
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
    }
}
