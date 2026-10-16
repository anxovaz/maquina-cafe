enum class CoffeeType(val ingredients: Array<Boolean> = arrayOf(), val temperature:Int = 0) {
    //Ingredients {water, milk, coffee}, temp: INT

    EXPRESO(arrayOf(true, false, true), 50),
    CAPUCHINO(arrayOf(true, true, true), 50),
    ONLY(arrayOf(true, false, true), 60),
    UNDEFINED, //user didnt indicate a coffeeType
}