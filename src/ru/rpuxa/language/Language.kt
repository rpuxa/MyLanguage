package ru.rpuxa.language

import ru.rpuxa.*
import ru.rpuxa.clazz.AccessFlags
import ru.rpuxa.constantpool.ByteCodeStream
import ru.rpuxa.constantpool.ConstantPool
import ru.rpuxa.constantpool.types.*
import ru.rpuxa.instructions.InstructionTypes
import ru.rpuxa.language.code.Code
import ru.rpuxa.language.code.Method
import ru.rpuxa.language.elements.*
import ru.rpuxa.language.elements.literals.StringLiteral
import ru.rpuxa.language.elements.specialsymbols.Space
import ru.rpuxa.language.elements.specialsymbols.SpecialSymbols
import java.io.*
import java.util.*


object Language {

    const val CLASS_NAME = "MyLanguageClass"
    const val SCANNER_FIELD_NAME = "scanner"
    const val SCANNER_FIELD_DESCRIPTOR = "Ljava/util/Scanner;"

    private fun read(): String {
        val i = BufferedReader(InputStreamReader(FileInputStream("code.txt")))
        val sb = StringBuilder()
        while (true) {
            val readLine = i.readLine() ?: break
            sb.append(readLine).append('\n')
        }
        i.close()
        return sb.toString()
    }

    private fun stringToElementsSequence(code: String): ElementsSequence {
        //string literals
        var list = ArrayList<CodeElement>()
        code.split('\"').forEachIndexed { index, s ->
            list.add(
                if (index % 2 == 0)
                    Word(s)
                else
                    StringLiteral(s)
            )
        }
        for (symbol in SpecialSymbols.ALL) {
            val splitList = ArrayList<CodeElement>()
            for (element in list)
                if (element is Word) {
                    val split = element.value.split(symbol.getNewInstance().symbols)
                        .flatMap { listOf(Word(it), symbol.getNewInstance()) }
                    repeat(split.size - 1) {
                        splitList += split[it]
                    }
                } else {
                    splitList += element
                }
            list = splitList
        }
        list.add(EndOfCode)
        return ElementsSequence(
            list
                .asSequence()
                .filterNot { it is Word && it.value.isBlank() }
                .filterNot { it is Space }
                .map { if (it is Word) Word(it.value.trim()) else it }
                .toList()
        )
    }

    fun elementSequenceToCode(sequence: ElementsSequence): Code {
        val code = Code()
        code.currentMethod = Method(code, "main")
        while (sequence.hasNext()) {
            sequence.currentElement.parse(code, sequence)
            sequence.moveToNext()
        }

        return code
    }

    fun codeToByteCode(code: Code): ByteCodeClass {
        val pool = ConstantPool()
        val methods = ArrayList<ByteCodeMethod>()
        val fields = ArrayList<ByteCodeField>()
        val staticInitInstructions = ArrayList<Instruction>()
        for (method in code.methods) {
            var instructions = method.block.beginElement.toByteCode(pool, method.block)
            val commands = arrayOf("LOAD", "STORE")
            instructions = instructions.map { instruction ->
                for (command in commands) {
                    val arg = instruction.args.firstOrNull()
                    if (instruction.type.name.endsWith(command) && arg is OneByteInstructionArgument && arg.value in 0..3) {
                        val type =
                            InstructionTypes.values().find { it.name == "${instruction.type.name}_${arg.value}" }!!
                        return@map Instruction(type)
                    }
                }
                instruction
            }
            if (method.returnType === VOID_TYPE)
                instructions += Instruction(InstructionTypes.RETURN)

            methods.add(
                ByteCodeMethod(
                    accessFlags = arrayOf(AccessFlags.PUBLIC, AccessFlags.STATIC),
                    name = Utf8Type(method.name, pool),
                    descriptor = Utf8Type(method.getDescriptor(), pool),
                    code = CodeAttribute(
                        pool = pool,
                        instructions = instructions.toTypedArray()
                    )
                )
            )
        }

        if (code.generateInputMethod) {
            fields += ByteCodeField(
                arrayOf(AccessFlags.PUBLIC, AccessFlags.STATIC),
                Utf8Type(SCANNER_FIELD_NAME, pool),
                Utf8Type(SCANNER_FIELD_DESCRIPTOR, pool)
            )


            val scannerClass = ClassType(Utf8Type("java/util/Scanner", pool), pool)
            staticInitInstructions += listOf(
                Instruction(InstructionTypes.NEW, scannerClass),
                Instruction(InstructionTypes.DUP),
                Instruction(
                    InstructionTypes.GETSTATIC,
                    FieldType(
                        ClassType(Utf8Type("java/lang/System", pool), pool),
                        NameAndType(Utf8Type("in", pool), Utf8Type("Ljava/io/InputStream;", pool), pool),
                        pool
                    )
                ),
                Instruction(
                    InstructionTypes.INVOKESPECIAL, MethodRefType(
                        scannerClass,
                        NameAndType(
                            Utf8Type("<init>", pool),
                            Utf8Type("(Ljava/io/InputStream;)V", pool),
                            pool
                        ),
                        pool
                    )
                ),
                Instruction(
                    InstructionTypes.PUTSTATIC,
                    FieldType(
                        ClassType(Utf8Type(CLASS_NAME, pool), pool),
                        NameAndType(Utf8Type(SCANNER_FIELD_NAME, pool), Utf8Type(SCANNER_FIELD_DESCRIPTOR, pool), pool),
                        pool
                    )
                )
            )
        }

        methods += ByteCodeMethod(
            arrayOf(AccessFlags.PUBLIC, AccessFlags.STATIC),
            Utf8Type("main", pool),
            Utf8Type("([$STRING)V", pool),
            code = CodeAttribute(
                pool, arrayOf(
                    Instruction(
                        InstructionTypes.INVOKESTATIC, MethodRefType(
                            ClassType(Utf8Type(CLASS_NAME, pool), pool),
                            NameAndType(Utf8Type("main", pool), Utf8Type("()V", pool), pool),
                            pool
                        )
                    ),
                    Instruction(InstructionTypes.RETURN)
                )
            )
        )


        if (staticInitInstructions.isNotEmpty()) {
            methods.add(
                0,
                ByteCodeMethod(
                    arrayOf(AccessFlags.STATIC),
                    Utf8Type("<clinit>", pool),
                    Utf8Type("()V", pool),
                    CodeAttribute(
                        pool, arrayOf(*staticInitInstructions.toTypedArray(), Instruction(InstructionTypes.RETURN))
                    )
                )
            )
        }

        return ByteCodeClass(
            constantPool = pool,
            accessFlags = arrayOf(AccessFlags.PUBLIC, AccessFlags.FINAL),
            thisClass = ClassType(Utf8Type(CLASS_NAME, pool), pool),
            fields = fields.toTypedArray(),
            methods = methods.toTypedArray()
        )
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val source = read()
        val sequence = stringToElementsSequence(source)
        val code = elementSequenceToCode(sequence)
        val byteCode = codeToByteCode(code)
        byteCode.write(
            ByteCodeStream(DataOutputStream(FileOutputStream("$CLASS_NAME.class")))
        )
    }

    /* fun parse(line: String): List<Element> {
         var list = ArrayList<Element>()
         list.add(Word(line))
         for (specialSymbols in SpecialSymbols.ALL) {
             val newList = ArrayList<Element>()
             for (e in list) {
                 newList += if (e is Word) parse(e, specialSymbols) else listOf(e)
             }
             list = newList
         }

         return list.filter { it !is Word || !it.string.isBlank() }.map {
             if (it !is Word)
                 it
             else {
                 Word(it.string.trim())
             }
         }
     }

     private fun parse(word: Word, specialSymbols: SpecialSymbols): List<Element> {
         val s = word.string.split(specialSymbols.symbols)
         val result = ArrayList<Element>()
         repeat(s.size - 1) {
             result += Word(s[it])
             result += specialSymbols
         }
         result += Word(s.last())

         return result
     }

     private fun parse2(list: List<Element>): List<Element> {
         var list = list
         val result = Stack<Element>()
         val operators = Stack<Element>()
         val newList = ArrayList<Element>()
         var last: Element? = null
         for (e in list) {
             if (last is Word && e is Assignment) {
                 newList.removeAt(newList.lastIndex)
                 newList.add(ByteCodeOperator(e, last))
             } else {
                 newList.add(e)
             }
             last = e
         }
         list = newList

         f@ for ((i, e) in list.withIndex()) {
             val element = if (e is Word && i < list.lastIndex && list[i + 1] == OpenedRoundBracket) {
                 MethodOperator(e.string)
             } else {
                 e
             }
             when (element) {
                 is Word -> {
                     val int = element.string.toIntOrNull()
                     if (int != null) {
                         result.push(IntegerLiteral(int))
                         continue@f
                     }
                     result.push(element)
                 }
                 is Operator -> {
                     when (element.type) {
                         Operator.Types.POSTFIX -> result.push(element)
                         Operator.Types.PREFIX -> operators.push(element)
                         else -> {
                             while (operators.isNotEmpty()) {
                                 val operator = operators.peek()
                                 if (operator is Operator && (
                                             operator.type == Operator.Types.PREFIX ||
                                                     operator.priority > element.priority ||
                                                     (operator.priority == element.priority && operator.type == Operator.Types.LEFT_ASSOCIATIVE)
                                             )
                                 ) {
                                     result.push(operators.pop())
                                 } else {
                                     break
                                 }
                             }
                             operators.push(element)
                         }
                     }
                 }
                 is OpenedRoundBracket -> operators.push(element)
                 is ClosedRoundBracket -> {
                     while (true) {
                         if (operators.isEmpty())
                             throw IllegalStateException("Wrong braces")
                         val operator = operators.pop()
                         if (operator is OpenedRoundBracket)
                             break
                         result.push(operator)
                     }
                 }

                 else -> throw IllegalStateException()
             }
         }
         while (operators.isNotEmpty())
             result.push(operators.pop())
         return result.map { if (it is Word) Variable(it.string) else it }
     }

     @JvmStatic
     fun main(args: Array<String>) {
         val parse = parse("sin = 2 * sin(4)")
         val result = parse2(parse)
     }

     class InnerClass {
         class InnerClass2 {
             fun method() {

             }
         }
     }*/
}