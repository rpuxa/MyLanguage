package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.Variable
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.elements.specialsymbols.brakets.OpenedRoundBracket

class Word(val value: String) : Element {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val v = value.toIntOrNull()
        if (v != null) {
            sequence.replace(IntegerLiteral(v))
            return
        }
        val bracket = sequence.nextIs<OpenedRoundBracket>()
        if (bracket != null)
            sequence.replaceAndRepeat(MethodInvocation(value))

        sequence.replaceAndRepeat(Variable(value))
    }
}