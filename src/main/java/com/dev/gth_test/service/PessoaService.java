package com.dev.gth_test.service;

import com.dev.gth_test.dto.PessoaDTO;
import com.dev.gth_test.task.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final Task task;

    @Autowired
    public PessoaService(Task task) {
        this.task = task;
    }

    public PessoaDTO createPessoa(@Valid PessoaDTO pessoaDTO) {
        return pessoaDTO;
    }

    public PessoaDTO updatePessoa(Long id, @Valid PessoaDTO pessoaDTO) {
        return pessoaDTO;
    }

    public void deletePessoa(Long id) {
    }

    public List<PessoaDTO> getAllPessoas() {
        return List.of();
    }

}
