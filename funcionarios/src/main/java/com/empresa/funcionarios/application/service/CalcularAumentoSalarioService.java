package com.empresa.funcionarios.application.service;

import com.empresa.funcionarios.adapter.out.postgres.entity.FuncionarioEntity;
import com.empresa.funcionarios.application.port.in.CalcularAumentoSalarioUseCase;
import com.empresa.funcionarios.application.port.in.ListarUseCase;
import com.empresa.funcionarios.application.port.out.postgres.FuncionarioRepository;
import com.empresa.funcionarios.application.service.calcularsalario.PromocaoEnum;
import com.empresa.funcionarios.domain.AumentoSalario;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalcularAumentoSalarioService implements CalcularAumentoSalarioUseCase {


    private static final Logger log = LoggerFactory.getLogger(CalcularAumentoSalarioService.class);
    private final FuncionarioRepository repository;

    public CalcularAumentoSalarioService(FuncionarioRepository repository, ListarUseCase listador) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Funcionario calcular(String cpf) {
        log.info("Calcular o novo salario do funcionario com o cpf {}", cpf);
        FuncionarioEntity funcionario = repository.findByCpf(cpf).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));

        BigDecimal porcentagemAumento = PromocaoEnum.calcularPorcentagem(funcionario.getSalario());
        log.info("Porcentagem de aumento {} do funcionario cpf {}", porcentagemAumento, cpf);

        BigDecimal aumentoReal = funcionario.getSalario()
                .multiply(porcentagemAumento).setScale(2, RoundingMode.HALF_UP);
        log.info("Aumento real {} do funcionario cpf {}", aumentoReal, cpf);

        BigDecimal novoSalario = funcionario.getSalario().add(aumentoReal).setScale(2, RoundingMode.HALF_UP);
        log.info("Novo salario {} do funcionario cpf {}", novoSalario, cpf);
        funcionario.setSalario(novoSalario);
        repository.save(funcionario);

        Funcionario domain = new Funcionario(funcionario);

        domain.setAumentoSalario(new AumentoSalario(aumentoReal, porcentagemAumento.movePointRight(2)));
        log.info("Calculou o aumento de salario do funcionario com o cpf {}", cpf);

        return domain;
    }
}
