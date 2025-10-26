package com.example.ac2backend.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2backend.Models.Setor;
import com.example.ac2backend.Repositories.SetorRepository;
import com.example.ac2backend.dtos.RegraNegocioException;
import com.example.ac2backend.dtos.SetorDTO;
import com.example.ac2backend.dtos.SetorRequestDTO;

@Service
public class SetorServiceImp implements SetorService {
private SetorRepository setorRepository;

    public SetorServiceImp(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    public void salvar(SetorRequestDTO setorRequestDTO) {
        Setor setor = new Setor();
        setor.setNome(setorRequestDTO.getNome());
        setorRepository.save(setor);
    }

    @Override
    public SetorDTO obterPorId(Integer id) {
        return setorRepository.findById(id)
            .map(setor -> SetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .build())
            .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));
    }

    @Override
    public void editar(Integer id, SetorRequestDTO setorRequestDTO) {
        Setor setor = setorRepository.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        setor.setNome(setorRequestDTO.getNome());
        setorRepository.save(setor);
    }

    @Override
    public void remover(Integer id) {
        if (!setorRepository.existsById(id)) {
            throw new RegraNegocioException("Setor não encontrado");
        }
        setorRepository.deleteById(id);
    }

    @Override
    public List<SetorDTO> obterTodos() {
        return setorRepository.findAll().stream()
            .map(s -> SetorDTO.builder()
                .id(s.getId())
                .nome(s.getNome())
                .build())
            .collect(Collectors.toList());
    }  
}

