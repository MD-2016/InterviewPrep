package Chapter_10;

public class NoSizeSortedSearch
{


    //support class
    class Listy
    {
        int[] arr;

        public Listy(int[] arr)
        {
            arr = arr.clone();
        }

        public int elementAt(int index)
        {
            if(index >= arr.length)
            {
                return -1;
            }

            return arr[index];
        }
    }

    /*
     * My Solution
     * Time: O(log n)
     */
    public static int search(Listy list, int val)
    {
        int index = 1;
        while(list.elementAt(index) != -1 && list.elementAt(index) < val)
        {
            index *= 2;
        }
        return binarySearch(list, val, index / 2, index);
    }

    public static int binarySearch(Listy list, int val, int left, int right)
    {
        int mid = 0;
        while (left + 1 < right)
        {
            mid = left + (right - left) / 2;
            if(list.elementAt(mid) == val)
            {
                return mid;
            }
            else if(list.elementAt(mid) > val)
            {
                //going left
                right = mid;
            }
            else
            {
                left = mid;
            }
        }
        return list.elementAt(left) == val ? left : list.elementAt(right) == val ? right : -1;
    }

    /*
     * Book Solution
     * Time: O(log n)
     */
    public static int binarySearchBookSol(Listy list, int value, int low, int high) {
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            int middle = list.elementAt(mid);
            if (middle > value || middle == -1) {
                high = mid - 1;
            } else if (middle < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int searchBookSol(Listy list, int value) {
        int index = 1;
        while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
            index *= 2;
        }
        return binarySearchBookSol(list, value, index / 2, index);
    }

}
