package com.github.caua.sistema_escolar.controllers;

import com.github.caua.sistema_escolar.dtos.AdminDTO;
import com.github.caua.sistema_escolar.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AdminDTO>> listar() {
        return ResponseEntity.ok(adminService.listarAdmins());
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody AdminDTO data) {
        adminService.registrarAdmin(data);
        return ResponseEntity.ok("Admin registrado com sucesso");
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizar(@RequestBody AdminDTO data, @PathVariable Long id) {
        adminService.atualizarAdmin(data, id);
        return ResponseEntity.ok("Admin atualizado com sucesso");
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> remover(@PathVariable Long id) {
        adminService.deletarAdmin(id);
        return ResponseEntity.ok("Admin removido do sistema com sucesso");
    }
}
