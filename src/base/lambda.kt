package base

/**
 * @author hao_zhang3
 * @date 2018-12-15
 */

data class Person(val name: String, val age: Int) {
    override fun equals(other: Any?): Boolean {
        // 安全的类型
        val otherPerson = other as? Person ?: return false
        return otherPerson.name == name && otherPerson.age == age
    }
}

fun print2() {
    println(2)
}

fun printProblemCounts(response: Collection<String>) {
    var clientErrorCount = 0
    var serverErrorCount = 0

    //lambda可以更新非final的值.使用包装器实现的
    response.forEach {
        if (it.startsWith("4")) clientErrorCount++
        else if (it.startsWith("5")) serverErrorCount++
    }

    println("$clientErrorCount client errors, $serverErrorCount server errors.")
}

class Outer {
    val name = "outer calss"

    fun alphabet() = with(StringBuffer()) {
        for (letter in 'A'..'Z') append(letter)
        append("\n Now I know the alphabet").append(this@Outer.toString())
        toString()
    }
}


fun main(args: Array<String>) {
    val person1 = listOf(Person("Alice", 20), Person("Bob", 29))
    println(person1.maxBy { it.age })
    println(person1.minBy(Person::age))

    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x + $y")
        x + y
    }
    println(sum(1, 2))
    run { println(123) }


    val names = person1.joinToString(",") { it.name }
    println(names)

    val responses = listOf("200 OK", "404 not found", "500 Internal Server Error")
    printProblemCounts(responses)
    run(::print2)

    val createPerson = ::Person
    val p = createPerson("Alice", 29)
    println(p)
    val personAgeFunction = Person::age
    println(personAgeFunction(p))

    val dmitrysAgeFunction = p::age
    println(dmitrysAgeFunction())

    val allResponses = listOf(responses, responses)
    allResponses.flatten()

    println(Outer().alphabet())
}

