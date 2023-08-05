package com.empresa.funcionarios.adapter.in.rest.v1.api;

import com.empresa.funcionarios.adapter.in.rest.v1.api.response.FuncionarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface Listar {

    //TODO: adicionar anotacoes para openapi
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/funcionarios", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<FuncionarioResponse> listar(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size);


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/api/v1/funcionarios/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FuncionarioResponse> obter(String cpf);
}
