package ru.rpuxa

import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ByteCodeWritable
import ru.rpuxa.instructions.InstructionTypes
import java.io.DataOutputStream

class Instruction(val type: InstructionTypes, vararg val args: InstructionArgument) : ByteCodeWritable {

    override fun write(output: ByteCodeStream) {
        output.write(type)
        for (arg in args)
            arg.writeBytes(output)
    }

    val bytesCount
        get() = 1 + args.sumBy {
           it.bytesCount
        }
}