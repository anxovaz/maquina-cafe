package org.example

import CoffeeMachine
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class MainTest{
    @BeforeTest
    fun resetMachine(){
        CoffeeMachine.wage = 0.0
        CoffeeMachine.currentState = CoffeeMachineState.Idle()
    }


    @Test
    fun test1(){
        CoffeeMachine.wage = 0.40
        CoffeeMachine.makeCoffee()
        assertEquals(
            CoffeeMachineState.ChargingMachine(),
            CoffeeMachine.makeCoffee()
        )

    }

    @Test
    fun test2(){
        CoffeeMachine.wage = 0.70
        CoffeeMachine.makeCoffee()

        assertEquals(
            CoffeeMachineState.MakingCoffee(),
            CoffeeMachine.makeCoffee()
        )
        println("---")
        assertEquals(
            CoffeeMachineState.ServingCoffee(),
            CoffeeMachine.makeCoffee(CoffeeType.EXPRESO)
        )

        println("---")
        assertEquals(
            CoffeeMachineState.ReturnChange(),
            CoffeeMachine.makeCoffee()
        )
    }

    @Test
    fun test3() {
        CoffeeMachine.chargeMachine(0.60)
        CoffeeMachine.makeCoffee()
        assertEquals(
            CoffeeMachineState.Error("Por favor especifica un café"),
            CoffeeMachine.makeCoffee()
        )

        println("---")

        assertEquals(
            CoffeeMachineState.ReturnChange(),
            CoffeeMachine.makeCoffee()
        )
    }

    @AfterTest
    fun resetMachineAfterTest() {
        CoffeeMachine.clean()
    }
}