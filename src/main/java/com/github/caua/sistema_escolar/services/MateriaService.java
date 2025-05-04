package com.github.caua.sistema_escolar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caua.sistema_escolar.dtos.MateriaDTO;
import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.model.usuarios.Professor;
import com.github.caua.sistema_escolar.repositories.MateriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MateriaService {
    private final MateriaRepository materiaRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository, ObjectMapper objectMapper) {
        this.materiaRepository = materiaRepository;
        this.objectMapper = objectMapper;
    }

    public Materia fromDtoToEntity(MateriaDTO data) {
        List<Professor> professores = null;

        if(!Objects.equals(data.getProfessores(), null)) {
            professores = data.getProfessores()
                    .stream()
                    .map(professor -> objectMapper.convertValue(professor, Professor.class))
                    .collect(Collectors.toList());
        }

        return Materia.builder()
                .id(data.getId())
                .nome(data.getNome())
                .cargaHoraria(data.getCargaHoraria())
                .professores(professores)
                .build();
    }

    public List<MateriaDTO> listarMaterias() {
        return materiaRepository.findAll()
                .stream()
                .map(MateriaDTO::fromEntityToDto)
                .toList();
    }

    public void registrarMateria(MateriaDTO data) {
        materiaRepository.findByNome(data.getNome())
                .ifPresent(materia -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Já existe um materia registrada com esse nome"
                    );
                });

        materiaRepository.save(fromDtoToEntity(data));
    }

    public void atualizarMateria(MateriaDTO data, Long id) {
        Materia materiaBanco = materiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar a materia com esse ID"
                ));

        Materia atualizacoesMateria = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesMateria, materiaBanco, "id");
        materiaRepository.save(materiaBanco);
    }

    public void deletarMateria(Long id) {
        Materia materiaBanco = materiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar a materia com esse ID"
                ));

        materiaRepository.delete(materiaBanco);
    }
}
