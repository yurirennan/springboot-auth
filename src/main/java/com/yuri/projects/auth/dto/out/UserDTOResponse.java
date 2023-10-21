package com.yuri.projects.auth.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yuri.projects.auth.models.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTOResponse implements Serializable {

    private String username;
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    public static UserDTOResponse from(final User user) {
        final UserDTOResponse userDTO = new UserDTOResponse();

        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());

        return userDTO;
    }

}
