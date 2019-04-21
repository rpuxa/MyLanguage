package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.Variable
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.elements.literals.IntegerLiteral
import ru.rpuxa.language.elements.specialsymbols.brakets.OpenedRoundBracket

class Word(val value: String) : CodeElement {
    override fun parse(code: Code, sequence: ElementsSequence) {
        val v = value.toIntOrNull()
        if (v != null) {
            sequence.replaceAndRepeat(IntegerLiteral(v))
            return
        }
        val bracket = sequence.nextIs<OpenedRoundBracket>()
        if (bracket != null) {
            sequence.replaceAndRepeat(MethodInvocation(value))
            return
        }

        val variable = code.currentMethod?.block?.expression?.getVariable(value)
        sequence.replaceAndRepeat(variable ?: Variable(value))
    }
}