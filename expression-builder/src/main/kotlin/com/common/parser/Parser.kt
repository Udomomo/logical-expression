package com.common.parser

import java.util.Stack

class Parser {
    private val operatorStack = Stack<Operator>()

    fun execute(inputs: List<Token>): List<Token> {
        val result = mutableListOf<Token>()
        inputs.forEach {token ->
            parse(token)?.let { result.add(it) }
        }
        while (operatorStack.isNotEmpty()) {
            result.add(operatorStack.pop())
        }
        return result
    }

    private fun parse(token: Token): Token? {
        return if (token is Operator) nextOperator(token) else token
    }

    private fun nextOperator(input: Operator): Operator? {
        if (operatorStack.isEmpty()) {
            operatorStack.push(input)
            return null
        }

        // NOTは単項演算子であり、直前にValueが来ることはないため、入力として来たら無条件でstackに入れる。
        if (input == Operator.NOT) {
            operatorStack.push(input)
            return null
        }

        val top = operatorStack.peek()
        return if (precedence(input) > precedence(top)) {
            input
        } else {
            val popped = operatorStack.pop()
            operatorStack.push(input)
            return popped
        }
    }

    private fun precedence(input: Operator): Int {
        return when (input) {
            Operator.NOT -> 2
            else -> 1
        }
    }
}
