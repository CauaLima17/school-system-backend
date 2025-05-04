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
    private List<AlunoSimplesDTO> alunos;
    private Curso curso;

    public static TurmaDTO fromEntityToDto(Turma data) {
        return TurmaDTO.builder()
                .id(data.getId())
                .anoPeriodo(data.getAnoPeriodo())
                .alunos(data.getAlunos()
                        .stream()
                        .map(AlunoSimplesDTO::fromEntityToDto)
                        .toList())
                .curso(data.getCurso())
                .build();
    }
}
