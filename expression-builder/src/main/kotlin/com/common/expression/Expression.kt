package com.common.expression

sealed class Expression {
    abstract fun evaluate(): Value
    abstract fun toScript(): String

    @ConsistentCopyVisibility
    data class BinaryExpression internal constructor (
        private val left: Expression,
        private val operator: BinaryOperator,
        private val right: Expression,
    ): Expression() {
        override fun evaluate() = operator(left, right)

        override fun toScript(): String {
            return "${toScriptAsChild(left)} ${operator.toScript()} ${toScriptAsChild(right)}"
        }

        private fun toScriptAsChild(expression: Expression) = when (expression) {
            is Value -> expression.toScript()
            else -> "(${expression.toScript()})"
        }
    }

    @ConsistentCopyVisibility
    data class UnaryExpression internal constructor (
        private val expression: Expression,
        private val operator: UnaryOperator,
    ): Expression() {
        override fun evaluate() = operator(expression)

        override fun toScript(): String {
            val expressionScript = when (expression) {
                is Value -> expression.toScript()
                else -> "(${expression.toScript()})"
            }
            return "${operator.toScript()} $expressionScript"
        }
    }

    sealed class Value: Expression() {
        object TRUEVAL: Value() {
           override val name = "TRUE"
        }

        object FALSEVAL: Value() {
            override val name = "FALSE"
        }

        abstract val name: String
        override fun evaluate(): Value = this
        override fun toScript(): String = this.name
    }
}
