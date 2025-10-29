package com.example.ac2backend.Services;

import java.util.List;

import com.example.ac2backend.dtos.ProjetoDTO;
import com.example.ac2backend.dtos.ProjetoRequestDTO;

public interface ProjetoService {
    void salvar(ProjetoRequestDTO projetoRequestDTO);
    void editar(Integer id, ProjetoRequestDTO projetoRequestDTO);
    void remover(Integer id);
    ProjetoDTO obterPorId(Integer id);
    List<ProjetoDTO> obterTodos();
    void vincularFuncionario(Integer idProjeto, Integer idFuncionario);
}
