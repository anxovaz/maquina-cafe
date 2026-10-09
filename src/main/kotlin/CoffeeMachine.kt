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
            is Idle -> {
                println("Cargando máquina...")
                wage = 0.60
            }
            is ChargingMachine -> {
                if (wage < 0.60){ //Si el usuario ha metido menos de 60cents
                    println("Saldo insuficiente")
                    currentState = ChargingMachine("Introduzca monedas")
                }else { //si ha metido más
                    val idleState = currentState as Idle
                    println("Máquina encendida, Empezando a hacer café...")
                    Thread.sleep(2000)
                    // Simula un proceso de preparación
                    currentState = ServingCoffee("Nescafé")
                    println("¡Café listo! Estado: $currentState")
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

    fun clean() {
        println("Limpiando la máquina...")
        currentState = CoffeeMachineState.Idle()
        println("Máquina limpia. Estado: $currentState")
    }
}
