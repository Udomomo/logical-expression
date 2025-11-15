package com.common.parser

enum class Token(val value: String) {
    AND("AND"),
    OR("OR"),
    XOR("XOR"),
    NOR("NOR"),
    NAND("NAND"),
    NOT("NOT"),
    TRUE("TRUE"),
    FALSE("FALSE"),
    LPAREN("("),
    RPAREN(")");

    companion object {
        fun from(input: String) =
            entries.firstOrNull { it.value == input.uppercase() }
    }
}
