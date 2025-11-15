package com.common.parser

import com.common.expression.Expression

// 最終的にExpressionBuilderを使ってExpressionを組み立てる
object Parser {
    fun parse(input: String): List<Token> {
        val tokens = input
            .split(" ")
            .separateParenthesis()
            .map {
                Token.from(it)
                    ?: throw IllegalArgumentException("Invalid token: $it")
            }

        return tokens

        // 操車場アルゴリズムで式を作れるか試してみる。
        // テストを先に書きながら検証した方が良さそう。
        TODO("Not yet implemented")
    }

    /**
     * カッコは他のトークンとスペースなしで書かれている場合があるため、それらを分離するユーティリティ関数。
     */
    private fun List<String>.separateParenthesis(): List<String> =
        this.flatMap { token ->
            when {
                token == Token.LPAREN.value || token == Token.RPAREN.value ->
                    listOf(token)
                token == Token.LPAREN.value + Token.RPAREN.value ->
                    listOf(Token.LPAREN.value, Token.RPAREN.value)
                token.startsWith(Token.LPAREN.value) && token.endsWith(Token.RPAREN.value) -> 
                    listOf(Token.LPAREN.value, token.substring(1, token.length - 1), Token.RPAREN.value)
                token.startsWith(Token.LPAREN.value) -> 
                    listOf(Token.LPAREN.value, token.substring(1))
                token.endsWith(Token.RPAREN.value) -> 
                    listOf(token.dropLast(1), Token.RPAREN.value)
                else -> listOf(token)
            }
        }
}