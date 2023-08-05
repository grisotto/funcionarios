package com.empresa.funcionarios.application.service.calcularsalario;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromocaoEnumTest {
    @ParameterizedTest
    @MethodSource("promocaoProvider")
    void testCalcularPorcentagem(BigDecimal salario, BigDecimal expectedPercentage) {
        BigDecimal calculatedPercentage = PromocaoEnum.calcularPorcentagem(salario);
        assertEquals(expectedPercentage, calculatedPercentage);
    }

    private static Stream<Arguments> promocaoProvider() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(400), new BigDecimal("0.15")),
                Arguments.of(BigDecimal.valueOf(600), new BigDecimal("0.12")),
                Arguments.of(BigDecimal.valueOf(800), new BigDecimal("0.12")),
                Arguments.of(BigDecimal.valueOf(1000), new BigDecimal("0.10")),
                Arguments.of(BigDecimal.valueOf(1200), new BigDecimal("0.10")),
                Arguments.of(BigDecimal.valueOf(1500), new BigDecimal("0.07")),
                Arguments.of(BigDecimal.valueOf(2000), new BigDecimal("0.07")),
                Arguments.of(BigDecimal.valueOf(2500), new BigDecimal("0.04")),
                Arguments.of(BigDecimal.valueOf(5000), new BigDecimal("0.04")),
                Arguments.of(BigDecimal.valueOf(Long.MAX_VALUE), new BigDecimal("0.04"))
        );
    }

}