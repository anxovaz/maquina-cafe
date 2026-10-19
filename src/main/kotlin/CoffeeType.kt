enum class CoffeeType(val ingredients: Array<Boolean> = arrayOf(), val temperature:Int = 0, val perc: Array<Double> = arrayOf()) {
    //Ingredients {water, milk, coffee}, temp: INT, percent { % watter, % milk, % coffee}

    EXPRESO(arrayOf(true, false, true), 50, arrayOf(40.0, 00.0, 60.0)),
    CAPUCHINO(arrayOf(true, true, true), 50, arrayOf(15.00, 75.0, 10.0)),
    ONLY(arrayOf(true, false, true), 60, arrayOf(10.0, 0.0, 90.0)),
    UNDEFINED, //user didnt indicate a coffeeType
}