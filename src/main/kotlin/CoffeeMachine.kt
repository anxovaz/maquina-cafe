import CoffeeMachineState.*

/**
 * Singleton que maneja lo que ocurre en cada estado
 */
object CoffeeMachine {
    public var currentState: CoffeeMachineState = CoffeeMachineState.Idle()
    public var wage: Double = 0.0

    //Machine Valves
    private var water: Boolean = false
    private var milk: Boolean = false
    private var coffe: Boolean = false

    //Machine resources perc
    private var waterPerc: Double = 0.0
    private var milkPerc: Double = 0.0
    private var coffePerc: Double = 0.0

    //coffe temperature
    private var temp: Int = 10

    fun makeCoffee(wage: Int = 0, coffeeType: CoffeeType = CoffeeType.UNDEFINED) {
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
                    this.currentState = MakingCoffee()
                }
            }
            is MakingCoffee -> {
                println("¡Espera! La máquina ya está haciendo café.")

                if (coffeeType == CoffeeType.UNDEFINED) {
                    println("Por favor especifica un café")
                    //current state doesnt change
                }else{
                    this.water = coffeeType.ingredients[0]
                    this.milk = coffeeType.ingredients[1]
                    this.coffe = coffeeType.ingredients[2]

                    this.temp = coffeeType.temperature

                    this.waterPerc = coffeeType.perc[0]
                    this.milkPerc = coffeeType.perc[1]
                    this.coffePerc = coffeeType.perc[2]

                    //Prepares the coffee
                    if(this.prepareCoffee()){
                        //If no errors during preparation
                        this.currentState = ServingCoffee();
                    }else{
                        //Errors during preparation
                        this.currentState = Error("Error durante la preparación del café, contacte con al siguiente número (+34) 000 00 00 00");
                    }
                }
            }
            is Error -> {
                println("La máquina tiene un error: ${(currentState as Error).message}")
                this.currentState = ReturnChange() //it gives back the customer his money
            }
            is ServingCoffee -> {
                //I decress wage by 0.60 (coffee price)
                this.wage -= 0.60

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

                this.currentState = ReturnChange()
            }
            is ReturnChange -> {
                if(this.wage != 0.0){
                    println("Aquí tiene su cambio, cantidad: " + String.format("%.2f", this.wage))
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
                this.currentState = Idle()
            }
        }
    }
    fun chargeMachine(money: Double) {
        this.wage += money
        this.makeCoffee()
    }
    fun changCoffeTemp(): Boolean {
        //This function activates the coffee machine's heater if the temperature recorded by the sensor is different from this.temp.
        return true
    }
    fun prepareCoffee(): Boolean{
        //Function that uses the water, milk, coffe and tem variables to acctionate the valves and sensors inside the coffee machine
        return true
    }
    fun clean() {
        println("Limpiando la máquina...")
        currentState = CoffeeMachineState.Idle()
        println("Máquina limpia. Estado: $currentState")
    }
}
