package ru.rpuxa.language.elements.specialsymbols.operators

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression

abstract class BinaryOperator(symbols: String, priority: Int) : Operator(symbols, priority) {
    override fun parse(code: Code, sequence: ElementsSequence, expression: Expression) {
        while (expression.operators.isNotEmpty()) {
            val element = expression.operators.peek()
            if (element is PrefixOperator ||
                (element is Operator && element.priority > priority) ||
                (element is LeftAssociativeOperator && element.priority == priority)
            ) {
                expression.outStack.push(expression.operators.pop())
            } else {
                break
            }
        }
        expression.operators.push(this)
    }
}
