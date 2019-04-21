package ru.rpuxa

import ru.rpuxa.clazz.AccessFlags
import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ByteCodeWritable
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.ClassType
import ru.rpuxa.constantpool.types.Utf8Type

class ByteCodeClass(
    val minor: Int = 0,
    val major: Int = 52,
    val constantPool: ConstantPool = ConstantPool(),
    val accessFlags: Array<AccessFlags> = arrayOf(AccessFlags.PUBLIC),
    val thisClass: ClassType = ClassType(Utf8Type("MyLanguage", constantPool), constantPool),
    val superClass: ClassType = constantPool.objectClass,
    val interfaces: Array<ClassType> = emptyArray(),
    val fields: Array<ByteCodeField> = emptyArray(),
    val methods: Array<ByteCodeMethod> = emptyArray()
) : ByteCodeWritable {
    override fun write(output: ByteCodeStream) {
        output.write4(0xCAFEBABE)
        output.write2(minor)
        output.write2(major)
        output.write(constantPool)
        output.write2(accessFlags.toInt())
        output.writeIndex(thisClass)
        output.writeIndex(superClass)
        output.write2(interfaces.size)
        interfaces.forEach(output::writeIndex)
        output.write2(fields.size)
        fields.forEach(output::write)
        output.write2(methods.size)
        methods.forEach(output::write)
        output.write2(0)
    }
}
