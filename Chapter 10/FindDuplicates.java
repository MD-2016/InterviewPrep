package Chapter_10;

public class FindDuplicates
{
    /*
     * My Solution
     * Time:
     */
    public static void checkDuplicates(int[] arr)
    {
        BitArray ba = new BitArray(3200000);

        for(int i = 0; i < arr.length; i++)
        {
            int num = arr[i] - 1;

            if(ba.get(num))
            {
                System.out.print(num + " ");
            }
            else
            {
                ba.set(num);
            }

        }
    }

    /*
     * Book Solution
     * Time:
     *
     */
    public static void checkDuplicatesBook(int[] array) {
        BitSet bs = new BitSet(32000);
        for (int i = 0; i < array.length; i++) {
            int num = array[i];
            int num0 = num - 1; // bitset starts at 0, numbers start at 1
            if (bs.get(num0)) {
                System.out.println(num);
            } else {
                bs.set(num0);
            }
        }
    }

}

//Support class
class BitArray
{
    int[] arr;

    public BitArray(int n)
    {
        arr = new int[(n >> 5) + 1];
    }

    boolean get(int pos)
    {
        int index = (pos >> 5);

        int bitNo = (pos & 0x1F);

        return (arr[index] & (1 << bitNo)) != 0;
    }

    void set(int pos)
    {
        int index = (pos >> 5);

        int bitNo = (pos & 0x1F);
        arr[index] |= (1 << bitNo);
    }
}

//Book Solution
class BitSet {
    int[] bitset;

    public BitSet(int size) {
        bitset = new int[(size >> 5) + 1]; // divide by 32
    }

    boolean get(int pos) {
        int wordNumber = (pos >> 5); // divide by 32
        int bitNumber = (pos & 0x1F); // mod 32
        return (bitset[wordNumber] & (1 << bitNumber)) != 0;
    }

    void set(int pos) {
        int wordNumber = (pos >> 5); // divide by 32
        int bitNumber = (pos & 0x1F); // mod 32
        bitset[wordNumber] |= 1 << bitNumber;
    }
}
