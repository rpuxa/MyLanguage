package ru.rpuxa

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ByteCodeWritable

interface InstructionArgument {

    val bytesCount: Int

    fun writeBytes(output: ByteCodeStream)
}