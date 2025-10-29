package com.example.ac2backend.Controllers;

import org.springframework.http.ResponseEntity; // Importação adicionada
import org.springframework.web.bind.annotation.CrossOrigin; // Importação adicionada
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2backend.Services.ProjetoService;
import com.example.ac2backend.dtos.ProjetoDTO;
import com.example.ac2backend.dtos.ProjetoRequestDTO;


@RestController
@RequestMapping("/projetos")
@CrossOrigin(origins = "*") // 👈 CORS Adicionado
public class ProjetoController {
    private ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    // Alterado de 'void' para 'ResponseEntity<Void>' para retornar 201 Created
    public ResponseEntity<Void> adicionar(@RequestBody ProjetoRequestDTO projetoRequestDTO) {
        projetoService.salvar(projetoRequestDTO);
        return ResponseEntity.created(null).build(); // Retorna 201 Created
    }
    
    @GetMapping("/{id}")
    public ProjetoDTO buscarProjetoPorId(@PathVariable Integer id) {
        return projetoService.obterPorId(id);
    }   
}