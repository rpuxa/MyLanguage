package ru.rpuxa.language.code

import ru.rpuxa.language.Variable
import ru.rpuxa.language.elements.beginningblock.BeginningBlockElement
import java.util.*
import kotlin.test.fail

abstract class AbstractBlock(val method: Method) {

    abstract val parent: AbstractBlock
    abstract val beginElement: BeginningBlockElement

    abstract val children: MutableList<AbstractBlock>
    abstract var currentChild: AbstractBlock?

    open fun getAvailableVariablesNames(child: AbstractBlock): List<Variable> =
        parent.getAvailableVariablesNames(this) + getAvailableVariablesNamesWithoutParent(child)


    fun getAvailableVariablesNamesWithoutParent(child: AbstractBlock): List<Variable> {
        val map = Stack<Variable>()

        var indexOf = children.indexOf(child)
        if (indexOf == -1 && currentChild == child) {
            indexOf = children.lastIndex
        } else {
            fail()
        }
        for (i in 0..indexOf) {
            val c = children[i]
            if (c is Expression) {
                map += c.variables
            }
        }
        return map
    }

    abstract fun pack()

    open fun onCurrentBlockPacked() {
        val element = currentChild!!
        if (element is Expression && element.outStack.isNotEmpty())
            children.add(element)
        currentChild = null
    }

    fun startNewExpression() {
        currentChild?.pack()
        currentChild = Expression(method, this)
    }

    open fun getAllVariables(): List<Variable> =
        children.flatMap { it.getAllVariables() }

}