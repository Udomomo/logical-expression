package com.common.expression

/**
 * 論理式を表すクラス。パッケージ外に公開している。
 * 内容はExpressionImplの実装を隠蔽するためのラッパーにすぎない。
 */
class Expression internal constructor(
    internal val expr: ExpressionImpl
) {
    // ExpressionImplを返り値にしてしまうと、`'public' function exposes its 'internal' return type` エラーになる。
    // そこでこのように返り値の型をExpressionにすることで、ExpressionImplがシグネチャに現れず、実装を隠蔽しつつ外部に公開できる。
    // メソッドの実態はExpressionImplに委譲しているだけ。
    fun evaluate() = Expression(expr.evaluate())
    fun toScript() = expr.toScript()
}
