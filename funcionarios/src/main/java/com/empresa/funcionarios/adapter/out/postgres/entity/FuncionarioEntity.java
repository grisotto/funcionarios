package com.empresa.funcionarios.adapter.out.postgres.entity;


import com.empresa.funcionarios.domain.Funcionario;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity(name = "funcionario")
@Table(name = "funcionario")
@Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NaturalIdCache
public class FuncionarioEntity implements Serializable {
    private static final long serialVersionUID = -1790250484700128113L;

    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(length = 150)
    private String nome;
    @Column(name = "data_nascimento", length = 9)
    private Date dataNascimento;

    @Column(length = 14)
    private String telefone;
    @Column(length = 150)
    private String endereco;
    @Column(precision = 14, scale = 2)
    private BigDecimal salario;

    public FuncionarioEntity(String nome, String cpf, Date dataNascimento, String telefone, String endereco, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.salario = salario;
    }

    public FuncionarioEntity(Funcionario funcionario) {

        this.nome = funcionario.getNome();
        this.cpf = funcionario.getCpf();
        this.dataNascimento = funcionario.getDataNascimento();
        this.telefone = funcionario.getTelefone();
        this.endereco = funcionario.getEndereco();
        this.salario = funcionario.getSalario();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncionarioEntity that = (FuncionarioEntity) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    public FuncionarioEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }


}
