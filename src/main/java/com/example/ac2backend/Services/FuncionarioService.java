package com.example.ac2backend.Services;

import java.util.List;

import com.example.ac2backend.dtos.FuncionarioDTO;
import com.example.ac2backend.dtos.FuncionarioRequestDTO;

public interface FuncionarioService {

    void salvar(FuncionarioRequestDTO funcionarioRequestDTO);

    void remover(Integer id);

    void editar(Integer id, FuncionarioRequestDTO dto);

    FuncionarioDTO obterPorId(Integer id);

    List<FuncionarioDTO> obterTodos();
}