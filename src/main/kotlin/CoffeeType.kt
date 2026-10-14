enum class CoffeeType(val ingredients: Array<String>, val preparation: Array<String>) {
    EXPRESO(arrayOf("Café","agua"), arrayOf("1. Calentar agua", "2. Preparar café", "3. Listo")),
    CAPUCHINO(arrayOf("Café","agua", "leche"), arrayOf("1. Calentar agua y leche", "2. Preparar café", "3. Mezclar con Leche","4. Listo")),
    SOLO(arrayOf("Café","agua"), arrayOf("1. Calentar agua", "2. Preparar café", "3. Listo")),
}