package com.empresa.funcionarios.domain;

import com.empresa.funcionarios.adapter.in.rest.v1.api.request.FuncionarioRequest;
import com.empresa.funcionarios.adapter.out.postgres.entity.FuncionarioEntity;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.Objects;

public class Funcionario {

    private String nome;

    private String cpf;
    private Date dataNascimento;

    private String telefone;

    private String endereco;

    private BigDecimal salario;

    private URI uri;

    private AumentoSalario aumentoSalario;
    private String imposto;

    public Funcionario(String nome, String cpf, Date dataNascimento, String telefone, String endereco, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Funcionario() {
    }

    public Funcionario(FuncionarioRequest request) {
        this(request.getNome(), request.getCpf(), request.getDataNascimento(), request.getTelefone(), request.getEndereco(), request.getSalario());
    }

    public Funcionario(FuncionarioEntity entity) {
        this(entity.getNome(), entity.getCpf(), entity.getDataNascimento(), entity.getTelefone(), entity.getEndereco(), entity.getSalario());
    }

    @Override
    public String toString() {
        return "FuncionarioRequest{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", salario=" + salario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Funcionario that = (Funcionario) o;

        if (!Objects.equals(nome, that.nome)) return false;
        if (!cpf.equals(that.cpf)) return false;
        return Objects.equals(dataNascimento, that.dataNascimento);
    }

    @Override
    public int hashCode() {
        int result = nome != null ? nome.hashCode() : 0;
        result = 31 * result + cpf.hashCode();
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        return result;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public AumentoSalario getAumentoSalario() {
        return aumentoSalario;
    }

    public void setAumentoSalario(AumentoSalario aumentoSalario) {
        this.aumentoSalario = aumentoSalario;
    }

    public String getImposto() {
        return imposto;
    }

    public void setImposto(String imposto) {
        this.imposto = imposto;
    }
}
