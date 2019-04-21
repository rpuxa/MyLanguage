package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code

object Semicolon : SpecialSymbols(";"), SpecialSymbolInstance {

    override fun getNewInstance() = this
    override fun parse(code: Code, sequence: ElementsSequence) {
        EndOfLine.parse(code, sequence)
    }
}