package com.common.expression

// TODO: カッコに対応する

/**
 * Expressionクラスのビルダー。パッケージ外に公開している。
 */
fun Expression.and(other: Expression) =
    Expression(ExpressionImpl.BinaryExpressionImpl(this.expr, BinaryOperator.AND, other.expr))

fun Expression.or(other: Expression) =
    Expression(ExpressionImpl.BinaryExpressionImpl(this.expr, BinaryOperator.OR, other.expr))

fun Expression.xor(other: Expression) =
    Expression(ExpressionImpl.BinaryExpressionImpl(this.expr, BinaryOperator.XOR, other.expr))

fun Expression.nor(other: Expression) =
    Expression(ExpressionImpl.BinaryExpressionImpl(this.expr, BinaryOperator.NOR, other.expr))

fun Expression.nand(other: Expression) =
    Expression(ExpressionImpl.BinaryExpressionImpl(this.expr, BinaryOperator.NAND, other.expr))

fun Expression.not() =
    Expression(ExpressionImpl.UnaryExpressionImpl(this.expr, UnaryOperator.NOT))

val TRUE get() = Expression(ExpressionImpl.ValueImpl.TRUE)

val FALSE get() = Expression(ExpressionImpl.ValueImpl.FALSE)
