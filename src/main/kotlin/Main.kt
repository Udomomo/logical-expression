package com.example

fun main() {
    // builderでExpressionを作ることができる
    // そのExpressionを評価することができる
    // 式をStringとして出力することができる

    // Nice: Stringで書いた式をparseしてExpressionを作ることができる
    // -> parserを作ろうと思ったら、自然とbuilderを使うことになるのでは？
    // -> つまり、parserはbuilderのラッパーになる
    // また、parser以前にlexer(字句解析)も必要

    // builderは複雑なobjectを組み上げる一手法でしかないので、最終的なASTの構造とは無関係

    // ASTの構造
    // Expression (抽象クラス)
    // - Value (Expressionを継承)
    //   - ZERO
    //   - ONE
    // - BinaryExpression (Expressionを継承)
    //   - left: Expression
    //   - operator: Operator (enum class)
    //   - right: Expression
    // - NotExpression (Expressionを継承)
    //   - expression: Expression
    //
    // この名前はJSQLParserから取っているっぽい

    // 対応するもの
    // and
    // or
    // not <- これだけ式を1つしか取らない
    // nor
    // xor
    // and
    // ()

    // 値はZEROとONEのみ

    // expression1.and(expression2) のようにしたときに、expression1を左に、expression2を右に取る
}