package com.github.caua.sistema_escolar.model.usuarios;

import com.github.caua.sistema_escolar.model.Entidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Representa a tabela de usuários que inclui:
 * Admins, Professores e Alunos.
 *
 * @author Cauã
 */
@Entity
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Data
@SuperBuilder
public class Usuario extends Entidade {
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String matricula;
    @Column(nullable = false)
    private String senha;
    @Column(insertable = false, updatable = false)
    private String tipo;
}
