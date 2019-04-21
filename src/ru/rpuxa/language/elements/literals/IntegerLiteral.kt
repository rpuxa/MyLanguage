package ru.rpuxa.language.elements.literals

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.IntegerType
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.INTEGER

class IntegerLiteral(private val value: Int) : Literal {
    override fun getTypeDescriptor(code: Code): String = INTEGER

    override fun toByteCode(
        pool: ConstantPool,
        expression: Expression,
        code: Code
    ) = listOf(
        Instruction(InstructionTypes.LDC_W, IntegerType(value, pool))
    )
}