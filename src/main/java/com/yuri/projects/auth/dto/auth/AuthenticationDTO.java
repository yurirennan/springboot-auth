package com.yuri.projects.auth.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationDTO {

    @NotBlank(message = "O campo username não pode ser vazio!")
    private String username;

    @NotBlank(message = "O campo senha não pode ser vazio!")
    @Size(min = 8, max = 16, message = "A senha deve possuir no mínimo 8 e no máximo 16 caracteres!")
    private String password;

}
