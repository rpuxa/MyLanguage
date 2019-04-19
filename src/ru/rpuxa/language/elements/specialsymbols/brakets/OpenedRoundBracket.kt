package ru.rpuxa.language.elements.specialsymbols.brakets

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import kotlin.test.fail

object OpenedRoundBracket : SpecialSymbols("("){
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use bracket only in expressions")
        expression.operators   .push(this)
    }
}