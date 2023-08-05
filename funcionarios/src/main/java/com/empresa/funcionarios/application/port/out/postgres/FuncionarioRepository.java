package com.empresa.funcionarios.application.port.out.postgres;

import com.empresa.funcionarios.adapter.out.postgres.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Long> {
    Optional<FuncionarioEntity> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}
