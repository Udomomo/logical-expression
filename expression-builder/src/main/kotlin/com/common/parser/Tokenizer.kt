package com.common.parser

// 入力された文字列をtokenに変換する
object Tokenizer {
    fun execute(input: String): List<Token> {
        val tokens = input
            .split(" ")
            .separateParenthesis()
            .map {
                Token.from(it)
                    ?: throw IllegalArgumentException("Invalid token: $it")
            }

        return tokens
    }

    /**
     * カッコは他のトークンとスペースなしで書かれている場合があるため、それらを分離するユーティリティ関数。
     */
    private fun List<String>.separateParenthesis(): List<String> =
        this.flatMap { token ->
            when {
                token == Operator.LPAREN.value || token == Operator.RPAREN.value ->
                    listOf(token)
                token == Operator.LPAREN.value + Operator.RPAREN.value ->
                    listOf(Operator.LPAREN.value, Operator.RPAREN.value)
                token.startsWith(Operator.LPAREN.value) && token.endsWith(Operator.RPAREN.value) ->
                    listOf(Operator.LPAREN.value, token.substring(1, token.length - 1), Operator.RPAREN.value)
                token.startsWith(Operator.LPAREN.value) ->
                    listOf(Operator.LPAREN.value, token.substring(1))
                token.endsWith(Operator.RPAREN.value) ->
                    listOf(token.dropLast(1), Operator.RPAREN.value)
                else -> listOf(token)
            }
        }
}