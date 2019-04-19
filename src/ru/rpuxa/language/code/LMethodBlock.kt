package ru.rpuxa.language.code

import ru.rpuxa.language.elements.BeginningBlockElement
import kotlin.test.fail

class LMethodBlock(method: LMethod, begin: BeginningBlockElement) : LAbstractBlock(method, begin) {
    override val parent: LAbstractBlock get() = fail("Method block dont have parents")

    val expression: LExpression? get() {
        var c = currentChild
        var answer: LExpression? = null
        while (c != null) {
            c = c.currentChild
            if (c is LExpression)
            answer = c
        }
        return answer
    }

    val innerBlock: LAbstractBlock? get() {
        if (currentChild == null)
            return this
        return expression!!.parent
    }

    override fun pack() {
        method.pack()
    }

    fun pack0() {
        currentChild?.pack()
    }
}