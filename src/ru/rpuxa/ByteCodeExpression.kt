package ru.rpuxa

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ByteCodeWritable
import ru.rpuxa.constantpool.ConstantPool

class CodeAttribute(pool: ConstantPool, val instructions: Array<Instruction>) : Attribute(pool.codeAttribute) {
    override fun writeInfo(output: ByteCodeStream) {
        output.write2(1000)//Max stack
        output.write2(1000)//Max locals
        val instructionsBytesCount = instructions.sumBy { it.bytesCount }
        output.write4(instructionsBytesCount)
        for (instruction in instructions)
            output.write(instruction)
        output.write2(0)//Exceptions count
        output.write2(0)//Attrs count
    }
}