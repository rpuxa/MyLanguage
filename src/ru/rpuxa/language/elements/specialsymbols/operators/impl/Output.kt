package ru.rpuxa.language.elements.specialsymbols.operators.impl

import ru.rpuxa.Instruction
import ru.rpuxa.OneByteInstructionArgument
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.*
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.getInstructionFromType
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbolFactory
import ru.rpuxa.language.elements.specialsymbols.operators.RightAssociativeOperator
import java.util.*

class Output : RightAssociativeOperator("<<", -100) {

    private var type: Type? = null

    override fun replaceStack(stack: Stack<Type>, expression: Expression, code: Code) {
        type = stack.peek()
    }

    override fun toByteCode(pool: ConstantPool, expression: Expression, code: Code): List<Instruction> {
        val signature = Utf8Type("(${type!!.getTypeDescriptor(code)})V", pool)
        val value = expression.method.syntacticVariableNum
        val instructions = mutableListOf(
            Instruction(
                getInstructionFromType(type!!, code, "STORE"),
                OneByteInstructionArgument(value)
            ),
            Instruction(
                InstructionTypes.GETSTATIC, FieldType(
                    ClassType(Utf8Type("java/lang/System", pool), pool),
                    NameAndType(
                        Utf8Type("out", pool),
                        Utf8Type(
                            "Ljava/io/PrintStream;",
                            pool
                        ), pool
                    ),
                    pool
                )
            ),
            Instruction(
                getInstructionFromType(type!!, code, "LOAD"),
                OneByteInstructionArgument(value)
            ),
            Instruction(
                InstructionTypes.INVOKEVIRTUAL, MethodRefType(
                    ClassType(Utf8Type("java/io/PrintStream", pool), pool),
                    NameAndType(
                        Utf8Type("println", pool),
                        signature,
                        pool
                    ),
                    pool
                )
            )
        )
        if (expression.outStack.peek() !== this) {
            instructions +=
                    Instruction(
                        getInstructionFromType(type!!, code, "LOAD"),
                        OneByteInstructionArgument(value)
                    )
        }
        return instructions
    }

    companion object : SpecialSymbolFactory {
        override fun getNewInstance() = Output()
    }
}