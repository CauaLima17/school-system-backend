package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.usuarios.Admin;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AdminDTO extends UsuarioDTO {
    public static Admin fromDtoToEntity(AdminDTO data) {
        return Admin.builder()
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .senha(data.getSenha())
                .build();
    }

    public static AdminDTO fromEntityToDto(Admin data) {
        return AdminDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .build();
    }
}
