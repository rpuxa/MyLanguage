package ru.rpuxa.language

import ru.rpuxa.Instruction
import ru.rpuxa.OneByteInstructionArgument
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.ExpressionElement
import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.getInstructionFromType
import java.util.*
import kotlin.test.fail

class Variable(val name: String) : ExpressionElement, Type {
    var type: Type? = null
    var expression: Expression? = null
    var number: Int? = null

    override fun getTypeDescriptor(code: Code): String {
        return type?.getTypeDescriptor(code) ?: fail("Problem with checking variable type")
    }

    override fun replaceStack(
        stack: Stack<Type>,
        expression: Expression,
        code: Code
    ) {
        stack.push(this)
    }

    override fun toByteCode(
        pool: ConstantPool,
        expression: Expression,
        code: Code
    ) = listOf(
        Instruction(getInstructionFromType(type!!, code, "LOAD"), OneByteInstructionArgument(number!!))
    )

    override fun parse(code: Code, sequence: ElementsSequence) {
       val expression = code.currentMethod?.block?.expression ?: fail("You can use variables only in expressions")
        expression.outStack.push(this)
    }
}