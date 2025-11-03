package LLDTicTacToe.entity;

public class Board {
    private Symbol[][] grid;
    private int size;
    private Symbol emptyCell;

    public Board(int size) {
        this.size = size;
        grid = new Symbol[size][size];
        emptyCell = new Symbol('_');
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                grid[i][j] = emptyCell;
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == emptyCell;
    }

    public int getSize() {
        return size;
    }

    public Symbol getCell(int row, int col) {
        return grid[row][col];
    }

    public Symbol getEmptyCell() {
        return emptyCell;
    }

    public void display() {
        System.out.print("\n  ");
        for(int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getMark() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void placeMark(int row, int col, Symbol symbol) {
        grid[row][col] = symbol;
    }
}
