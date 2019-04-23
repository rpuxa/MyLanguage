package ru.rpuxa.language.elements.specialsymbols.operators.impl

import ru.rpuxa.Instruction
import ru.rpuxa.OneByteInstructionArgument
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.*
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.Language
import ru.rpuxa.language.Variable
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.INTEGER_TYPE
import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbolFactory
import ru.rpuxa.language.elements.specialsymbols.operators.PostfixOperator
import java.util.*
import kotlin.test.fail

object InputInt : PostfixOperator(">>", 100), SpecialSymbolFactory {

    override fun getNewInstance() = this

    override fun parse(code: Code, sequence: ElementsSequence, expression: Expression) {
        val variable = expression.outStack.pop() as? Variable ?: fail("You can input only to variable")
        variable.type = INTEGER_TYPE
        expression.variables.push(variable)
        super.parse(code, sequence, expression)
    }

    override fun replaceStack(stack: Stack<Type>, expression: Expression, code: Code) {
        stack.push(INTEGER_TYPE)
    }

    override fun toByteCode(pool: ConstantPool, expression: Expression, code: Code): List<Instruction> {
        code.generateInputMethod = true
        return listOf(
            Instruction(
                InstructionTypes.GETSTATIC,
                FieldType(
                    ClassType(Utf8Type(Language.CLASS_NAME, pool), pool),
                    NameAndType(
                        Utf8Type(Language.SCANNER_FIELD_NAME, pool),
                        Utf8Type(Language.SCANNER_FIELD_DESCRIPTOR, pool),
                        pool
                    ),
                    pool
                )
            ),
            Instruction(
                InstructionTypes.INVOKEVIRTUAL,
                MethodRefType(
                    ClassType(Utf8Type("java/util/Scanner", pool), pool),
                    NameAndType(Utf8Type("nextInt", pool), Utf8Type("()I", pool), pool),
                    pool
                )
            ),
            Instruction(
                InstructionTypes.ISTORE,
                OneByteInstructionArgument(
                    expression.variables.pop().number!!
                )
            )
        )
    }
}