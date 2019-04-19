package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import kotlin.test.fail

object ClosedBrace : SpecialSymbols("}") {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val block = code.currentMethod?.block?.innerBlock ?: fail("You can use closed brace only in code block")
        block.pack()
    }
}