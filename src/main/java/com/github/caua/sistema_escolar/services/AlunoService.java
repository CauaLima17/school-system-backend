package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.dtos.AlunoDTO;
import com.github.caua.sistema_escolar.model.usuarios.Aluno;
import com.github.caua.sistema_escolar.repositories.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
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

        alunoRepository.save(
                AlunoDTO.fromDtoToEntity(data)
        );
    }

    public void atualizarAluno(AlunoDTO data, Long id) {
        Aluno alunoBanco = alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o aluno com esse ID"
                ));

        Aluno atualizacoesAluno = AlunoDTO.fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesAluno, alunoBanco, "id");
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
