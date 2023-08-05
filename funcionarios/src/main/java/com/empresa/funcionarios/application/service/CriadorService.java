package com.empresa.funcionarios.application.service;

import com.empresa.funcionarios.adapter.out.postgres.entity.FuncionarioEntity;
import com.empresa.funcionarios.application.port.in.CriarFuncionarioUseCase;
import com.empresa.funcionarios.application.port.in.ListarUseCase;
import com.empresa.funcionarios.application.port.out.postgres.FuncionarioRepository;
import com.empresa.funcionarios.domain.Funcionario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class CriadorService implements CriarFuncionarioUseCase {
    private static final Logger log = LoggerFactory.getLogger(CriadorService.class);
    private final FuncionarioRepository repository;

    private final ListarUseCase listador;


    public CriadorService(FuncionarioRepository repository, ListarUseCase listador) {
        this.repository = repository;
        this.listador = listador;
    }

    @Override
    @Transactional
    public Funcionario criar(Funcionario funcionario) {
        log.info("Criando funcionario com cpf: {}", funcionario.getCpf());
        if (listador.existe(funcionario.getCpf())) {
            log.error("Falha em criar funcionario. Funcionario com cpf: {} já cadastrado", funcionario.getCpf());
            //TODO: ver qual status devolver quando já existir
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "CPF já cadastrado");
        }
        try {
            var funcionarioSalvo = repository.save(new FuncionarioEntity(funcionario));
            log.info("Salvo funcionario com cpf: {} e id = {}", funcionarioSalvo.getCpf(), funcionarioSalvo.getId());
        } catch (DataAccessException ex) {
            log.error("Falha em salvar funcionario. Funcionario com cpf: {}. Erro ", funcionario.getCpf(), ex);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na persistencia");

        }
        funcionario.setUri(buildURI(funcionario.getCpf()));
        log.info("Funcionario com cpf: {} criado com sucesso", funcionario.getCpf());
        return funcionario;
    }

    @Nullable
    private URI buildURI(String cpf) {
        try {
            HttpServletRequest request2 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String serverAddress = request2.getServerName();
            int serverPort = request2.getServerPort();
            String uri = "http://" + serverAddress + ":" + serverPort + "api/v1/funcionarios/" + cpf;
            return new URI(uri);
        } catch (URISyntaxException e) {
            log.warn("Falhou em criar a URI. Ignorando essa exceção", e);
            return null;
        }
    }
}
