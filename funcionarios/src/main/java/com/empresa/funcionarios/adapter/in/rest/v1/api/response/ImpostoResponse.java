package com.empresa.funcionarios.adapter.in.rest.v1.api.response;

import com.empresa.funcionarios.domain.Funcionario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ImpostoResponse {

    private String cpf;

    private String imposto;

    public ImpostoResponse(Funcionario domain) {
        this.cpf = domain.getCpf();
        this.imposto = domain.getImposto();
    }

    public ImpostoResponse() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getImposto() {
        return imposto;
    }

    public void setImposto(String imposto) {
        this.imposto = imposto;
    }
}
