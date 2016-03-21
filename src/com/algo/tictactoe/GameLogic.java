package com.algo.tictactoe;

import java.util.Scanner;
/*
 * Tictactoe gamelogic.
 * Board and players should be received from others and converted into enum/strings.
 * For the test, players are strings.
 * Board indexing: 
 * 
 * 0 | 1 | 2
 * ---------
 * 3 | 4 | 5
 * ---------
 * 6 | 7 | 8
 * 
 */
public class GameLogic {
	// Players - should be passed from menu
			
	/*private static final String CROSS = "X";
	private static final String NOUGHT = "O";
	private static final String EMPTY = "";
	*/
	
	// Game state
	private static GameState currentState;
	private static PlayerBoard currentPlayer;
	
	// Board is an array/matrix? should be received by board designers
	private static final int boardSize = 9;
	private static PlayerBoard gameBoard[] = new PlayerBoard[boardSize];
	private static final int ROWS = 3;
	private static final int COLS = 3;
	
	public GameLogic() {
		
	}
	
	public void GameMain(){
		initGame();
		while (currentState == GameState.PLAYING){
			playerMove(currentPlayer);
			//printBoard();
			updatePlayer(currentPlayer);
			switchPlayer(currentPlayer);
		}
	}
	
	
	
	public void initGame(){
		for(int i=0; i<boardSize; i++){
			gameBoard[i] = PlayerBoard.EMPTY;
		}
		currentPlayer = PlayerBoard.CROSS;
		currentState = GameState.PLAYING;
	}
	
	@SuppressWarnings("resource")
	public void playerMove(PlayerBoard player){
		Scanner scnr = new Scanner(System.in);
		boolean isValid = false;
		while(!isValid){
			if(player.equals(PlayerBoard.CROSS))
				System.out.println("Player X - your turn. Enter a row(1-3) and a column(1-3)");
			else
				System.out.println("Player O - your turn. Enter a row(1-3) and a column(1-3)");
			int row = scnr.nextInt();
			int col = scnr.nextInt();
			if(row > 0 && row <= ROWS && col > 0 && col <= COLS){
				int boardSpot = calculateBoardSpot(row, col);
				gameBoard[boardSpot] = player;
				isValid = true;
			}
			else{
				System.out.println("This move is not valid. Please try again");
			}
		}
	}
		
	public void switchPlayer(PlayerBoard player){
		if(player!=null){
			currentPlayer = (player.equals(PlayerBoard.CROSS)) ? PlayerBoard.NOUGHT : PlayerBoard.CROSS;
			System.out.println("It's " + player.toString() + " turn now.");
		}
		else
			System.out.println("Cant switch player");
	}
	
	public void updatePlayer(PlayerBoard player){
		if(isWinner(player)){
			currentState = player.equals(PlayerBoard.CROSS) ?  GameState.CROSS_WON :  GameState.NOUGHT_WON;
			System.out.println(player.toString() + " Has won the game! GJ");
		}
		else if (isDraw()){
			currentState = GameState.DRAW;
			System.out.println("It's a DRAW!!!");
		}
	}
	
	public boolean isDraw(){
		// To do
	}
	
	public boolean isWinner(PlayerBoard playerSign){
		return( (gameBoard[0] == playerSign && gameBoard[1] == playerSign && gameBoard[2] == playerSign) ||
				(gameBoard[3] == playerSign && gameBoard[4] == playerSign && gameBoard[5] == playerSign) || 
				(gameBoard[6] == playerSign && gameBoard[7] == playerSign && gameBoard[8] == playerSign) || 
				(gameBoard[0] == playerSign && gameBoard[3] == playerSign && gameBoard[6] == playerSign) ||
				(gameBoard[1] == playerSign && gameBoard[4] == playerSign && gameBoard[7] == playerSign) ||
				(gameBoard[2] == playerSign && gameBoard[5] == playerSign && gameBoard[8] == playerSign) ||
				(gameBoard[0] == playerSign && gameBoard[4] == playerSign && gameBoard[8] == playerSign) ||
				(gameBoard[2] == playerSign && gameBoard[4] == playerSign && gameBoard[6] == playerSign) 
				);
	}
	
	public int calculateBoardSpot(int row, int col){
		if(row == 1)
			return col-row;
		else if(row == 2)
			return col+row;
		else
			return col+row+2;
	}

}
