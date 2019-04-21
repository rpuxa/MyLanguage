package ru.rpuxa.language.elements.beginningblock

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.code.AbstractBlock
import ru.rpuxa.language.code.Expression

object ExpressionBlockElement : BeginningBlockElement {
    override fun toByteCode(pool: ConstantPool, block: AbstractBlock): List<Instruction> {
        val expression = block as Expression
        return expression.outStack.flatMap { it.toByteCode(pool, expression, block.method.code) }
    }
}