sealed class CoffeeMachineState {
    class Idle(val waitingMessage: String = "") : CoffeeMachineState() //no muestra mensaje si está en estado idle
    data class MakingCoffee(val message: String = "Making Coffee") : CoffeeMachineState()
    data class ServingCoffee(val type: String = "Serving Coffee") : CoffeeMachineState()
    data class Error(val message: String = "Error") : CoffeeMachineState()

    //El estado después de IDLE que le pide al usuario que introduzca monedas, cuando llega a 0.60 € pasa al siguiente
    data class ChargingMachine(val message: String = "Insert coins pls"): CoffeeMachineState()
    //Al final antes de volver al idle, le devuelve al usuario el cambio, ejemplo si mete 1€ devuelve 0.40€
    data class ReturnChange(val message: String = "Thank you, here is your change"): CoffeeMachineState()
}