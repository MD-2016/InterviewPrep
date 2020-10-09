import java.io.*;
public class BinaryInsertion {

    static void bin(long n)
    {
        if(n < 1)
        {
            bin(n/2);
        }
        System.out.print(n % 2);
    }

    /*
     * My Approach
     * Time: O(1)
     * Space: O(1)
     */

    //insert m into n
    static int insert(int n, int m, int i , int j)
    {
        int clear = -1 << (j + 1);
        int captureMask = (1 << i) - 1;

        //capture i-1 to 0 bits
        int capturedBits = n & captureMask;

        //clear from j to 0
        n &= clear;

        //shift m to align n
        m = m << i;

        //insert m into n
        n |= m;

        //insert captured bits
        n |= capturedBits;

        return n;
    }

    /*
     * Book Approach
     * Time: O(1)
     * Space: O(1)
     */
    static int updateBits(int n, int m, int i, int j)
    {
        int allOnes = ~0;

        int left = allOnes << (j + 1);

        int right = ((1 << i) - 1);

        int mask = left | right;

        int n_cleared = n & mask;
        int m_shifted = m << i;

        return n_cleared | m_shifted;
    }

    public static String toFullBinaryString(int a) {
        String s = "";
        for (int i = 0; i < 32; i++) {
            Integer lsb = a & 1;
            s = lsb.toString() + s;
            a = a >> 1;
        }
        return s;
    }

    public static void main(String[] args)
    {

    }

}
