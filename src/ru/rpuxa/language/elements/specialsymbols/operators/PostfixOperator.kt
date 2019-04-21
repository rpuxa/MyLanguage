package ru.rpuxa.language.elements.specialsymbols.operators

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression

abstract class PostfixOperator(symbols: String, priority: Int) : Operator(symbols, priority) {
    override fun parse(code: Code, sequence: ElementsSequence, expression: Expression) {
        expression.outStack.push(this)
    }
}