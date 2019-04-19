package ru.rpuxa.language.elements.operators

import ru.rpuxa.language.elements.Element

class ByteCodeOperator(val operator: Operator, val operand: Element) : Operator(operator.symbols, operator.type, operator.priority)
