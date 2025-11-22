package com.common.parser

/**
 * Stringで入力された式を解釈して評価する。このクラスとメソッドは利用者に公開する。
 */
object Calculator {
    fun run(input: String): String {
        val tokens = Tokenizer.execute(input)
        val parsed = Parser().execute(tokens)
        val result = ExpressionCreator.create(parsed)

        return result.evaluate().toScript()
    }
}
