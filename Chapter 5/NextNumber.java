public class NextNumber {

   static int getNext(int n)
   {
       //compute c0 and c1
       int c = n;
       int c0 = 0;
       int c1 = 0;

       while(((c & 1) == 0)&& (c != 0))
       {
           c0++;
           c >>= 1;
       }

       while ((c & 1) == 1)
       {
           c1++;
           c >>= 1;
       }

       //if there is no bigger number with same number of 1s
       if(c0 + c1 == 31 || c0 + c1 == 0)
       {
           return -1;
       }

       //position of rightmost non trailing zero
       int p = c0 + c1;

       //flip rightmost non trailing zero
       n |= (1 << p);

       //clear all bits to right of p
       n &= ~((1 << p) - 1);

       //insert (c1-1) ones on right
       n |= (1 << (c1 - 1)) - 1;

       return n;

   }

   static int getPrev(int n)
   {
       //compute c0 and c1 and store n
       int temp = n;
       int c0 = 0;
       int c1 = 0;

       while((temp & 1) == 1)
       {
           c1++;
           temp = temp >> 1;
       }

       if(temp == 0)
       {
           return -1;
       }

       while(((temp & 1) == 0) && (temp != 0))
       {
           c0++;
           temp = temp >> 1;
       }

       //position of rightmost non trailing one
       int p = c0 + c1;

       //clear bit p
       n = n & ((~0) << (p + 1));

       //sequence of (c1 + 1) ones
       int mask = (1 << (c1 + 1)) - 1;
       n = n | mask << (c0 - 1);

       return n;

   }

   /*
    * Book Approach
    */
   public static int getNextArith(int n) {
       int c = n;
       int c0 = 0;
       int c1 = 0;
       while (((c & 1) == 0) && (c != 0)) {
           c0++;
           c >>= 1;
       }

       while ((c & 1) == 1) {
           c1++;
           c >>= 1;
       }

       /* If c is 0, then n is a sequence of 1s followed by a sequence of 0s. This is already the biggest
        * number with c1 ones. Return error.
        */
       if (c0 + c1 == 31 || c0 + c1 == 0) {
           return -1;
       }

       /* Arithmetically:
        * 2^c0 = 1 << c0
        * 2^(c1-1) = 1 << (c0 - 1)
        * next = n + 2^c0 + 2^(c1-1) - 1;
        */

       return n + (1 << c0) + (1 << (c1 - 1)) - 1;
   }

    public static int getPrevArith(int n) {
        int temp = n;
        int c0 = 0;
        int c1 = 0;
        while (((temp & 1) == 1) && (temp != 0)) {
            c1++;
            temp >>= 1;
        }

        /* If temp is 0, then the number is a sequence of 0s followed by a sequence of 1s. This is already
         * the smallest number with c1 ones. Return -1 for an error.
         */
        if (temp == 0) {
            return -1;
        }

        while ((temp & 1) == 0 && (temp != 0)) {
            c0++;
            temp >>= 1;
        }

        /* Arithmetic:
         * 2^c1 = 1 << c1
         * 2^(c0 - 1) = 1 << (c0 - 1)
         */
        return n - (1 << c1) - (1 << (c0 - 1)) + 1;
    }

    public static void binPrint(int i) {
        System.out.println(i + ": " + Integer.toBinaryString(i));
    }


    public static void main(String[] args)
    {

    }

}
