package Chapter_10;

//Assumption: Integers
public class SortedMerge
{
    /*
     * My Solution
     * Time: O(m+n) where m is size of array m and n is size of array n
     * Space: O(1)
     */
    public static int NA = -1;
    public static void sortedMerge(int a[], int b[], int n, int m)
    {
        int i = n - 1;
        int j = m - 1;

        int lastIndex = n + m - 1;

        //merge a and b starting with last element in each
        while(j >= 0)
        {
            //End of 1st array is greater than end of 2nd array
            if(i >= 0 && a[i] > b[j])
            {
                //copy element
                a[lastIndex] = a[i];
                i--;
            }
            else
            {
                //copy element
                a[lastIndex] = b[j];
                j--;
            }

            //move indices
            lastIndex--;
        }

    }

    //Support code
    static void printArray(int arr[], int n)
    {
        System.out.println("Array A after merging B in sorted order : ");
        for(int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
    }

    /*
     * Book Solution
     * Time:
     */
    public static void merge(int[] a, int[] b, int countA, int countB)
    {
        int indexMerged = countB + countA - 1;
        int indexA = countA - 1;
        int indexB = countB - 1;

        //Merge a and b starting from last element in each
        while(indexB >= 0)
        {
            if(indexA >= 0 && a[indexA] > b[indexB]) //end of A bigger than B
            {
                a[indexMerged] = a[indexA]; //copy element
                indexA--;
            }
            else
            {
                a[indexMerged] = b[indexB]; //copy element
                indexB--;
            }
            indexMerged--;
        }
    }




    public static void main(String[] args)
    {

    }
}
