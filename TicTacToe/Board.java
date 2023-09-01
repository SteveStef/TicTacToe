
public class Board {

    private String[][] board;
    public static String X = "X";
    public static String O = "O";
    public static String EMT = "-";
    public static int size = 3;

    public Board() {
      board = new String[size][size]; // init board size
      fill();
    }

    public Board(String[][] copyBoard) {
      board = new String[size][size]; // init board size
      for(int i = 0; i < copyBoard.length; i++) {
         for(int j = 0; j < copyBoard[i].length; j++) {
            board[i][j] = copyBoard[i][j];
         }
      }
    }
    // filling the board with "-"
    public void fill() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                board[i][j] = EMT;
            }
        }
    }

    // just a prompt
    public static void prompt(String currentPlayer) {
	    System.out.print("It is " + currentPlayer + "'s turn\n" + 
        "Enter location to place " + currentPlayer +" (1-9): ");
    }

    public String[][] getBoard() {
      return board;
    }

    public String getResult() {
        if(isDraw()) return "is a draw";
        int val = evaluate();
        if(val == 1) return X + " wins";
        else return O + " wins";
    }
    // returns string at pos row col
    public String get(int row, int col) {
        return board[row][col];
    }
    // self explainitory
    public void set(int row, int col, String val) {
        board[row][col] = val;
    }

    // returns true if there are no moves left on the board
    public boolean isDraw() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j].equals(EMT)) return false;
			}
		}
		return true;
	}

    // returns 1 if X won, -1 for O, 0 if none
	public int evaluate() {

   		// Checking for Diagonals for X or O victory.
   	if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
        	if(board[0][0].equals(X)) return 1;
       	else if (board[0][0].equals(O))  return -1;
   	}
 
    	if(board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
       		if (board[0][2].equals(X))  return 1;
        	else if (board[0][2].equals(O))  return -1;
   		}

    	// Checking for Rows for X or O victory.
    	for (int row = 0; row < size; row++) {
    	    if (board[row][0].equals(board[row][1]) && board[row][1].equals(board[row][2]))  {
    	        if(board[row][0].equals(X)) return 1;
    	        else if(board[row][0].equals(O)) return -1;
    	    }
 	   }
 
   	// Checking for Columns for X or O victory.
    	for (int col = 0; col < size; col++)  {
       		 if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col]))  {
            	if (board[0][col].equals(X)) return 1;
            	else if (board[0][col].equals(O)) return -1;
        	}
    	}
 
    	return 0;
	}

	public void print() {
        System.out.println (
             "|---|---|---|\n"+
             "| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |\n"
                +"|-----------|\n"+"| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |\n"
                +"|-----------|\n"+"| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |\n"
                +"|---|---|---|"
        );
     }

     public static void howToPlay() {
        System.out.println("===================HOW TO PLAY===================");
        System.out.println (
            "|---|---|---|\n"+
            "| " + 1 + " | " + 2 + " | " + 3 + " |\n"
            +"|-----------|\n"+"| " + 4 + " | " + 5 + " | " + 6 + " |\n"
            +"|-----------|\n"+"| " + 7 + " | " +8+ " | " + 9 + " |\n"
            +"|---|---|---|"
       );
       System.out.println("Enter a number from 1-9 to place an X");
       System.out.println("=====================================");
       System.out.println();
    }
}
