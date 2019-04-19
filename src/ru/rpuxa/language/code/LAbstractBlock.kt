package ru.rpuxa.language.code

import ru.rpuxa.language.elements.BeginningBlockElement

abstract class LAbstractBlock(val method: LMethod, val beginElement: BeginningBlockElement) {
    abstract val parent: LAbstractBlock

    val children = ArrayList<LAbstractBlock>()
    var currentChild: LAbstractBlock? = null

    fun getAvailableVariablesNames(child: LAbstractBlock): Map<String, Int> {
        val map = HashMap<String, Int>()
        for (i in 0..children.indexOf(child)) {
            val c = children[i]
            if (c is LExpression) {
                map += c.variablesNames
            }
        }
        map += parent.getAvailableVariablesNames(this)
        return map
    }

    abstract fun pack()

    open fun onCurrentBlockPacked() {
        val element = currentChild!!
        if (element is LExpression && element.outStack.isNotEmpty())
            children.add(element)
        currentChild = null
    }
}