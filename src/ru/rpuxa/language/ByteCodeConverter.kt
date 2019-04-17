package ru.rpuxa.language

import ru.rpuxa.ByteCodeClass
import ru.rpuxa.Instruction
import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.language.elements.Element
import ru.rpuxa.language.elements.operators.Assignment
import java.io.DataOutputStream
import java.io.FileOutputStream
import kotlin.test.fail

object ByteCodeConverter {
    fun convertToCode(list: List<Element>): Array<Instruction> {
        val output = ByteCodeStream(DataOutputStream(FileOutputStream("MyLanguage.class")))
        val pool = ConstantPool()
        val variables = HashMap<String, Int>()
        val mainCode = ArrayList<Instruction>()
        var last1: Element? = null
        var last2: Element? = null
        for (e in list) {
            when (e) {
                 is Assignment -> {
                     if (last1 !is Variable)
                         fail()

                 }
            }
            last1 = last2
            last2 = e
        }
        ByteCodeClass()
    }
}