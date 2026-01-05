package com.dev.gth_test.task;

import com.dev.gth_test.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Task {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public Task(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
}
