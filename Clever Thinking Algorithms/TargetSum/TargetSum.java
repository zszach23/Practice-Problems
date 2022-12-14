public class TargetSum
{

    static boolean hasTargetSum(int [] array, int target)
    {
        int low = 0, high = array.length - 1;

        while (low < high)
        {
            if (array[low] == target || array[high] == target)
            {
                return true;
            }

            if (array[low] + array[high] > target)
            {
                high--;
            }
            else if (array[low] + array[high] < target)
            {
                low++;
            }
            else
            {
                return true;
            }
        }

        return false;
    }

    public static void main(String [] args)
    {
        int [] array;

        // True
        array = new int [] {0, 0, 0, 9, 10, 20, 50};
        System.out.println(hasTargetSum(array, 0));

        // True
        array = new int [] {0, 0, 0, 9, 10, 20, 50};
        System.out.println(hasTargetSum(array, 19));

        // False
        array = new int [] {0, 0, 0, 9, 10, 20, 50};
        System.out.println(hasTargetSum(array, 69));
    }
}