package ru.rpuxa.language

import ru.rpuxa.language.elements.Element
import ru.rpuxa.language.elements.IntegerElement
import ru.rpuxa.language.elements.operators.Assignment
import ru.rpuxa.language.elements.operators.ByteCodeOperator
import ru.rpuxa.language.elements.operators.MethodOperator
import ru.rpuxa.language.elements.operators.Operator
import ru.rpuxa.language.elements.specialsymbols.ClosedRoundBracket
import ru.rpuxa.language.elements.specialsymbols.OpenedRoundBracket
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import java.io.FileReader
import java.util.*
import kotlin.collections.ArrayList


object Parser {
    fun read(): String {
        val i = Scanner(FileReader("code.txt"))
        val sb = StringBuilder()
        while (i.hasNext()) {
            sb.append(i.next())
        }
        i.close()
        return sb.toString()
    }

    fun parse(line: String): List<Element> {
        var list = ArrayList<Element>()
        list.add(Word(line))
        for (specialSymbols in SpecialSymbols.ALL) {
            val newList = ArrayList<Element>()
            for (e in list) {
                newList += if (e is Word) parse(e, specialSymbols) else listOf(e)
            }
            list = newList
        }

        return list.filter { it !is Word || !it.string.isBlank() }.map {
            if (it !is Word)
                it
            else {
                Word(it.string.trim())
            }
        }
    }

    private fun parse(word: Word, specialSymbols: SpecialSymbols): List<Element> {
        val s = word.string.split(specialSymbols.symbols)
        val result = ArrayList<Element>()
        repeat(s.size - 1) {
            result += Word(s[it])
            result += specialSymbols
        }
        result += Word(s.last())

        return result
    }

    private fun parse2(list: List<Element>): List<Element> {
        var list = list
        val result = Stack<Element>()
        val operators = Stack<Element>()
        val newList = ArrayList<Element>()
        var last: Element? = null
        for (e in list) {
            if (last is Word && e is Assignment) {
                newList.removeAt(newList.lastIndex)
                newList.add(ByteCodeOperator(e, last))
            } else {
                newList.add(e)
            }
            last = e
        }
        list = newList

        f@ for ((i, e) in list.withIndex()) {
            val element = if (e is Word && i < list.lastIndex && list[i + 1] == OpenedRoundBracket) {
                MethodOperator(e.string)
            } else {
                e
            }
            when (element) {
                is Word -> {
                    val int = element.string.toIntOrNull()
                    if (int != null) {
                        result.push(IntegerElement(int))
                        continue@f
                    }
                    result.push(element)
                }
                is Operator -> {
                    when (element.type) {
                        Operator.Types.POSTFIX -> result.push(element)
                        Operator.Types.PREFIX -> operators.push(element)
                        else -> {
                            while (operators.isNotEmpty()) {
                                val operator = operators.peek()
                                if (operator is Operator && (
                                            operator.type == Operator.Types.PREFIX ||
                                                    operator.priority > element.priority ||
                                                    (operator.priority == element.priority && operator.type == Operator.Types.LEFT_ASSOCIATIVE)
                                            )
                                ) {
                                    result.push(operators.pop())
                                } else {
                                    break
                                }
                            }
                            operators.push(element)
                        }
                    }
                }
                is OpenedRoundBracket -> operators.push(element)
                is ClosedRoundBracket -> {
                    while (true) {
                        if (operators.isEmpty())
                            throw IllegalStateException("Wrong braces")
                        val operator = operators.pop()
                        if (operator is OpenedRoundBracket)
                            break
                        result.push(operator)
                    }
                }

                else -> throw IllegalStateException()
            }
        }
        while (operators.isNotEmpty())
            result.push(operators.pop())
        return result.map { if (it is Word) Variable(it.string) else it }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val parse = parse("sin = 2 * sin(4)")
        val result = parse2(parse)
    }

    class InnerClass {
        class InnerClass2 {
            fun method() {

            }
        }
    }
}