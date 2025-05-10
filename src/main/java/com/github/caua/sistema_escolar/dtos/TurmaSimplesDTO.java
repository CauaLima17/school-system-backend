package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.model.Turma;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TurmaSimplesDTO {
    private Long id;
    private String anoPeriodo;
    private Curso curso;

    public static TurmaSimplesDTO fromEntityToDto(Turma data) {
        return TurmaSimplesDTO.builder()
                .id(data.getId())
                .anoPeriodo(data.getAnoPeriodo())
                .curso(data.getCurso())
                .build();
    }
}
