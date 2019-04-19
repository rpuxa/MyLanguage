package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode

object EndOfCode : Element {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        code.pack()
    }
}