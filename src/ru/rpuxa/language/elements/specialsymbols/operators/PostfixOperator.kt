package ru.rpuxa.language.elements.specialsymbols.operators

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.code.LExpression
import kotlin.test.fail

abstract class PostfixOperator(symbols: String) : Operator(symbols) {
    override fun parse(code: LCode, sequence: ElementsSequence, expression: LExpression) {
        expression.outStack.push(this)
    }
}