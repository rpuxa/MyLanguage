package ru.rpuxa.constantpool.types

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.ConstantPoolEntry
import ru.rpuxa.constantpool.ConstantPoolEntryTag

open class ClassType(val classPath: Utf8Type, pool: ConstantPool) : ConstantPoolEntry(ConstantPoolEntryTag.CLASS, pool) {

    override fun writeInfo(output: ByteCodeStream) {
        output.writeIndex(classPath)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClassType

        if (classPath != other.classPath) return false

        return true
    }

    override fun hashCode(): Int {
        return classPath.hashCode()
    }


}