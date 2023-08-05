package com.empresa.funcionarios.adapter.in.rest.v1.api.response;

import com.empresa.funcionarios.domain.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AumentoSalarioResponse {

    private String cpf;

    private BigDecimal novoSalario;
    private BigDecimal porcentual;
    private BigDecimal reajusteGanho;

    public AumentoSalarioResponse() {
    }

    public AumentoSalarioResponse(Funcionario domain) {
        this.cpf = domain.getCpf();
        this.novoSalario = domain.getSalario();
        this.porcentual = domain.getAumentoSalario().getPorcentagem();
        this.reajusteGanho = domain.getAumentoSalario().getAumentoReal();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getNovoSalario() {
        return novoSalario;
    }

    public void setNovoSalario(BigDecimal novoSalario) {
        this.novoSalario = novoSalario;
    }

    public BigDecimal getPorcentual() {
        return porcentual;
    }

    public void setPorcentual(BigDecimal porcentual) {
        this.porcentual = porcentual;
    }

    public BigDecimal getReajusteGanho() {
        return reajusteGanho;
    }

    public void setReajusteGanho(BigDecimal reajusteGanho) {
        this.reajusteGanho = reajusteGanho;
    }
}
