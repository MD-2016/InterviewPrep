public class BinaryToString {

    /*
     * My Approach
     * Time:
     * Space:
     */
    static String printBinary(double num)
    {

        //Check number between 0 to 1 or not
        if (num >= 1 || num <= 0)
        {
            return "ERROR";
        }
        StringBuilder bin = new StringBuilder();
        double frac = 0.5;
        bin.append(".");

        while(num > 0) {
            /* set limit to 32 characters
             * if num cannot be represented report error
             */
            if (bin.length() >= 32) {
                return "ERROR";
            }

            //compare to 0.5
            if (num >= frac) {
                bin.append(1);
                num -= frac;
            } else {
                bin.append(0);
            }

            //becomes 0.25
            frac /= 2;
        }

        return bin.toString();
    }

    /*
     * Book Approach
     * Time:
     * Space:
     */
    public String printBin(double num)
    {
        if(num >= 1 || num <= 0)
        {
            return "ERROR";
        }

        StringBuilder binary = new StringBuilder();
        binary.append(".");
        while(num > 0)
        {
            //set limit to 32 char
            if (binary.length() >= 32)
            {
                return "ERROR";
            }

            double r = num * 2;
            if(r >= 1)
            {
                binary.append(1);
                num = r - 1;
            }
            else
            {
                binary.append(0);
                num = r;
            }
        }

        return binary.toString();
    }

    public static void main(String[] args)
    {

    }

}
