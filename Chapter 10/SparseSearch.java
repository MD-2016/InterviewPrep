package Chapter_10;

public class SparseSearch
{

    /*
     * My Solution
     * Time: O(n)
     */

    //compare two strings for equality
    public static int compareStrings(String s1, String s2)
    {
        int i = 0;
        while(i < s1.length() - 1 && s1.charAt(i) == s2.charAt(i))
        {
            i++;
        }

        if(s1.charAt(i) > s2.charAt(i))
        {
            return -1;
        }

        if(s1.charAt(i) < s2.charAt(i))
        {
            return 1;
        }
        else
            return 0;

    }

    public static int searchString(String[] arr, String str, int first, int last)
    {
        if(first > last)
        {
            return -1;
        }

        //move mid to middle
        int mid = (last + first) / 2;

        //if mid empty, find closest non empty string
        if(arr[mid].isEmpty())
        {
            // if mid empty, search both sides of mid and find closest non empty string and set mid
            int left = mid - 1;
            int right = mid + 1;
            while (true)
            {
                if(left < right && right < last)
                {
                    return -1;
                }
                if(right <= last && !arr[right].isEmpty())
                {
                    mid = right;
                    break;
                }
                if(left >= right && !arr[left].isEmpty())
                {
                    mid = left;
                    break;
                }
                right++;
                left--;
            }
        }

        //str found at mid
        if(compareStrings(str, arr[mid]) == 0)
        {
            return mid;
        }

        //str greater than mid
        if(compareStrings(str, arr[mid]) < 0)
        {
            return searchString(arr, str, mid + 1, last);
        }

        //str smaller than mid
        return searchString(arr, str, first, mid - 1);

    }

    /*
     * Book Solution
     * Time: O(n)
     */
    public static int searchI(String[] strings, String str, int first, int last) {
        while (first <= last) {
            /* Move mid to the middle */
            int mid = (last + first) / 2;

            /* If mid is empty, find closest non-empty string */
            if (strings[mid].isEmpty()) {
                int left = mid - 1;
                int right = mid + 1;
                while (true) {
                    if (left < first && right > last) {
                        return -1;
                    } else if (right <= last && !strings[right].isEmpty()) {
                        mid = right;
                        break;
                    } else if (left >= first && !strings[left].isEmpty()) {
                        mid = left;
                        break;
                    }
                    right++;
                    left--;
                }
            }

            int res = strings[mid].compareTo(str);
            if (res == 0) { // Found it!
                return mid;
            } else if (res < 0) { // Search right
                first = mid + 1;
            } else { // Search left
                last = mid - 1;
            }
        }
        return -1;
    }

    public static int searchR(String[] strings, String str, int first, int last) {
        if (first > last) {
            return -1;
        }

        /* Move mid to the middle */
        int mid = (last + first) / 2;

        /* If mid is empty, find closest non-empty string. */
        if (strings[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;
            while (true) {
                if (left < first && right > last) {
                    return -1;
                } else if (right <= last && !strings[right].isEmpty()) {
                    mid = right;
                    break;
                } else if (left >= first && !strings[left].isEmpty()) {
                    mid = left;
                    break;
                }
                right++;
                left--;
            }
        }

        /* Check for string, and recurse if necessary */
        if (str.equals(strings[mid])) { // Found it!
            return mid;
        } else if (strings[mid].compareTo(str) < 0) { // Search right
            return searchR(strings, str, mid + 1, last);
        } else { // Search left
            return searchR(strings, str, first, mid - 1);
        }
    }

    public static int searchBookSol(String[] strings, String str) {
        if (strings == null || str == null || str.isEmpty()) {
            return -1;
        }
        return searchR(strings, str, 0, strings.length - 1);
    }

    public static void main(String[] args)
    {

    }
}
