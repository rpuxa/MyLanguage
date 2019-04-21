package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.elements.CodeElement
import ru.rpuxa.language.elements.specialsymbols.brakets.ClosedRoundBracket
import ru.rpuxa.language.elements.specialsymbols.brakets.OpenedRoundBracket
import ru.rpuxa.language.elements.specialsymbols.operators.Assignment
import ru.rpuxa.language.elements.specialsymbols.operators.Output

abstract class SpecialSymbols(val symbols: String) : CodeElement {


    companion object {
        @JvmField
        val ALL: Array<SpecialSymbolInstance>

        init {
            val array = arrayOf(
                ClosedRoundBracket,
                OpenedRoundBracket,
                Assignment,
                Output,
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