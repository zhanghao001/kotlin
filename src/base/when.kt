package base

import java.lang.IllegalArgumentException

/**
 * @author zhanghao3
 * @date 2018-12-14
 */
interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun main(args: Array<String>) {
    println(evalWithPrint(Sum(Sum(Num(1), Num(2)), Num(4))))
}

fun eval(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.right) + eval(e.left)
            else -> throw IllegalArgumentException("Unknown")
        }


fun evalWithPrint(e: Expr): Int =
        when (e) {
            is Num -> {
                println("name: ${e.value}")
                e.value
            }
            is Sum -> {
                val left = evalWithPrint(e.left)
                val right = evalWithPrint(e.right)
                println("sum: $left + $right")
                right + left
            }
            else -> throw IllegalArgumentException("Unknown")
        }
