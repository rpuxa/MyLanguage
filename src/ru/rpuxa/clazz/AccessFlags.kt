package ru.rpuxa.clazz

enum class AccessFlags(val mask: Int) {
    PUBLIC(0x0001),
    FINAL(0x0010),
    STATIC(0x0008)


}