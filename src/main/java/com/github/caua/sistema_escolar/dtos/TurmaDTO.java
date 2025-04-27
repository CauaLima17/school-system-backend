package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.model.Turma;
import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TurmaDTO {
    private Long id;
    private String anoPeriodo;
    private List<Aluno> alunos;
    private Curso curso;

    public static Turma fromDtoToEntity(TurmaDTO data) {
        return Turma.builder()
                .anoPeriodo(data.getAnoPeriodo())
                .alunos(data.getAlunos())
                .curso(data.getCurso())
                .build();
    }

    public static TurmaDTO fromEntityToDto(Turma data) {
        return TurmaDTO.builder()
                .id(data.getId())
                .anoPeriodo(data.getAnoPeriodo())
                .alunos(data.getAlunos())
                .curso(data.getCurso())
                .build();
    }
}
