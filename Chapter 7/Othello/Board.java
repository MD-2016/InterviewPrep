package Chapter_7.Othello;

public class Board {
    private int blackCount = 0;
    private int whiteCount = 0;
    private Piece[][] board;

    public Board(int r, int c) {
        board = new Piece[r][c];
    }

    public void initialize() {
        int middleR = board.length / 2;
        int middleC = board[middleR].length / 2;
        board[middleR][middleC] = new Piece(Color.White);
        board[middleR + 1][middleC] = new Piece(Color.Black);
        board[middleR + 1][middleC + 1] = new Piece(Color.White);
        board[middleR][middleC + 1] = new Piece(Color.Black);
        blackCount = 2;
        whiteCount = 2;
    }

    public boolean placeColor(int r, int c, Color col) {
        if (board[r][c] != null) {
            return false;
        }

        int[] results = new int[4];
        results[0] = flipSection(r - 1, c, col, Direction.UP);
        results[1] = flipSection(r + 1, c, col, Direction.DOWN);
        results[2] = flipSection(r, c + 1, col, Direction.RIGHT);
        results[3] = flipSection(r, c - 1, col, Direction.LEFT);

        int flipped = 0;
        for (int res : results) {
            if (res > 0) {
                flipped += res;
            }
        }

        if (flipped < 0) {
            return false;
        }

        board[r][c] = new Piece(col);
        updateScore(col, flipped + 1);
        return true;
    }

    private int flipSection(int r, int c, Color col, Direction d)
    {
        int row = 0;
        int column = 0;
        switch (d)
        {
            case UP:
                row = -1;
                break;
            case DOWN:
                row = 1;
                break;
            case LEFT:
                column = -1;
                break;
            case RIGHT:
                column = 1;
                break;
        }

        if(r < 0 || row >= board.length || c < 0 || c >= board[r].length || board[r][c] == null)
        {
            return -1;
        }

        if(board[r][c].getColor() == col)
        {
            return 0;
        }

        int flipped = flipSection(r + row, c + column, col, d);
        if(flipped < 0)
        {
            return -1;
        }

        board[r][c].flip();
        return flipped + 1;
    }

    public int getScoreForColor(Color c)
    {
        if(c == Color.Black)
        {
            return blackCount;
        }
        else
        {
            return whiteCount;
        }
    }

    public void updateScore(Color newColor, int newPieces)
    {
        if(newColor == Color.Black)
        {
            whiteCount -= newPieces - 1;
            blackCount += newPieces;
        }
        else
        {
            blackCount -= newPieces - 1;
            whiteCount += newPieces;
        }
    }

    public void printBoard()
    {
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[r].length; c++)
            {
                if(board[r][c] == null)
                {
                    System.out.print("_");
                }
                else if(board[r][c].getColor() == Color.White)
                {
                    System.out.print("W");
                }
                else
                {
                    System.out.print("B");
                }
            }
            System.out.println();
        }
    }

}
