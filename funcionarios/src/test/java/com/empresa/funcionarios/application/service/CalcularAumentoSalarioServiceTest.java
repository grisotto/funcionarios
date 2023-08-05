package com.empresa.funcionarios.application.service;

import com.empresa.funcionarios.adapter.out.postgres.entity.FuncionarioEntity;
import com.empresa.funcionarios.application.port.out.postgres.FuncionarioRepository;
import com.empresa.funcionarios.domain.Funcionario;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalcularAumentoSalarioServiceTest {
    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private CalcularAumentoSalarioService calcularAumentoSalarioService;

    @ParameterizedTest
    @MethodSource("salaryProvider")
    void testCalcularAumentoSalario(BigDecimal salary, BigDecimal novoSalarioEsperado) {
        FuncionarioEntity funcionarioEntity = new FuncionarioEntity();
        funcionarioEntity.setCpf("12345678900");
        funcionarioEntity.setSalario(salary);

        when(funcionarioRepository.findByCpf(any())).thenReturn(Optional.of(funcionarioEntity));

        Funcionario funcionario = calcularAumentoSalarioService.calcular("12345678900");

        verify(funcionarioRepository).findByCpf("12345678900");

        verify(funcionarioRepository).save(funcionarioEntity);

        BigDecimal novoSalario = funcionario.getSalario();
        assertEquals(novoSalarioEsperado, novoSalario);
    }

    private static Stream<Arguments> salaryProvider() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(200), new BigDecimal("230.00")),
                Arguments.of(BigDecimal.valueOf(400), new BigDecimal("460.00")),
                Arguments.of(BigDecimal.valueOf(600), new BigDecimal("672.00")),
                Arguments.of(BigDecimal.valueOf(800), new BigDecimal("896.00")),
                Arguments.of(BigDecimal.valueOf(1000), new BigDecimal("1100.00")),
                Arguments.of(BigDecimal.valueOf(1200), new BigDecimal("1320.00")),
                Arguments.of(BigDecimal.valueOf(1500), new BigDecimal("1605.00")),
                Arguments.of(BigDecimal.valueOf(2000), new BigDecimal("2140.00")),
                Arguments.of(BigDecimal.valueOf(2500), new BigDecimal("2600.00")),
                Arguments.of(BigDecimal.valueOf(5000), new BigDecimal("5200.00")),
                Arguments.of(BigDecimal.valueOf(10000), new BigDecimal("10400.00"))
        );
    }
}
