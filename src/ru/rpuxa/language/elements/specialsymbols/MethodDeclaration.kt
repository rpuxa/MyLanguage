package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.code.LMethod
import ru.rpuxa.language.elements.Word
import kotlin.test.fail

object MethodDeclaration : SpecialSymbols(":") {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val word = sequence.nextIs<Word>() ?: fail("Wrong method declaration")
        code.currentMethod?.pack()
        code.currentMethod = LMethod(code, word.value)
        sequence.skip()
    }
}