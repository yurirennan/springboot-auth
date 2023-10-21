package com.yuri.projects.auth.handlers;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ApiErrors {
    private List<String> errors;

    public ApiErrors(final String error) {
        this.errors = Collections.singletonList(error);
    }

    public ApiErrors(final List<String> errors) {
        this.errors = errors;
    }
}