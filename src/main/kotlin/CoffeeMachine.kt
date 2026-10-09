/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object CoffeeMachine {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle()

    fun makeCoffee() {
        println("Estado actual: $currentState")

        when (currentState) {
            is CoffeeMachineState.Idle -> {

                val idleState = currentState as CoffeeMachineState.Idle
                println("Máquina encendida desde: ${idleState.timestamp}. Empezando a hacer café...")
                Thread.sleep(2000)
                // Simula un proceso de preparación
                currentState = CoffeeMachineState.ServingCoffee("Nescafé")
                println("¡Café listo! Estado: $currentState")
            }
            is CoffeeMachineState.MakingCoffee -> {
                println("¡Espera! La máquina ya está haciendo café.")
            }
            is CoffeeMachineState.ServingCoffee -> {
                println("Ya hay café servido. Por favor, toma tu café.")
            }
            is CoffeeMachineState.Error -> {
                println("La máquina tiene un error: ${(currentState as CoffeeMachineState.Error).message}")
            }
        }
    }

    fun clean() {
        println("Limpiando la máquina...")
        currentState = CoffeeMachineState.Idle()
        println("Máquina limpia. Estado: $currentState")
    }
}
