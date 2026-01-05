package com.dev.gth_test.exception;

public class PessoaNotFoundException extends PessoaException {

    public PessoaNotFoundException(Long id) {
        super("Pessoa não encontrada com ID: " + id);
    }

    public PessoaNotFoundException(String message) {
        super(message);
    }
}
