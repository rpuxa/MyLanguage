package ru.rpuxa.language.elements.beginningblock

import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.code.AbstractBlock
import ru.rpuxa.language.elements.Element

interface BeginningBlockElement : Element {
    fun toByteCode(pool: ConstantPool, block: AbstractBlock): List<Instruction>
}