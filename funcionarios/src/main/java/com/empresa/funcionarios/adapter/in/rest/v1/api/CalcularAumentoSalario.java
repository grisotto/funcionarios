package com.empresa.funcionarios.adapter.in.rest.v1.api;

import com.empresa.funcionarios.adapter.in.rest.v1.api.response.AumentoSalarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface CalcularAumentoSalario {

    //TODO: adicionar anotacoes para openapi
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/api/v1/funcionarios/{cpf}/calculo_aumento_salario", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AumentoSalarioResponse> calculo(@PathVariable("cpf") @Valid @NotNull(message = "CPF obrigatório") String cpf);
}
