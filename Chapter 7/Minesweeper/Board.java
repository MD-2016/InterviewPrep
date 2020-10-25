package Chapter_7.Minesweeper;

import Chapter_7.Chat_Server.User;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Board {
    private int rows;
    private int cols;
    private int nBombs = 0;
    private Cell[][] cells;
    private Cell[] bombs;
    private int numUnexposedRemaining;

    public Board(int r, int c, int b) {
        rows = r;
        cols = c;
        nBombs = b;

        initializeBoard();
        shuffleBoard();
        setNumberedCells();

        numUnexposedRemaining = rows * cols - nBombs;
    }

    private void initializeBoard() {
        cells = new Cell[rows][cols];
        bombs = new Cell[nBombs];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells[r][c] = new Cell(r, c);
            }
        }

        for (int i = 0; i < nBombs; i++) {
            int r = i / cols;
            int c = (i - r * cols) % cols;
            bombs[i] = cells[r][c];
            bombs[i].setBomb(true);
        }
    }

    private void shuffleBoard() {
        int nCells = rows * cols;
        Random ran = new Random();
        for (int index1 = 0; index1 < nCells; index1++) {
            int index2 = index1 + ran.nextInt(nCells - index1);
            if (index1 != index2) {
                int r1 = index1 / cols;
                int col1 = (index1 - r1 * cols) % cols;
                Cell cell1 = cells[r1][col1];

                int r2 = index2 / cols;
                int col2 = (index2 - r2 * cols) % cols;
                Cell cell2 = cells[r2][col2];

                cells[r1][col1] = cell2;
                cell2.setRowAndColumn(r1, col1);
                cells[r2][col2] = cell1;
                cell1.setRowAndColumn(r2, col2);
            }
        }
    }

    private boolean inBounds(int r, int c)
    {
        return rows >= 0 && r < rows && c >= 0 && c < cols;
    }

    private void setNumberedCells()
    {
        int[][] deltas = { // Offsets of 8 surrounding cells
                {-1, -1}, {-1, 0}, {-1, 1},
                { 0, -1},          { 0, 1},
                { 1, -1}, { 1, 0}, { 1, 1}
        };
        for(Cell bomb : bombs)
        {
            int r = bomb.getRow();
            int c = bomb.getColumn();
            for(int[] delta : deltas)
            {
                int row = r + delta[0];
                int col = c + delta[1];
                if(inBounds(row,col))
                {
                    cells[row][col].incrementNumber();
                }
            }
        }
    }

    public void printBoard(boolean showUnderside) {
        System.out.println();
        System.out.print("  ");
        for (int i = 0; i < cols; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cols; i++)
        {
            System.out.print("--");
        }
        System.out.println();
        for(int r = 0; r < rows; r++)
        {
            System.out.print(r + "| ");
            for(int c = 0; c < cols; c++)
            {
                if(showUnderside)
                {
                    System.out.print(cells[r][c].getStateUnder());
                }
                else
                {
                    System.out.print(cells[r][c].getSurface());
                }
            }
            System.out.println();
        }
    }

    private boolean flipCell(Cell cell)
    {
        if(!cell.isExposed() && !cell.isGuess())
        {
            cell.flip();
            numUnexposedRemaining--;
            return true;
        }
        return false;
    }

    public void expandBlank(Cell cell)
    {
        int[][] deltas = {
                {-1,-1}, {-1,0}, {-1,1},
                {0, -1}, {0,1},
                {1,-1}, {1,0}, {1,1}
        };

        Queue<Cell> toExplore = new LinkedList<>();
        toExplore.add(cell);

        while(!toExplore.isEmpty())
        {
            Cell curr = toExplore.remove();

            for(int[] delta : deltas)
            {
                int r = curr.getRow() + delta[0];
                int c = curr.getColumn() + delta[1];

                if(inBounds(r,c))
                {
                    Cell neighbor = cells[r][c];
                    if(flipCell(neighbor) && neighbor.isBlank())
                    {
                        toExplore.add(neighbor);
                    }
                }

            }

        }
    }

    public UserPlayResult playFlip(UserPlay play)
    {
        Cell cell = getCellAtLocation(play);
        if(cell == null)
        {
            return new UserPlayResult(false, Game.GameState.RUNNING);
        }

        if(play.isGuess())
        {
            boolean guessRes = cell.toggleGuess();
            return new UserPlayResult(guessRes, Game.GameState.RUNNING);
        }

        boolean res = flipCell(cell);

        if(cell.isBomb())
        {
            return new UserPlayResult(res, Game.GameState.LOST);
        }

        if(cell.isBlank())
        {
            expandBlank(cell);
        }

        if(numUnexposedRemaining == 0)
        {
            return new UserPlayResult(res, Game.GameState.WON);
        }

        return new UserPlayResult(res, Game.GameState.RUNNING);
    }

    public Cell getCellAtLocation(UserPlay play)
    {
        int row = play.getRow();
        int col = play.getCol();
        if(!inBounds(row,col))
        {
            return null;
        }
        return cells[row][col];
    }

    public int getNumRemaining()
    {
        return numUnexposedRemaining;
    }

}
