package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.DiarioClasseDTO;
import com.github.caua.sistema_escolar.services.DiarioClasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN') || hasRole('PROFESSOR')")
@RestController
@RequestMapping("/detalhes/diario")
public class DiarioClasseController {

    private final DiarioClasseService diarioClasseService;

    @Autowired
    public DiarioClasseController(DiarioClasseService diarioClasseService) {
        this.diarioClasseService = diarioClasseService;
    }

    @PreAuthorize("hasRole('ALUNO')")
    @GetMapping("/listar")
    public ResponseEntity<List<DiarioClasseDTO>> listar() {
        return ResponseEntity.ok(diarioClasseService.listarDiarioClasses());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody DiarioClasseDTO data) {
        diarioClasseService.registrarDiarioClasse(data);
        return ResponseEntity.ok("Diário registrado com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody DiarioClasseDTO data, @PathVariable Long id) {
        diarioClasseService.atualizarDiarioClasse(data, id);
        return ResponseEntity.ok("Diário atualizado com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        diarioClasseService.deletarDiarioClasse(id);
        return ResponseEntity.ok("Diário removido do sistema com sucesso");
    }
}
