package com.dev.gth_test.task;

import com.dev.gth_test.entity.Pessoa;
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
            throw new IllegalArgumentException("Sexo deve ser 'M' ou 'F'");
        }

        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        return pessoaRepository.save(pessoa);
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa) {
        Pessoa existingPessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa not found with id " + id));

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
                .orElseThrow(() -> new RuntimeException("Pessoa not found with id " + id));
        pessoaRepository.delete(existingPessoa);
    }

    public Optional<Pessoa> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }


    public Double calculateIdealWeight(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return pessoa.get().calculateIdealWeight();
        }
        throw new RuntimeException("Pessoa não encontrada com ID: " + id);
    }

}
