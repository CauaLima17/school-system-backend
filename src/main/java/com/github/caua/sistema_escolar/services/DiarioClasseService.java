package com.github.caua.sistema_escolar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caua.sistema_escolar.dtos.DiarioClasseDTO;
import com.github.caua.sistema_escolar.model.DiarioClasse;
import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.model.Turma;
import com.github.caua.sistema_escolar.model.usuarios.Professor;
import com.github.caua.sistema_escolar.repositories.DiarioClasseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DiarioClasseService {
    private final DiarioClasseRepository diarioClasseRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public DiarioClasseService(DiarioClasseRepository diarioClasseRepository, ObjectMapper objectMapper) {
        this.diarioClasseRepository = diarioClasseRepository;
        this.objectMapper = objectMapper;
    }

    public DiarioClasse fromDtoToEntity(DiarioClasseDTO data) {
        Materia materia = null;
        Professor professor = null;
        Turma turma = null;

        if (data.getMateria() != null) {
            materia = objectMapper.convertValue(data.getMateria(), Materia.class);
        }

        if (data.getProfessor() != null) {
            professor = objectMapper.convertValue(data.getProfessor(), Professor.class);
        }

        if (data.getMateria() != null) {
            turma = objectMapper.convertValue(data.getTurma(), Turma.class);
        }

        return DiarioClasse.builder()
                .turma(turma)
                .professor(professor)
                .data(data.getData())
                .descricao(data.getDescricao())
                .materia(materia)
                .build();
    }

    public List<DiarioClasseDTO> listarDiarioClasses() {
        return diarioClasseRepository.findAll()
                .stream()
                .map(DiarioClasseDTO::fromEntityToDto)
                .toList();
    }

    public void registrarDiarioClasse(DiarioClasseDTO data) {
        DiarioClasse curso = fromDtoToEntity(data);
        curso.prePersist();

        diarioClasseRepository.save(curso);
    }

    public void atualizarDiarioClasse(DiarioClasseDTO data, Long id) {
        DiarioClasse diarioBanco = diarioClasseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o diário com esse ID"
                ));

        DiarioClasse atualizacoesDiarioClasse = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesDiarioClasse, diarioBanco, "id");
        diarioBanco.preUpdate();

        diarioClasseRepository.save(diarioBanco);
    }

    public void deletarDiarioClasse(Long id) {
        DiarioClasse diarioBanco = diarioClasseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o diário com esse ID"
                ));

        diarioClasseRepository.delete(diarioBanco);
    }
}
