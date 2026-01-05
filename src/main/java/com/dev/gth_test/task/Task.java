package com.dev.gth_test.task;

import com.dev.gth_test.entity.Pessoa;
import com.dev.gth_test.exception.CpfAlreadyExistsException;
import com.dev.gth_test.exception.InvalidSexException;
import com.dev.gth_test.exception.PessoaNotFoundException;
import com.dev.gth_test.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Task {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public Task(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa createPessoa(Pessoa pessoa) {
        if (pessoa.getSexo() != 'M' && pessoa.getSexo() != 'F') {
            throw new InvalidSexException(pessoa.getSexo());
        }

        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new CpfAlreadyExistsException(pessoa.getCpf());
        }

        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        if (pessoa.getSexo() != 'M' && pessoa.getSexo() != 'F') {
            throw new InvalidSexException(pessoa.getSexo());
        }

        Pessoa existingPessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));

        if (!existingPessoa.getCpf().equals(pessoa.getCpf()) &&
                pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new CpfAlreadyExistsException(pessoa.getCpf());
        }

        existingPessoa.setNome(pessoa.getNome());
        existingPessoa.setDataNasc(pessoa.getDataNasc());
        existingPessoa.setCpf(pessoa.getCpf());
        existingPessoa.setSexo(pessoa.getSexo());
        existingPessoa.setAltura(pessoa.getAltura());
        existingPessoa.setPeso(pessoa.getPeso());

        return pessoaRepository.save(existingPessoa);
    }

    public void deletePessoa(Long id) {
        Pessoa existingPessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));
        pessoaRepository.delete(existingPessoa);
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }


    public Double calculateIdealWeight(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNotFoundException(id));
        return pessoa.calculateIdealWeight();
    }

}
