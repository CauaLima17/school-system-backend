package com.github.caua.sistema_escolar.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.caua.sistema_escolar.dtos.AlunoDTO;
import com.github.caua.sistema_escolar.model.Turma;
import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import com.github.caua.sistema_escolar.repositories.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    private final ObjectMapper objectMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.alunoRepository = alunoRepository;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Aluno fromDtoToEntity(AlunoDTO data) {
        Turma turma = Objects.nonNull(data.getTurma())
                ? objectMapper.convertValue(data.getTurma(), Turma.class)
                : null;

        return Aluno.builder()
                .nome(data.getNome())
                .email(data.getEmail())
                .matricula(data.getMatricula())
                .turma(turma)
                .senha(data.getSenha())
                .build();
    }

    public List<AlunoDTO> listarAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoDTO::fromEntityToDto)
                .toList();
    }

    public void registrarAluno(AlunoDTO data) {
        alunoRepository.findByMatricula(data.getMatricula())
                .ifPresent(aluno -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Já existe um aluno registrado com essa matrícula"
                    );
                });
        alunoRepository.findByEmail(data.getEmail())
                .ifPresent(aluno -> {
                    throw new ResponseStatusException(
                            HttpStatus.CONFLICT,
                            "Já existe um aluno registrado com esse email"
                    );
                });

        data.setSenha(passwordEncoder.encode(data.getSenha()));

        Aluno aluno = fromDtoToEntity(data);
        aluno.prePersist();

        alunoRepository.save(aluno);
    }

    public void atualizarAluno(AlunoDTO data, Long id) {
        Aluno alunoBanco = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o aluno com esse ID"
                ));
        alunoRepository.findByMatricula(data.getMatricula())
                .ifPresent(aluno -> {
                    if(!Objects.equals(aluno.getId(), id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Já existe um aluno registrado com essa matrícula"
                        );
                    }
                });
        alunoRepository.findByEmail(data.getEmail())
                .ifPresent(aluno -> {
                    if(!Objects.equals(aluno.getId(), id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Já existe um aluno registrado com esse email"
                        );
                    }
                });

        Aluno atualizacoesAluno = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesAluno, alunoBanco, "id");
        alunoBanco.preUpdate();

        alunoRepository.save(alunoBanco);
    }

    public void deletarAluno(Long id) {
        Aluno alunoBanco = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o aluno com esse ID"
                ));

        alunoRepository.delete(alunoBanco);
    }
}
