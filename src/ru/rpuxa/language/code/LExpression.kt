package ru.rpuxa.language.code

import ru.rpuxa.language.Variable
import ru.rpuxa.language.elements.BeginningBlockElement
import ru.rpuxa.language.elements.ExpressionElement
import java.util.*
import kotlin.test.fail

class LExpression(
    method: LMethod,
    begin: BeginningBlockElement,
    override val parent: LAbstractBlock
) : LAbstractBlock(method, begin) {
    val variablesNames = HashMap<String, Int>()
    val variables = Stack<Variable>()
    val outStack = Stack<ExpressionElement>()
    val operators = Stack<ExpressionElement>()
    var type: String? = null

    private fun getAvailableVariablesNames(): Map<String, Int> {
        return variablesNames + parent.getAvailableVariablesNames(this)
    }

    fun declareNewVariable(name: String): Int {
        val n = (getAvailableVariablesNames().values.max() ?: 0) + 1
        variablesNames[name] = n
        return n
    }

    fun getVariableNumber(name: String): Int =
        getAvailableVariablesNames()[name]!!

    override fun pack() {
        while (operators.isNotEmpty()) {
            outStack.push(operators.pop())
        }
        parent.onCurrentBlockPacked()
    }

    private fun checkType() {
        val types = Stack<String>()
        for (e in outStack) {
            types.push(e.getType(types, this))
        }
        if (types.size != 1)
            fail("Fail with type checking")
        type = types[0]
    }

    override fun onCurrentBlockPacked() {
        fail()
    }
}