package ru.rpuxa.language.elements

import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.LCode
import kotlin.properties.Delegates
import kotlin.properties.Delegates.notNull
import kotlin.test.fail

class MethodInvocation(val name: String) : Element {
    var argsCount by notNull<Int>()
    override fun parse(code: LCode, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can invoke methods only in expressions")
        expression.operators.push(this)
    }
}