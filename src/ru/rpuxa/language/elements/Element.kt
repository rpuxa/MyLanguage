package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode

interface Element {
    fun parse(code: LCode, sequence: ElementsSequence)
}