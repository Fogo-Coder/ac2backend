package com.example.ac2backend.Controllers;

import org.springframework.http.ResponseEntity; // Importação adicionada
import org.springframework.web.bind.annotation.CrossOrigin; // Importação adicionada
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2backend.Services.SetorService;
import com.example.ac2backend.dtos.SetorDTO;
import com.example.ac2backend.dtos.SetorRequestDTO;

@RestController
@RequestMapping("/setores")
@CrossOrigin(origins = "*") // 👈 CORS Adicionado
public class SetorController {
    private SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    // Alterado de 'void' para 'ResponseEntity<Void>' para retornar 201 Created
    public ResponseEntity<Void> adicionar(@RequestBody SetorRequestDTO setorRequestDTO) { 
        setorService.salvar(setorRequestDTO);
        return ResponseEntity.created(null).build(); // Retorna 201 Created
    }

    @GetMapping("/{id}")
    public SetorDTO buscarSetorPorId(@PathVariable Integer id) {
        return setorService.obterPorId(id);
    }
}