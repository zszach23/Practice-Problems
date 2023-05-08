// Zach Sally

// ================
// |  Rando.java  |
// ================

// Take two integer parameters, m and n, repeatedly get a random integer within the range
// of m through n (inclusively) until it has produced every value on the range of m through n
// at least once.

// This program acts as an exercise in automated testing.

import java.util.*;

public class Rando
{
    public static void main(String[] args)
    {
        if (args.length < 2)
        {
            System.out.println("Not enough arguments, run java Rando lowBound highBound");
            return;
        }

        int lowBound = Integer.parseInt(args[0]);
        int highBound = Integer.parseInt(args[1]);

        if (lowBound > highBound)
        {
            System.out.println("First argument should be less than or equal to second argument");
            return;
        }

        if (lowBound < 0 || highBound < 0)
        {
            System.out.println("Argument(s) must be non-negative");
            return;
        }

        testRando(lowBound, highBound);
    }

    public static void testRando(int lowBound, int highBound)
    {
        HashSet<Integer> seen = new HashSet<>();

        while (seen.size() < (highBound - lowBound + 1))
        {
            int rando = getRando(lowBound, highBound);

            if (!seen.contains(rando))
                seen.add(rando);
        }

        for (int num : seen)
            System.out.println(num);
    }

    public static int getRando(int lowBound, int highBound)
    {
        return (int)(Math.random() * (highBound - lowBound + 1) + lowBound);
    }
}