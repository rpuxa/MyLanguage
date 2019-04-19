package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode

object Semicolon : SpecialSymbols(";") {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        EndOfLine.parse(code, sequence)
    }
}