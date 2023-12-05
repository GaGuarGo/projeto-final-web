package com.br.projeto_clientes.cliente.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandartError {

    private List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

}
