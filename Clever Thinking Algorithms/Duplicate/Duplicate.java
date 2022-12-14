public class Duplicate
{

    // Intakes a sorted array
    static boolean containsDuplicate(int [] array)
    {
        if (array.length < 2)
            return false;

        for (int i = 0; i < array.length - 1; i++)
        {
            // Check if the current index and next index are equivalent 
            if (array[i] == array[i + 1])
                return true;
        }

        return false;
    }

    public static void main(String [] args)
    {
        int [] array;
        // True
        array = new int[] {-1, 0, 0, 1, 2, 5, 5, 8, 9, 9};
        System.out.println(containsDuplicate(array));

        // False
        array = new int[] {-1, 0, 1, 2, 5, 6, 8, 9, 10};
        System.out.println(containsDuplicate(array));

        // False
        array = new int[] {0};
        System.out.println(containsDuplicate(array));
    }
}