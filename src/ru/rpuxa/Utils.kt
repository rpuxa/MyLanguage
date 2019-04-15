package ru.rpuxa

import ru.rpuxa.clazz.AccessFlags

fun Array<AccessFlags>.toInt(): Int {
    var access = 0
    for (flag in this)
        access = access or flag.mask
    return access
}