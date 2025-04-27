package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.dtos.TurmaDTO;
import com.github.caua.sistema_escolar.model.Turma;
import com.github.caua.sistema_escolar.repositories.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<TurmaDTO> listarTurmas() {
        return turmaRepository.findAll()
                .stream()
                .map(TurmaDTO::fromEntityToDto)
                .toList();
    }

    public void registrarTurma(TurmaDTO data) {
        turmaRepository.save(
                TurmaDTO.fromDtoToEntity(data)
        );
    }

    public void atualizarTurma(TurmaDTO data, Long id) {
        Turma turmaBanco = turmaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o turma com esse ID"
                ));

        Turma atualizacoesTurma = TurmaDTO.fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesTurma, turmaBanco, "id");
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
