package com.capgemini.thegameoflive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TheGameOfLive {

	Logger logger = Logger.getGlobal();
	private final Integer CELL_DEAD_HAS_ONE_NEIGHBOR = 1;
	private final Integer CELL_ALIVE_HAS_TWO_NEIGHBORS = 2;
	private final Integer CELL_ALIVE_HAS_THREE_NEIGHBORS = 3;
	private final Integer CELL_DEAD_HAS_AT_LEAST_FOUR_NEIGHBORS = 4;
	
	private Map<Cell,State> mapOfCellState;
	private Integer gameBoardX = 4;
	private Integer gameBoardY = 4;
	
	public TheGameOfLive(){
		this.mapOfCellState = new HashMap<>();
		generateGameBoard();
	}

	private void generateGameBoard() {
		for (int x = 0; x < gameBoardX; x++) {
			for (int y = 0; y < gameBoardY; y++) {
				mapOfCellState.put(new Cell(x, y), State.DEAD);
			}
		}
	}
	
	public void setInitialState(List<Cell> params) {
		for (Cell cell : params) {
			boolean cellExists = mapOfCellState.containsKey(cell);
			
			if(!cellExists){
				throw new IllegalArgumentException("The cell not exist");
			}
			
			mapOfCellState.replace(cell, State.ALIVE);
			logger.info("Initial "+cell.toString()+State.ALIVE);

		}
	}

	public void nextStates(){
		Map<Cell, State> newMapOfCellState = new HashMap<>();
		for(Map.Entry<Cell, State> entry: mapOfCellState.entrySet()){
			Cell cell = entry.getKey();
			State newStateForCell = getNewStateForCell(cell);
			newMapOfCellState.put(cell, newStateForCell);
		}
		mapOfCellState = newMapOfCellState;
	}
	

	private State getNewStateForCell(Cell cell) {
		Integer numberOfLiveNeighborCells = getNumberOfLiveNeighborCells(cell);
		State stateOfActualCell = mapOfCellState.get(cell);
		
		if(CELL_DEAD_HAS_ONE_NEIGHBOR.equals(numberOfLiveNeighborCells) ||
				CELL_DEAD_HAS_AT_LEAST_FOUR_NEIGHBORS <= numberOfLiveNeighborCells){
			return State.DEAD;
		}
		
		if(CELL_ALIVE_HAS_TWO_NEIGHBORS.equals(numberOfLiveNeighborCells) && State.DEAD.equals(stateOfActualCell) ){
			return State.DEAD;
		}
		
		if(CELL_ALIVE_HAS_THREE_NEIGHBORS.equals(numberOfLiveNeighborCells)){
			return State.ALIVE;
		}
		
		if(CELL_ALIVE_HAS_TWO_NEIGHBORS.equals(numberOfLiveNeighborCells) && State.ALIVE.equals(stateOfActualCell) ){
			return State.ALIVE;
		}

		return State.DEAD;
	}

	private Integer getNumberOfLiveNeighborCells(Cell cell) {
		Integer x = cell.getX();
		Integer y = cell.getY();
		Integer numberOfLiveNeighborCells = Integer.valueOf(0);
		
		for (Integer neigborX = x-1; neigborX <= x+1; neigborX++) {
			
			for (Integer neigborY = y-1; neigborY <= y+1; neigborY++) {
				
				if(!neigborX.equals(x) || !neigborY.equals(y)){
					State state = mapOfCellState.get(new Cell(neigborX, neigborY));
					
					if(State.ALIVE.equals(state)){
						numberOfLiveNeighborCells++;
					}
					
				}
				
			}
			
		}
		
		return numberOfLiveNeighborCells;
	}

	public List<Cell> getPositionsOfLiveCell() {
		List<Cell> listOfLiveCell = new ArrayList<>();
		for(Map.Entry<Cell, State> entry: mapOfCellState.entrySet()){
			if(entry.getValue().equals(State.ALIVE)){
				listOfLiveCell.add(entry.getKey());
			}
		}
		return listOfLiveCell;
	}

}
