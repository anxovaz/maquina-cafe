fun main(){
    val  cm = CoffeeMachine
    cm.makeCoffee()
    println("---")
    cm.chargeMachine(0.70)
    println("---")
    cm.makeCoffee()
    println("---")
    cm.makeCoffee(coffeeType =  CoffeeType.EXPRESO)
    println("---")
    cm.makeCoffee()
    println("---")
    cm.makeCoffee()
}