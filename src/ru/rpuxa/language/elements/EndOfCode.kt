package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code

object EndOfCode : CodeElement {
    override fun parse(code: Code, sequence: ElementsSequence) {
        code.pack()
    }
}