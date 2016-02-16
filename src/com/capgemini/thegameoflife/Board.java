package com.capgemini.thegameoflife;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Board {
		
	private Integer sizeX;
	private Integer sizeY;
	
	private Map<Cell, State> board = new HashMap<>();
	
	public Board(Integer sizeX,Integer sizeY){
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		generateGameBoard();
	}
	
	private void generateGameBoard() {
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				board.put(new Cell(x, y), State.DEAD);
			}
		}
	}
	
	public Board getNewClearBoard(){
		return new Board(sizeX,sizeY);
	}
	
	public boolean containsCell(Object key){
		return board.containsKey(key);
	}
	
	public void changeStateOfCell(Cell cell, State state){
		board.replace(cell, state);
	}

	public Set<Entry<Cell, State>> entrySet() {
		return board.entrySet();
	}

	public State get(Cell cell) {
		return board.get(cell);
	}
	

}
