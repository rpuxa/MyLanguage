package ru.rpuxa.language

import ru.rpuxa.language.elements.CodeElement
import ru.rpuxa.language.elements.Element

class ElementsSequence(elements: List<CodeElement>) {
    private val list = elements.toMutableList()
    private var current = 0
    private var next = 1

    fun next() = if (list.lastIndex == current) null else list[current + 1]

    fun previous() = if (current == 0) null else list[current - 1]

    fun skip() = skip(1)

    fun skip(i: Int) {
        next += i
    }

    fun replace(element: CodeElement) {
        list.removeAt(current)
        list.add(current, element)
    }

    inline fun <reified T : Element> nextIs() = next() as? T

    fun replaceAndRepeat(element: CodeElement) {
        replace(element)
        next = current
    }

    val currentElement get() = list[current]

    fun moveToNext() {
        current = next
        next = current + 1
    }

    fun hasNext() = current < list.size
}