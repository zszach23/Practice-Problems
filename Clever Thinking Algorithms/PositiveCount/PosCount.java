// Zach Sally
// December 12, 2022

// ===================
// |  PosCount.java  |
// ===================

// Write an efficient function that takes an sorted input array of n elements and:

// 1. Returns true if there are at least k positive elements in the array; otherwise return false

// 2. Returns the number of positive elements in the array

import java.util.*;

public class PosCount
{
    
    // 1
    // Returns true if there are at least k positive elements in the array; otherwise return false
    static boolean posCount(int [] array, int k)
    {
        if (array.length < k) return false;
        if (k <= 0) return true;

        // If the length - k index of array is positive, every value after is positive since the
        // array is sorted. Therefore, there are at least k positive elements in the array
        return (array[array.length - k] > 0);
    }

    // 2
    // Returns the number of positive elements in the array
    static int posCount(int [] array)
    {
        int smallestPosIndex = Integer.MAX_VALUE;
        int lo = 0, hi = array.length;
        int mid = -1;
        boolean foundPos = false;

        // Binary search for smallest index with a positive value
        while (lo < hi)
        {
            mid = lo + (hi - lo) / 2;

            if (array[mid] > 0)
            {
                foundPos = true;
                hi = mid;

                // Mid is guaranteed to be the smallest positive index at this point
                smallestPosIndex = mid;
            }
            else
            {
                lo = mid + 1;
            }
        }

        // If there is a positive value in the array, then the smallest positive value
        // index was found, otherwise there weren't any positive values in the array
        return (foundPos) ? (array.length - smallestPosIndex) : 0;
    }

    public static void main(String [] args)
    {
        int [] array;

        // 1

        // True
        array = new int[] {-1, -1, -1, 0, 0, 2, 3};
        System.out.println(posCount(array, 2));

        // False
        array = new int[] {-1, -1, -1, 0, 0, 2, 3};
        System.out.println(posCount(array, 3));

        // False
        array = new int[] {-1, -1, -1, 0, 0, 0, 0, 0, 0};
        System.out.println(posCount(array, 1));

        // 2

        // 2
        array = new int[] {-1, -1, -1, 0, 0, 2, 3};
        System.out.println(posCount(array));

        // 0
        array = new int[] {-1, -1, -1, 0, 0, 0, 0, 0, 0};
        System.out.println(posCount(array));

        // 8
        array = new int[] {1, 1, 1, 1, 2, 5, 8, 10};
        System.out.println(posCount(array));
    }

}