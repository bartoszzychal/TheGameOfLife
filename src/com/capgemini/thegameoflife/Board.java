package com.capgemini.thegameoflife;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Board {

    // REVIEW bzychal - please define as final
    private final Integer sizeX;
    private final Integer sizeY;

    private final Map<Cell, State> board = new HashMap<>();

    public Board(Integer sizeX, Integer sizeY) {
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

    public Board getNewClearBoard() {
        return new Board(sizeX, sizeY);
    }

    // REVIEW bzychal - API to loose, it is pointless to allow Object here, please change to Cell
    public boolean containsCell(Object key) {
        return board.containsKey(key);
    }

    public void changeStateOfCell(Cell cell, State state) {
        board.replace(cell, state);
    }

    // REVIEW bzychal - wrong naming, till now the user didn't even now how the aplication is holding its internal
    // state. With this name he is informed that some kind of Map backs the storage.
    public Set<Entry<Cell, State>> entrySet() {
        return board.entrySet();
    }

    public State get(Cell cell) {
        return board.get(cell);
    }

}
