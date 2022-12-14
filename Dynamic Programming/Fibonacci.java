// Zach Sally
// December 14, 2022

// ====================
// |  Fibonacci.java  |
// ====================

// Recursive, Memoized, and DP solution to find the nth Fibonacci number

import java.util.*;

public class Fibonacci
{
    // 0 is a valid number for fib, but negative is not
    // This is also language agnostic
    private static final int UNINITIALIZED = -1;

    // Recursive Approach
    // Runtime = O(phi^n)   Space = O(n) <-- Call stack
    public static int fib_recursive(int n)
    {
        if (n < 2)
            return n;

        return fib_recursive(n - 1) + fib_recursive(n - 2);
    }

    // Memoized Approach
    // Runtime = O(n)   Space = O(n) <-- Call stack and memo array
    public static int fib_memo(int n)
    {
        int [] memo = new int[n + 1];
        Arrays.fill(memo, UNINITIALIZED);

        return fib_memo(n, memo);
    }

    private static int fib_memo(int n, int [] memo)
    {
        if (n < 2)
            return n;

        // Already computed memo[n]
        if (memo[n] != UNINITIALIZED)
            return memo[n];

        memo[n] = fib_memo(n - 1, memo) + fib_memo(n - 2, memo);

        return memo[n];
    }

    // DP
    // Runtime = O(n)   Space = O(n)
    public static int fib_dp(int n)
    {
        if (n < 2)
            return n;
        
        int [] dp = new int[n + 1];

        // Base Cases
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++)
        {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // DP Optimized - Only need the previous two values
    // Runtime = O(n)   Space = O(1)
    public static int fib_dp_opt(int n)
    {
        int [] dp = new int[2];

        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++)
        {
            dp[i % 2] = dp[(i - 1) % 2] + dp[(i - 2) % 2];
        }

        return dp[n % 2];
    }

    // Run as:
    // java Fibonacci n1 n2 ...
    public static void main(String [] args)
    {
        int n;

        if (args.length < 1)
        {
            System.err.println("Enter value(s)");
            return;
        }

        for (int i = 0; i < args.length; i++)
        {
            n = Integer.parseInt(args[i]);
            System.out.println(n);

            if (n < 0)
            {
                System.err.println("Fibonacci does not handle negative values\n");
                continue;
            }

            System.out.println("Optimized DP Fib: " + fib_dp_opt(n));
            System.out.println("DP Fib: " + fib_dp(n));
            System.out.println("Memo Fib: " + fib_memo(n));
            System.out.println("Recursive Fib: " + fib_recursive(n));
            System.out.println();
        }

    }

}