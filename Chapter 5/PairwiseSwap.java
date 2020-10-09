/*
 * Works for 32-bit integers only
 */
public class PairwiseSwap {

    /*public static boolean isPowerOfTwo(int x)
    {
        return x > 0 && (x & x - 1) == 0;
    }

    //support method
    public static String toBinaryString(int n)
    {
        return String.format("%32s", Integer.toBinaryString(n).replaceAll(" ", "0"));
    }
     */

    public static String toFullBinaryString(int a) {
        String s = "";
        for (int i = 0; i < 32; i++) {
            Integer lsb = new Integer(a & 1);
            s = lsb.toString() + s;
            a = a >> 1;
        }
        return s;
    }

    //function that completes the task
    public static int swapOddEvenBits(int n)
    {
        return (((n & 0xAAAAAAAA) >>> 1) | ((n & 0x55555555) << 1));
    }

    public static void main(String[] args)
    {

    }


}
