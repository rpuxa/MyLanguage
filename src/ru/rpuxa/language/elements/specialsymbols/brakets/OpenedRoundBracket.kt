package ru.rpuxa.language.elements.specialsymbols.brakets

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.ExpressionElement
import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbolInstance
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import java.util.*
import kotlin.test.fail

object OpenedRoundBracket : SpecialSymbols("("), ExpressionElement, SpecialSymbolInstance {

    override fun getNewInstance() = this
    override fun parse(code: Code, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use bracket only in expressions")
        expression.operators.push(this)
        expression.outStackSize = expression.outStack.size
    }

    override fun replaceStack(
        stack: Stack<Type>,
        expression: Expression,
        code: Code
    ) {
        fail()
    }

    override fun toByteCode(pool: ConstantPool, expression: Expression, code: Code): List<Instruction> {
        fail()
    }
}