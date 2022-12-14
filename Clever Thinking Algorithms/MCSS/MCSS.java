// Zach Sally
// December 12, 2022

// ===============
// |  MCSS.java  |
// ===============

// What is the maximal contiguous subsequence sum of a given array

import java.util.*;

public class MCSS
{
    // Determines the maximal contiguous subsequence sum of a given array
    static int MCSS(int [] array)
    {
        int max = 0;
        int sum = 0;

        for (int i = 0; i < array.length; i++)
        {
            sum += array[i];

            // If this index is negative enough to cause the sum to go negative,
            // then it can't be included in the max
            if (sum < 0)
                sum = 0; 
            else if (sum > max)
                max = sum;           
        }

        return max;
    }  
}