package com.empresa.funcionarios.application.port.in;

import com.empresa.funcionarios.domain.Funcionario;

@FunctionalInterface
public interface CalcularImpostoUseCase {

    Funcionario calcular(String cpf);
}
