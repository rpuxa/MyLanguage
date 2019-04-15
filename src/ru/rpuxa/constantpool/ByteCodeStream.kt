package ru.rpuxa.constantpool

import java.io.DataOutputStream

class ByteCodeStream(val os: DataOutputStream) {

    fun write1(b: Int) {
        os.writeByte(b)
    }

    fun write2(b: Int) {
        os.writeShort(b)
    }

    fun write(writable: ByteCodeWritable) {
        writable.write(this)
    }

    fun write4(value: Int) {
        os.writeInt(value)
    }

    fun write4(value: Long) {
        os.writeInt(value.toInt())
    }

    fun writeUTF(s: String) {
        os.writeUTF(s)
    }

    fun writeIndex(type: ConstantPoolEntry) {
        write2(type.index)
    }
}