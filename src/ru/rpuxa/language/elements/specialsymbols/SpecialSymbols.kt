package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.elements.CodeElement
import ru.rpuxa.language.elements.specialsymbols.brakets.ClosedRoundBracket
import ru.rpuxa.language.elements.specialsymbols.brakets.OpenedRoundBracket
import ru.rpuxa.language.elements.specialsymbols.operators.impl.*

abstract class SpecialSymbols(val symbols: String) : CodeElement {


    companion object {
        @JvmField
        val ALL: Array<SpecialSymbolFactory>

        init {
            val array = arrayOf(
                ClosedRoundBracket,
                OpenedRoundBracket,

                Assignment,
                Div,
                InputInt,
                Output,
                Sum,
                Times,

                ClosedBrace,
                Comma,
                EndOfLine,
                MethodDeclaration,
                Semicolon,
                Space
            )

            array.sortBy { -it.getNewInstance().symbols.length }
            ALL = array
        }
    }
}