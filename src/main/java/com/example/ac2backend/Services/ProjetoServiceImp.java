package com.example.ac2backend.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2backend.Models.Funcionario;
import com.example.ac2backend.Models.Projeto;
import com.example.ac2backend.Repositories.FuncionarioRepository;
import com.example.ac2backend.Repositories.ProjetoRepository;
import com.example.ac2backend.dtos.ProjetoDTO;
import com.example.ac2backend.dtos.ProjetoRequestDTO;
import com.example.ac2backend.dtos.FuncionarioDTO;
import com.example.ac2backend.dtos.RegraNegocioException;

@Service
public class ProjetoServiceImp implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoServiceImp(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void salvar(ProjetoRequestDTO dto) {
        List<Funcionario> funcionarios = funcionarioRepository.findAllById(dto.getIdsFuncionarios());

        Projeto projeto = Projeto.builder()
                .descricao(dto.getDescricao())
                .dataInicio(dto.getDataInicio())
                .dataFim(dto.getDataFim())
                .funcionario(funcionarios)
                .build();

        projetoRepository.save(projeto);
    }

    @Override
    public void editar(Integer id, ProjetoRequestDTO dto) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        List<Funcionario> funcionarios = funcionarioRepository.findAllById(dto.getIdsFuncionarios());

        projeto.setDescricao(dto.getDescricao());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());
        projeto.setFuncionario(funcionarios);

        projetoRepository.save(projeto);
    }

    @Override
    public void remover(Integer id) {
        projetoRepository.deleteById(id);
    }

    @Override
    public ProjetoDTO obterPorId(Integer id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        List<FuncionarioDTO> funcionariosDTO = projeto.getFuncionario().stream()
                .map(f -> FuncionarioDTO.builder()
                        .id(f.getId())
                        .nome(f.getNome())
                        .build())
                .collect(Collectors.toList());

        return ProjetoDTO.builder()
                .id(projeto.getId())
                .descricao(projeto.getDescricao())
                .dataInicio(projeto.getDataInicio())
                .dataFim(projeto.getDataFim())
                .funcionarios(funcionariosDTO)
                .build();
    }

    @Override
    public List<ProjetoDTO> obterTodos() {
        return projetoRepository.findAll().stream().map(proj -> {
            List<FuncionarioDTO> funcionariosDTO = proj.getFuncionario().stream()
                    .map(f -> FuncionarioDTO.builder().id(f.getId()).nome(f.getNome()).build())
                    .collect(Collectors.toList());
            return ProjetoDTO.builder()
                    .id(proj.getId())
                    .descricao(proj.getDescricao())
                    .dataInicio(proj.getDataInicio())
                    .dataFim(proj.getDataFim())
                    .funcionarios(funcionariosDTO)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
        public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
                Projeto projeto = projetoRepository.findById(idProjeto)
                                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));
                Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));
                projeto.getFuncionario().add(funcionario);
                funcionario.getProjeto().add(projeto);
                projetoRepository.save(projeto);
        }
}
