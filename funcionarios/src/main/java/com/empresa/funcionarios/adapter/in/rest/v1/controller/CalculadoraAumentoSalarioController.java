package com.empresa.funcionarios.adapter.in.rest.v1.controller;

import com.empresa.funcionarios.adapter.in.rest.v1.api.CalcularAumentoSalario;
import com.empresa.funcionarios.adapter.in.rest.v1.api.response.AumentoSalarioResponse;
import com.empresa.funcionarios.application.port.in.CalcularAumentoSalarioUseCase;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraAumentoSalarioController implements CalcularAumentoSalario {

    private static final Logger log = LoggerFactory.getLogger(CalculadoraAumentoSalarioController.class);

    private final CalcularAumentoSalarioUseCase service;

    public CalculadoraAumentoSalarioController(CalcularAumentoSalarioUseCase service) {
        this.service = service;
    }


    @Override
    public ResponseEntity<AumentoSalarioResponse> calculo(String cpf) {
        log.info("Calculando o novo salario do funcionando com o cpf {}", cpf);
        Funcionario funcionario = service.calcular(cpf);

        log.info("Calculou o novo salario do funcionando com o cpf {}", cpf);
        return ResponseEntity.ok(new AumentoSalarioResponse(funcionario));
    }
}
