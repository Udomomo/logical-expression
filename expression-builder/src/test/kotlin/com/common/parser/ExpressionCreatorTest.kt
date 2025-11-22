package com.common.parser

import com.common.expression.Expression
import com.common.expression.FALSE
import com.common.expression.TRUE
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class ExpressionCreatorTest {
    @ParameterizedTest
    @ArgumentsSource(ExpressionCreatorArgumentsProvider::class)
    fun `should correctly create expression`(tokens: List<Token>, expect: Expression) {
        // Assert
        Assertions.assertEquals(
            expect.evaluate().toScript(),
            ExpressionCreator.create(tokens).evaluate().toScript(),
        )
    }

    internal class ExpressionCreatorArgumentsProvider: ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments?>? {
            // ParserTestでパースした式を利用する
            return Stream.of(
                arguments(
                    listOf(
                        Value.TRUE,
                        Value.FALSE,
                        Operator.AND,
                        Value.TRUE,
                        Operator.OR,
                    ),
                    TRUE
                ),
                arguments(
                    listOf(
                        Value.TRUE,
                        Value.FALSE,
                        Operator.AND,
                        Value.TRUE,
                        Operator.NOT,
                        Operator.OR,
                    ),
                    FALSE
                ),
                arguments(
                    listOf(
                        Value.TRUE,
                        Operator.NOT,
                        Value.FALSE,
                        Operator.AND,
                        Value.TRUE,
                        Operator.OR,
                    ),
                    TRUE,
                ),
                arguments(
                    listOf(
                        Value.TRUE,
                        Value.FALSE,
                        Value.TRUE,
                        Operator.OR,
                        Value.FALSE,
                        Operator.AND,
                        Operator.AND,
                    ),
                    FALSE,
                ),
                arguments(
                    listOf(
                        Value.TRUE,
                        Value.FALSE,
                        Value.TRUE,
                        Value.FALSE,
                        Operator.AND,
                        Operator.OR,
                        Operator.AND,
                    ),
                    FALSE,
                ),
                arguments(
                    listOf(
                        Value.TRUE,
                        Value.FALSE,
                        Operator.NOT,
                        Operator.AND,
                    ),
                    TRUE,
                ),
                arguments(
                    listOf(
                        Value.TRUE,
                        Value.TRUE,
                        Value.FALSE,
                        Operator.OR,
                        Operator.NOT,
                        Operator.AND,
                    ),
                    FALSE,
                )
            )
        }
    }
}

