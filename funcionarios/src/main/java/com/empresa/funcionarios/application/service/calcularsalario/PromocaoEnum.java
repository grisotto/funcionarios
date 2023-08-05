package com.empresa.funcionarios.application.service.calcularsalario;

import java.math.BigDecimal;

public enum PromocaoEnum {
    INTERVALO1(BigDecimal.ZERO, new BigDecimal("400.00"), new BigDecimal("0.15")),
    INTERVALO2(new BigDecimal("400.01"), new BigDecimal("800.00"), new BigDecimal("0.12")),
    INTERVALO3(new BigDecimal("800.01"), new BigDecimal("1200.00"), new BigDecimal("0.10")),
    INTERVALO4(new BigDecimal("1200.01"), new BigDecimal("2000.00"), new BigDecimal("0.07")),
    INTERVALO5(new BigDecimal("2000.01"), new BigDecimal(Long.MAX_VALUE), new BigDecimal("0.04"));

    private final BigDecimal min;
    private final BigDecimal max;
    private final BigDecimal porcentagem;

    PromocaoEnum(BigDecimal min, BigDecimal max, BigDecimal porcentagem) {
        this.min = min;
        this.max = max;
        this.porcentagem = porcentagem;
    }

    public BigDecimal getMin() {
        return min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public BigDecimal getPorcentagem() {
        return porcentagem;
    }

    public static BigDecimal calcularPorcentagem(BigDecimal salario) {
        for (PromocaoEnum range : PromocaoEnum.values()) {
            if (salario.compareTo(range.min) >= 0 && salario.compareTo(range.max) <= 0) {
                return range.porcentagem;
            }
        }
        throw new IllegalArgumentException("Salario do funcionario é invalido: " + salario);
    }


}
