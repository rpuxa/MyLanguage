package ru.rpuxa.language.code

import ru.rpuxa.language.Variable
import ru.rpuxa.language.elements.ExpressionElement
import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.beginningblock.BeginningBlockElement
import ru.rpuxa.language.elements.beginningblock.ExpressionBlockElement
import java.util.*
import kotlin.test.fail

class Expression(method: Method, override val parent: AbstractBlock) : AbstractBlock(method) {
    override val beginElement: BeginningBlockElement get() = ExpressionBlockElement
    override val children: MutableList<AbstractBlock> get() = fail()
    override var currentChild: AbstractBlock?
        get() = fail()
        set(_) = fail()

    val variables = Stack<Variable>()
    val outStack = Stack<ExpressionElement>()
    val operators = Stack<ExpressionElement>()
    var type: Type? = null

    var outStackSize = 0

    private fun getAvailableVariablesNames(): List<Variable> =
        variables + parent.getAvailableVariablesNames(this)

    fun declareNewVariable(variable: Variable): Int {
        val n = (getAvailableVariablesNames().maxBy { it.number ?: 0 }?.number ?: 0) + 1
        variable.number = n
        return n
    }

    fun getVariable(name: String) =
        getAvailableVariablesNames().find { it.name == name }

    override fun pack() {
        while (operators.isNotEmpty()) {
            outStack.push(operators.pop())
        }
        checkType()
        variables.forEach { declareNewVariable(it) }
        parent.onCurrentBlockPacked()
    }

    private fun checkType() {
        if (outStack.empty())
            return
        val types = Stack<Type>()
        for (e in outStack) {
            e.replaceStack(types, this, method.code)
        }
        if (types.size != 1)
            fail("Fail with type checking")
        type = types[0]
    }

    override fun getAllVariables() = variables + (outStack.filter { it is Variable } as List<Variable>)

    override fun onCurrentBlockPacked() {
        fail()
    }
}