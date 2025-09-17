package com.example

import com.common.expression.FALSE
import com.common.expression.TRUE
import com.common.expression.and

fun main() {
    val expression = TRUE.and(FALSE)
    println("Expression: ${expression.toScript()}")
    println("Result: ${expression.evaluate().toScript()}")
}