sealed class CoffeeMachineState {
    class Idle(val timestamp: Long = System.currentTimeMillis()) : CoffeeMachineState(){
        init{
            println("Estoy en la función inicial del estado IDLE")
        }
    }
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val type: String) : CoffeeMachineState()
    data class Error(val message: String) : CoffeeMachineState()
}