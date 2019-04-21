package ru.rpuxa.language.elements.beginningblock

import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.code.AbstractBlock

object SimpleBlock : BeginningBlockElement {

    override fun toByteCode(pool: ConstantPool, block: AbstractBlock) =
        block.children.flatMap { it.beginElement.toByteCode(pool, it) }

}