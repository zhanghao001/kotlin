package base

import java.io.File
import java.util.*

/**
 * @author hao_zhang3
 * @date 2018-12-15
 */
private interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable")
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable")
}

open class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    open fun test() = println("test")
}

open class Buttons(val name: String, val id: Int) : Button() {
    val buttons = arrayListOf<Button>()

    open fun addButton(e: Button) {
        println(e)
        buttons.add(e)
    }
}

data class AppUser(val name: String, val address: String)

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectAdded = 0

    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

fun getFacebookName(id: Int) = id.toString()

class UserB private constructor(val nickName: String) {
    companion object {
        fun newSubscribingUser(email: String) =
                UserB(email.substringBefore("@"))

        fun newFacebookUser(accountId: Int) = UserB(getFacebookName(accountId))
    }
}

interface JSONFactory<T> {
    fun formJSON(jsonText: String): T
}

class PersonA(val name: String) {
    companion object : JSONFactory<PersonA> {
        override fun formJSON(jsonText: String): PersonA = PersonA("testJSON")
    }
}

fun <T> loadFromJSON(factory: JSONFactory<T>): T = factory.formJSON("")

fun PersonA.Companion.formJSON2(): PersonA = PersonA("testJSON2")

fun countClicks(buttons: Buttons) {
    var clickCount = "1"

    buttons.addButton(object : Button() {
        override fun test() {
            clickCount = "2"
            super.test()
        }
    })

    println(clickCount)
    buttons.buttons[0].test()
    println(clickCount)

}

fun main(args: Array<String>) {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()

    println(Buttons("test", 1))
    println(AppUser("name", "addr") == AppUser("name", "addr"))
    println(AppUser("name", "addr"))
    println(AppUser("name", "addr").copy(name = "name2"))

    //委派对象 代理模式
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 2, 2))
    println("${cset.objectAdded} is added")

    //object 对象声明,单例
    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))

    //伴生对象
    val subscribingUser = UserB.newSubscribingUser("bob@gmail.com")
    val facebookUser = UserB.newFacebookUser(1)
    println(subscribingUser.nickName)
    println(facebookUser.nickName)

    println(loadFromJSON(PersonA).name)
    println(PersonA.formJSON2().name)

    //对象表达式, 匿名内部类
    val buttons = Buttons("s", 1)
    countClicks(buttons)
}

