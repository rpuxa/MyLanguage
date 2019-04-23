package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Method
import ru.rpuxa.language.elements.Word
import kotlin.test.fail

object MethodDeclaration : SpecialSymbols(":"), SpecialSymbolFactory {

    override fun getNewInstance() = this
    override fun parse(code: Code, sequence: ElementsSequence) {
        val word = sequence.nextIs<Word>() ?: fail("Wrong method declaration")
        code.currentMethod?.pack()
        code.currentMethod = Method(code, word.value)
        sequence.skip()
    }
}