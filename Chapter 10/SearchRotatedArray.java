package Chapter_10;


//Assumption: Integers
public class SearchRotatedArray
{
    /*
     * My Solution
     * Time: O(log n)
     * Space: O(1)
     */
    public static int search(int[] arr, int start, int end, int key)
    {
        if(start > end)
        {
            return -1;
        }

        int mid = (start + end) / 2;
        if(arr[mid] == key)
        {
            return mid;
        }

        if(arr[start] <= arr[mid])
        {
            if(key >= arr[start] && key <= arr[mid])
            {
                return search(arr, start, mid - 1, key);
            }
            return search(arr, mid + 1, end, key);
        }

        if(key >= arr[mid] && key <= arr[end])
        {
            return search(arr, mid + 1, end, key);
        }

        return search(arr, start, mid - 1, key);
    }

    /*
     * Book Solution
     * Time: O(log n)
     */
    public static int searchBookSol(int a[], int x) {
        return searchBookSol(a, 0, a.length - 1, x);
    }


    public static int searchBookSol(int a[], int left, int right, int x) {
        int mid = (left + right) / 2;
        if (x == a[mid]) { // Found element
            return mid;
        }
        if (right < left) {
            return -1;
        }

        /* While there may be an inflection point due to the rotation, either the left or
         * right half must be normally ordered.  We can look at the normally ordered half
         * to make a determination as to which half we should search.
         */
        if (a[left] < a[mid]) { // Left is normally ordered.
            if (x >= a[left] && x < a[mid]) {
                return searchBookSol(a, left, mid - 1, x);
            } else {
                return searchBookSol(a, mid + 1, right, x);
            }
        } else if (a[mid] < a[right]) { // Right is normally ordered.
            if (x > a[mid] && x <= a[right]) {
                return searchBookSol(a, mid + 1, right, x);
            } else {
                return searchBookSol(a, left, mid - 1, x);
            }
        } else if (a[left] == a[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
            if (a[mid] != a[right]) { // If right half is different, search there
                return searchBookSol(a, mid + 1, right, x);
            } else { // Else, we have to search both halves
                int result = search(a, left, mid - 1, x);
                if (result == -1) {
                    return searchBookSol(a, mid + 1, right, x);
                } else {
                    return result;
                }
            }
        }
        return -1;
    }



    public static void main(String[] args)
    {

    }
}
