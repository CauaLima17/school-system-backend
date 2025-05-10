package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.CursoDTO;
import com.github.caua.sistema_escolar.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalhes/curso")
public class CursoController {
    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CursoDTO>> listar() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody CursoDTO data) {
        cursoService.registrarCurso(data);
        return ResponseEntity.ok("Curso registrado com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody CursoDTO data, @PathVariable Long id) {
        cursoService.atualizarCurso(data, id);
        return ResponseEntity.ok("Curso atualizado com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.ok("Curso removido do sistema com sucesso");
    }
}
