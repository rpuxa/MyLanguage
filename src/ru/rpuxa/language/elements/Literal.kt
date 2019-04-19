package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import kotlin.test.fail

interface Literal : Element {
    override fun parse(code: LCode, sequence: ElementsSequence) {
       val expression = code.currentMethod?.block?.expression ?: fail("You can use literals only in expressions")
        expression.outStack.push(this)
    }
}