package com.common.expression

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ExpressionBuilderTest {
    @Nested
    inner class Value {
        @Test
        fun `should TRUE is evaluated as TRUE`() {
            // Arrange
            val expression = TRUE

            // Act
            // Assert
            Assertions.assertEquals("TRUE", expression.toScript())
            Assertions.assertEquals("TRUE", expression.evaluate().toScript())
        }

        @Test
        fun `should FALSE is evaluated as FALSE`() {
            // Arrange
            val expression = FALSE

            // Act
            // Assert
            Assertions.assertEquals("FALSE", expression.toScript())
            Assertions.assertEquals("FALSE", expression.evaluate().toScript())
        }
    }

    @Nested
    inner class Operator {
        // 各演算子について、evaluate実行前のtoScript()と、evaluate()実行後のtoScript()の両方を確認するテストケースを用意する。
        @Test
        fun `should AND operator combine TRUE and FALSE correctly`() {
            // Arrange
            val expression = TRUE.and(FALSE)

            // Act
            // Assert
            Assertions.assertEquals("TRUE AND FALSE", expression.toScript())
            Assertions.assertEquals("FALSE", expression.evaluate().toScript())
        }

        @Test
        fun `should OR operator combine TRUE and FALSE correctly`() {
            // Arrange
            val expression = TRUE.or(FALSE)

            // Act
            // Assert
            Assertions.assertEquals("TRUE OR FALSE", expression.toScript())
            Assertions.assertEquals("TRUE", expression.evaluate().toScript())
        }

        @Test
        fun `should NOT operator invert TRUE correctly`() {
            // Arrange
            val expression = TRUE.not()

            // Act
            // Assert
            Assertions.assertEquals("NOT TRUE", expression.toScript())
            Assertions.assertEquals("FALSE", expression.evaluate().toScript())
        }

        // XOR, NOR, NANDのテストケースも同様に追加する。
        @Test
        fun `should XOR operator combine TRUE and FALSE correctly`() {
            // Arrange
            val expression = TRUE.xor(FALSE)

            // Act
            // Assert
            Assertions.assertEquals("TRUE XOR FALSE", expression.toScript())
            Assertions.assertEquals("TRUE", expression.evaluate().toScript())
        }

        @Test
        fun `should NOR operator combine TRUE and FALSE correctly`() {
            // Arrange
            val expression = TRUE.nor(FALSE)

            // Act
            // Assert
            Assertions.assertEquals("TRUE NOR FALSE", expression.toScript())
            Assertions.assertEquals("FALSE", expression.evaluate().toScript())
        }

        @Test
        fun `should NAND operator combine TRUE and FALSE correctly`() {
            // Arrange
            val expression = TRUE.nand(FALSE)

            // Act
            // Assert
            Assertions.assertEquals("TRUE NAND FALSE", expression.toScript())
            Assertions.assertEquals("TRUE", expression.evaluate().toScript())
        }

        @Test
        fun `should nested expressions evaluate correctly`() {
            // Arrange
            val expression = TRUE.and(FALSE.or(TRUE))

            // Act
            // Assert
            Assertions.assertEquals("TRUE AND (FALSE OR TRUE)", expression.toScript())
            Assertions.assertEquals("TRUE", expression.evaluate().toScript())
        }
    }
}