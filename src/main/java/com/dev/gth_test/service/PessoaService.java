package com.dev.gth_test.service;

import com.dev.gth_test.dto.PessoaDTO;
import com.dev.gth_test.entity.Pessoa;
import com.dev.gth_test.task.Task;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final Task task;

    @Autowired
    public PessoaService(Task task) {
        this.task = task;
    }

    public PessoaDTO createPessoa(@Valid PessoaDTO pessoaDTO) {
        Pessoa pessoa = toEntity(pessoaDTO);
        task.createPessoa(pessoa);
        return toDTO(pessoa);
    }

    public PessoaDTO updatePessoa(Long id, @Valid PessoaDTO pessoaDTO) {
        pessoaDTO.setId(id);
        Pessoa pessoa = toEntity(pessoaDTO);
        Pessoa updatedPessoa = task.updatePessoa(id, pessoa);
        return toDTO(updatedPessoa);
    }

    public void deletePessoa(Long id) {
        task.deletePessoa(id);
    }

    public List<PessoaDTO> getAllPessoas() {
        List<Pessoa> pessoas = task.getAllPessoas();
        return pessoas.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Double calculateIdealWeight(Long id) {
        return task.calculateIdealWeight(id);
    }

    Pessoa toEntity(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.getId());
        pessoa.setNome(dto.getNome());
        pessoa.setDataNasc(dto.getDataNasc());
        pessoa.setCpf(dto.getCpf());
        pessoa.setSexo(dto.getSexo());
        pessoa.setAltura(dto.getAltura());
        pessoa.setPeso(dto.getPeso());
        return pessoa;
    }

    PessoaDTO toDTO(Pessoa pessoa) {
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getDataNasc(),
                pessoa.getCpf(),
                pessoa.getSexo(),
                pessoa.getAltura(),
                pessoa.getPeso()
        );
    }

}
