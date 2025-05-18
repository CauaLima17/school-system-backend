package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.ProfessorDTO;
import com.github.caua.sistema_escolar.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/usuario/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ALUNO')")
    @GetMapping("/listar")
    public ResponseEntity<List<ProfessorDTO>> listar() {
        return ResponseEntity.ok(professorService.listarProfessores());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody ProfessorDTO data) {
        professorService.registrarProfessor(data);
        return ResponseEntity.ok("Professor registrado com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody ProfessorDTO data, @PathVariable Long id) {
        professorService.atualizarProfessor(data, id);
        return ResponseEntity.ok("Professor atualizado com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        professorService.deletarProfessor(id);
        return ResponseEntity.ok("Professor removido do sistema com sucesso");
    }
}
