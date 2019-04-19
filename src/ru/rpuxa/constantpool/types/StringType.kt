package ru.rpuxa.constantpool.types

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.ConstantPoolEntry
import ru.rpuxa.constantpool.ConstantPoolEntryTag

class StringType(val value: Utf8Type, pool: ConstantPool) : ConstantPoolEntry(ConstantPoolEntryTag.STRING, pool) {
    override fun writeInfo(output: ByteCodeStream) {
        output.writeIndex(value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StringType

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }


}