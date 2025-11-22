package com.common.parser

internal sealed interface Token {
    val value: String

    companion object {
        private val allTokens: List<Token> by lazy {
            // サブクラスが増えるとここに追加しなければいけないが、自動でサブクラスを検出するにはreflectionが必要になる。
            Operator.entries + Value.entries
        }

        fun from(input: String): Token? =
            allTokens.firstOrNull { it.value == input.uppercase() }
    }
}

internal enum class Operator(override val value: String) : Token {
    AND("AND"),
    OR("OR"),
    XOR("XOR"),
    NOR("NOR"),
    NAND("NAND"),
    NOT("NOT"),
    LPAREN("("),
    RPAREN(")");
}

internal enum class Value(override val value: String) : Token {
    TRUE("TRUE"),
    FALSE("FALSE");
}
