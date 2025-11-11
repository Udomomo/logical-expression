package com.common.expression

internal sealed class ExpressionImpl {
    abstract fun evaluate(): ValueImpl
    abstract fun toScript(): String

    @ConsistentCopyVisibility
    data class BinaryExpressionImpl internal constructor (
        private val left: ExpressionImpl,
        private val operator: BinaryOperator,
        private val right: ExpressionImpl,
    ): ExpressionImpl() {
        override fun evaluate() = operator(left, right)

        override fun toScript(): String {
            return "${toScriptAsChild(left)} ${operator.toScript()} ${toScriptAsChild(right)}"
        }

        private fun toScriptAsChild(expressionImpl: ExpressionImpl) = when (expressionImpl) {
            is ValueImpl -> expressionImpl.toScript()
            else -> "(${expressionImpl.toScript()})"
        }
    }

    @ConsistentCopyVisibility
    data class UnaryExpressionImpl internal constructor (
        private val expressionImpl: ExpressionImpl,
        private val operator: UnaryOperator,
    ): ExpressionImpl() {
        override fun evaluate() = operator(expressionImpl)

        override fun toScript(): String {
            val expressionScript = when (expressionImpl) {
                is ValueImpl -> expressionImpl.toScript()
                else -> "(${expressionImpl.toScript()})"
            }
            return "${operator.toScript()} $expressionScript"
        }
    }

    sealed class ValueImpl: ExpressionImpl() {
        object TRUE: ValueImpl() {
           override val name = "TRUE"
        }

        object FALSE: ValueImpl() {
            override val name = "FALSE"
        }

        abstract val name: String
        override fun evaluate(): ValueImpl = this
        override fun toScript(): String = this.name
    }
}
