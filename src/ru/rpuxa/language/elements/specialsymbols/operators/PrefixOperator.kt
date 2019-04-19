package ru.rpuxa.language.elements.specialsymbols.operators

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.code.LExpression

abstract class PrefixOperator(symbols: String) : Operator(symbols) {
    override fun parse(code: LCode, sequence: ElementsSequence, expression: LExpression) {
        expression.operators.push(this)
    }
}