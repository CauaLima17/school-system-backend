package com.github.caua.sistema_escolar.services;

import com.github.caua.sistema_escolar.dtos.BoletimDTO;
import com.github.caua.sistema_escolar.model.Boletim;
import com.github.caua.sistema_escolar.model.Materia;
import com.github.caua.sistema_escolar.repositories.BoletimRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BoletimService {
    private final BoletimRepository boletimRepository;

    @Autowired
    public BoletimService(BoletimRepository boletimRepository) {
        this.boletimRepository = boletimRepository;
    }

    public Boletim fromDtoToEntity(BoletimDTO data) {
        Materia materia = null;

        if (data.getMateria() != null) {
            materia = data.getMateria();
        }

        return Boletim.builder()
                .nota(data.getNota())
                .quantidadeFaltas(data.getQuantidadeFaltas())
                .bimestre(data.getBimestre())
                .materia(materia)
                .build();
    }

    public List<BoletimDTO> listarBoletins() {
        return boletimRepository.findAll()
                .stream()
                .map(BoletimDTO::fromEntityToDto)
                .toList();
    }

    public void registrarBoletim(BoletimDTO data) {
        Boletim boletim = fromDtoToEntity(data);
        boletim.prePersist();

        boletimRepository.save(boletim);
    }

    public void atualizarBoletim(BoletimDTO data, Long id) {
        Boletim boletimBanco = boletimRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o boletim com esse ID"
                ));

        Boletim atualizacoesBoletim = fromDtoToEntity(data);

        BeanUtils.copyProperties(atualizacoesBoletim, boletimBanco, "id");
        boletimBanco.preUpdate();

        boletimRepository.save(boletimBanco);
    }

    public void deletarBoletim(Long id) {
        Boletim boletimBanco = boletimRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não foi possível encontrar o boletim com esse ID"
                ));

        boletimRepository.delete(boletimBanco);
    }
}
