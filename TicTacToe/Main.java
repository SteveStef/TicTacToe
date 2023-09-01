import java.util.Scanner;
import java.util.concurrent.TimeUnit; // allows me to use Time Delays

class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Would you like to play agaist human(1) or AI(2) (1 or 2): ");
		int choice = in.nextInt();
		boolean run = true;
		Board.howToPlay(); // Displays how the game works
		while(run) {
			play(in,choice);
			System.out.print("Do you want to play again (Y/N): ");
			String res = in.next();
			if(!res.equalsIgnoreCase("Y")) run = false;
		}
		in.close();
	}

	public static void play(Scanner in, int choice) {
		Board board = new Board(); // creates a board object
		board.print(); // displays the board
		int pos;
		boolean gameover = false, playerTurn = true;
		while(!gameover) {
			String currPlayer = getPlayerSymbol(playerTurn); // gets eiterh X or O depending on turn
			Board.prompt(currPlayer);
			pos = in.nextInt(); // getting user input
			Move cords = validCoordinate(pos,board,in); // validating cords
			board.set(cords.row,cords.col,currPlayer); // placing move on board
			board.print(); // displaying the board
			if(choice == 2) makeAiMove(board); // if AI was chosen, it will make the AI move
			else playerTurn = !playerTurn; // if player was chosen we need to change the turn
			gameover = Math.abs(board.evaluate()) > 0 || board.isDraw(); // is true if board is at a terminal state
		}
		System.out.println("Game is over, the result is " + board.getResult()); // printing results from game
	}
	
	public static void makeAiMove(Board board) {
		Move bestMove = AI.bestMove(board); // gets the best possible move to make given board pos
		if(bestMove.row >= 0 && bestMove.col >= 0) { // if there is a move to make
			System.out.println("AI is making a move...");
			try {
				TimeUnit.SECONDS.sleep(1); // Setting a time delay for the AI
			} catch(Exception e) {
				System.out.println(e);
			}
			board.set(bestMove.row,bestMove.col,Board.O); // make the move
			board.print(); // display the board
		}
	}
	// returns X if true O is false
	public static String getPlayerSymbol(boolean playerTurn) {
		return playerTurn ? Board.X : Board.O;
	}
	// returns Move that is valid
	public static Move validCoordinate(int pos, Board board, Scanner in) {
		Move cords = translateCords(pos); // translates the coords from (1-9) -> (row,col)
		while(pos < 1 || pos > 9) { // makes pos in bounds
			System.out.print("Position out of bounds try again: ");
			pos = in.nextInt();
		}
		while(!board.get(cords.row,cords.col).equals(Board.EMT)) { // makes sure you place in a non-filled location
			System.out.print("That position is already filled try again: ");
			pos = in.nextInt();
			while(pos < 1 || pos > 9) {
				System.out.print("Position out of bounds try again: ");
				pos = in.nextInt();
			}
			cords = translateCords(pos);
		}
		return cords;
	}
	// this function takes a pos 1-9 and returns its Move equivilant
	public static Move translateCords(int pos) {
		int row, col;
		if(pos <= 3) {
			row = 0;
			col = pos-1;
		} else if(pos <= 6) {
			row = 1;
			col = pos - 3 - 1;
		} else {
			row = 2;
			col = pos - 6 - 1;
		}
		return new Move(row,col);
	}
}