package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.dtos.ProfessorDTO;
import com.github.caua.sistema_escolar.model.usuarios.Professor;
import com.github.caua.sistema_escolar.repositories.ProfessorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
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

        professorRepository.save(
                ProfessorDTO.fromDtoToEntity(data)
        );
    }

    public void atualizarProfessor(ProfessorDTO data, Long id) {
        Professor professorBanco = professorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o professor com esse ID"
                ));

        Professor atualizacoesProfessor = ProfessorDTO.fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesProfessor, professorBanco, "id");
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
