package Chapter_8;

public class Coins
{
    /*
     * My Approach
     * Time: O(mn) where m and n are inner loops
     * Space: O(n)
     */
    public static int count(int[] S, int m, int n)
    {
        int table[] = new int[n + 1];

        table[0] = 1;

        for(int i = 0; i < m; i++)
        {
            for(int j = S[i]; j <= n; j++)
            {
                table[j] += table[j-S[i]];
            }
        }
        return table[n];
    }

    /*
     * Book Approach 1
     * Time: O(S^n) where n is number of coins and S is combinations
     * Space: O(n)
     */
    public static int makeChangeHelper(int total, int[] denoms, int index) {
        int coin = denoms[index];
        if (index == denoms.length - 1) { // One denom left, either you can do it or not
            int remaining = total % coin;
            return remaining == 0 ? 1 : 0;
        }
        int ways = 0;
        /* Try 1 coin, then 2 coins, 3 three, ... (recursing each time on rest). */
        for (int amount = 0; amount <= total; amount += coin) {
            ways += makeChangeHelper(total - amount, denoms, index + 1); // go to next denom
        }
        return ways;
    }

    public static int makeChange(int amount, int[] denoms) {
        return makeChangeHelper(amount, denoms, 0);
    }

    /*
     * Book Approach 2
     * Time:
     * Space: O(mn) for 2d Array
     */
    public static int makeChange2(int n, int[] denoms) {
        int[][] map = new int[n + 1][denoms.length];
        return makeChangeHelper2(n, denoms, 0, map);
    }

    public static int makeChangeHelper2(int total, int[] denoms, int index, int[][] map) {
        /* Check cache for prior result. */
        if (map[total][index] > 0) { // retrieve value
            return map[total][index];
        }

        int coin = denoms[index];
        if (index == denoms.length - 1) {
            int remaining = total % coin;
            return remaining == 0 ? 1 : 0;
        }
        int numberOfWays = 0;
        /* Try 1 coin, then 2 coins, 3 three, ... (recursing each time on rest). */
        for (int amount = 0; amount <= total; amount += coin) {
            numberOfWays += makeChangeHelper2(total - amount, denoms, index + 1, map); // go to next denom
        }

        /* Update cache with current result. */
        map[total][index] = numberOfWays;

        return numberOfWays;
    }

}
