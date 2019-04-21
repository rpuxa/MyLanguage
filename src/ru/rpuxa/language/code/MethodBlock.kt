package ru.rpuxa.language.code

import ru.rpuxa.language.elements.beginningblock.SimpleBlock
import java.util.*
import kotlin.test.fail

class MethodBlock(method: Method) : AbstractBlock(method) {
    override val beginElement get() = SimpleBlock
    override val children: MutableList<AbstractBlock> = ArrayList()
    override var currentChild: AbstractBlock? = null
    override val parent: AbstractBlock get() = fail("Method block dont have parents")

    init {
        startNewExpression()
    }

    override fun getAvailableVariablesNames(child: AbstractBlock) =
        getAvailableVariablesNamesWithoutParent(child)

    val expression: Expression
        get() {
            var c = currentChild
            while (c != null) {
                if (c is Expression)
                    return c
                c = c.currentChild
            }
            fail()
        }

    val innerBlock: AbstractBlock?
        get() {
            if (currentChild == null)
                return this
            return expression.parent
        }

    override fun pack() {
        method.pack()
    }

    fun pack0() {
        currentChild?.pack()
    }
}