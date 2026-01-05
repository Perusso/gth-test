package com.dev.gth_test.exception;

public class InvalidSexException extends PessoaException {

    public InvalidSexException(char sexo) {
        super("Sexo inválido: " + sexo + ". Deve ser 'M' ou 'F'");
    }
}
