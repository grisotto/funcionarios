package com.empresa.funcionarios.adapter.in.rest.v1.api;

import com.empresa.funcionarios.adapter.in.rest.v1.api.request.FuncionarioRequest;
import com.empresa.funcionarios.adapter.in.rest.v1.api.response.FuncionarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.net.URISyntaxException;

public interface Criar {

    //TODO: adicionar anotacoes para openapi
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/api/v1/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FuncionarioResponse> criar(@Valid @RequestBody FuncionarioRequest request) throws URISyntaxException;
}
