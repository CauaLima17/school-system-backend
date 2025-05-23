package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Data
@SuperBuilder
@NoArgsConstructor
public class AlunoDTO extends UsuarioDTO {
    private TurmaSimplesDTO turma;

    public static AlunoDTO fromEntityToDto(Aluno data) {
        return AlunoDTO.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .turma(Objects.nonNull(data.getTurma())
                        ? TurmaSimplesDTO.fromEntityToDto(data.getTurma())
                        : null)
                .tipo(data.getTipo())
                .build();
    }
}
