package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Turma;
import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class AlunoDTO extends UsuarioDTO {
    private Turma turma;

    public static Aluno fromDtoToEntity(AlunoDTO data) {
        return Aluno.builder()
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .turma(data.getTurma())
                .senha(data.getSenha())
                .build();
    }

    public static AlunoDTO fromEntityToDto(Aluno data) {
        return AlunoDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .turma(data.getTurma())
                .tipo(data.getTipo())
                .build();
    }
}
