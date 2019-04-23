package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import kotlin.test.fail

object Space : SpecialSymbols(" "), SpecialSymbolFactory {

    override fun getNewInstance() = this

    override fun parse(code: Code, sequence: ElementsSequence) {
        fail()
    }
}