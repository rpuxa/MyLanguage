package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code

interface CodeElement : Element {
    fun parse(code: Code, sequence: ElementsSequence)
}