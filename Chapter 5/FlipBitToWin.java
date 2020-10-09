public class FlipBitToWin {

    /*
     * My Approach
     * Time: O(b) for number of bits
     * Space: O(1)
     */
    static int flipBit(int binNum)
    {
        //if all bits are 1, binNum has all 1s
        if(~binNum == 0)
        {
            return 8 * sizeOf();
        }

        int currLength = 0, prevLength = 0, maxLength = 0;
        while(binNum != 0)
        {
            //if current bit is 1 then increment current length
            if((binNum & 1) == 1)
            {
                currLength++;
            }

            //current bit is 0 then check next bit
            else if((binNum & 1) == 0)
            {
                //update previous length to 0 if next bit is 0 or current length if next bit is 1
                prevLength = (binNum & 2) == 0 ? 0 : currLength;

                //if two consecutive bits are 0 then current length is 0
                currLength = 0;
            }

            //update max length if needed
            maxLength = Math.max(prevLength + currLength, maxLength);

            //remove last bit (right shift)
            binNum >>= 1;
        }

        //always can have a sequence of at least one 1, flipped bit
        return maxLength + 1;
    }

    static byte sizeOf()
    {
        byte sizeOfInt = 8;
        return sizeOfInt;
    }

    /*
     * Book Approach
     * Time: O(b) where b is number of bits
     * Space: O(1)
     */
    public static int flipBits(int a)
    {
        //if all 1s then already longest sequence
        if(~a == 0) return Integer.BYTES * 8;

        int currLen = 0;
        int prevLen = 0;
        int maxLen = 1; //always have a sequence of at least one 1
        while(a != 0)
        {
            if((a & 1) == 1)
            {
                //current bit is 1
                currLen++;
            }
            else if((a & 1) == 0)
            {
                //current bit is 0
                //update to 0 (if next is 0) or current length (if next is 1)
                prevLen = (a & 2) == 0 ? 0 : currLen;
                currLen = 0;
            }
            maxLen = Math.max(prevLen + currLen + 1, maxLen);
            a >>>= 1;
        }
        return maxLen;
    }

    public static void main(String[] args)
    {

    }

}
