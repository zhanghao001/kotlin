package base

import java.math.BigDecimal
import java.time.LocalDate

/**
 * @author hao_zhang3
 * @date 2018-12-20
 */

data class Point(val x: Int, val y: Int) {
    // += 可以用 plusAssign 或者 p1 = p1+p2
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}

//重载的参数,返回值类型都可不等于原类型. 重载不支持运算符的交换性
operator fun Point.minus(other: Point) = Point(x - other.x, y - other.y)

operator fun Point.times(scale: Double) = Point(x * scale.toInt(), y * scale.toInt())

operator fun Point.unaryMinus() = Point(-x, -y)

//参数是可变得 比如 p["name"] area[1,2]
operator fun Point.get(index: Int) = when (index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid index $index")
}

data class MutablePoint(var x: Int, var y: Int)

//set的最后一个参数为=右边的值
operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        0 -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid index $index")
    }
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE

class Person4(val firstName: String, val lastName: String) : Comparable<Person4> {
    override fun compareTo(other: Person4): Int {
        return compareValuesBy(this, other, Person4::firstName, Person4::lastName)
    }
}

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
        object : Iterator<LocalDate> {
            var cur = start

            override fun hasNext(): Boolean = cur <= endInclusive

            override fun next(): LocalDate = cur.apply { cur = plusDays(1) }
        }

fun main(args: Array<String>) {
    var p1 = Point(1, 2)
    val p2 = Point(3, 5)
    println(p1 + p2)
    println(p2 - p1)
    p1 += p2
    println(p1)
    println(-p1)

    val list = arrayListOf(1, 2)
    //+=修改了list的内容
    list += 3
    println(list)
    //返回一个新的list
    val newList = list + listOf(4, 5)
    println(newList)

    var zero = BigDecimal.ZERO
    println(zero++)
    println(++zero)

    val person = Person4("zh", "abc")
    val person2 = Person4("zhao", "bdc")
    println(person < person2)

    println(p1[1])
    //error
    //p1[2]

    val p = MutablePoint(2, 4)
    p[1] = 5
    println(p)

    //调用了component1,最多5个
    println(p.component1())
    val (x, y) = p
    println(x)
    println(y)
}
