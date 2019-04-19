package ru.rpuxa.language

import ru.rpuxa.language.elements.Element

class ElementsSequence(elements: List<Element>) {
    private val list = elements.toMutableList()
    private var current = 0
    private var next = 1

    fun next() = if (list.lastIndex == current) null else list[current + 1]

    fun previous() = if (current == 0) null else list[current - 1]

    fun skip() = skip(1)

    fun skip(i: Int) {
        next += i
    }

    fun replace(element: Element) {
        list.removeAt(current)
        list.add(current, element)
    }

    inline fun <reified T : Element> nextIs() = next() as? T

    fun replaceAndRepeat(element: Element) {
        replace(element)
        next = current
    }
}