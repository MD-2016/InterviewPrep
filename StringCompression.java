public class StringCompression {

    public static void main(String[] args)
    {
        String t = "aabbcccccaaa";
        String res = compress(t);
        String res2 = compress2(t);
        System.out.println(res);
        System.out.println(res2);
    }

    /*
     * Using StringBuilder reduces the runtime from O(n^2)
     * Now Time is O(n) since we only need to compare current character to next
     * Space is O(n) for the size of the string being compressed
     */
    public static String compress(String s) {
        StringBuilder compressedStr = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;

            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                compressedStr.append(s.charAt(i));
                compressedStr.append(count);
                count = 0;
            }

        }

        return compressedStr.length() < s.length() ? compressedStr.toString() : s;
    }

    /*
     * Better solution?
     */
    public static String compress2(String s)
    {
        int finalLen = countCompress(s);
        if(finalLen >= s.length())
        {
            return s;
        }

        StringBuilder compressed = new StringBuilder(finalLen);
        int countConsect = 0;

        for(int i = 0; i < s.length(); i++) {
            countConsect++;

            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                compressed.append(s.charAt(i));
                compressed.append(countConsect);
                countConsect = 0;
            }
        }
        return compressed.toString();
    }
    public static int countCompress(String s)
    {
        int compressLen = 0;
        int countCons = 0;

        for(int i = 0; i < s.length(); i++)
        {
            countCons++;

            //current char different than next, increase length
            if(i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1))
            {
                compressLen += 1 + String.valueOf(countCons).length();
                countCons = 0;
            }

        }
        return compressLen;

    }

}
