sealed class CoffeeMachineState {
    class Idle(val waitingMessage: String = "") : CoffeeMachineState() { //no muestra mensaje si está en estado idle
        fun enter(){
            println("[LOG] entrando al estado IDLE")
        }
    }
    data class MakingCoffee(val message: String = "Haciendo café") : CoffeeMachineState(){
        fun enter(){
            println("[LOG] entrando al estado MakingCoffee")
        }
    }

    data class ServingCoffee(val type: String = "Sirviendo café") : CoffeeMachineState(){
        fun enter(){
            println("[LOG] entrando al estado ServingCoffee")
        }
    }
    data class Error(val message: String = "Error") : CoffeeMachineState(){
        fun enter(){
            println("[LOG] Error: ${message}")
        }
    }
    //El estado después de IDLE que le pide al usuario que introduzca monedas, cuando llega a 0.60 € pasa al siguiente
    data class ChargingMachine(val message: String = "Inserta monedas por favor"): CoffeeMachineState(){
        fun enter(){
            println("[LOG] entrando al estado ChargingMachine")
        }
    }
    //Al final antes de volver al idle, le devuelve al usuario el cambio, ejemplo si mete 1€ devuelve 0.40€
    data class ReturnChange(val message: String = "Gracias, aquí tiene su cambio"): CoffeeMachineState(){
        fun enter(){
            println("[LOG] entrando al estado ReturnChange")
        }
    }
}