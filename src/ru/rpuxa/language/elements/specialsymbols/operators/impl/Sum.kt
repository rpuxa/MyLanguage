package ru.rpuxa.language.elements.specialsymbols.operators.impl

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.*
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbolFactory
import ru.rpuxa.language.elements.specialsymbols.operators.LeftAssociativeOperator
import java.util.*

class Sum : LeftAssociativeOperator("+", 0), TypeOperator {

    private lateinit var type: Type
    private lateinit var firstOperandType: Type
    private lateinit var secondOperandType: Type

    override fun replaceStack(stack: Stack<Type>, expression: Expression, code: Code) {
        secondOperandType = stack.pop()
        firstOperandType = stack.pop()
        val typeExpression = TypeExpression(this, firstOperandType, secondOperandType)
        type = typeExpression
        stack.push(typeExpression)
    }

    override fun toByteCode(pool: ConstantPool, expression: Expression, code: Code): List<Instruction> {
//       if (type.getTypeDescriptor(code) == INTEGER)
        return listOf(Instruction(getInstructionFromType(type, code, "ADD")))
    }

    override fun deriveType(
        code: Code,
        first: Type,
        second: Type
    ): Type {
       /* if (first.getS || second is StringLiteral)
            return first*/
        return INTEGER_TYPE
    }

    companion object : SpecialSymbolFactory {
        override fun getNewInstance() = Sum()
    }
}