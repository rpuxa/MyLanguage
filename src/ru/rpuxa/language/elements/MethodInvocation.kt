package ru.rpuxa.language.elements

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.ClassType
import ru.rpuxa.constantpool.types.MethodRefType
import ru.rpuxa.constantpool.types.NameAndType
import ru.rpuxa.constantpool.types.Utf8Type
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.Language
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.specialsymbols.operators.PrefixOperator
import java.util.*
import kotlin.properties.Delegates.notNull
import kotlin.test.fail

class MethodInvocation(val name: String) : PrefixOperator(name, 100), Type {
    var argsCount by notNull<Int>()
    var argsTypes: Array<Type>? = null
    override fun parse(code: Code, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can invoke methods only in expressions")
        expression.operators.push(this)
    }

    override fun getTypeDescriptor(code: Code) =
        code.methods.find { it.name == name }!!.returnType!!.getTypeDescriptor(code)

    override fun replaceStack(
        stack: Stack<Type>,
        expression: Expression,
        code: Code
    ) {
        argsTypes = Array(argsCount) {
            stack.pop()
        }
        stack.push(this)
    }

    fun getDescriptor(code: Code) = code.methods.find { it.name == name }!!.getDescriptor()

    override fun toByteCode(pool: ConstantPool, expression: Expression, code: Code): List<Instruction> = listOf(
        Instruction(
            InstructionTypes.INVOKESTATIC,
            MethodRefType(
                ClassType(Utf8Type(Language.CLASS_NAME, pool), pool),
                NameAndType(Utf8Type(name, pool), Utf8Type(getDescriptor(code), pool), pool),
                pool
            )
        )
    )

}