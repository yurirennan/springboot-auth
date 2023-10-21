package com.yuri.projects.auth.dto;

import com.yuri.projects.auth.models.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String username;
    private String email;
    private String password;

    public User toUser() {
        final User user = new User();

        user.setUsername(this.getUsername());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());

        return user;
    }

}
