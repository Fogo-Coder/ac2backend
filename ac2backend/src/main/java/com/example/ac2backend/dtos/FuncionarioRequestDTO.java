package com.example.ac2backend.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioRequestDTO {
    private Integer id;
    private String nome;
    private Integer idSetor;
    private List<Integer> idsProjetos;
}