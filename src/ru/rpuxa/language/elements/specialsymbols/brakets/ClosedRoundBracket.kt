package ru.rpuxa.language.elements.specialsymbols.brakets

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.elements.MethodInvocation
import ru.rpuxa.language.elements.specialsymbols.Comma
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbolInstance
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import kotlin.test.fail

object ClosedRoundBracket : SpecialSymbols(")"), SpecialSymbolInstance {

    override fun getNewInstance() = this
    override fun parse(code: Code, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use bracket only in expressions")
        var noArgs = true
        var commaCount = 0
        while (expression.operators.isNotEmpty() &&
            expression.operators.peek() !is OpenedRoundBracket
        ) {
            val element = expression.operators.pop()
            if (element is Comma)
                commaCount++
            else {
                expression.outStack.push(element)
            }
            noArgs = false
        }
        expression.operators.pop()
        if (expression.operators.empty())
            fail("Missing comma or open bracket")
        val element = expression.operators.peek()
        if (element is MethodInvocation) {
            element.argsCount = if (noArgs && expression.outStackSize == expression.outStack.size) 0 else commaCount + 1
        } else if (commaCount > 0)
            fail("Missing method invocation")
    }
}