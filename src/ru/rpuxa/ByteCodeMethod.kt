package ru.rpuxa

import ru.rpuxa.clazz.AccessFlags
import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ByteCodeWritable
import ru.rpuxa.constantpool.types.Utf8Type

class ByteCodeMethod(
    val accessFlags: Array<AccessFlags> = emptyArray(),
    val name: Utf8Type,
    val descriptor: Utf8Type,
    val code: CodeAttribute
) : ByteCodeWritable {

    override fun write(output: ByteCodeStream) {
        output.write2(accessFlags.toInt())
        output.writeIndex(name)
        output.writeIndex(descriptor)
        output.write2(1) // Attrs count
        output.write(code)
    }
}