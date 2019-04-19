package ru.rpuxa.language.code

class LCode {
    val methods = ArrayList<LMethod>()

    var currentMethod: LMethod? = null

    fun pack() {
        currentMethod?.pack()
    }

    fun onCurrentMethodPacked() {
        methods.add(currentMethod!!)
        currentMethod = null
    }
}