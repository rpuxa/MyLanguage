package ru.rpuxa.language.elements

import ru.rpuxa.language.code.Code

interface TypeOperator {

    fun deriveType(
        code: Code,
        first: Type,
        second: Type
    ): Type
}