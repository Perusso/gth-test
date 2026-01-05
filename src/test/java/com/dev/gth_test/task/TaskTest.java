// TaskTest.java
package com.dev.gth_test.task;

import com.dev.gth_test.entity.Pessoa;
import com.dev.gth_test.exception.CpfAlreadyExistsException;
import com.dev.gth_test.exception.InvalidSexException;
import com.dev.gth_test.exception.PessoaNotFoundException;
import com.dev.gth_test.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private Task task;

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa(
                "Carlos Almeida",
                LocalDate.of(1980, 3, 20),
                "11122233344",
                'M',
                1.80,
                80.0
        );
        pessoa.setId(1L);
    }

    @Test
    void createPessoa_Success() {
        when(pessoaRepository.existsByCpf(anyString())).thenReturn(false);
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa result = task.createPessoa(pessoa);

        assertNotNull(result);
        assertEquals(pessoa.getNome(), result.getNome());
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void createPessoa_InvalidSex_ThrowsException() {
        pessoa.setSexo('X');

        assertThrows(InvalidSexException.class, () -> task.createPessoa(pessoa));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void createPessoa_DuplicateCpf_ThrowsException() {
        when(pessoaRepository.existsByCpf(anyString())).thenReturn(true);

        assertThrows(CpfAlreadyExistsException.class, () -> task.createPessoa(pessoa));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void updatePessoa_Success() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);

        Pessoa updated = task.updatePessoa(1L, pessoa);

        assertNotNull(updated);
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void updatePessoa_NotFound_ThrowsException() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PessoaNotFoundException.class, () -> task.updatePessoa(1L, pessoa));
        verify(pessoaRepository, never()).save(any(Pessoa.class));
    }

    @Test
    void deletePessoa_Success() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoa));
        doNothing().when(pessoaRepository).delete(any(Pessoa.class));

        assertDoesNotThrow(() -> task.deletePessoa(1L));
        verify(pessoaRepository, times(1)).delete(pessoa);
    }

    @Test
    void calculateIdealWeight_Success() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoa));

        Double result = task.calculateIdealWeight(1L);

        // Cálculo para homem: (72.7 * 1.80) - 58 = 72.86
        assertEquals(72.86, result, 0.01);
    }
}