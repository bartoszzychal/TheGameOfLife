package com.capgemini.thegameoflife;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    private final Integer columns;
    private final Integer rows;

    private final Map<Cell, State> board = new HashMap<>();

    public Board(Integer columns, Integer rows) {
        this.columns = columns;
        this.rows = rows;
        generateGameBoard();
    }

    private void generateGameBoard() {
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                board.put(new Cell(x, y), State.DEAD);
            }
        }
    }

    public Board getNewClearBoard() {
        return new Board(columns, rows);
    }

    public boolean containsCell(Cell key) {
        return board.containsKey(key);
    }

    public void changeStateOfCell(Cell cell, State state) {
        board.replace(cell, state);
    }

    public Map<Cell, State> getAllCellsWithState() {
        return board;
    }

    public Set<Cell> getAllCells() {
    	return board.keySet();
    }

    public State getState(int x,int y) {
        return board.get(new Cell(x,y));
    }
    
    public State getState(Cell cell) {
    	return board.get(cell);
    }
}
