package com.github.caua.sistema_escolar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caua.sistema_escolar.dtos.TurmaDTO;
import com.github.caua.sistema_escolar.model.Curso;
import com.github.caua.sistema_escolar.model.Turma;
import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import com.github.caua.sistema_escolar.model.usuarios.Professor;
import com.github.caua.sistema_escolar.repositories.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, ObjectMapper objectMapper) {
        this.turmaRepository = turmaRepository;
        this.objectMapper = objectMapper;
    }

    public Turma fromDtoToEntity(TurmaDTO data) {
        List<Aluno> alunos = null;
        List<Professor> professores = null;

        if (!Objects.equals(data.getAlunos(), null)) {
            alunos = data.getAlunos()
                    .stream()
                    .map(aluno -> objectMapper.convertValue(aluno, Aluno.class))
                    .collect(Collectors.toList());
        }

        if (!Objects.equals(data.getProfessores(), null)) {
            professores = data.getProfessores()
                    .stream()
                    .map(professor -> objectMapper.convertValue(professor, Professor.class))
                    .collect(Collectors.toList());
        }

        return Turma.builder()
                .anoPeriodo(data.getAnoPeriodo())
                .alunos(alunos)
                .curso(Objects.nonNull(data.getCurso())
                        ? objectMapper.convertValue(data.getCurso(), Curso.class)
                        : null)
                .professores(professores)
                .build();
    }

    public List<TurmaDTO> listarTurmas() {
        return turmaRepository.findAll()
                .stream()
                .map(TurmaDTO::fromEntityToDto)
                .toList();
    }

    public void registrarTurma(TurmaDTO data) {
        Turma turma = fromDtoToEntity(data);
        turma.prePersist();

        turmaRepository.save(turma);
    }

    public void atualizarTurma(TurmaDTO data, Long id) {
        Turma turmaBanco = turmaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o turma com esse ID"
                ));

        Turma atualizacoesTurma = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesTurma, turmaBanco, "id");
        turmaBanco.preUpdate();

        turmaRepository.save(turmaBanco);
    }

    public void deletarTurma(Long id) {
        Turma turmaBanco = turmaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o curso com esse ID"
                ));

        turmaRepository.delete(turmaBanco);
    }
}
