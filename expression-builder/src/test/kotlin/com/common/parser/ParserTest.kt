package com.common.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ParserTest {
    @Test
    fun `should correctly parse input`() {
        // Parser.parse()に式を渡し、正しいList<Token>になるかのテストを書く
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
    fun `should correctly parse input when parenthesis is not split`() {
        // Parser.parse()に式を渡し、正しいList<Token>になるかのテストを書く
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
}