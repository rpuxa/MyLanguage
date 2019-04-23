package ru.rpuxa.language.elements

import ru.rpuxa.language.code.Code

class TypeExpression(val operator: TypeOperator, val first: Type, val second: Type) : Type {
    override fun getTypeDescriptor(code: Code) =
        operator.deriveType(code, first, second).getTypeDescriptor(code)

}