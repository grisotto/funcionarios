package com.empresa.funcionarios.application.service;

import com.empresa.funcionarios.adapter.out.postgres.entity.FuncionarioEntity;
import com.empresa.funcionarios.application.port.in.ListarUseCase;
import com.empresa.funcionarios.application.port.out.postgres.FuncionarioRepository;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ListadorService implements ListarUseCase {

    private static final Logger log = LoggerFactory.getLogger(ListadorService.class);
    private final FuncionarioRepository repository;

    public ListadorService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Page<Funcionario> listar(int page, int size) {

        log.info("Buscando fujcionarios");
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Funcionario> funcionarios;
        try {
            Page<FuncionarioEntity> funcionarioEntities = repository.findAll(pageRequest);
            log.info("buscou funcionarios");
            funcionarios = funcionarioEntities.map(Funcionario::new);
        } catch (DataAccessException ex) {
            log.error("Falha em buscar funcionarios", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na busca de funcionarios");
        }
        return funcionarios;
    }

    @Override
    public Funcionario obter(String cpf) {
        log.info("Buscando funcionario com cpf: {}", cpf);
        try {
            return repository.findByCpf(cpf).map(Funcionario::new)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));

        } catch (DataAccessException ex) {
            log.error("Falha em buscar funcionario", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na busca de funcionario");
        }
    }

    @Override
    public boolean existe(String cpf) {
        log.info("Verificando se o funcionario com o cpf {} existe", cpf);
        try {
            return repository.existsByCpf(cpf);
        } catch (DataAccessException ex) {
            log.error("Falha em verificar se funcionario existe", ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha em verificar se funcionario existe");
        }
    }
}
