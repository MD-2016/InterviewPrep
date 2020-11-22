package Chapter_8;

import java.util.Arrays;

public class Magic_Index
{
    /*
     * Brute Force Approach
     * Time: O(n)
     */
    public static int getMagicIndex(int[] arr)
    {
        int i;
        for(i = 0; i < arr.length; i++)
        {
            if(arr[i] == i)
            {
                return i;
            }
        }
        return -1;
    }

    /*
     * Better Approach
     * Time: O(log n)
     */
    public static int getMagicIndexDivideConquer(int arr[], int low, int high)
    {
        if(high >= low)
        {
            int mid = (low + high) / 2;
            if(mid == arr[mid])
            {
                return mid;
            }
            if(mid > arr[mid])
            {
                return getMagicIndexDivideConquer(arr, (mid + 1), high);
            }
            else
            {
                return getMagicIndexDivideConquer(arr, low, (mid - 1));
            }
        }
        return -1;
    }

    /*
     * Follow Up
     * Time: O(log n)
     */
    public static int getMagicFollowUp(int[] arr)
    {
        return getMagicFollowUp(arr, 0, arr.length - 1);
    }

    private static int getMagicFollowUp(int[] arr, int left, int right)
    {
        if(right < left)
        {
            return -1;
        }
        int mid = left + (right - left) / 2;
        int midVal = arr[mid];
        if(midVal == mid)
        {
            return mid;
        }

        //search left
        int leftIndex = Math.min(mid - 1, midVal);
        int l = getMagicFollowUp(arr, left, leftIndex);
        if(l >= 0)
        {
            return l;
        }

        //search right
        int rightIndex = Math.max(mid + 1, midVal);
        int r = getMagicIndexDivideConquer(arr, rightIndex, right);
        return r;
    }

    /*
     * Book Approach Brute Force
     * Time: O(n)
     */
    public static int magicSlow(int[] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] == i)
            {
                return i;
            }
        }
        return -1;
    }

    /*
     * Book Approach Divide and Conquer
     * Time: O(log n)
     */
    public static int magicFast(int[] arr, int start, int end)
    {
        if(end < start)
        {
            return -1;
        }
        int mid = (start + end) / 2;
        if(arr[mid] == mid)
        {
            return mid;
        }
        else if(arr[mid] > mid)
        {
            return magicFast(arr, start, mid - 1);
        }
        else
        {
            return magicFast(arr, mid + 1, end);
        }
    }

    /*
     * Book Approach Follow Up
     * Time: O(log n)
     */
    public static int magicFastFollowUp(int[] arr)
    {
        return magicFastFollowUp(arr, 0, arr.length - 1);
    }

    private static int magicFastFollowUp(int[] arr, int start, int end)
    {
        if (end < start) return -1;
        int midIndex = (start + end) / 2;
        int midVal = arr[midIndex];
        if (midVal == midIndex)
        {
            return midIndex;
        }

        //search left
        int leftIndex = Math.min(midIndex - 1, midVal);
        int left = magicFastFollowUp(arr, start, leftIndex);
        if(left >= 0)
        {
            return left;
        }

        //search right
        int rightIndex = Math.max(midIndex + 1, midVal);
        int right = magicFastFollowUp(arr, rightIndex, end);

        return right;

    }

    public static void main(String[] args)
    {
        int arr[] = {-10, -1, 0, 3 , 10, 11, 30, 50, 100};
        int n = arr.length;
        System.out.println("Fixed Point is "
                + getMagicIndex(arr));
    }


}
