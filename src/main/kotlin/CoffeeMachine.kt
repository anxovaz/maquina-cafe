import CoffeeMachineState.*

/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object CoffeeMachine {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle()
    public var wage: Double = 0.0

    fun makeCoffee() {
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
                if (this.wage < 0.60){ //Si el usuario ha metido menos de 60cents
                    println("Saldo insuficiente")
                    currentState = ChargingMachine("Inserta monedas")
                }else { //si ha metido más
                    this.wage -= 60
                    this.currentState = MakingCoffee()
                }

            }
            is MakingCoffee -> {
                println("¡Espera! La máquina ya está haciendo café.")
            }
            is ServingCoffee -> {
                println("Ya hay café servido. Por favor, toma tu café.")
            }
            is Error -> {
                println("La máquina tiene un error: ${(currentState as Error).message}")
            }

            is ReturnChange -> TODO()
            is CoffeeMachineState.ChargingMachine -> TODO()
            is CoffeeMachineState.Error -> TODO()
            is CoffeeMachineState.MakingCoffee -> TODO()
            is CoffeeMachineState.ReturnChange -> TODO()
            is CoffeeMachineState.ServingCoffee -> TODO()
        }
    }
    fun chargeMachine(money: Int) {
        this.wage += money
        this.makeCoffee()
    }
    fun clean() {
        println("Limpiando la máquina...")
        currentState = CoffeeMachineState.Idle()
        println("Máquina limpia. Estado: $currentState")
    }
}
