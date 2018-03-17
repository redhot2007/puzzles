package org.puzzles.coding.snippet.design.tictactoe;

public class Game {
	private Player player;
	private Player computer;
	private char[][] gameBoard;
	private int boardSize;
	private char gameSymbol;
	
	
	public boolean isWinner(Player player){
		
		return false;
	}
	
	public void makeMove(Player player, Move move){
		
	}
	
	public Player getWinner(){
		Player winner = null;
		
		return winner;
	}
	
	public void makeComputerMove(){
		
	}
	
	class Move{
		char column;
		int row;
		
		protected int[] returnPosition(){
			int[] position = new int[2];
			
			return position;
			
		}
	}
}

