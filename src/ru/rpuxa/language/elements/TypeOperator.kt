package ru.rpuxa.language.elements

interface TypeOperator {

    fun deriveType(first: Type, second: Type): Type
}