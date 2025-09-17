package com.common.expression

fun Expression.and(other: Expression) =
    Expression.BinaryExpression(this, BinaryOperator.AND, other)

fun Expression.or(other: Expression) =
    Expression.BinaryExpression(this, BinaryOperator.OR, other)

fun Expression.xor(other: Expression) =
    Expression.BinaryExpression(this, BinaryOperator.XOR, other)

fun Expression.nor(other: Expression) =
    Expression.BinaryExpression(this, BinaryOperator.NOR, other)

fun Expression.nand(other: Expression) =
    Expression.BinaryExpression(this, BinaryOperator.NAND, other)

fun Expression.not() =
    Expression.UnaryExpression(this, UnaryOperator.NOT)

typealias TRUE = Expression.Value.TRUE
typealias FALSE = Expression.Value.FALSE
