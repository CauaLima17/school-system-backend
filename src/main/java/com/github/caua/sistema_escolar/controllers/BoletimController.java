package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.BoletimDTO;
import com.github.caua.sistema_escolar.services.BoletimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/detalhes/boletim")
public class BoletimController {
    private final BoletimService boletimService;

    @Autowired
    public BoletimController(BoletimService boletimService) {
        this.boletimService = boletimService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ALUNO')")
    @GetMapping("/listar")
    public ResponseEntity<List<BoletimDTO>> listar() {
        return ResponseEntity.ok(boletimService.listarBoletins());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody BoletimDTO data) {
        boletimService.registrarBoletim(data);
        return ResponseEntity.ok("Boletim registrado com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody BoletimDTO data, @PathVariable Long id) {
        boletimService.atualizarBoletim(data, id);
        return ResponseEntity.ok("Boletim atualizado com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        boletimService.deletarBoletim(id);
        return ResponseEntity.ok("Boletim removido do sistema com sucesso");
    }
}
