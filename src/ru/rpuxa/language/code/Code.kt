package ru.rpuxa.language.code

import ru.rpuxa.language.elements.ExpressionElement
import ru.rpuxa.language.elements.MethodInvocation
import kotlin.test.fail

class Code {
    val methods = ArrayList<Method>()

    var currentMethod: Method? = null

    var generateInputMethod = false

    fun pack() {
        currentMethod?.pack()
        methods.forEach { method ->
            val allInvocations =
                getAllElements().filter { it is MethodInvocation && it.name == method.name } as List<MethodInvocation>
            val argsCount = allInvocations.firstOrNull()?.argsCount ?: method.arguments!!.size
            val types = allInvocations.firstOrNull()?.argsTypes ?: method.arguments!!
            if (!allInvocations.all { it.argsCount == argsCount })
                fail("Different args method invocation")
            method.arguments = types
            val allVariables = method.block.getAllVariables()
            val emptyNumbers = ArrayList<Int>()
            var argVariablesCount = 0
            allVariables.forEach {
                val chars = it.name.toCharArray()
                if (chars.size != 1 || chars[0] !in 'a'..('a' + argsCount - 1)) {
                    it.number = it.number!! + argsCount
                } else {
                    if (it.number != null)
                        emptyNumbers.add(it.number!!)
                    argVariablesCount++
                    val i = chars[0] - 'a'
                    it.number = i + 1
                    it.type = types[i]
                }
            }

            allVariables.forEach {
                val less = emptyNumbers.count { num -> num < it.number!! }
                it.number = it.number!! - less
            }

            method.syntacticVariableNum = allVariables.size + argsCount -
                    argVariablesCount // +1 for non static methods!!!!!!!!!
            //for static methods!!!!!!!!!!!!!!
            allVariables.forEach { it.number = it.number!! - 1 }
        }
    }

    fun onCurrentMethodPacked() {
        methods.add(currentMethod!!)
        currentMethod = null
    }

    fun getAllElements(): List<ExpressionElement> {
        var allBlocks = methods.flatMap { it.block.children }
        while (!allBlocks.all { it is Expression }) {
            allBlocks = allBlocks.flatMap { if (it is Expression) listOf(it) else it.children }
        }
        return allBlocks.flatMap { (it as Expression).outStack }
    }
}