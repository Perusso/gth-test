package com.dev.gth_test.exception;

import java.util.List;

public class ValidationException extends PessoaException {

    private final List<String> validationErrors;

    public ValidationException(List<String> validationErrors) {
        super("Erro de validação: " + String.join(", ", validationErrors));
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }
}
