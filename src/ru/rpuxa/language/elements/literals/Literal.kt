package ru.rpuxa.language.elements.literals

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.ExpressionElement
import ru.rpuxa.language.elements.Type
import java.util.*
import kotlin.test.fail

interface Literal : ExpressionElement, Type {
    override fun parse(code: Code, sequence: ElementsSequence) {
       val expression = code.currentMethod?.block?.expression ?: fail("You can use literals only in expressions")
        expression.outStack.push(this)
    }

    override fun replaceStack(
        stack: Stack<Type>,
        expression: Expression,
        code: Code
    ) {
        stack.push(this)
    }
}