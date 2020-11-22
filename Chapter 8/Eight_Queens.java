package Chapter_8;

import java.util.ArrayList;

public class Eight_Queens
{
    /*
     * My Approach
     * Time:
     * Space: O(n)
     */

    public static Pos[] solveEightQueens(int n)
    {
        Pos[] pos = new Pos[n];

        boolean placed = solveEightQueens(n, 0, pos);
        if(placed)
        {
            return pos;
        }
        else
        {
            return new Pos[0];
        }
    }

    private static boolean solveEightQueens(int n, int row, Pos[] pos)
    {
        if(n == row)
        {
            return true;
        }

        int col;
        for(col = 0; col < n; col++)
        {
            boolean safe = true;

            for(int queen = 0; queen < row; queen++)
            {
                if(pos[queen].col == col || pos[queen].row - pos[queen].col == row - col || pos[queen].row + pos[queen].col == row + col)
                {
                    safe = false;
                    break;
                }
            }
            if(safe)
            {
                pos[row] = new Pos(row, col);
                if(solveEightQueens(n, row + 1, pos))
                {
                    return true;
                }
            }
        }
        return false;
    }

    //support class
    public static class Pos
    {
        int row, col;

        public Pos(int r, int c)
        {
            this.row = r;
            this.col = c;
        }

        @Override
        public String toString()
        {
            return "(" + row + "," + col + ")";
        }
    }



    /*
     * Book Approach
     * Time:
     * Space:
     */

    public static int GRID_SIZE = 8;

    public static boolean checkValid(Integer[] cols, int r1, int c1)
    {
        for(int r2 = 0; r2 < r1; r2++)
        {
            int c2 = cols[r2];

            if(c1 == c2)
            {
                return false;
            }

            int columDist = Math.abs(c2 - c1);
            int rowDist = r1 - r2;

            if(columDist == rowDist)
            {
                return false;
            }

        }
        return true;
    }

    public static void placeQueens(int r, Integer[] cols, ArrayList<Integer[]> res)
    {
       if(r == GRID_SIZE)
       {
           res.add(cols.clone());
       }
       else
       {
           for(int col = 0; col < GRID_SIZE; col++)
           {
               if(checkValid(cols, r, col))
               {
                   cols[r] = col;
                   placeQueens(r + 1, cols, res);
               }
           }
       }
    }

    public static void clear(Integer[] cols)
    {
        for(int i = 0; i < GRID_SIZE; i++)
        {
            cols[i] = -1;
        }
    }

    public static void printBoard(Integer[] cols)
    {
        drawLine();
        for(int i = 0; i < GRID_SIZE; i++)
        {
            System.out.print("|");
            for(int j = 0; j < GRID_SIZE; j++)
            {
                if(cols[i] == j)
                {
                    System.out.print("Q|");
                }
                else
                {
                    System.out.print(" |");
                }
            }
            System.out.print("\n");
            drawLine();
        }
        System.out.println("");
    }

    private static void drawLine()
    {
        StringBuilder line = new StringBuilder();
        for(int i = 0; i < GRID_SIZE * 2 + 1; i++)
        {
            line.append('-');
        }
        System.out.println(line.toString());
    }

    public static void printBoards(ArrayList<Integer[]> boards) {
        for (int i = 0; i < boards.size(); i++)
        {
            Integer[] board = boards.get(i);
            printBoard(board);
        }
    }

}
