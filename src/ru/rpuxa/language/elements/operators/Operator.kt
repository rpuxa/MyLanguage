package ru.rpuxa.language.elements.operators

import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols

open class Operator(
    symbols: String,
    val type: Types,
    val priority: Int
): SpecialSymbols(symbols) {



    companion object {
        @JvmField
        val ALL : Array<Operator>

        init {
            val arrayOf = arrayOf(
                Sum, Times, Assignment
            )
            arrayOf.sortBy { -it.symbols.length }
            ALL = arrayOf
        }
    }

    enum class Types {
        PREFIX,
        POSTFIX,
        LEFT_ASSOCIATIVE,
        RIGHT_ASSOCIATIVE
    }
}