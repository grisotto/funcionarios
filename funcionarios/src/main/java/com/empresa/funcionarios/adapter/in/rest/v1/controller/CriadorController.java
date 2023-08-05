package com.empresa.funcionarios.adapter.in.rest.v1.controller;

import com.empresa.funcionarios.adapter.in.rest.v1.api.Criar;
import com.empresa.funcionarios.adapter.in.rest.v1.api.request.FuncionarioRequest;
import com.empresa.funcionarios.adapter.in.rest.v1.api.response.FuncionarioResponse;
import com.empresa.funcionarios.application.port.in.CriarFuncionarioUseCase;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CriadorController implements Criar {

    private static final Logger log = LoggerFactory.getLogger(CriadorController.class);
    private final CriarFuncionarioUseCase service;

    public CriadorController(CriarFuncionarioUseCase service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<FuncionarioResponse> criar(FuncionarioRequest request) {
        log.info("Cadastrando um funcionando com o cpf {}", request.getCpf());
        Funcionario funcionario = service.criar(new Funcionario(request));

        log.info("Cadastrado o funcionando com o cpf {}", request.getCpf());
        return ResponseEntity.created(funcionario.getUri()).body(new FuncionarioResponse(funcionario));

    }
}
