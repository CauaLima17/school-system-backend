package com.github.caua.sistema_escolar.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.caua.sistema_escolar.model.Entidade;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String matricula;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String tipo;
}
