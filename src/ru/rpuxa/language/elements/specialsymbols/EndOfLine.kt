package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code

object EndOfLine : SpecialSymbols("\n"), SpecialSymbolFactory {

    override fun getNewInstance() = this
    override fun parse(code: Code, sequence: ElementsSequence) {
        code.currentMethod?.block?.innerBlock?.startNewExpression()
    }
}