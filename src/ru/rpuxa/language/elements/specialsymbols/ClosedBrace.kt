package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import kotlin.test.fail

object ClosedBrace : SpecialSymbols("}"), SpecialSymbolInstance {

    override fun getNewInstance() = this
    override fun parse(code: Code, sequence: ElementsSequence) {
        val block = code.currentMethod?.block?.innerBlock ?: fail("You can use closed brace only in code block")
        block.pack()
    }
}