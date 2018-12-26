package base

import java.lang.Appendable
import java.lang.StringBuilder
import java.util.*
import javax.xml.ws.Service

/**
 * @author hao_zhang3
 * @date 2018-12-23
 */
//指定了泛型的上界
fun <T : Comparable<T>> max(first: T, second: T): T {
    return if (first > second) first else second
}

//T必须实现CharSequence和Appendable
fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith(".")) {
        seq.append(".")
    }
}

//无inline和reified,编译报错
inline fun <reified T> isA(value: Any) = value is T

inline fun <reified T> loadService(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java)
}

open class Animal {
    fun feed() {
        println("feed  ")
    }
}

class Herd<out T : Animal> {
    val size: Int get() = size
    operator fun get(int: Int): T {
        return listOf<T>()[int]
    }
}

interface Comparator<in T> {
    fun compare(e1: T, e2: T): Int {
        return 0
    }
}

//out T对应java的? extends T, in T对应java的? super T
fun <T : R, R> copyData(source: MutableList<T>, destination: MutableList<R>) {
    for (item in source)
        destination.add(item)
}
fun <T> copyData2(source: MutableList<out T>, destination: MutableList<T>) {
    for (item in source)
        destination.add(item)
}
fun <T> copyData3(source: MutableList<T>, destination: MutableList<in T>) {
    for (item in source)
        destination.add(item)
}


fun main(args: Array<String>) {
//    println(max("Kotlin", 43))
    val helloWorld = StringBuilder("Helloword")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld)

    println(isA<String>("test"))
    println(isA<Int>("test"))

    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())

    //获取java class对应的kotlin类
    val serviceImpl = ServiceLoader.load(Service::class.java)
    println(serviceImpl)
    val serviceImpl2 = loadService<Service>()
    println(serviceImpl2)
}