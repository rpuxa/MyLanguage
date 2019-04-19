package ru.rpuxa.language.elements.specialsymbols.operators

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.code.LExpression
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import java.beans.Expression
import kotlin.math.exp
import kotlin.test.fail

abstract class Operator(symbols: String) : SpecialSymbols(symbols) {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use operators only in expressions")
        parse(code, sequence, expression)
    }

    open fun parse(code: LCode, sequence: ElementsSequence, expression: LExpression) {
    }
}