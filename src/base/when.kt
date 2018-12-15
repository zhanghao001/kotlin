package base

/**
 * @author hao_zhang3
 * @date 2018-12-14
 */
sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}


fun main(args: Array<String>) {
    println(evalWithPrint(Expr.Sum(Expr.Sum(Expr.Num(1), Expr.Num(2)), Expr.Num(4))))
    println(eval(Expr.Sum(Expr.Sum(Expr.Num(1), Expr.Num(2)), Expr.Num(4))))

}

fun eval(e: Expr): Int =
    when (e) {
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }


fun evalWithPrint(e: Expr): Int =
    when (e) {
        is Expr.Num -> {
            println("name: ${e.value}")
            e.value
        }
        is Expr.Sum -> {
            val left = evalWithPrint(e.left)
            val right = evalWithPrint(e.right)
            println("sum: $left + $right")
            right + left
        }
    }
