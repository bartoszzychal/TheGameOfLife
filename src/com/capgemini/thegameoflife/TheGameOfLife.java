package com.capgemini.thegameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TheGameOfLife {

	private final Integer CELL_ALIVE_HAS_TWO_NEIGHBORS = 2;
	private final Integer CELL_ALIVE_HAS_THREE_NEIGHBORS = 3;
	private Board board;
	
	public TheGameOfLife(Integer sizeX,Integer sizeY){
		board = new Board(sizeX, sizeY);
	}
	
	public void setInitialState(List<Cell> params) {
		for (Cell cell : params) {
			boolean cellExists = board.containsKey(cell);
			
			if(!cellExists){
				throw new IllegalArgumentException("The cell not exist.");
			}
			
			board.replace(cell, State.ALIVE);
		}
	}

	public void evolve(){
		Board newBoard = board.getNewClearBoard();
		for(Map.Entry<Cell, State> entry: board.entrySet()){
			Cell cell = entry.getKey();
			State newStateForCell = getNewStateForCell(cell);
			newBoard.replace(cell, newStateForCell);
		}
		board = newBoard;
	}
	

	private State getNewStateForCell(Cell cell) {
		Integer numberOfLifeNeighborCells = getNumberOfLifeNeighborCells(cell);
		State actualStateOfCell = board.get(cell);
		State newState = State.DEAD;
				
		if(CELL_ALIVE_HAS_THREE_NEIGHBORS.equals(numberOfLifeNeighborCells)){
			newState = State.ALIVE;
		}
		
		if(CELL_ALIVE_HAS_TWO_NEIGHBORS.equals(numberOfLifeNeighborCells) && State.ALIVE.equals(actualStateOfCell) ){
			newState = State.ALIVE;
		}

		return newState;
	}

	private Integer getNumberOfLifeNeighborCells(Cell cell) {
		Integer x = cell.getX();
		Integer y = cell.getY();
		Integer numberOfLifeNeighborCells = Integer.valueOf(0);
		
		for (Integer xNeigbor = x-1; xNeigbor <= x+1; xNeigbor++) {
			for (Integer yNeigbor = y-1; yNeigbor <= y+1; yNeigbor++) {
				
				if(!xNeigbor.equals(x) || !yNeigbor.equals(y)){
					State state = board.get(new Cell(xNeigbor, yNeigbor));
					
					if(State.ALIVE.equals(state)){
						numberOfLifeNeighborCells++;
					}	
				}	
			}	
		}
		return numberOfLifeNeighborCells;
	}

	public List<Cell> getAliveCell() {
		List<Cell> listOfAliveCell = new ArrayList<>();		
		for(Map.Entry<Cell, State> entry: board.entrySet()){
			if(entry.getValue().equals(State.ALIVE)){
				listOfAliveCell.add(entry.getKey());
			}
		}
		return listOfAliveCell;
	}

}
