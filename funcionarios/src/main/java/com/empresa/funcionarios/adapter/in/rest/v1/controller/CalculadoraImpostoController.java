package com.empresa.funcionarios.adapter.in.rest.v1.controller;

import com.empresa.funcionarios.adapter.in.rest.v1.api.CalcularImposto;
import com.empresa.funcionarios.adapter.in.rest.v1.api.response.ImpostoResponse;
import com.empresa.funcionarios.application.port.in.CalcularImpostoUseCase;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraImpostoController implements CalcularImposto {

    private static final Logger log = LoggerFactory.getLogger(CalculadoraImpostoController.class);

    private final CalcularImpostoUseCase service;

    public CalculadoraImpostoController(CalcularImpostoUseCase service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ImpostoResponse> calculo(String cpf) {
        log.info("Calculando o imposto funcionando com o cpf {}", cpf);
        Funcionario funcionario = service.calcular(cpf);

        log.info("Calculou o imposto funcionando com o cpf {}", cpf);
        return ResponseEntity.ok(new ImpostoResponse(funcionario));
    }
}
