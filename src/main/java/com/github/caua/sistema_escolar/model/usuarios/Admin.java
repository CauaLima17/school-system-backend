package com.github.caua.sistema_escolar.model.usuarios;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Representa os admins do sistema
 *
 * @author Cauã
 */
@Entity
@NoArgsConstructor
@Data
@DiscriminatorValue("Admin")
@SuperBuilder
public class Admin extends Usuario {
}
