package ru.rpuxa.constantpool.types

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.ConstantPoolEntry
import ru.rpuxa.constantpool.ConstantPoolEntryTag

class Utf8Type(var value: String, pool: ConstantPool) : ConstantPoolEntry(ConstantPoolEntryTag.UTF8, pool) {

    override fun writeInfo(output: ByteCodeStream) {
        output.writeUTF(value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Utf8Type

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}