package ru.rpuxa.language

import ru.rpuxa.language.code.LCode
import ru.rpuxa.language.code.LExpression
import ru.rpuxa.language.elements.ExpressionElement
import java.util.*
import kotlin.test.fail

class Variable(val name: String) : ExpressionElement {
    var type: String? = null

    override fun getType(types: Stack<String>, expression: LExpression): String {
        return type ?: fail("Problem with checking variable type")
    }

    override fun parse(code: LCode, sequence: ElementsSequence) {
       val expression = code.currentMethod?.block?.expression ?: fail("You can use variables only in expressions")
        expression.outStack.push(this)
    }
}