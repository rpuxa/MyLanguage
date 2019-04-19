package ru.rpuxa.language.elements

import ru.rpuxa.language.code.LExpression
import java.util.*

interface ExpressionElement : Element {
    fun getType(types: Stack<String>, expression: LExpression): String
}