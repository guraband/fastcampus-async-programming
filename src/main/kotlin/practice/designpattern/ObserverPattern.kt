package practice.designpattern

import java.util.*

class Coffee(val name: String)
class Barista : Observable() {
    private lateinit var coffeeName: String

    fun orderCoffee(name: String) {
        this.coffeeName = name
    }

    fun makeCoffee() {
        setChanged()
        notifyObservers(Coffee(this.coffeeName))
    }
}

class Customer(val name: String) : Observer {
    override fun update(o: Observable?, arg: Any?) {
        val coffee = arg as Coffee
        println("${name}이 ${coffee.name}을 받았습니다.")
    }
}

fun main() {
    val barista = Barista()
    barista.orderCoffee("아이스 아메리카노")

    barista.addObserver(Customer("고객1"))
    barista.addObserver(Customer("고객2"))
    barista.addObserver(Customer("고객3"))

    barista.makeCoffee()
}
