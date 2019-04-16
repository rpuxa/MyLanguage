package ru.rpuxa.constantpool.types

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.ConstantPoolEntry
import ru.rpuxa.constantpool.ConstantPoolEntryTag

class NameAndType(val name: Utf8Type, val descriptor: Utf8Type, pool: ConstantPool) : ConstantPoolEntry(ConstantPoolEntryTag.NAME_AND_TYPE, pool) {
    override fun writeInfo(output: ByteCodeStream) {
        output.writeIndex(name)
        output.writeIndex(descriptor)
    }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NameAndType

        if (name != other.name) return false
        if (descriptor != other.descriptor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + descriptor.hashCode()
        return result
    }


}