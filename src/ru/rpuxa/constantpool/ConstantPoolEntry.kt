package ru.rpuxa.constantpool

abstract class ConstantPoolEntry(
    val tag: ConstantPoolEntryTag,
    pool: ConstantPool
) : ByteCodeWritable {

    val index: Int = pool.add(this)

    override fun write(output: ByteCodeStream) {
        output.write(tag)
        writeInfo(output)
    }

    abstract fun writeInfo(output: ByteCodeStream)

    abstract override fun equals(other: Any?): Boolean
}


