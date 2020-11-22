package Chapter_8;

import java.util.Arrays;

public class Triple_Step
{

    //Assumption: Doesn't exceed integer bounds

    /*
     * Brute Force Approach
     * Time: O(3^n)
     * Space: O(1)
     */
    public static int findSteps(int n)
    {
        if(n == 1 || n == 0)
        {
            return 1;
        }
        else if(n == 2)
        {
            return 2;
        }
        else
        {
            return findSteps(n - 3) + findSteps(n - 2) + findSteps(n - 1);
        }
    }

    /*
     * Dynamic Programming using Bottom Up Approach
     * Time: O(n)
     * Space: O(n)
     */
    public static int findStepsDP(int n)
    {
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        res[2] = 2;

        for(int i = 3; i <= n; i++)
        {
            res[i] = res[i - 1] + res[i - 2] + res[i - 3];
        }
        return res[n];

    }

    /*
     * Book Brute Force Approach
     * Time: O(3^n)
     * Space: O(1)
     */
    public static int bookCountWays(int n)
    {
        if(n < 0)
        {
            return 0;
        }
        else if(n == 0)
        {
            return 1;
        }
        else
        {
            return bookCountWays(n - 1) + bookCountWays(n - 2) + bookCountWays(n - 3);
        }
    }

    /*
     * Book Approach Memoization (top down)
     * Time: O(n)
     * Space: O(n)
     */
    public static int bookCountWaysMemo(int n)
    {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return bookCountWaysMemo(n, cache);
    }

    private static int bookCountWaysMemo(int n, int[] cache)
    {
        if(n < 0)
        {
            return 0;
        }
        else if(n == 0)
        {
            return 1;
        }
        else if(cache[n] > -1)
        {
            return cache[n];
        }
        else
        {
            cache[n] = bookCountWaysMemo(n - 1, cache) + bookCountWaysMemo(n - 2, cache) + bookCountWaysMemo(n - 3, cache);
            return cache[n];
        }
    }


    /*
     * DP with Rolling Array (bottom up)
     * Time: O(n)
     * Space: O(1)
     */
    public static int countWaysDP(int n)
    {
        if(n < 0) return 0;
        if(n == 0) return 1;
        if(n == 1) return 2;
        if(n == 2) return 4;

        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for(int i = 3; i < n; ++i)
        {
            dp[i%3] = dp[(i - 1) % 3] + dp[(i - 2) % 3] + dp[(i - 3) % 3];
        }
        return dp[(n-1) % 3];


    }

    public static void main(String[] args)
    {
        int n = 4;
        System.out.println(findSteps(n));
        System.out.println(bookCountWays(n));
        System.out.println(bookCountWaysMemo(n));
        System.out.println(findStepsDP(n));
        System.out.println(countWaysDP(n));
    }

}
