@file: JvmName("StringFunctions")

package base

import java.lang.IllegalArgumentException
import java.lang.StringBuilder

/**
 * @author hao_zhang3
 * @date 2018-12-15
 */

var opCount = 0

fun performOperation() {
    opCount++
}

fun reportOperationCount() {
    println("Operation preformed $opCount times")
}

fun String.lastChar(): Char = this[this.length - 1]

fun String.isNotEmpty2(): Boolean = !isEmpty()

var StringBuilder.secondChar: Char
    get() = get(1)
    set(c: Char) {
        this.setCharAt(1, c)
    }

/**
 * 生成N多的重载函数
 */
@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ",",
    prefix: String = "",
    postFix: String = ""
): String {
    var result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postFix)
    return result.toString()
}

fun main(args: Array<String>) {
    println(joinToString(hashSetOf(1, 6, 7), separator = ",", prefix = "[", postFix = "]"))
    println(joinToString(arrayListOf(1, 4, 8, 9)))
    println(joinToString(hashMapOf(1 to "one", 2 to "two", 3 to "three").values, prefix = "#", postFix = ""))

    reportOperationCount()
    performOperation()
    performOperation()
    reportOperationCount()

    println("Kotlin".lastChar())
    println("".isNotEmpty2())

    var sb = StringBuilder("Kotlin")
    print(sb.secondChar)
    sb.secondChar = 'C'
    println(sb.secondChar)


    println(mapOf("one" to 1, "two" to 2))

    saveUser(User(1, "", ""))
}

class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSaveUser() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser(user: User) {
    user.validateBeforeSaveUser()
}