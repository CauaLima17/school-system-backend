package com.github.caua.sistema_escolar.dtos;

import com.github.caua.sistema_escolar.model.Entidade;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class UsuarioDTO extends Entidade {
    private String nome;
    private String email;
    private String matricula;
    private String senha;
}
