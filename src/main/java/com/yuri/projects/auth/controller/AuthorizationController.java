package com.yuri.projects.auth.controller;

import com.yuri.projects.auth.dto.auth.AuthenticationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthorizationController {

    @PostMapping("/login")
    public void fakeLogin(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

}