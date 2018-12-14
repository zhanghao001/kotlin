package base

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


}