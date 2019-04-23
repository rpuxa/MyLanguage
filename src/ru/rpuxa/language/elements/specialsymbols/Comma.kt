package ru.rpuxa.language.elements.specialsymbols

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.ElementsSequence
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Expression
import ru.rpuxa.language.elements.ExpressionElement
import ru.rpuxa.language.elements.Type
import ru.rpuxa.language.elements.specialsymbols.brakets.OpenedRoundBracket
import java.util.*
import kotlin.test.fail

object Comma : SpecialSymbols(","), ExpressionElement, SpecialSymbolFactory {

    override fun getNewInstance() = this
    override fun parse(code: Code, sequence: ElementsSequence) {
        val expression = code.currentMethod?.block?.expression ?: fail("You can use comma only in expressions")
        while (expression.operators.isNotEmpty() &&
            expression.operators.peek() !is OpenedRoundBracket &&
            expression.operators.peek() !is Comma
        ) {
            expression.outStack.push(expression.operators.pop())
        }
        if (expression.operators.empty())
            fail("Missing comma or open bracket")
        expression.operators.push(this)
    }

    override fun replaceStack(stack: Stack<Type>, expression: Expression, code: Code) {
        fail()
    }

    override fun toByteCode(pool: ConstantPool, expression: Expression, code: Code): List<Instruction> {
        fail()
    }
}