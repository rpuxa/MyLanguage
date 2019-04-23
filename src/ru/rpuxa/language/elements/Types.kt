package ru.rpuxa.language.elements

import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.code.Code
import kotlin.test.fail

const val INTEGER = "I"
const val VOID = "V"
const val OBJECT = "A"
const val STRING = "Ljava/lang/String;"
val VOID_TYPE = object : Type {
    override fun getTypeDescriptor(code: Code) = VOID
}

val STRING_ARRAY_TYPE = object : Type {
    override fun getTypeDescriptor(code: Code) = "[$STRING"
}

val INTEGER_TYPE = object : Type {
    override fun getTypeDescriptor(code: Code) = INTEGER
}

fun getInstructionPrefixFromType(type: Type, code: Code) = when (type.getTypeDescriptor(code)) {
    INTEGER -> INTEGER
    VOID -> fail()
    else -> OBJECT
}

fun getInstructionFromType(type: Type, code: Code, instructionPostfix: String): InstructionTypes {
    val name = (getInstructionPrefixFromType(type, code) + instructionPostfix).toUpperCase()
    return InstructionTypes.values().find { it.name == name }!!
}
