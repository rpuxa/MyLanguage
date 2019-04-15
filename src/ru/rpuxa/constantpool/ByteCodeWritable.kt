package ru.rpuxa.constantpool

interface ByteCodeWritable {
    fun write(output: ByteCodeStream)
}