fun main(){
    val  cm = CoffeeMachine
    cm.makeCoffee()
    cm.chargeMachine(0.70)
    cm.makeCoffee()
    cm.makeCoffee(coffeeType =  CoffeeType.EXPRESO)
    cm.makeCoffee()
    cm.makeCoffee()
}