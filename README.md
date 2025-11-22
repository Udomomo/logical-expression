# logical-expression
論理演算式を解釈して計算するコード。以下に対応。

- 値
  - TRUE
  - FALSE
- 演算子
  - AND
  - OR
  - NOR
  - NAND
  - XOR
  - NOT
- カッコ

## 利用方法
2つの方法がある。
- `ExpressionBuilder` を叩いて式を構築する。返り値はExpression型であり、以下のように利用できる。
  - `evaluate()` で式を評価してTRUEまたはFALSEのExpressionを返す。
  - `script()` で式をStringとして出力する。Builderで作った式の出力も、 `evaluate()` した後の結果の出力も可能。
- Stringで書いた式を `Calculator` に渡し、 `run()` で実行する。式を評価した結果がStringとして返る。

いずれも `Main.kt` に利用例がある。
