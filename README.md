# ShootingBoxGame

A 2D UI-based desktop game built with Java, Swing, and AWT using OOP principles, featuring multithreading and save/load state persistence.

## 🎮 Gameplay & Mechanics

- **Dual-Side Shooting:** Clicking the Left Mouse Button fires two horizontal projectiles simultaneously—one from the left and one from the right side of the player.
- **Friendly NPCs (Green):** Allied units that cannot harm the player or each other through projectiles or collisions.
- **Enemy NPCs:** Hostile units capable of eliminating the player upon collision or projectile impact.
- **Save & Load System:** Start fresh with **New Game** or resume your saved progress (`savegame.txt`) via **Load Game** on the main menu.

## 🕹️ Controls

- **`W` `A` `S` `D`**: Move the player
- **`Left Click`**: Fire projectiles simultaneously to the left and right
- **`ESC`**: Pause the game and access **Save & Exit**

## 🛠️ Technologies Used

- **Language:** Java
- **GUI Frameworks:** Java Swing & AWT
- **Concepts:** OOP, Multithreading, Collision Detection, File I/O (State Persistence)

## 🚀 How to Run

1. Clone or download this repository.
2. Open the project in an IDE (such as Eclipse or IntelliJ IDEA) or compile via terminal:
   ```bash
   javac -d bin src/*.java
   java -cp bin Main
   ```
