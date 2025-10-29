package com.example.ac2backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetorRequestDTO {
    private Integer id;
    private String nome;
    private Integer idFuncionario;
}