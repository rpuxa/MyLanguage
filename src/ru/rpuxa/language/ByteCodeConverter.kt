/*
package ru.rpuxa.language

import ru.rpuxa.ByteCodeClass
import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.elements.Element
import ru.rpuxa.language.elements.operators.Assignment
import ru.rpuxa.language.elements.operators.ByteCodeOperator
import java.io.DataOutputStream
import java.io.FileOutputStream
import kotlin.test.fail

object ByteCodeConverter {
    fun convertToCode(list: List<Element>): Array<Instruction> {
        val output = ByteCodeStream(DataOutputStream(FileOutputStream("MyLanguage.class")))
        val pool = ConstantPool()
        val variables = HashMap<STRING, Int>()
        val mainCode = ArrayList<Instruction>()
        var last1: Element? = null
        var last2: Element? = null
        for (e in list) {
            when (e) {
                is ByteCodeOperator -> {
                    when (e.operator) {
                        is Assignment -> {
                            if (last1 !is Variable)
                                fail()
                            val args = (e.operand as Variable).name

                            mainCode.add(Instruction(InstructionTypes.ISTORE, args))
                        }
                    }
                }
            }
            last1 = last2
            last2 = e
        }
        ByteCodeClass()
    }
}
*/
