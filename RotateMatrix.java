import java.util.*;
import java.awt.*;

public class RotateMatrix {

    public static void main(String[] args)
    {

        int[][] matrix = randomMatrix(3,3,0,9);
        canRotate(matrix);
        System.out.println( canRotate(matrix));
        printMatrix(matrix);

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < 10 && matrix[i][j] > -10) {
                    System.out.print(" ");
                }
                if (matrix[i][j] < 100 && matrix[i][j] > -100) {
                    System.out.print(" ");
                }
                if (matrix[i][j] >= 0) {
                    System.out.print(" ");
                }
                System.out.print(" " + matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] randomMatrix(int m, int n, int min, int max)
    {
        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {
                matrix[i][j] = randomIntInRange(min,max);
            }
        }
        return matrix;
    }

    private static int randomInt(int n) {
        return (int) (Math.random() * n);
    }

    private static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }


    public static boolean canRotate(int[][] matrix)
    {
        //check for an empty matrix or not being a square matrix
        if(matrix.length == 0 || matrix.length != matrix[0].length)
        {
            return false;
        }

        int size = matrix.length;

        for(int i = 0; i < size / 2; i++)
        {
            int first = i;
            int last = size - 1 - i;

            for(int j = first; j < last; j++)
            {
                int offset = j - first;
                int top = matrix[first][j];

                matrix[first][j] = matrix[last - offset][first];

                matrix[last-offset][first] = matrix[last][last - offset];

                matrix[last][last - offset] = matrix[j][last];

                matrix[j][last] = top;

            }
        }
        return true;
    }

}
