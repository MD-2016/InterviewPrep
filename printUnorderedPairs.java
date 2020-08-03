public class printUnorderedPairs {

    public static void main(String[] args)
    {
        int[] arr = {0,1,2,3,4,5,6,7};
        printPairs(arr);

    }

    public static void printPairs(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            System.out.println("Value of i " + i);
            for(int j = i + 1; j < array.length; j++)
            {
                System.out.println("Value of j " + j);
                //System.out.println(array[i] + "," + array[j]);
            }
        }
    }
}
