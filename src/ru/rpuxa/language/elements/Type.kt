package ru.rpuxa.language.elements

import ru.rpuxa.language.code.Code

interface Type {
    fun getTypeDescriptor(code: Code): String
}