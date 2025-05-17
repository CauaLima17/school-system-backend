package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.MateriaDTO;
import com.github.caua.sistema_escolar.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/detalhes/materia")
public class MateriaController {
    private final MateriaService materiaService;

    @Autowired
    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @PreAuthorize("hasRole('PROFESSOR') || hasRole('ALUNO')")
    @GetMapping("/listar")
    public ResponseEntity<List<MateriaDTO>> listar() {
        return ResponseEntity.ok(materiaService.listarMaterias());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody MateriaDTO data) {
        materiaService.registrarMateria(data);
        return ResponseEntity.ok("Materia registrada com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody MateriaDTO data, @PathVariable Long id) {
        materiaService.atualizarMateria(data, id);
        return ResponseEntity.ok("Materia atualizada com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        materiaService.deletarMateria(id);
        return ResponseEntity.ok("Materia removida do sistema com sucesso");
    }
}
