
public class AI {
	public static Move bestMove(Board board) {
		Move bestMove = new Move(-1,-1); // create temp move
		int bestScore = Integer.MAX_VALUE; // keeps track of the best score
		
		for(int i = 0; i < Board.size; i++) {
			for(int j = 0; j < Board.size; j++) {
				if(board.get(i,j).equals(Board.EMT)) { // for every move that is open
					board.set(i,j,Board.O); // make the move
					int score = search(board,true); // search for the best move
					board.set(i,j,Board.EMT); // reset the move just made
					if(score < bestScore) { // we finding the min val to make O win
						bestScore = score;
						bestMove.set(i,j);
					}
				}
			}
		}
		return bestMove;
	}
	
	public static int search(Board board, boolean playersMove) {
		int eval = board.evaluate();
		if(eval != 0) return eval; // if eval is not 0, tht means someone won
		if(board.isDraw()) return 0; // if board is drawn the board state is neutral
		
		if(playersMove) { // if it's x's move
			int bestScore = Integer.MIN_VALUE;
			for(int i = 0; i < Board.size; i++) {
				for(int j = 0; j < Board.size; j++) {
					if(board.get(i,j).equals(Board.EMT)) { // for every possible open move...
						board.set(i,j,Board.X); // make the move
						bestScore = Math.max(bestScore,search(board,!playersMove)); // recurse in search
						board.set(i,j,Board.EMT); // reset the move
					}
				}
			}
			return bestScore;
		} else {
			int bestScore = Integer.MAX_VALUE;
			for(int i = 0; i < Board.size; i++) {
				for(int j = 0; j < Board.size; j++) {
					if(board.get(i,j).equals(Board.EMT)) {
						board.set(i,j,Board.O);
						bestScore = Math.min(bestScore,search(board,!playersMove));
						board.set(i,j,Board.EMT);
					}
				}
			}
			return bestScore;
		}
	}
}