package com.capgemini.thegameoflife;

import java.util.HashMap;

public class Board extends HashMap<Cell, State> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4022931082278579406L;
	
	private Integer sizeX;
	private Integer sizeY;
		
	public Board(Integer sizeX,Integer sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		generateGameBoard();
	}
	
	private void generateGameBoard() {
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				put(new Cell(x, y), State.DEAD);
			}
		}
	}
	
	public Board getNewClearBoard(){
		return new Board(sizeX,sizeY);
	}

}
