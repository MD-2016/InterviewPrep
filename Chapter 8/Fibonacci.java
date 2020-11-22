package Chapter_8;

public class Fibonacci
{

    public int regularFibonacciApporach(int i)
    {
       if(i == 0) return 0;
       if(i == 1) return 1;
       return regularFibonacciApporach(i - 1) + regularFibonacciApporach(i - 2);
    }

    //Top down with memoization
    public int fibonacci(int i)
    {
        return fibonacci(i, new int [i + 1]);
    }

    public int fibonacci(int i, int[] memo)
    {
        if(i == 0 || i == 1) return i;
        if(memo[i] == 0)
        {
            memo[i] = fibonacci(i - 1, memo) + fibonacci(i - 2, memo);
        }
        return memo[i];
    }

    //Bottom up approach
    int bottomDownFib(int n)
    {
        if(n == 0) return 0;
        else if(n == 1) return 1;

        int[] memo = new int[n];
        memo[0] = 0;
        memo[1] = 1;
        for(int i = 2; i < n; i++)
        {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n - 1] + memo[n - 2];
    }

    int bottomDownFibNoArray(int n)
    {
        if(n == 0) return 0;
        int a = 0;
        int b = 1;
        for(int i = 2; i < n; i++)
        {
            int c = a + b;
            a = b;
            b = c;
        }
        return a + b;
    }
}
