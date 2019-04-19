package ru.rpuxa.language.elements.specialsymbols.brakets

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.elements.MethodInvocation
import ru.rpuxa.language.elements.specialsymbols.Comma
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import kotlin.test.fail

object ClosedRoundBracket : SpecialSymbols(")"){
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use bracket only in expressions")
        while (expression.operators.isNotEmpty() && expression.operators.peek() !is OpenedRoundBracket) {
            expression.outStack.push(expression.operators.pop())
        }
        expression.operators.pop()
        if (expression.operators.empty())
            fail("Missing comma or open bracket")
        val element = expression.operators.peek()
        var commaCount = 0
        for (i in expression.operators.indices.reversed())
            if (expression.operators[i] is Comma) {
                expression.operators.removeAt(i)
                commaCount++
            }
        if (element is MethodInvocation) {
            element.argsCount = commaCount + 1
        } else if (commaCount > 0)
            fail("Missing method invocation")
    }
}