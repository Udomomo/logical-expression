package com.common.expression

import com.common.expression.Expression.Value

sealed interface Operator

enum class BinaryOperator(private val value: String) : Operator {
    AND("AND") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.TRUE && right.evaluate() == Value.TRUE) {
                return Value.TRUE
            }
            return Value.FALSE
        }
    },
    OR("OR") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.TRUE || right.evaluate() == Value.TRUE) {
                return Value.TRUE
            }
            return Value.FALSE
        }
    },
    XOR("XOR") {
        override fun execute(left: Expression, right: Expression): Value {
            return if (left.evaluate() != right.evaluate()) {
                Value.TRUE
            } else {
                Value.FALSE
            }
        }
    },
    NOR("NOR") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.FALSE && right.evaluate() == Value.FALSE) {
                return Value.TRUE
            }
            return Value.FALSE
        }
    },
    NAND("NAND") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.TRUE && right.evaluate() == Value.TRUE) {
                return Value.FALSE
            }
            return Value.TRUE
        }
    };

    abstract fun execute(left: Expression, right: Expression): Value
    fun toScript() = value

    // 関数呼び出し演算子を定義し、operator()の形式で呼び出せるようにする
    operator fun invoke(left: Expression, right: Expression) = execute(left, right)
}

enum class UnaryOperator(private val value: String) : Operator {
    NOT("NOT") {
        override fun execute(expression: Expression) =
            when (expression.evaluate()) {
                Value.TRUE -> Value.FALSE
                Value.FALSE -> Value.TRUE
        }
    };

    abstract fun execute(expression: Expression): Value
    fun toScript() = value

    operator fun invoke(expression: Expression) = execute(expression)
}
