package com.empresa.funcionarios.application.port.in;

import com.empresa.funcionarios.domain.Funcionario;
import org.springframework.data.domain.Page;


public interface ListarUseCase {

    Page<Funcionario> listar(int page, int size);

    Funcionario obter(String cpf);

    boolean existe(String cpf);
}
