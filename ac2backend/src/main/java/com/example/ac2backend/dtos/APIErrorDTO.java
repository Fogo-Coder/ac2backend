package com.example.ac2backend.dtos;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class APIErrorDTO {
    @Getter
    private List<String> errors;

    public APIErrorDTO (String mensagem){
        this.errors = Arrays.asList(mensagem);
    }
}