import CoffeeMachine.currentState

sealed class CoffeeMachineState {
    data class Idle(val waitingMessage: String = "") : CoffeeMachineState() { //no muestra mensaje si está en estado idle
        fun onEnter(){
            println("[LOG] entrando al estado IDLE")
        }
    }
    data class MakingCoffee(val message: String = "Haciendo café", val coffeeType: CoffeeType = CoffeeType.UNDEFINED) : CoffeeMachineState(){
        fun onEnter(){
            println("¡Espera! La máquina ya está haciendo café.")

            if (coffeeType == CoffeeType.UNDEFINED) {
                CoffeeMachine.currentState = Error("Por favor especifica un café")
                //current state doesnt change
            }else{
                CoffeeMachine.water = coffeeType.ingredients[0]
                CoffeeMachine.milk = coffeeType.ingredients[1]
                CoffeeMachine.coffee = coffeeType.ingredients[2]

                CoffeeMachine.temp = coffeeType.temperature

                CoffeeMachine.waterPerc = coffeeType.perc[0]
                CoffeeMachine.milkPerc = coffeeType.perc[1]
                CoffeeMachine.coffeePerc = coffeeType.perc[2]

                //Prepares the coffee
                if(CoffeeMachine.prepareCoffee()){
                    //If no errors during preparation
                    CoffeeMachine.currentState = ServingCoffee();
                }else{
                    //Errors during preparation
                    CoffeeMachine.currentState = Error("Error durante la preparación del café, contacte con al siguiente número (+34) 000 00 00 00");
                }
            }
        }
    }

    data class ServingCoffee(val type: String = "Sirviendo café") : CoffeeMachineState(){
        fun onEnter(){
            //I decress wage by 0.60 (coffee price)
            CoffeeMachine.wage -= 0.60

            println("Aquí tiene su café")
            println("(  )   (   )  )\n" +
                    "      ) (   )  (  (\n" +
                    "      ( )  (    ) )\n" +
                    "     _________________\n" +
                    "    |                 |===|\n" +
                    "    |  Café Recién    |   |\n" +
                    "    |   Hecho         |===|\n" +
                    "    |                 |\n" +
                    "     \\_______________/\n" +
                    "      \\_____________ /\n" +
                    "    -------------------\n" +
                    "   (___________________)")

            CoffeeMachine.currentState = ReturnChange()
        }
    }
    data class Error(val message: String = "Error") : CoffeeMachineState(){
        fun onEnter(){
            println("[LOG] Error: ${message}")
        }
    }
    //El estado después de IDLE que le pide al usuario que introduzca monedas, cuando llega a 0.60 € pasa al siguiente
    data class ChargingMachine(val message: String = "Inserta monedas por favor"): CoffeeMachineState(){
        fun onEnter(){
            if (CoffeeMachine.wage < 0.60){ //Si el usuario ha metido menos de 60cents
                println("Saldo insuficiente")
                currentState = ChargingMachine("Inserta monedas por favor")
            }else { //si ha metido más
                CoffeeMachine.currentState = MakingCoffee()
            }
        }
    }
    //Al final antes de volver al idle, le devuelve al usuario el cambio, ejemplo si mete 1€ devuelve 0.40€
    data class ReturnChange(val message: String = "Gracias, aquí tiene su cambio"): CoffeeMachineState(){
        fun onEnter(){
            if(CoffeeMachine.wage != 0.0){
                println("Aquí tiene su cambio, cantidad: " + String.format("%.2f", CoffeeMachine.wage))
                println("        .-----------------.\n" +
                        "      .'                   '.\n" +
                        "     /       _______         \\\n" +
                        "    |       /       \\         |\n" +
                        "    |       |   \$   |         |\n" +
                        "    |       \\_______/         |\n" +
                        "     \\                       /\n" +
                        "      '.                   .'\n" +
                        "        '-----------------'")

            }
            CoffeeMachine.currentState = Idle()
        }

    }
}