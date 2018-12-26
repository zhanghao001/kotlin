package base

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author hao_zhang3
 * @date 2018-12-23
 */
data class Email(val name: String)

fun loadEmails(person: Person5): List<Email> {
    println("Load emails for ${person.name}")
    return listOf(Email(person.name))
}

data class Person5(val name: String) {
    val email by lazy { loadEmails(this) }
}

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {
    operator fun getValue(p: Person6, prop: KProperty<*>): Int = propValue

    operator fun setValue(p: Person6, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class Person6(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}

class Person7(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

class Person8 {
    //map实现了getValue和setValue
    private val _attributes = hashMapOf<String, String>()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    var name: String by _attributes
}

fun main(args: Array<String>) {
    val p5 = Person5("test")
    println(p5)
    //lazy load
    println(p5.email)

    val p6 = Person6("test", 12, 2000)
    p6.addPropertyChangeListener(PropertyChangeListener { event -> println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}") })
    p6.age = 13
    p6.salary = 3000

    val p7 = Person7("test", 18, 6000)
    p7.addPropertyChangeListener(PropertyChangeListener { event -> println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}") })
    p7.age = 13
    p7.salary = 3000

    val p8 = Person8()
    p8.name = "test8"
    println(p8.name)
}

