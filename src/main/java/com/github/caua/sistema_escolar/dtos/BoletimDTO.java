package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Boletim;
import com.github.caua.sistema_escolar.model.Materia;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoletimDTO {
    private Long id;
    private String bimestre;
    private Double nota;
    private Long quantidadeFaltas;
    @ManyToOne
    private Materia materia;

    public static BoletimDTO fromEntityToDto(Boletim data) {
        return BoletimDTO.builder()
                .id(data.getId())
                .bimestre(data.getBimestre())
                .nota(data.getNota())
                .quantidadeFaltas(data.getQuantidadeFaltas())
                .materia(data.getMateria())
                .build();
    }
}
