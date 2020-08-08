public class ZeroMatrix {

    public static void main(String[] args)
    {

    }

    /*
     * Time complexity is O(mn) because of the matrices of size m and n plus needing to iterate both.
     * Space is O(1) because we dont need extra space after using the boolean checks
     */
    public static void setZeros(int[][] matrix)
    {
        boolean firstRowZero = false;
        boolean firstColZero = false;
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        for(int i = 0; i < rowLen; i++)
        {
            if(matrix[i][0] == 0)
            {
                firstColZero = true;
                break;
            }
        }

        for(int i = 0; i < colLen; i++)
        {
            if(matrix[0][i] == 0)
            {
                firstRowZero = true;
                break;
            }
        }

        for(int i = 1; i < rowLen; i++)
        {
            for(int j = 1; j < colLen; j++)
            {
                if(matrix[i][j] == 0)
                {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for(int i = 1; i < rowLen; i++)
        {
            if(matrix[i][0] == 0)
            {
                for(int j = 1; j < rowLen; j++)
                {
                    matrix[i][j] = 0;
                }
            }
        }

        for(int i = 1; i < colLen; i++)
        {
            if(matrix[0][i] == 0)
            {
                for(int j = 1; j < rowLen; j++)
                {
                    matrix[j][i] = 0;
                }
            }
        }

        if(firstRowZero)
        {
            for(int i = 0; i < colLen; i++)
            {
                matrix[0][i] = 0;
            }
        }

        if(firstColZero)
        {
            for(int i = 0; i < rowLen; i++)
            {
                matrix[i][0] = 0;
            }
        }

    }

    /*
     * better solution?
     * Time is O(mn) because we still need to check for zeroes in the 2d array so we need to iterate through it
     * and we also need to account for m and n can be different sizes
     * Space is O(1) like before but we reduced the amount of booleans used.
     * This solution post pons the setting of a row or column to zeros.
     * Using the first cell of every row and column as a flag to determine whether a row or column has been set to zero.
     */
    public void setZeroes(int[][] matrix)
    {
        boolean isCol = false;
        int rowLen = matrix.length;
        int colLen = matrix[0].length;

        for(int i = 0; i < rowLen; i++)
        {
            //since first cell for both 1st row and column is same
            //we can use an additional variable for either first row / column
            //we use a boolean for first column reducing the row boolean
            //matrix [0][0] is for first row
            if(matrix[i][0] == 0)
            {
                isCol = true;
            }

            for(int j = 1; j < colLen; j++)
            {
                //if an element is zero, we set the first element of the corresponding row and column to 0

                if(matrix[i][j] == 0)
                {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }

        }

        //iterate over the array once again using first row and first column, update the elements
        for(int i = 1; i < rowLen; i++)
        {
            for(int j = 1; j < colLen; j++)
            {
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                {
                    matrix[i][j] = 0;
                }
            }
        }

        //See if first row needs to be set to zero as well
        if(matrix[0][0] == 0)
        {
            for(int j = 0; j < colLen; j++)
            {
                matrix[0][j] = 0;
            }
        }

        //see if first column needs to be set to zero
        if(isCol)
        {
            for(int i = 0; i < rowLen; i++)
            {
                matrix[i][0] = 0;
            }
        }

    }

}
