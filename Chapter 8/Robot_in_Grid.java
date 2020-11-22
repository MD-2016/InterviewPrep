package Chapter_8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Robot_in_Grid
{
    static class Point
    {
        int row;
        int col;

        public Point(int r, int c)
        {
            this.row = r;
            this.col = c;
        }

        @Override
        public String toString()
        {
            return "(" + row + "," + col + ")";
        }

        @Override
        public int hashCode()
        {
            return this.toString().hashCode();
        }

        @Override
        public boolean equals(Object o)
        {
            if((o instanceof Point) && ((Point) o).row == this.row && ((Point) o).col == this.col)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /*
     * Brute Force Approach
     * Time: O(2^(r+c))
     */
    public List<Point> getPath1(boolean[][] maze)
    {
        if(maze == null || maze.length == 0)
        {
            return null;
        }
        List<Point> res = new ArrayList<>();
        if(getPath1(maze, maze.length - 1, maze[0].length - 1, res))
        {
            return res;
        }
        return null;
    }

    private boolean getPath1(boolean[][] maze, int row, int col, List<Point> path)
    {
        //out of bounds or not there
        if(!maze[row][col] || col < 0 || row < 0)
        {
            return false;
        }

        boolean isAtOrigin = (row == 0) && (col == 0);
        if(getPath1(maze, row, col - 1, path) || getPath1(maze, row - 1, col, path) || isAtOrigin)
        {
            Point p = new Point(row, col);
            path.add(p);
            return true;
        }
        return false;
    }

    /*
     * Better bottom up approach DP
     * Time: O(rc)
     */
    public List getPath2(boolean[][] maze)
    {
        if(maze == null || maze.length == 0)
        {
            return null;
        }
        List<Point> res = new ArrayList<>();
        Set<Point> failedPoint = new HashSet<>();
        if(getPath2(maze, maze.length - 1, maze[0].length - 1, res, failedPoint))
        {
            return res;
        }
        return null;
    }

    private boolean getPath2(boolean[][] maze, int r, int c, List<Point> path, Set<Point> failed)
    {
        //out of bounds or not there
        if(!maze[r][c] || c < 0 || r < 0)
        {
            return false;
        }
        Point p = new Point(r,c);
        if(failed.contains(p))
        {
            return false;
        }
        boolean isAtOrigin = (r == 0) && (c == 0);
        if(getPath2(maze, r, c - 1, path, failed) || getPath2(maze, r - 1, c, path, failed) || isAtOrigin)
        {
            path.add(p);
            return true;
        }
        return false;
    }

    /*
     * Book Brute Force
     * Time: O(2^(r+c))
     */
    static ArrayList<Point> getPathBook(boolean[][] maze)
    {
        if(maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        if(getPathBook(maze,maze.length - 1, maze[0].length - 1, path))
        {
            return path;
        }
        return null;
    }

    static boolean getPathBook(boolean[][] maze, int r, int c, ArrayList<Point> path)
    {
        //out of bounds or not there
        if(c < 0 || r < 0 || !maze[r][c])
        {
            return false;
        }
        boolean isAtOrigin = (r == 0) && (c == 0);

        if(isAtOrigin || getPathBook(maze, r, c - 1, path) || getPathBook(maze, r - 1, c, path))
        {
            Point p = new Point(r,c);
            path.add(p);
            return true;
        }
        return false;
    }

    /*
     * Book DP Approach
     * Time: O(RC)
     */
    static ArrayList<Point> getPathBookDP(boolean[][] maze)
    {
        if(maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        HashSet<Point> failed = new HashSet<>();
        if(getPathBookDP(maze, maze.length - 1, maze[0].length - 1, path, failed))
        {
            return path;
        }
        return null;
    }

    static boolean getPathBookDP(boolean[][] maze, int r, int c, ArrayList<Point> path, HashSet<Point> failed)
    {
        //out of bounds or not there
        if(c < 0 || r < 0 || !maze[r][c])
        {
            return false;
        }

        Point p = new Point(r,c);

        if(failed.contains(p))
        {
            return false;
        }

        boolean isAtOrigin = (r == 0) && (c == 0);

        if(isAtOrigin || getPathBookDP(maze, r, c - 1, path, failed) || getPathBookDP(maze, r-1, c, path, failed))
        {
            path.add(p);
            return true;
        }

        failed.add(p);
        return false;
    }

    //support code
    public static boolean[][] randomBooleanMatrix(int M, int N, int percentTrue) {
        boolean[][] matrix = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = randomBoolean(percentTrue);
            }
        }
        return matrix;
    }

    public static boolean randomBoolean() {
        return randomIntInRange(0, 1) == 0;
    }

    public static boolean randomBoolean(int percentTrue) {
        return randomIntInRange(1, 100) <= percentTrue;
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

    public static void printMatrix(boolean[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        int size = 5;
        boolean[][] maze = randomBooleanMatrix(size, size, 70);

        printMatrix(maze);

        ArrayList<Point> pathA = getPathBook(maze);
        ArrayList<Point> pathB = getPathBookDP(maze);
        if (pathA != null) {
            System.out.println(pathA.toString());
        } else {
            System.out.println("No path found.");
        }

        if (pathB != null) {
            System.out.println(pathB.toString());
        } else {
            System.out.println("No path found.");
        }
    }


}
