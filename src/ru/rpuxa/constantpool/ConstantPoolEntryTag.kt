package ru.rpuxa.constantpool

enum class ConstantPoolEntryTag(private val value: Int) : ByteCodeWritable {
    INTEGER(3),
    UTF8(1),
    CLASS(7)
    ;

    override fun write(output: ByteCodeStream) {
        output.write1(value)
    }
}