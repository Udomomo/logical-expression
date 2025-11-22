package com.common.parser

import java.util.Stack

internal class Parser {
    private val operatorStack = Stack<Operator>()

    fun execute(inputs: List<Token>): List<Token> {
        val result = mutableListOf<Token>()
        inputs.forEach {token ->
            if (token == Operator.RPAREN) {
                result.addAll(bulkPopByRparen())
            }
            else {
                parse(token)?.let { result.add(it) }
            }
        }


        // 最後のOperatorがstackに1つ残っているはずなのでpushする。
        // カッコが閉じているかとoperatorを使い切ったかはここで確認するが、
        // 操車場アルゴリズムは、組み立てた式のバリデーションをするものではないため、この後の評価の段階でエラーになればよいものとする。
        while (operatorStack.isNotEmpty()) {
            val remainedOperator = operatorStack.pop()
            // カッコが残っていた場合はカッコが閉じていないので、その場合は例外を返す。
            if (remainedOperator in listOf(Operator.LPAREN, Operator.RPAREN)) {
                throw IllegalStateException("Unmatched Parenthesis: $remainedOperator")
            }

            result.add(remainedOperator)
        }
        require(operatorStack.isEmpty()) { "Operator remains in stack: $operatorStack" }
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
        // また左カッコは右カッコを待つ必要があるので、入力として来たら無条件でstackに入れる。
        if (input == Operator.NOT || input == Operator.LPAREN) {
            operatorStack.push(input)
            return null
        }

        val top = operatorStack.peek()
        // stackのtopが左カッコの場合は、新しい式が始まっているので無条件でpushする。
        if (top == Operator.LPAREN) {
            operatorStack.push(input)
            return null
        }
        // それ以外の場合は優先度を比較する。
        return if (precedence(input) > precedence(top)) {
            input
        } else {
            val popped = operatorStack.pop()
            operatorStack.push(input)
            return popped
        }
    }

    private fun precedence(input: Operator): Int {
        require(input !in listOf(Operator.LPAREN, Operator.RPAREN ))
        return when (input) {
            Operator.NOT -> 2
            else -> 1
        }
    }

    // 右カッコが来たときのメソッド。
    // 対応する左カッコが見つかるまでpopする。
    private fun bulkPopByRparen(): List<Token> {
        val poppedOperators = mutableListOf<Operator>()
        while (operatorStack.isNotEmpty()) {
            val top = operatorStack.pop()
            if (top == Operator.LPAREN) {
                return poppedOperators
            } else {
                poppedOperators.add(top)
            }
        }

        throw IllegalStateException("Unmatched right parenthesis")
    }
}
