package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.elements.specialsymbols.brakets.OpenedRoundBracket
import kotlin.test.fail

object Comma : SpecialSymbols(",") {
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use comma only in expressions")
        while (expression.operators.isNotEmpty() && expression.operators.peek() !is OpenedRoundBracket)
                expression.outStack.push(expression.operators.pop())
        if (expression.operators.empty())
            fail("Missing comma or open bracket")
    }
}