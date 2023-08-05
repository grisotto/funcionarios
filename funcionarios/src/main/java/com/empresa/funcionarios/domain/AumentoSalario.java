package com.empresa.funcionarios.domain;

import java.math.BigDecimal;

public class AumentoSalario {

    private BigDecimal aumentoReal;
    private BigDecimal porcentagem;

    public AumentoSalario(BigDecimal aumentoReal, BigDecimal porcentagem) {
        this.aumentoReal = aumentoReal;
        this.porcentagem = porcentagem;
    }

    public AumentoSalario() {
    }

    public BigDecimal getAumentoReal() {
        return aumentoReal;
    }

    public void setAumentoReal(BigDecimal aumentoReal) {
        this.aumentoReal = aumentoReal;
    }

    public BigDecimal getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(BigDecimal porcentagem) {
        this.porcentagem = porcentagem;
    }

}
