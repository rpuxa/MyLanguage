package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.elements.Element
import ru.rpuxa.language.elements.operators.Operator

open class SpecialSymbols(val symbols: String) : Element {
    companion object {
        @JvmField
        val ALL: Array<SpecialSymbols>

        init {
            val array = arrayOf(
                *Operator.ALL,
                OpenedRoundBracket,
                ClosedRoundBracket
            )

            array.sortBy { -it.symbols.length }
            ALL = array
        }
    }
}