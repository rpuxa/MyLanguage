package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode

object EndOfLine : SpecialSymbols("\n") {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        code.currentMethod?.block?.expression?.pack()
    }
}