package ru.rpuxa

import ru.rpuxa.constantpool.ByteCodeStream

class OneByteInstructionArgument(val value: Int) : InstructionArgument {
    override val bytesCount get() = 1

    override fun writeBytes(output: ByteCodeStream) {
        output.write1(value)
    }
}