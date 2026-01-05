// PessoaServiceTest.java
package com.dev.gth_test.service;

import com.dev.gth_test.dto.PessoaDTO;
import com.dev.gth_test.entity.Pessoa;
import com.dev.gth_test.exception.CpfAlreadyExistsException;
import com.dev.gth_test.exception.PessoaNotFoundException;
import com.dev.gth_test.task.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTest {

    @Mock
    private Task task;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;
    private PessoaDTO pessoaDTO;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa(
                "João Silva",
                LocalDate.of(1990, 1, 1),
                "12345678901",
                'M',
                1.75,
                70.0
        );
        pessoa.setId(1L);

        pessoaDTO = new PessoaDTO(
                1L,
                "João Silva",
                LocalDate.of(1990, 1, 1),
                "12345678901",
                'M',
                1.75,
                70.0
        );
    }

    @Test
    void createPessoa_Success() {
        when(task.createPessoa(any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO result = pessoaService.createPessoa(pessoaDTO);

        assertNotNull(result);
        assertEquals(pessoaDTO.getNome(), result.getNome());
        assertEquals(pessoaDTO.getCpf(), result.getCpf());
        verify(task, times(1)).createPessoa(any(Pessoa.class));
    }

    @Test
    void createPessoa_ThrowsCpfAlreadyExistsException() {
        when(task.createPessoa(any(Pessoa.class)))
                .thenThrow(new CpfAlreadyExistsException("12345678901"));

        assertThrows(CpfAlreadyExistsException.class, () ->
                pessoaService.createPessoa(pessoaDTO)
        );
    }

    @Test
    void updatePessoa_Success() {
        when(task.updatePessoa(anyLong(), any(Pessoa.class))).thenReturn(pessoa);

        PessoaDTO result = pessoaService.updatePessoa(1L, pessoaDTO);

        assertNotNull(result);
        assertEquals(pessoaDTO.getNome(), result.getNome());
        verify(task, times(1)).updatePessoa(anyLong(), any(Pessoa.class));
    }

    @Test
    void updatePessoa_ThrowsPessoaNotFoundException() {
        when(task.updatePessoa(anyLong(), any(Pessoa.class)))
                .thenThrow(new PessoaNotFoundException(1L));

        assertThrows(PessoaNotFoundException.class, () ->
                pessoaService.updatePessoa(1L, pessoaDTO)
        );
    }

    @Test
    void deletePessoa_Success() {
        doNothing().when(task).deletePessoa(anyLong());

        assertDoesNotThrow(() -> pessoaService.deletePessoa(1L));
        verify(task, times(1)).deletePessoa(anyLong());
    }

    @Test
    void deletePessoa_ThrowsPessoaNotFoundException() {
        doThrow(new PessoaNotFoundException(1L))
                .when(task).deletePessoa(anyLong());

        assertThrows(PessoaNotFoundException.class, () ->
                pessoaService.deletePessoa(1L)
        );
    }

    @Test
    void getAllPessoas_Success() {
        List<Pessoa> pessoas = Arrays.asList(pessoa);
        when(task.getAllPessoas()).thenReturn(pessoas);

        List<PessoaDTO> result = pessoaService.getAllPessoas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(pessoaDTO.getNome(), result.get(0).getNome());
        verify(task, times(1)).getAllPessoas();
    }

    @Test
    void calculateIdealWeight_Success() {
        when(task.calculateIdealWeight(anyLong())).thenReturn(69.225);

        Double result = pessoaService.calculateIdealWeight(1L);

        assertNotNull(result);
        assertEquals(69.225, result);
        verify(task, times(1)).calculateIdealWeight(anyLong());
    }

    @Test
    void calculateIdealWeight_ThrowsPessoaNotFoundException() {
        when(task.calculateIdealWeight(anyLong()))
                .thenThrow(new PessoaNotFoundException(1L));

        assertThrows(PessoaNotFoundException.class, () ->
                pessoaService.calculateIdealWeight(1L)
        );
    }

    @Test
    void toEntity_Conversion() {
        Pessoa entity = pessoaService.toEntity(pessoaDTO);

        assertEquals(pessoaDTO.getId(), entity.getId());
        assertEquals(pessoaDTO.getNome(), entity.getNome());
        assertEquals(pessoaDTO.getCpf(), entity.getCpf());
    }

    @Test
    void toDTO_Conversion() {
        PessoaDTO dto = pessoaService.toDTO(pessoa);

        assertEquals(pessoa.getId(), dto.getId());
        assertEquals(pessoa.getNome(), dto.getNome());
        assertEquals(pessoa.getCpf(), dto.getCpf());
    }
}