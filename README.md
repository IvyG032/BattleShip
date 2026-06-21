# Battleship Game Takeaways

This is a summary of what I learned while building a Battleship game from scratch. These are the practical lessons I figured out through trial and error.

### 1. Understanding "Decoupling" in Practice
I became more familiar with the "decouple" nature of code. I started to understand which functions should be wrapped in a method and which can stay as they are:
*   For simple tasks, like checking if a ship exists (`if location[row][col] == '@'`), I realized there’s no need for a separate method. It’s just one line, it only appears three times, and it's already very intuitive. 
*   However, checking "out of bounds" involves a long range of conditions. Making this a separate method prevents me from repeating long lines of code and reduces mistakes that happen during copy-pasting.

### 2. Consolidating Input and Validation
Since the program has to check the range every time a user inputs a coordinate, I thought: why not put the input and the range check together? 
*   Through this, I learned about the nature of the `Scanner` object. If I initialized a Scanner in every method, the code would be messy and hard to maintain. It’s also easy to accidentally close the Scanner and cause errors. 
*   Keeping it in one place also makes it easier to control settings like bold text. Now, I can just call `getValidLocation` and use two `int` variables to "catch" the coordinates.

### 3. Using a "Switch" to Hide/Show Ships
I added a feature to the `printBattleShip` method to choose whether to show enemy ships. 
*   This way, at the beginning (placing ships) and at the end (winner announcement), the ships are correctly displayed as `@`. But during the attack phase, the "display switch" is turned off, so players only see `X` for hits and `O` for misses. 
*   This method avoided having to write a whole new method for a "hidden map." I used one `boolean` switch to toggle between two views of the same board. Also, I don't need to compare two different maps to check for a winner; I just count the `X` marks on the actual board.

### 4. Dynamic Attacker/Defender Labels
I used `attackerNum` and `defenderNum` in the `attackShip` method to correctly print who is attacking and who is defending. 
*   By adding `attackNum` to the parameters and passing the player number, I used the line `int defenderNum = (attackerNum == 1) ? 2 : 1;` to switch between them. This allows the program to correctly display "Player 1" and "Player 2" during the turns.

### 5. Cleaning up the Main Method
After organizing all the functional code blocks into methods, the `main` method can focus purely on the game logic. It just calls each function in order, which makes the overall program simple, clear, and easy to maintain.