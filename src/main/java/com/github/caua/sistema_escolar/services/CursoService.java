package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.dtos.CursoDTO;
import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.repositories.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
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

        cursoRepository.save(
                CursoDTO.fromDtoToEntity(data)
        );
    }

    public void atualizarCurso(CursoDTO data, Long id) {
        Curso cursoBanco = cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o curso com esse ID"
                ));

        Curso atualizacoesCurso = CursoDTO.fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesCurso, cursoBanco, "id");
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
