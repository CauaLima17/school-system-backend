package com.github.caua.sistema_escolar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caua.sistema_escolar.dtos.CursoDTO;
import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.repositories.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public CursoService(CursoRepository cursoRepository, ObjectMapper objectMapper) {
        this.cursoRepository = cursoRepository;
        this.objectMapper = objectMapper;
    }

    public Curso fromDtoToEntity(CursoDTO data) {
        List<Materia> materias = null;

        if (!Objects.equals(data.getMaterias(), null)) {
            materias = data.getMaterias()
                    .stream()
                    .map(materia -> objectMapper.convertValue(materia, Materia.class))
                    .collect(Collectors.toList());
        }

        return Curso.builder()
                .nome(data.getNome())
                .turno(data.getTurno())
                .cargaHoraria(data.getCargaHoraria())
                .materias(materias)
                .build();
    }

    public List<CursoDTO> listarCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(CursoDTO::fromEntityToDto)
                .toList();
    }

    public void registrarCurso(CursoDTO data) {
        cursoRepository.findByNome(data.getNome())
                .ifPresent(curso -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Já existe um curso registrado com esse nome"
                    );
                });

        Curso curso = fromDtoToEntity(data);
        curso.prePersist();

        cursoRepository.save(curso);
    }

    public void atualizarCurso(CursoDTO data, Long id) {
        Curso cursoBanco = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o curso com esse ID"
                ));

        Curso atualizacoesCurso = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesCurso, cursoBanco, "id");
        cursoBanco.preUpdate();

        cursoRepository.save(cursoBanco);
    }

    public void deletarCurso(Long id) {
        Curso cursoBanco = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o curso com esse ID"
                ));

        cursoRepository.delete(cursoBanco);
    }
}
