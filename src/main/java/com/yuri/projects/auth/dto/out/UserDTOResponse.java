package com.yuri.projects.auth.dto.out;

import com.yuri.projects.auth.models.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTOResponse implements Serializable {

    private String username;
    private String email;

    public static UserDTOResponse from(final User user) {
        final UserDTOResponse userDTO = new UserDTOResponse();

        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());

        return userDTO;
    }

}
