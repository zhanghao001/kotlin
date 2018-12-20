package base

import java.lang.String.format

/**
 * @author hao_zhang3
 * @date 2018-12-16
 */
fun strLenSafe(s: String?): Int = s?.length ?: 0

fun sendEmail(s: String) = println("send email.$s")

fun <T> printHashcode(t: T) {
    println(t?.hashCode())
}

//指定泛型非空上界后,不为可空类型
fun <T : Any> printHashcode2(t: T) {
    println(t?.hashCode())
}

interface Processor<T> {
    fun process(): T
}

class NoResultProcessor : Processor<Unit> {
    //可以不用return
    override fun process() {
    }
}

fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    source.forEach { target.add(it) }
}

fun main(args: Array<String>) {
    println(strLenSafe("zhang"))
    println(strLenSafe(null))

    var email: String? = null
    email?.let { sendEmail(it) }
    //error
    //email.let { sendEmail(it) }
    email = "zhang"
    email?.let { sendEmail(it) }

    printHashcode(null)
    printHashcode2("za")
    //erroe
    //printHashcode2(null)

    val x = 1
    val list = listOf(1L, 2L, 3L)
    //不会做任何自动的类型转化.哪怕是int->long
    //println(x in list)//error
    println(x.toLong() in list)

    //不可变接口和可变接口
    val source: Collection<Int> = arrayListOf(2, 3, 5)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)

    //ARRAY
    val letters = Array<String>(26) { i -> ('a' + i).toString() }
    println(letters.joinToString(""))
    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))
}