package ru.rpuxa.constantpool.types

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.ConstantPoolEntry
import ru.rpuxa.constantpool.ConstantPoolEntryTag

class IntegerType(private val value: Int, pool: ConstantPool) : ConstantPoolEntry(ConstantPoolEntryTag.INTEGER, pool) {
    override fun writeInfo(output: ByteCodeStream) {
        output.write4(value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IntegerType

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }


}