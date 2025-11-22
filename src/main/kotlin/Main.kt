package com.example

import com.common.expression.FALSE
import com.common.expression.TRUE
import com.common.expression.and
import com.common.parser.Calculator

fun main() {
    val expression = TRUE.and(FALSE)
    println("Expression: ${expression.toScript()}")
    println("Result: ${expression.evaluate().toScript()}")

    val rawExpression = "TRUE AND NOT (TRUE OR FALSE)"
    println("Expression: $rawExpression")
    println("Result: ${Calculator.run(rawExpression)}")
}