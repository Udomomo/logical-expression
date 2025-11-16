package com.common.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TokenizerTest {
    @Test
    @DisplayName("入力した式を正しくtokenに変換できること")
    fun `should correctly parse input`() {
        val input = "TRUE AND ( FALSE OR NOT TRUE )"
        val expectedTokens: List<Token> = listOf(
            Value.TRUE,
            Operator.AND,
            Operator.LPAREN,
            Value.FALSE,
            Operator.OR,
            Operator.NOT,
            Value.TRUE,
            Operator.RPAREN
        )
        val actualTokens = Tokenizer.execute(input)
        Assertions.assertEquals(actualTokens, expectedTokens)
    }

    @Test
    @DisplayName("入力した式で、カッコと値にスペースがなくても正しくtokenに変換できること")
    fun `should correctly parse input when parenthesis is not split`() {
        val input = "TRUE AND (FALSE OR NOT TRUE)"
        val expectedTokens: List<Token> = listOf(
            Value.TRUE,
            Operator.AND,
            Operator.LPAREN,
            Value.FALSE,
            Operator.OR,
            Operator.NOT,
            Value.TRUE,
            Operator.RPAREN
        )
        val actualTokens = Tokenizer.execute(input)
        Assertions.assertEquals(actualTokens, expectedTokens)
    }

    @Test
    @DisplayName("入力した式が小文字でも正しくtokenに変換できること")
    fun `should correctly parse input when value and operator is lowercase`() {
        val input = "true and ( false or not true )"
        val expectedTokens: List<Token> = listOf(
            Value.TRUE,
            Operator.AND,
            Operator.LPAREN,
            Value.FALSE,
            Operator.OR,
            Operator.NOT,
            Value.TRUE,
            Operator.RPAREN
        )
        val actualTokens = Tokenizer.execute(input)
        Assertions.assertEquals(actualTokens, expectedTokens)
    }
}
