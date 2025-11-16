package com.common.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParserTest {
    @Test
    @DisplayName("式を正しくパースできること")
    fun `should correctly parse expression`() {
        // Arrange
        val input: List<Token> = listOf(
            Value.TRUE,
            Operator.AND,
            Value.FALSE,
            Operator.OR,
            Value.TRUE,
        )

        val expect: List<Token> = listOf(
            Value.TRUE,
            Value.FALSE,
            Operator.AND,
            Value.TRUE,
            Operator.OR,
        )

        // Act
        val result = Parser().execute(input)

        // Assert
        Assertions.assertEquals(expect, result)
    }

    @Test
    @DisplayName("NOTが途中にある式を正しくパースできること")
    fun `should correctly parse expression with NOT`() {
        // Arrange
        val input: List<Token> = listOf(
            Value.TRUE,
            Operator.AND,
            Value.FALSE,
            Operator.OR,
            Operator.NOT,
            Value.TRUE,
        )

        val expect: List<Token> = listOf(
            Value.TRUE,
            Value.FALSE,
            Operator.AND,
            Value.TRUE,
            Operator.NOT,
            Operator.OR,
        )

        // Act
        val result = Parser().execute(input)

        // Assert
        Assertions.assertEquals(expect, result)
    }

    @Test
    @DisplayName("NOTが最初にある式を正しくパースできること")
    fun `should correctly parse expression starting with NOT`() {
        // Arrange
        val input: List<Token> = listOf(
            Operator.NOT,
            Value.TRUE,
            Operator.AND,
            Value.FALSE,
            Operator.OR,
            Value.TRUE,
        )

        val expect: List<Token> = listOf(
            Value.TRUE,
            Operator.NOT,
            Value.FALSE,
            Operator.AND,
            Value.TRUE,
            Operator.OR,
        )

        // Act
        val result = Parser().execute(input)

        // Assert
        Assertions.assertEquals(expect, result)
    }
}