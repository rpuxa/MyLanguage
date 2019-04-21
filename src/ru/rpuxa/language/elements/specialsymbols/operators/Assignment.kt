package ru.rpuxa.language.elements.specialsymbols.operators

import ru.rpuxa.Instruction
import ru.rpuxa.OneByteInstructionArgument
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.Variable
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.getInstructionFromType
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbolInstance
import java.util.*
import kotlin.test.fail

object Assignment : RightAssociativeOperator("=", -100), SpecialSymbolInstance {

    override fun getNewInstance() = this
    override fun replaceStack(
        stack: Stack<Type>,
        expression: Expression,
        code: Code
    ) {
        val findLast = expression.variables.findLast { it.type == null } ?: return
        findLast.type = stack.peek()
        findLast.expression = expression
    }

    override fun toByteCode(
        pool: ConstantPool,
        expression: Expression,
        code: Code
    ): List<Instruction> {
        val variable = expression.variables.pop()
        val value = variable.number!!
        return listOf(
            Instruction(InstructionTypes.DUP),
            Instruction(getInstructionFromType(variable, code, "STORE"), OneByteInstructionArgument(value))
        )
    }

    override fun parse(code: Code, sequence: ElementsSequence, expression: Expression) {
        val variable = expression.outStack.pop() as? Variable ?: fail("You can assign only to variable")
        expression.variables.push(variable)
        super.parse(code, sequence, expression)
    }
}
