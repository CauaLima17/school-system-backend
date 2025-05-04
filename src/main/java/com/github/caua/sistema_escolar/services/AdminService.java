package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.dtos.AdminDTO;
import com.github.caua.sistema_escolar.model.usuarios.Admin;
import com.github.caua.sistema_escolar.repositories.AdminRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin fromDtoToEntity(AdminDTO data) {
        return Admin.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .senha(data.getSenha())
                .build();
    }

    public List<AdminDTO> listarAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(AdminDTO::fromEntityToDto)
                .toList();
    }

    public void registrarAdmin(AdminDTO data) {
        adminRepository.findByMatricula(data.getMatricula())
                .ifPresent(admin -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Já existe um admin registrado com essa matrícula"
                    );
                });
        adminRepository.findByEmail(data.getEmail())
                .ifPresent(admin -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Já existe um professor registrado com esse email"
                    );
                });

        Admin admin = fromDtoToEntity(data);
        admin.prePersist();

        adminRepository.save(admin);
    }

    public void atualizarAdmin(AdminDTO data, Long id) {
        Admin adminBanco = adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o admin com esse ID"
                ));

        adminRepository.findByMatricula(data.getMatricula())
                .ifPresent(admin -> {
                    if(!Objects.equals(admin.getId(), id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Já existe um admin registrado com essa matrícula"
                        );
                    }
                });
        adminRepository.findByEmail(data.getEmail())
                .ifPresent(admin -> {
                    if(!Objects.equals(admin.getId(), id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Já existe um admin registrado com esse email"
                        );
                    }
                });

        Admin atualizacoesAdmin = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesAdmin, adminBanco, "id");
        adminBanco.preUpdate();

        adminRepository.save(adminBanco);
    }

    public void deletarAdmin(Long id) {
        Admin adminBanco = adminRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o admin com esse ID"
                ));

        adminRepository.delete(adminBanco);
    }
}
