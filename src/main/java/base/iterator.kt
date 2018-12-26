package base

import java.io.BufferedReader
import java.io.StringReader
import java.util.*

/**
 * @author hao_zhang3
 * @date 2018-12-14
 */

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i "
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know..."
}

fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }

    println(number)
}

fun main(args: Array<String>) {
    for (i in 1..100)
        print(fizzBuzz(i))
    for (i in 100 downTo 1 step 2)
        print(fizzBuzz(i))
    val binaryReps = TreeMap<Char, String>()

    //map 遍历
    for (c in 'A'..'Z') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }
    for ((letter, binary) in binaryReps)
        println("$letter = $binary")

    //list with index 遍历
    val list = arrayListOf("10", "12", "1001")
    for ((index, element) in list.withIndex())
        println("$index : $element")

    //in 语法
    println(isLetter('F'))
    println(isNotDigit('q'))
    println(recognize('F'))
    println(recognize('!'))

    // = "Kotlin" >= "Java" && "Kotlin" <= "Scala"
    println("Kotlin" in "Java".."Scala")
    // String not has a 'iterator' method
    // for (str in "Java".."Scala") //error

    //try catch
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
}