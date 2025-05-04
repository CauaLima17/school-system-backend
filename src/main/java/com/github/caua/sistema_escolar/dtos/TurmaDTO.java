package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.model.Turma;
import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@Builder
public class TurmaDTO {
    private Long id;
    private String anoPeriodo;
    private List<AlunoSimplesDTO> alunos;
    private CursoDTO curso;

    public static TurmaDTO fromEntityToDto(Turma data) {
        return TurmaDTO.builder()
                .id(data.getId())
                .anoPeriodo(data.getAnoPeriodo())
                .alunos(data.getAlunos()
                        .stream()
                        .map(AlunoSimplesDTO::fromEntityToDto)
                        .toList())
                .curso(Objects.nonNull(data.getCurso())
                        ? CursoDTO.fromEntityToDto(data.getCurso())
                        : null)
                .build();
    }
}
