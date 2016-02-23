package com.capgemini.thegameoflife;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TheGameOfLife class allow to set initial state for life cell, evolve, and get state of cells.
 * @author ZBARTOSZ
 *
 */
public class TheGameOfLife {

    private final int CELL_ALIVE_HAS_TWO_NEIGHBORS = 2;
    private final int CELL_ALIVE_HAS_THREE_NEIGHBORS = 3;
    private Board board;

    /**
     * Constructor receiving the size of board columns and rows
     * @param columns Count columns of  board
     * @param rows	Count rows of board
     */
    public TheGameOfLife(Integer columns, Integer rows) {
        board = new Board(columns, rows);
    }

    /**
     * Set the state LIVE for list of cells. Cells must have coordinates not bigger then board size.  
     * @param lifeCells list of cell, which should be life on begin of game
     * @throws IllegalArgumentException for cell with coordinates bigger then board size
     */
    public void setInitialState(List<Cell> lifeCells) {
    	if(!board.getAllCellsWithState().keySet().containsAll(lifeCells)){
    		throw new IllegalArgumentException();
    	}
    	lifeCells.stream().forEach((cell)->board.changeStateOfCell(cell, State.ALIVE));
    }

    /**
     * Evolve the state of Cells to next generation 
     */
    public void evolve() {
        Board newBoard = board.getNewClearBoard();
        board.getAllCellsWithState()
        .keySet()
        .stream()
        .forEach((cell)->{
        	newBoard.changeStateOfCell(cell, getNewStateForCell(cell));
        });
        board = newBoard;
    }

    private State getNewStateForCell(Cell cell) {
        int numberOfLifeNeighborCells = getNumberOfLifeNeighborCells(cell);
        State actualStateOfCell = board.getState(cell);
        State newState = State.DEAD;
        if (CELL_ALIVE_HAS_THREE_NEIGHBORS == numberOfLifeNeighborCells) {
            newState = State.ALIVE;
        }else if (CELL_ALIVE_HAS_TWO_NEIGHBORS == numberOfLifeNeighborCells && State.ALIVE == actualStateOfCell) {
            newState = State.ALIVE;
        }
        return newState;
    }

    private int getNumberOfLifeNeighborCells(Cell cell) {
        Integer x = cell.getX();
        Integer y = cell.getY();
        int numberOfLifeNeighborCells = 0;

        for (int xNeigbor = x - 1; xNeigbor <= x + 1; xNeigbor++) {
            for (int yNeigbor = y - 1; yNeigbor <= y + 1; yNeigbor++) {
                if (xNeigbor != x || yNeigbor != y) {
                    if (State.ALIVE == board.getState(xNeigbor, yNeigbor)) {
                        numberOfLifeNeighborCells+=1;
                    }
                }
            }
        }
        return numberOfLifeNeighborCells;
    }
    /**
     * 
     * @return List of live cell
     */
    public List<Cell> getAliveCell() {
        return board
        		.getAllCellsWithState()
        		.entrySet()
        		.stream()
        		.filter((cellEntry)->cellEntry.getValue() == State.ALIVE)
        		.map((cellEntry)->cellEntry.getKey())
        		.collect(Collectors.toList());
    }

}
