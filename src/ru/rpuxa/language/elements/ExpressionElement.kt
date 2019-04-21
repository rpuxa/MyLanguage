package ru.rpuxa.language.elements

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import java.util.*


interface ExpressionElement : CodeElement {

    fun replaceStack(
        stack: Stack<Type>,
        expression: Expression,
        code: Code
    )

    fun toByteCode(
        pool: ConstantPool,
        expression: Expression,
        code: Code
    ): List<Instruction>
}