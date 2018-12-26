package base

import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * @author hao_zhang3
 * @date 2018-12-23
 */
//文件操作
fun readFirstLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br -> return br.readLine() }
}

fun lookForAlice(people: List<Person>) {
    //because forEach is inline.
    people.forEach {
        if (it.name == "Alice") {
            println("Found")
            return
        }
    }
    println("Alice is not found")
}

fun lookForAlice2(people: List<Person>) {
    //because forEach is inline.
    people.forEach lable1@{
        if (it.name == "Alice") {
            println("Found")
            return@lable1
        }
    }
    println("Alice is not found")
}

fun lookForAlice3(people: List<Person>) {
    //because forEach is inline.
    people.forEach {
        if (it.name == "Alice") {
            println("Found")
            return@forEach
        }
    }
    println("Alice is not found")
}

fun lookForAlice4(people: List<Person>) {
    //return到了匿名函数的地方,foreach继续向下执行
    people.forEach(fun(person) {
        if (person.name == "Alice") return
        println("Alice is not found")
    })
}

fun test(code: () -> Unit) {
    code()
}

fun main(args: Array<String>) {

    val l: Lock = ReentrantLock()
    l.withLock { println("in lock") }

    lookForAlice(listOf<Person>(Person("Alice", 1), Person("test", 23)))
    lookForAlice2(listOf<Person>(Person("Alice", 1), Person("test", 23)))
    lookForAlice3(listOf<Person>(Person("Alice", 1), Person("test", 23)))
    lookForAlice4(listOf<Person>(Person("Alice", 1), Person("test", 23)))

    test {
        println("1234")
        //非内联,不能return
        //return
    }

}
