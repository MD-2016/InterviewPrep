package Chapter_8;

public class Recursive_Multiply
{
    //Assumption: Integers

    /*
     * My approach
     * Time: O(y) where y is the second argument. Mainly O(n)
     * Space: O(n)
     */
    public static int multiply(int x, int y)
    {
        //check for 0
        if(y == 0)
        {
            return 0;
        }

        //add x one by one
        if(y > 0)
        {
            return (x + multiply(x, y - 1));
        }

        //Negative cases for y
        if(y < 0)
        {
            return -multiply(x, -y);
        }

        return -1;

    }

    /*
     * Book Approach
     * Time:
     */
    public static int counter = 0;

    public static int sum(int x, int y)
    {
        counter++;
        return x + y;
    }

    public static int minProductHelper(int smaller, int bigger)
    {
        if(smaller == 0)
        {
            return 0;
        }
        else if(smaller == 1)
        {
            return bigger;
        }

        int s = smaller >> 1;
        int side1 = minProductHelper(s,bigger);
        int side2 = side1;
        if(smaller % 2 == 1)
        {
            counter++;
            side2 = minProductHelper(smaller - s, bigger);
        }
        counter++;
        return side1 + side2;
    }

    public static int minProduct(int a, int b)
    {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;
        return minProduct(smaller, bigger);
    }

    /*
     * Book Approach 2
     * Time: O(n)
     */
    public static int counter2 = 0;

    public static int sum2(int x, int y) {
        counter2 += 1;
        return x + y;
    }

    public static int minProduct2(int smaller, int bigger, int[] memo) {
        if (smaller == 0) {
            return 0;
        } else if (smaller == 1) {
            return bigger;
        } else if (memo[smaller] > 0) {
            return memo[smaller];
        }

        /* Compute half. If uneven, compute other half. If even,
         * double it. */
        int s = smaller >> 1; // Divide by 2
        int side1 = minProduct2(s, bigger, memo); // Compute half
        int side2 = side1;
        if (smaller % 2 == 1) {
            counter++;
            side2 = minProduct2(smaller - s, bigger, memo);
        }

        /* Sum and cache.*/
        counter++;
        memo[smaller] = side1 + side2;
        return memo[smaller];
    }

    public static int minProduct3(int a, int b) {
        int bigger = a < b ? b : a;
        int smaller = a < b ? a : b;

        int memo[] = new int[sum(smaller, 1)];
        return minProduct2(smaller, bigger, memo);
    }

    /*
     * Book Approach 3
     * Time: O(log s) where s is smaller
     */
    public static int minProduct4(int a, int b) {
        if (a < b) return minProduct(b, a);
        int value = 0;
        while (a > 0) {
            counter++;
            if ((a % 10) % 2 == 1) {
                value += b;
            }
            a >>= 1;
            b <<= 1;
        }
        return value;
    }

    public static void main(String[] args)
    {

    }
}
