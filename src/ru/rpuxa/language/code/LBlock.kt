package ru.rpuxa.language.code

import ru.rpuxa.language.elements.BeginningBlockElement
import ru.rpuxa.language.elements.SimpleBlock
import kotlin.test.fail

class LBlock(
    method: LMethod,
    override val parent: LAbstractBlock,
    begin: BeginningBlockElement
) : LAbstractBlock(method, begin) {

    init {
        if (begin is SimpleBlock)
            fail()
    }

    override fun pack() {
        currentChild?.pack()
        parent.onCurrentBlockPacked()
    }
}