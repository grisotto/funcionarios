package com.empresa.funcionarios.application.service.calcularimposto;

import java.math.BigDecimal;

public enum AliquotaEnum {
    INTERVALO1(new BigDecimal("0.00"), new BigDecimal("2000.00"), BigDecimal.ZERO),
    INTERVALO2(new BigDecimal("2000.01"), new BigDecimal("3000.00"), new BigDecimal("0.08")),
    INTERVALO3(new BigDecimal("3000.01"), new BigDecimal("4500.00"), new BigDecimal("0.18")),
    INTERVALO4(new BigDecimal("4500.01"), new BigDecimal(Long.MAX_VALUE), new BigDecimal("0.28"));

    private final BigDecimal min;
    private final BigDecimal max;
    private final BigDecimal porcentagem;

    AliquotaEnum(BigDecimal min, BigDecimal max, BigDecimal porcentagem) {
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

}
