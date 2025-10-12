package com.common.expression

import com.common.expression.Expression.Value

sealed interface Operator

enum class BinaryOperator(private val value: String) : Operator {
    AND("AND") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.TRUEVAL && right.evaluate() == Value.TRUEVAL) {
                return Value.TRUEVAL
            }
            return Value.FALSEVAL
        }
    },
    OR("OR") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.TRUEVAL || right.evaluate() == Value.TRUEVAL) {
                return Value.TRUEVAL
            }
            return Value.FALSEVAL
        }
    },
    XOR("XOR") {
        override fun execute(left: Expression, right: Expression): Value {
            return if (left.evaluate() != right.evaluate()) {
                Value.TRUEVAL
            } else {
                Value.FALSEVAL
            }
        }
    },
    NOR("NOR") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.FALSEVAL && right.evaluate() == Value.FALSEVAL) {
                return Value.TRUEVAL
            }
            return Value.FALSEVAL
        }
    },
    NAND("NAND") {
        override fun execute(left: Expression, right: Expression): Value {
            if (left.evaluate() == Value.TRUEVAL && right.evaluate() == Value.TRUEVAL) {
                return Value.FALSEVAL
            }
            return Value.TRUEVAL
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
                Value.TRUEVAL -> Value.FALSEVAL
                Value.FALSEVAL -> Value.TRUEVAL
        }
    };

    abstract fun execute(expression: Expression): Value

    fun toScript() = value

    operator fun invoke(expression: Expression) = execute(expression)
}
