package com.yuri.projects.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuri.projects.auth.models.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String username;
    private String email;
    private String password;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    public User toUser() {
        final User user = new User();

        user.setUsername(this.getUsername());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());

        return user;
    }

}
