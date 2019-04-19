package ru.rpuxa.language.code

import ru.rpuxa.language.elements.SimpleBlock

class LMethod(val code: LCode, val name: String) {
    var arguments: Array<String>? = null
    var returnType: String? = null

    val block = LMethodBlock(this, SimpleBlock)

    fun pack() {
        if (arguments == null)
            arguments = emptyArray()
        if (returnType == null)
            returnType = "V"
        block.pack0()
        code.onCurrentMethodPacked()
    }
}
