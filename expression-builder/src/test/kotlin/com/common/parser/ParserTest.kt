package com.common.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ParserTest {
    @Nested
    inner class Valid {
        @Test
        @DisplayName("式を正しくパースできること")
        fun `should correctly parse expression`() {
            // Arrange
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Value.FALSE,
                Operator.OR,
                Value.TRUE,
            )

            val expect: List<Token> = listOf(
                Value.TRUE,
                Value.FALSE,
                Operator.AND,
                Value.TRUE,
                Operator.OR,
            )

            // Act
            val result = Parser().execute(input)

            // Assert
            Assertions.assertEquals(expect, result)
        }

        @Test
        @DisplayName("NOTが途中にある式を正しくパースできること")
        fun `should correctly parse expression with NOT`() {
            // Arrange
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Value.FALSE,
                Operator.OR,
                Operator.NOT,
                Value.TRUE,
            )

            val expect: List<Token> = listOf(
                Value.TRUE,
                Value.FALSE,
                Operator.AND,
                Value.TRUE,
                Operator.NOT,
                Operator.OR,
            )

            // Act
            val result = Parser().execute(input)

            // Assert
            Assertions.assertEquals(expect, result)
        }

        @Test
        @DisplayName("NOTが最初にある式を正しくパースできること")
        fun `should correctly parse expression starting with NOT`() {
            // Arrange
            val input: List<Token> = listOf(
                Operator.NOT,
                Value.TRUE,
                Operator.AND,
                Value.FALSE,
                Operator.OR,
                Value.TRUE,
            )

            val expect: List<Token> = listOf(
                Value.TRUE,
                Operator.NOT,
                Value.FALSE,
                Operator.AND,
                Value.TRUE,
                Operator.OR,
            )

            // Act
            val result = Parser().execute(input)

            // Assert
            Assertions.assertEquals(expect, result)
        }

        @Test
        @DisplayName("カッコのある式を正しくパースできること")
        fun `should correctly parse expression with parenthesis`() {
            // Arrange
            // TRUE AND (FALSE OR TRUE AND FALSE)
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Operator.LPAREN,
                Value.FALSE,
                Operator.OR,
                Value.TRUE,
                Operator.AND,
                Value.FALSE,
                Operator.RPAREN,
            )

            val expect: List<Token> = listOf(
                Value.TRUE,
                Value.FALSE,
                Value.TRUE,
                Operator.OR,
                Value.FALSE,
                Operator.AND,
                Operator.AND,
            )

            // Act
            val result = Parser().execute(input)

            // Assert
            Assertions.assertEquals(expect, result)
        }

        @Test
        @DisplayName("入れ子のカッコのある式を正しくパースできること")
        fun `should correctly parse expression with multiple parenthesis`() {
            // Arrange
            // TRUE AND (FALSE OR (TRUE AND FALSE))
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Operator.LPAREN,
                Value.FALSE,
                Operator.OR,
                Operator.LPAREN,
                Value.TRUE,
                Operator.AND,
                Value.FALSE,
                Operator.RPAREN,
                Operator.RPAREN,
            )

            val expect: List<Token> = listOf(
                Value.TRUE,
                Value.FALSE,
                Value.TRUE,
                Value.FALSE,
                Operator.AND,
                Operator.OR,
                Operator.AND,
            )

            // Act
            val result = Parser().execute(input)

            // Assert
            Assertions.assertEquals(expect, result)
        }

        @Test
        @DisplayName("カッコ内にNOTがある式を正しくパースできること")
        fun `should correctly parse expression with NOT inside parenthesis`() {
            // Arrange
            // TRUE AND (NOT FALSE)
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Operator.LPAREN,
                Operator.NOT,
                Value.FALSE,
                Operator.RPAREN,
            )

            val expect: List<Token> = listOf(
                Value.TRUE,
                Value.FALSE,
                Operator.NOT,
                Operator.AND,
            )

            // Act
            val result = Parser().execute(input)

            // Assert
            Assertions.assertEquals(expect, result)
        }

        @Test
        @DisplayName("NOTの指定対象がカッコである式を正しくパースできること")
        fun `should correctly parse expression with NOT against parenthesis`() {
            // Arrange
            // TRUE AND NOT (TRUE OR FALSE)
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Operator.NOT,
                Operator.LPAREN,
                Value.TRUE,
                Operator.OR,
                Value.FALSE,
                Operator.RPAREN,
            )

            val expect: List<Token> = listOf(
                Value.TRUE,
                Value.TRUE,
                Value.FALSE,
                Operator.OR,
                Operator.NOT,
                Operator.AND,
            )

            // Act
            val result = Parser().execute(input)

            // Assert
            Assertions.assertEquals(expect, result)
        }
    }

    @Nested
    inner class Invalid {
        @Test
        @DisplayName("右括弧が多い場合はパースできないこと")
        fun `should fail when rparen is not closed`() {
            // Arrange
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Operator.NOT,
                Value.FALSE,
                Operator.RPAREN,
            )

            // Act
            // Assert
            Assertions.assertThrowsExactly(
                IllegalStateException::class.java,
                { Parser().execute(input) })
        }

        @Test
        @DisplayName("左括弧が多い場合はパースできないこと")
        fun `should fail when lparen is not closed`() {
            // Arrange
            val input: List<Token> = listOf(
                Value.TRUE,
                Operator.AND,
                Operator.LPAREN,
                Operator.NOT,
                Value.FALSE,
            )

            // Act
            // Assert
            Assertions.assertThrowsExactly(
                IllegalStateException::class.java,
                { Parser().execute(input) })
        }
    }
}
