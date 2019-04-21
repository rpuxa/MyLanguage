package ru.rpuxa.language.elements.specialsymbols.operators

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.ExpressionElement
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import kotlin.test.fail

abstract class Operator(symbols: String, val priority: Int) : SpecialSymbols(symbols), ExpressionElement {
    override fun parse(code: Code, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use operators only in expressions")
        parse(code, sequence, expression)
    }

    abstract fun parse(code: Code, sequence: ElementsSequence, expression: Expression)
}

