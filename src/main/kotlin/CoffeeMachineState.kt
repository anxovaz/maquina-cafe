sealed class CoffeeMachineState {
    class Idle(val waitingMessage: String = "") : CoffeeMachineState() //no muestra mensaje si está en estado idle
    data class MakingCoffee(val message: String = "Making Coffee") : CoffeeMachineState()
    data class ServingCoffee(val type: String = "Serving Coffee") : CoffeeMachineState()
    data class Error(val message: String = "Error") : CoffeeMachineState()

    data class ChargingMachine(val message: String = "Insert coins pls"): CoffeeMachineState()
    data class ReturnChange(val message: String = "Thank you, here is your change"): CoffeeMachineState()
}