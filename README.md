# Maquina de estados

Máquina de estados de una máquina de café. El usuario introduce monedas,
selecciona un café y la máquina avanza por los estados de preparación y entrega.

```mermaid
flowchart TD
    Idle["estado = Idle"] --> CargingMachine["estado = CargingMachine"]
    CargingMachine --> CoinsCheck
    CoinsCheck{"coins.wage() >= 0.60"}
    CoinsCheck -->|Sí| MakeCofee["estado = MakeCofee"]
    CoinsCheck -->|No| CargingMachine
    MakeCofee -->|Cofee preparado| ServeCofee["estado = ServeCofee"]
    MakeCofee -->|Error durante la preparación| Error["estado = error"]
    ServeCofee -->|Cofee servido| ReturnChange["estado = ReturnChange"]
    Error -->|Salir del error| ReturnChange
    ReturnChange -->|"Coins.returncoins(change)"| Idle
```

## Diagrama UML de clases

### Máquina y estados

```mermaid
classDiagram
    direction LR

    class CofeeMachine {
        -state: CofeeMachineState
        -coins: Coins
        +insertCoins(amount: Double) void
        +selectCofee(cofee: Cofee) void
        +makeCofee() void
        +serveCofee() void
        +returnChange() void
        +setState(state: CofeeMachineState) void
    }

    class CofeeMachineState {
        <<interface>>
        +insertCoins(machine: CofeeMachine, amount: Double) void
        +selectCofee(machine: CofeeMachine, cofee: Cofee) void
        +makeCofee(machine: CofeeMachine) void
        +serveCofee(machine: CofeeMachine) void
        +returnChange(machine: CofeeMachine) void
    }

    class Idle {
        +insertCoins(machine: CofeeMachine, amount: Double) void
    }
    class ChargingMachine {
        +insertCoins(machine: CofeeMachine, amount: Double) void
    }
    class MakeCofee {
        +makeCofee(machine: CofeeMachine) void
    }
    class ServeCofee {
        +serveCofee(machine: CofeeMachine) void
    }
    class ReturnChange {
        +returnChange(machine: CofeeMachine) void
    }
    class Error {
        +returnChange(machine: CofeeMachine) void
    }

    class Coins {
        -balance: Double
        +insert(amount: Double) void
        +wage() Double
        +returncoins(change: Double) void
    }

    class Cofee {
        <<abstract>>
    }

    CofeeMachine --> CofeeMachineState : current state
    CofeeMachine *-- Coins : owns
    CofeeMachine o-- Cofee : selected cofee
    CofeeMachineState <|.. Idle
    CofeeMachineState <|.. ChargingMachine
    CofeeMachineState <|.. MakeCofee
    CofeeMachineState <|.. ServeCofee
    CofeeMachineState <|.. ReturnChange
    CofeeMachineState <|.. Error
```

### Tipos de café

```mermaid
classDiagram
    direction LR

    class CofeeMachine {
        -selectedCofee: Cofee
        +selectCofee(cofee: Cofee) void
    }

    class Cofee {
        <<abstract>>
        -price: Double
        +getPrice() Double
        +prepare() void*
    }
    class Capuchino {
        -ingredients: String[]
        +prepare() void
    }
    class Expresso {
        -ingredients: String[]
        +prepare() void
    }
    class Solo {
        -ingredients: String[]
        +prepare() void
    }

    CofeeMachine o-- Cofee : selected cofee
    Cofee <|-- Capuchino
    Cofee <|-- Expresso
    Cofee <|-- Solo
```
