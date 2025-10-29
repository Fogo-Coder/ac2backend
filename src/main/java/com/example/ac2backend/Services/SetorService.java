package com.example.ac2backend.Services;

import java.util.List;

import com.example.ac2backend.dtos.SetorDTO;
import com.example.ac2backend.dtos.SetorRequestDTO;

public interface SetorService {
    void salvar(SetorRequestDTO setorRequestDTO);

    void remover(Integer id);

    void editar(Integer id, SetorRequestDTO dto);

    SetorDTO obterPorId(Integer id);

    List<SetorDTO> obterTodos();

}