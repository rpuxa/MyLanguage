package ru.rpuxa.constantpool

import ru.rpuxa.CountBytesStream
import ru.rpuxa.InstructionArgument
import java.io.DataOutputStream

abstract class ConstantPoolEntry(
    val tag: ConstantPoolEntryTag,
    pool: ConstantPool
) : ByteCodeWritable, InstructionArgument {

    override val bytesCount get() = 2
    val index: Int = pool.add(this)

    override fun write(output: ByteCodeStream) {
        output.write(tag)
        writeInfo(output)
    }

    override fun writeBytes(output: ByteCodeStream) {
        output.writeIndex(this)
    }

    abstract fun writeInfo(output: ByteCodeStream)

    abstract override fun equals(other: Any?): Boolean
}


