package com.empresa.funcionarios.application.service;

import com.empresa.funcionarios.application.port.in.CalcularImpostoUseCase;
import com.empresa.funcionarios.application.port.in.ListarUseCase;
import com.empresa.funcionarios.application.service.calcularimposto.AliquotaEnum;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalcularImpostoService implements CalcularImpostoUseCase {

    private static final Logger log = LoggerFactory.getLogger(CalcularImpostoService.class);

    private final ListarUseCase listador;

    public CalcularImpostoService(ListarUseCase listador) {
        this.listador = listador;
    }

    @Transactional
    @Override
    public Funcionario calcular(String cpf) {
        log.info("Calculando o imposto do salario do funcionario com o cpf {}", cpf);
        Funcionario funcionario = listador.obter(cpf);
        funcionario.setImposto("Isento");

        BigDecimal totalImposto = calculaImpostoDevido(funcionario.getSalario());
        if (totalImposto.compareTo(BigDecimal.ZERO) > 0) {
            funcionario.setImposto("R$ " + totalImposto);
        }

        log.info("Imposto devido é {} do funcionario com o cpf {}", funcionario.getImposto(), cpf);
        return funcionario;
    }

    public static BigDecimal calculaImpostoDevido(BigDecimal salario) {
        return BigDecimal.ZERO
                .add(calculaImpostoIntervalo(salario, AliquotaEnum.INTERVALO1))
                .add(calculaImpostoIntervalo(salario, AliquotaEnum.INTERVALO2))
                .add(calculaImpostoIntervalo(salario, AliquotaEnum.INTERVALO3))
                .add(calculaImpostoIntervalo(salario, AliquotaEnum.INTERVALO4))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private static BigDecimal calculaImpostoIntervalo(BigDecimal salario, AliquotaEnum aliquota) {
        BigDecimal intervaloMin = aliquota.getMin();
        BigDecimal intervaloMax = aliquota.getMax();
        BigDecimal valorImposto = (salario.compareTo(intervaloMax) <= 0)
                ? salario.subtract(intervaloMin)
                : intervaloMax.subtract(intervaloMin);
        BigDecimal valorFinal = valorImposto.multiply(aliquota.getPorcentagem());

        if (valorFinal.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return valorFinal;
    }
}
