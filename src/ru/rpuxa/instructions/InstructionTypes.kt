package ru.rpuxa.instructions

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ByteCodeWritable

enum class InstructionTypes(private val code: Int) : ByteCodeWritable {
    NONE(0x00),
    ALOAD_0(0x2a),
    INVOKE_SPECIAL(0xb7),
    RETURN(0xb1)
    ;

    override fun write(output: ByteCodeStream) {
        output.write1(code)
    }
}