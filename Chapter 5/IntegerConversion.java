public class IntegerConversion {

    /*
     * My Approach
     */
    //count set bits
    public static int countSetBits(int n)
    {
        int count = 0;
        while(n != 0)
        {
            count++;
            n &= (n-1);
        }
        return count;
    }

    //return count of flipped bits
    public static int countFlipped(int a, int b)
    {
        //return count of set bits in a XOR b
        return countSetBits(a ^ b);
    }

    /*
     * Book Approach
     */
    public static int bitSwapRequired(int a, int b){
        int count = 0;
        int c = a ^ b;

        System.out.println("****");
        System.out.println(c + ": " + toFullBinaryString(c));
        while (c != 0) {
            System.out.println("c - 1: " + c + ": " + toFullBinaryString(c - 1));
            c = c & (c-1);
            System.out.println("c: " + c + ": " + toFullBinaryString(c));
            count++;
            System.out.println("****");
        }
        return count;
    }

    public static String toFullBinaryString(int a) {
        String s = "";
        for (int i = 0; i < 32; i++) {
            Integer lsb = new Integer(a & 1);
            s = lsb.toString() + s;
            a = a >> 1;
        }
        return s;
    }

    public static void main(String[] args)
    {

    }

}
