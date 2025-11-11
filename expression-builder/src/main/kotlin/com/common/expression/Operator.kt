package com.common.expression

import com.common.expression.ExpressionImpl.ValueImpl

internal sealed interface Operator

internal enum class BinaryOperator(private val value: String) : Operator {
    AND("AND") {
        override fun execute(left: ExpressionImpl, right: ExpressionImpl): ValueImpl {
            if (left.evaluate() == ValueImpl.TRUE && right.evaluate() == ValueImpl.TRUE) {
                return ValueImpl.TRUE
            }
            return ValueImpl.FALSE
        }
    },
    OR("OR") {
        override fun execute(left: ExpressionImpl, right: ExpressionImpl): ValueImpl {
            if (left.evaluate() == ValueImpl.TRUE || right.evaluate() == ValueImpl.TRUE) {
                return ValueImpl.TRUE
            }
            return ValueImpl.FALSE
        }
    },
    XOR("XOR") {
        override fun execute(left: ExpressionImpl, right: ExpressionImpl): ValueImpl {
            return if (left.evaluate() != right.evaluate()) {
                ValueImpl.TRUE
            } else {
                ValueImpl.FALSE
            }
        }
    },
    NOR("NOR") {
        override fun execute(left: ExpressionImpl, right: ExpressionImpl): ValueImpl {
            if (left.evaluate() == ValueImpl.FALSE && right.evaluate() == ValueImpl.FALSE) {
                return ValueImpl.TRUE
            }
            return ValueImpl.FALSE
        }
    },
    NAND("NAND") {
        override fun execute(left: ExpressionImpl, right: ExpressionImpl): ValueImpl {
            if (left.evaluate() == ValueImpl.TRUE && right.evaluate() == ValueImpl.TRUE) {
                return ValueImpl.FALSE
            }
            return ValueImpl.TRUE
        }
    };

    abstract fun execute(left: ExpressionImpl, right: ExpressionImpl): ValueImpl
    fun toScript() = value

    // 関数呼び出し演算子を定義し、operator()の形式で呼び出せるようにする
    operator fun invoke(left: ExpressionImpl, right: ExpressionImpl) = execute(left, right)
}

internal enum class UnaryOperator(private val value: String) : Operator {
    NOT("NOT") {
        override fun execute(expressionImpl: ExpressionImpl) =
            when (expressionImpl.evaluate()) {
                ValueImpl.TRUE -> ValueImpl.FALSE
                ValueImpl.FALSE -> ValueImpl.TRUE
        }
    };

    abstract fun execute(expressionImpl: ExpressionImpl): ValueImpl

    fun toScript() = value

    operator fun invoke(expressionImpl: ExpressionImpl) = execute(expressionImpl)
}
