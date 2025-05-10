package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.TurmaDTO;
import com.github.caua.sistema_escolar.services.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalhes/turma")
public class TurmaController {
    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TurmaDTO>> listar() {
        return ResponseEntity.ok(turmaService.listarTurmas());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody TurmaDTO data) {
        turmaService.registrarTurma(data);
        return ResponseEntity.ok("Turma registrada com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody TurmaDTO data, @PathVariable Long id) {
        turmaService.atualizarTurma(data, id);
        return ResponseEntity.ok("Turma atualizada com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        turmaService.deletarTurma(id);
        return ResponseEntity.ok("Turma removida do sistema com sucesso");
    }
}
