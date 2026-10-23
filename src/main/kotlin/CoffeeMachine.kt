import CoffeeMachineState.*

/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object CoffeeMachine {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle()
    public var wage: Double = 0.0

    //Machine Valves
    public var water: Boolean = false
    public var milk: Boolean = false
    public var coffee: Boolean = false

    //Machine resources perc
    public var waterPerc: Double = 0.0
    public var milkPerc: Double = 0.0
    public var coffeePerc: Double = 0.0

    //coffe temperature
    public var temp: Int = 10

    fun makeCoffee(coffeeType: CoffeeType = CoffeeType.UNDEFINED): CoffeeMachineState {
        println("Estado actual: $currentState")

        when (currentState) {
            is Idle if this.wage > 0 -> {
                println("[LOG] Moneda detectada, saliendo del estado ${this.currentState}")
                this.currentState = ChargingMachine("Mete monedas");
            }

            is Idle -> {
                println("Estoy en espera, Introduce monedas para avanzar")

            }

            is ChargingMachine -> {
                ChargingMachine().onEnter()

            }

            is MakingCoffee -> {
                MakingCoffee(coffeeType = coffeeType).onEnter()
            }

            is Error -> {
                println("La máquina tiene un error: ${(currentState as Error).message}")
                this.currentState = ReturnChange() //it gives back the customer his money
            }

            is ServingCoffee -> {
                ServingCoffee().onEnter()
            }

            is ReturnChange -> {
                CoffeeMachineState.ReturnChange().onEnter()
            }
        }
        return this.currentState
    }
    fun chargeMachine(money: Double): Double {
        this.wage += money
        this.makeCoffee()
        return this.wage
    }
    fun prepareCoffee(): Boolean {
        //Function that uses the water, milk, coffe and tem variables to acctionate the valves and sensors inside the coffee machine
        return true
    }
    fun clean(): Boolean {
        println("Limpiando la máquina...")
        currentState = CoffeeMachineState.Idle()
        println("Máquina limpia. Estado: $currentState")
        return true
    }
}
