package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.elements.Element
import ru.rpuxa.language.elements.specialsymbols.brakets.ClosedRoundBracket
import ru.rpuxa.language.elements.specialsymbols.brakets.OpenedRoundBracket

abstract class SpecialSymbols(val symbols: String) : Element {
    companion object {
        @JvmField
        val ALL: Array<SpecialSymbols>

        init {
            val array = arrayOf(
                ClosedRoundBracket,
                OpenedRoundBracket,
                ClosedBrace,
                Comma,
                EndOfLine,
                MethodDeclaration,
                Semicolon,
                Space
            )

            array.sortBy { -it.symbols.length }
            ALL = array
        }
    }
}