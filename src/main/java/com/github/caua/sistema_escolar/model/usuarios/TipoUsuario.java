package com.github.caua.sistema_escolar.model.usuarios;

public enum TipoUsuario {
    ADMIN("Admin"),
    PROFESSOR("Professor"),
    ALUNO("Aluno");

    private String tipo;

    TipoUsuario(String tipo) {
        this.tipo = tipo;
    }
}
