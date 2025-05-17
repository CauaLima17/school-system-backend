package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.AlunoDTO;
import com.github.caua.sistema_escolar.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/usuario/aluno")
public class AlunoController {
    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ALUNO')")
    @GetMapping("/listar")
    public ResponseEntity<List<AlunoDTO>> listar() {
        return ResponseEntity.ok(alunoService.listarAlunos());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody AlunoDTO data) {
        alunoService.registrarAluno(data);
        return ResponseEntity.ok("Aluno registrado com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody AlunoDTO data, @PathVariable Long id) {
        alunoService.atualizarAluno(data, id);
        return ResponseEntity.ok("Aluno atualizado com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return ResponseEntity.ok("Aluno removido do sistema com sucesso");
    }
}
