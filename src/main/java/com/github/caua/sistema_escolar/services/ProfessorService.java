package com.github.caua.sistema_escolar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caua.sistema_escolar.dtos.ProfessorDTO;
import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.model.usuarios.Professor;
import com.github.caua.sistema_escolar.repositories.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, ObjectMapper objectMapper) {
        this.professorRepository = professorRepository;
        this.objectMapper = objectMapper;
    }

    public Professor fromDtoToEntity(ProfessorDTO data) {
        List<Materia> materias = null;

        if (!Objects.equals(data.getMaterias(), null)) {
            materias = data.getMaterias()
                    .stream()
                    .map(materia -> objectMapper.convertValue(materia, Materia.class))
                    .collect(Collectors.toList());
        }

        return Professor.builder()
                .id(data.getId())
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .materias(materias)
                .senha(data.getSenha())
                .build();
    }

    public List<ProfessorDTO> listarProfessores() {
        return professorRepository.findAll()
                .stream()
                .map(ProfessorDTO::fromEntityToDto)
                .toList();
    }

    public void registrarProfessor(ProfessorDTO data) {
        professorRepository.findByMatricula(data.getMatricula())
                .ifPresent(professor -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Já existe um professor registrado com essa matrícula"
                    );
                });

        Professor professor = fromDtoToEntity(data);
        professor.prePersist();

        professorRepository.save(professor);
    }

    public void atualizarProfessor(ProfessorDTO data, Long id) {
        Professor professorBanco = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o professor com esse ID"
                ));

        Professor atualizacoesProfessor = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesProfessor, professorBanco, "id");
        professorBanco.preUpdate();

        professorRepository.save(professorBanco);
    }

    public void deletarProfessor(Long id) {
        Professor professorBanco = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o professor com esse ID"
                ));

        professorRepository.delete(professorBanco);
    }
}
