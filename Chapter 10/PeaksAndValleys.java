package Chapter_10;

public class PeaksAndValleys
{
    //support method
    public static void swap(int[] arr, int left, int right)
    {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /*
     * My Solution
     * Time: O(n)
     * Space: O(1)
     */
    public static void sortPeakValley(int[] arr)
    {
        for(int i = 1; i < arr.length; i += 2)
        {
            int bigIndex = maxIndex(arr, i-1, i, i+1);
            if(i != bigIndex)
            {
                swap(arr, i, bigIndex);
            }
        }
    }

    //support method
    public static int maxIndex(int[] arr, int a, int b, int c)
    {
        int len = arr.length;
        int aVal = a >= 0 && a < len ? arr[a] : Integer.MIN_VALUE;
        int bVal = b >= 0 && b < len ? arr[b] : Integer.MIN_VALUE;
        int cVal = c >= 0 && c < len ? arr[c] : Integer.MIN_VALUE;

        int max = Math.max(aVal, Math.max(bVal, cVal));
        if(aVal == max)
        {
            return a;
        }
        else if(bVal == max)
        {
            return b;
        }
        else return c;

    }

    /*
     * Book Solution
     * Time: O(n)
     * Space: O(1)
     */
    public static void swapBook(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void sortValley(int[] array) {
        for (int i = 1; i < array.length; i += 2) {
            int biggestIndex = maxIndex(array, i - 1, i, i + 1);
            if (i != biggestIndex) {
                swapBook(array, i, biggestIndex);
            }
        }
    }

    public static int maxIndexBook(int[] array, int a, int b, int c) {
        int len = array.length;
        int aValue = a >= 0 && a < len ? array[a] : Integer.MIN_VALUE;
        int bValue = b >= 0 && b < len ? array[b] : Integer.MIN_VALUE;
        int cValue = c >= 0 && c < len ? array[c] : Integer.MIN_VALUE;

        int max = Math.max(aValue, Math.max(bValue, cValue));

        if (aValue == max) {
            return a;
        } else if (bValue == max) {
            return b;
        } else {
            return c;
        }
    }



    public static void main(String[] args)
    {

    }
}
