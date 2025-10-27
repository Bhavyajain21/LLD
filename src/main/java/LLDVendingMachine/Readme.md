<img width="1629" height="1526" alt="image" src="https://github.com/user-attachments/assets/a925d0db-64a1-40c9-9498-a5ccf59e146b" />

# 🏪 Vending Machine - Low Level Design (LLD)

This project implements a **Vending Machine** using **Object-Oriented Design** principles and the **State Design Pattern** in Java.  
It models a real-world vending machine with states such as *Idle*, *Item Selected*, *Has Money*, and *Dispensing*.

---

## 🧩 Overview

The vending machine allows users to:
1. **Select an item**
2. **Insert coins**
3. **Dispense the selected product**
4. **Return change or refund money** when applicable

The design ensures clean state transitions and encapsulates behaviors logically within each state class.

---

## ⚙️ Design Highlights

### 🧱 Key Classes

| Class | Responsibility |
|--------|----------------|
| `Item` | Represents a product (name, price, and code). |
| `Inventory` | Manages item storage, availability, and stock count. |
| `VendingMachine` | Core class that maintains current state, balance, and inventory. |
| `VendingMachineState` | Abstract base class defining state behaviors. |
| `IdleState`, `ItemSelectedState`, `HasMoneyState`, `DispensingState` | Concrete states representing the machine's lifecycle. |

### 💰 Enums

- `Coin` — Represents coin denominations with values (PENNY = 1, NICKEL = 5, DIME = 10, QUARTER = 25).

---

## 🧠 State Transitions

```text
+-------------+         +------------------+         +---------------+
|   IdleState | ----->  | ItemSelectedState| ----->  |  HasMoneyState|
+-------------+         +------------------+         +---------------+
       ^                         |                         |
       |                         v                         v
       |                  [Refund / Reset]          [DispensingState]
       |                         |                         |
       +-------------------------+-------------------------+
