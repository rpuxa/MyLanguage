package ru.rpuxa.constantpool.types

import ru.rpuxa.InstructionArgument
import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.ConstantPoolEntry
import ru.rpuxa.constantpool.ConstantPoolEntryTag
import javax.naming.Name

class MethodRefType(val clazz: ClassType,val name: NameAndType,  pool: ConstantPool)
    : ConstantPoolEntry(ConstantPoolEntryTag.METHOD_REF, pool) {

    override fun writeInfo(output: ByteCodeStream) {
        output.writeIndex(clazz)
        output.writeIndex(name)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MethodRefType

        if (clazz != other.clazz) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = clazz.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }


}