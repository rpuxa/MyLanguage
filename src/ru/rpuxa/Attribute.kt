package ru.rpuxa

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ByteCodeWritable
import ru.rpuxa.constantpool.types.Utf8Type
import java.io.DataOutputStream
import java.io.OutputStream

abstract class Attribute(val name: Utf8Type) : ByteCodeWritable {
    abstract fun writeInfo(output: ByteCodeStream)

    override fun write(output: ByteCodeStream) {
        output.writeIndex(name)
        val list = ArrayList<Int>()
        writeInfo(ByteCodeStream(DataOutputStream(object: OutputStream() {
            override fun write(b: Int) {
                list.add(b)
            }
        })))
        output.write4(list.size)
        for (e in list)
            output.write1(e)
    }
}