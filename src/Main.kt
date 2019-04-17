import ru.rpuxa.*
import ru.rpuxa.clazz.AccessFlags
import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.*
import ru.rpuxa.instructions.InstructionTypes
import java.io.DataOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
     write()
}

fun read() {
    println()

    var i = 0
    FileInputStream("MyLanguage.class").use {
        while (true) {
            if (i == 100) {
                i = 0
                println()
            }
            val message = it.read()
            if (message == -1)
                return
            print(message.toString() + " ")
            i++
        }
    }
}

fun write() {
    val pool = ConstantPool()
    val num1 = IntegerType(1488, pool)
    val num2 = IntegerType(228, pool)

    val byteCode = ByteCodeClass(
        constantPool = pool
                , methods = arrayOf(ByteCodeMethod(
            accessFlags = arrayOf(AccessFlags.STATIC, AccessFlags.PUBLIC),
            name = Utf8Type("myMethod", pool),
            descriptor = Utf8Type("()I", pool),
            code = CodeAttribute(pool, arrayOf(
                Instruction(InstructionTypes.LDC_W, num1),
                Instruction(InstructionTypes.LDC_W, num2),
                Instruction(InstructionTypes.IDIV),
                Instruction(InstructionTypes.IRETURN)
            ))
        ))
    )

    FileOutputStream("MyLanguage.class").use {
        byteCode.write(ByteCodeStream(DataOutputStream(it)))
    }
}

/*

202 254 186 190 0 0 0 52 0 16 10 0 3 0 13 7 0 14 7 0 15 1 0 6 60 105 110 105 116 62 1 0 3 40 41 86 1 0 4 67 111 100 101 1 0 15 76 105 110 101 78 117 109 98 101 114 84 97 98 108 101 1 0 18 76 111 99 97 108 86 97 114 105 97 98 108 101 84 97 98 108 101 1 0 4 116 104 105 115 1 0 9 76 77 121 67 108 97 115 115
59 1 0 10 83 111 117 114 99 101 70 105 108 101 1 0 12 77 121 67 108 97 115 115 46 106 97 118 97
12 0 4 0 5 1 0 7 77 121 67 108 97 115 115 1 0 16 106 97 118 97 47 108 97 110 103 47 79 98 106 101 99 116 0 32
 0 2 0 3 0 0 0 0
 0 1 methods count
0 0 flags
0 4 name
0 5 //descriptor
0 1 //attrscount
0 6 //Code attr
0 0 0 47
0 1
0 1
0 0 0 5
42 183 0 1 177
0 0
0 2 //attrscount
0 7 //
0 0 0 6 //length
0 1 // line_number_table_length
0 0 0 1
0 8 //local variable table
0 0 0 12 // length
0 1 0 0 0 5 0 9 0 10 0 0

0 1 /attrs count
0 11
0 0 0 2 0 12











202 254 186 190 0 0 0 53 0 12 1 0 6 60 105 110 105 116 62 1 0 3 40 41 86 1 0 16 106 97 118 97 47 108 97 110 103 47 79 98 106 101 99 116 7 0 3 1 0 6 60 105 110 105 116 62 1 0 3 40 41 86 12 0 5 0 6 10 0 4 0 7 1 0 4 67 111 100 101 1 0 15 77 121 76 97 110 103 117 97 103 101 67 108 97 115 115 7 0 10
0 1 0 11 0 4 0 0 0 0 0 1 0 0 0 1 0 2 0 1 0 9 0 0 0 17 3 232 3 232
0 0 0 5
42 183 0 8 177
0 0 0 0 0 0

 */