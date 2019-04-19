package ru.rpuxa.language.elements.keywords

import ru.rpuxa.language.elements.Element

abstract class Keyword(val name: String) : Element {
    companion object {
        @JvmField
        val ALL = arrayOf(If)
    }
}