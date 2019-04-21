package ru.rpuxa

import ru.rpuxa.constantpool.ByteCodeStream

class TwoByteInstructionArgument(val value: Int) : InstructionArgument {
    override val bytesCount get() = 2

    override fun writeBytes(output: ByteCodeStream) {
        output.write2(value)
    }
}