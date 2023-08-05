package com.empresa.funcionarios.adapter.in.rest.v1.controller;

import com.empresa.funcionarios.adapter.in.rest.v1.api.Listar;
import com.empresa.funcionarios.adapter.in.rest.v1.api.response.FuncionarioResponse;
import com.empresa.funcionarios.application.port.in.ListarUseCase;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class ListadorController implements Listar {

    private static final Logger log = LoggerFactory.getLogger(ListadorController.class);

    private final ListarUseCase listador;

    public ListadorController(ListarUseCase listador) {
        this.listador = listador;
    }

    @Override
    public Page<FuncionarioResponse> listar(int page, int size) {
        log.info("Listando funcionarios");
        Page<Funcionario> funcionarios = listador.listar(page, size);

        log.info("Buscou funcionarios");
        return funcionarios.map(FuncionarioResponse::new);
    }

    @Override
    public ResponseEntity<FuncionarioResponse> obter(@PathVariable("cpf") @Valid @NotNull(message = "CPF obrigatório") String cpf) {
        log.info("Buscando funcionario com cpf: {}", cpf);
        Funcionario funcionario = listador.obter(cpf);

        log.info("Buscou o funcionando com o cpf {}", cpf);
        return ResponseEntity.ok(new FuncionarioResponse(funcionario));
    }
}
