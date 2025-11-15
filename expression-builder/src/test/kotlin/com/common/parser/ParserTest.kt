package com.common.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParserTest {
    @Test
    @DisplayName("入力した式を正しくパースできること")
    fun `should correctly parse input`() {
        val input = "TRUE AND ( FALSE OR NOT TRUE )"
        val expectedTokens = listOf(
            Token.TRUE,
            Token.AND,
            Token.LPAREN,
            Token.FALSE,
            Token.OR,
            Token.NOT,
            Token.TRUE,
            Token.RPAREN
        )
        val actualTokens = Parser.parse(input)
        Assertions.assertEquals(actualTokens, expectedTokens)
    }

    @Test
    @DisplayName("入力した式で、カッコと値にスペースがなくても正しくパースできること")
    fun `should correctly parse input when parenthesis is not split`() {
        val input = "TRUE AND (FALSE OR NOT TRUE)"
        val expectedTokens = listOf(
            Token.TRUE,
            Token.AND,
            Token.LPAREN,
            Token.FALSE,
            Token.OR,
            Token.NOT,
            Token.TRUE,
            Token.RPAREN
        )
        val actualTokens = Parser.parse(input)
        Assertions.assertEquals(actualTokens, expectedTokens)
    }

    @Test
    @DisplayName("入力した式が小文字でも正しくパースできること")
    fun `should correctly parse input when value and operator is lowercase`() {
        val input = "true and ( false or not true )"
        val expectedTokens = listOf(
            Token.TRUE,
            Token.AND,
            Token.LPAREN,
            Token.FALSE,
            Token.OR,
            Token.NOT,
            Token.TRUE,
            Token.RPAREN
        )
        val actualTokens = Parser.parse(input)
        Assertions.assertEquals(actualTokens, expectedTokens)
    }
}