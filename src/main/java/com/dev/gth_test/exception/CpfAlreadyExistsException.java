package com.dev.gth_test.exception;

public class CpfAlreadyExistsException extends PessoaException {

    public CpfAlreadyExistsException(String cpf) {
        super("CPF já cadastrado: " + cpf);
    }

    public CpfAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
