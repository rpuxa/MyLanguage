package ru.rpuxa.language.elements.literals

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.StringType
import ru.rpuxa.constantpool.types.Utf8Type
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.STRING

class StringLiteral(val value: String) : Literal {
    override fun toByteCode(pool: ConstantPool, expression: Expression, code: Code) = listOf(
        Instruction(InstructionTypes.LDC_W, StringType(Utf8Type(value, pool), pool))
    )

    override fun getTypeDescriptor(code: Code) = STRING
}