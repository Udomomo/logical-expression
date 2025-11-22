package com.common.parser

import com.common.expression.Expression
import com.common.expression.FALSE
import com.common.expression.TRUE
import com.common.expression.and
import com.common.expression.nand
import com.common.expression.nor
import com.common.expression.not
import com.common.expression.or
import com.common.expression.xor
import java.util.Stack

/**
 * パース済みのtokenリストから、ExpressionBuilderを使ってExpressionを作る。
 */
object ExpressionCreator {
    fun create(tokens: List<Token>): Expression {
        // Parserで操車場アルゴリズムを使ったことで、Stackを用いて前から順番に処理できる。
        var intermediate = Stack<Expression>()

        for (token in tokens) {
            when (token) {
                Value.TRUE -> {
                    intermediate.add(TRUE)
                }
                Value.FALSE -> {
                    intermediate.add(FALSE)
                }
                Operator.AND -> {
                    val (left, right) = popOperands(intermediate)
                    intermediate.add(left.and(right))
                }
                Operator.OR -> {
                    val (left, right) = popOperands(intermediate)
                    intermediate.add(left.or(right))
                }
                Operator.NAND -> {
                    val (left, right) = popOperands(intermediate)
                    intermediate.add(left.nand(right))
                }
                Operator.NOR -> {
                    val (left, right) = popOperands(intermediate)
                    intermediate.add(left.nor(right))
                }
                Operator.XOR -> {
                    val (left, right) = popOperands(intermediate)
                    intermediate.add(left.xor(right))
                }
                Operator.NOT -> {
                    val operand = intermediate.pop()
                    intermediate.add(operand.not())
                }
                else -> {
                    throw IllegalArgumentException("This operator should not remain after parsing: $token")
                }
            }
        }

        val result = intermediate.pop()
        require(intermediate.isEmpty())
        return result
    }

    private fun popOperands(intermediate: Stack<Expression>): Pair<Expression, Expression> {
        val right = intermediate.pop()
        val left = intermediate.pop()
        return Pair(left, right)
    }
}