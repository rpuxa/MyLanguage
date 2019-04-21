package ru.rpuxa.language.code

import ru.rpuxa.language.elements.beginningblock.BeginningBlockElement

class Block(
    method: Method,
    override val parent: AbstractBlock,
    override val beginElement: BeginningBlockElement
) : AbstractBlock(method) {

    override val children: MutableList<AbstractBlock> = ArrayList()
    override var currentChild: AbstractBlock? = null

    init {
        startNewExpression()
    }

    override fun pack() {
        currentChild?.pack()
        parent.onCurrentBlockPacked()
    }
}