import java.util.Scanner;

public class Battleship {
	// Add controller for bold text
	private static final String BOLD = "\033[1m";
	private static final String RESET = "\033[0m";

	public static void main(String[] args) {
		
		// Initialize 2D array for both players
		char[][] player1 = new char[5][5]; 
		char[][] player2 = new char[5][5]; 

		// Call scanner in main
		Scanner mainScanner = new Scanner(System.in);

		// Starts game and call functions
		System.out.println("Welcome to Battleship!");
		System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
		recordBattleShip(player1, mainScanner);
		printBattleShip(player1, true);
		System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
		recordBattleShip(player2, mainScanner);
		printBattleShip(player2, true);

		while (true) {
			// Player1 attack
			System.out.println("Player 1, enter hit row/column:");
			attackShip(player2, mainScanner, 1);
			printBattleShip(player2, false);
			// Check if player1 wins
			if (findWinner(player2)) {
				System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF PLAYER 2'S SHIPS!");
				System.out.println("Final boards:");
				printBattleShip(player1, true); // Game over, show board
				System.out.println("\n");
				printBattleShip(player2, true);
				break;
			}
			// Player2 attack
			System.out.println("Player 2, enter hit row/column:");
			attackShip(player1, mainScanner, 2);
			printBattleShip(player1, false);
			// Check if player2 wins
			if (findWinner(player1)) {
				System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF PLAYER 1'S SHIPS!");
				System.out.println("Final boards:");
				printBattleShip(player1, true);
				System.out.println("\n");
				printBattleShip(player2, true);
				break;
			}		
		}
		mainScanner.close(); // Close scanner in main

	}

// Use this method to take and save player steps
	private static void recordBattleShip(char[][] player, Scanner input) {
		// Place empty slots
		for(int r = 0; r < 5; r++) {
        	for(int c = 0; c < 5; c++) player[r][c] = '-';
        }
		// Ask player to enter coordinate for five times
		for (int i = 0; i < 5; i ++) {	
			boolean isValid = false; // The switch to verify location
			while (!isValid) {
				System.out.println("Enter ship " + (i+1) + " location: ");  
				int[] locations = getValidLocation(input); // Call method to verify & get user input
				int row = locations[0];
				int col = locations[1];

				if (player[row][col] == '@') { // Check if coordinates already existed
					System.out.println("You already have a ship there. Choose different coordinates.");
				}
				else {
				player[row][col] = '@'; // Place ship at valid location
				isValid = true; // Switch to true to end loop
				}
			}
		}	
	}

// Use this method to print game boards to the console.
// Modified to add a switch that controls how the game board is printed
	private static void printBattleShip(char[][] player, boolean showShip) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					char currentLocation = player[row][column];
					// If switch is off, don't show ship
					if (currentLocation == '@' && !showShip) {
						System.out.print("- ");
					}
					else{
						System.out.print(currentLocation + " ");
					}
				}
			}
			System.out.println("");
		}
	}

// Use this method to get user input & check if location is out of bounds
	private static int[] getValidLocation(Scanner input) {
		while (true) {
			// Control bold feature only at one place 
			System.out.print(BOLD); // Turn on bold
			int row = input.nextInt();
			int col = input.nextInt();
			System.out.print(RESET); // Turn off bold

			if (row < 0 || row > 4 || col < 0 || col > 4) { // Handle outofbounds error
			System.out.println("Invalid coordinates. Choose different coordinates.");
			}
			else {
				return new int[] {row, col}; // Return valid location as int array
			}
		}
	}

// Use this method to check hit & miss
	private static void attackShip(char[][] player, Scanner input, int attackerNum) { // Add attacker & defender number to print correctly
		boolean isValid = false;
		int defenderNum = (attackerNum == 1) ? 2 : 1; // Loop through players to record who is attacking

		while (!isValid) { // Keep asking till user enters valid coordinate
		int[] locations = getValidLocation(input);
		int row = locations[0];
		int col = locations[1];
			if (player[row][col] == '@') {
				player[row][col] = 'X';
				System.out.println("PLAYER " + attackerNum + " HIT PLAYER " + defenderNum + "'s SHIP!"); 
				isValid = true;
			}
			else if ((player[row][col] == 'X' || player[row][col] == 'O')) {
				System.out.println("You already fired on this spot. Choose different coordinates.");
			}
			else{
				System.out.println("PLAYER " + attackerNum + " MISSED!");
				player[row][col] = 'O';
				isValid = true;
			}
		}
	}


// Use this method to detect winner
// Whoever gets 5 'X' first is the winner
	private static boolean findWinner(char[][] player) {
		int count = 0;
		for (int row = 0; row < 5; row++) {
			for (int col = 0; col < 5; col++) {
				if (player[row][col] == 'X') {
					count++;
				}
			}
		}
		return count == 5;
	}
}
