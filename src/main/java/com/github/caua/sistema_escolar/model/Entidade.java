package com.github.caua.sistema_escolar.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@SuperBuilder
public abstract class Entidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(updatable = false)
    private LocalDateTime data_criacao;
    private LocalDateTime data_atualizacao;
    private LocalDateTime data_delete;

    @PrePersist
    public void prePersist() {
        if (data_criacao == null) data_criacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        data_atualizacao = LocalDateTime.now();
    }
}
