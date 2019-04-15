package ru.rpuxa.constantpool

import ru.rpuxa.constantpool.types.ClassType
import ru.rpuxa.constantpool.types.Utf8Type

class ConstantPool : ByteCodeWritable {
    private val entries = ArrayList<ConstantPoolEntry>()

    fun add(entry: ConstantPoolEntry): Int {
        val indexOf = entries.indexOf(entry)
        if (indexOf >= 0)
            return indexOf

        entries.add(entry)
        return entries.lastIndex + 1
    }

    val objectClassName by lazy { Utf8Type("java/lang/Object", this) }
    val objectClass by lazy { ClassType(objectClassName, this) }

    override fun write(output: ByteCodeStream) {
        output.write2(entries.size + 1)
        entries.forEach {
            output.write(it)
        }
    }
}