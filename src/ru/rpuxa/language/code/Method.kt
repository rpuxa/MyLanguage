package ru.rpuxa.language.code

import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.VOID_TYPE

class Method(val code: Code, val name: String) {
    var arguments: Array<Type>? = null
    var returnType: Type? = null
    private var _syntacticVariableNum = -1
    var syntacticVariableNum: Int
        get() = _syntacticVariableNum++
        set(value) {
            _syntacticVariableNum = value
        }

    val block = MethodBlock(this)

    fun pack() {
        if (arguments == null)
            arguments = emptyArray()
        if (returnType == null)
            returnType = VOID_TYPE
        block.pack0()

        code.onCurrentMethodPacked()
    }

    fun getDescriptor(): String {
        val builder = StringBuilder("(")
        for (a in arguments!!)
            builder.append(a.getTypeDescriptor(code))
        builder.append(')').append(returnType!!.getTypeDescriptor(code))
        return builder.toString()
    }
}
