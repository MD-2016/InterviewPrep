import java.util.Arrays;

public class URLify {

    public static void main(String[] args)
    {
        String s = "Hello World";
        String s2 = "Mr John Smith    ";
        String r = replace(s);
        String r2 = URLSpaceMaker(s2,13);
        System.out.println(r2);
    }

    /*
     * Assuming ascii characters
     * Time O(
     */

    public static String URLSpaceMaker(String s, int trueLen)
    {
        int spaceCount = 0;
        char[] s_array = s.toCharArray();
        int len = s_array.length;
        for(int i = 0; i < trueLen; i++)
        {
            if(s_array[i] == ' ')
            {
                spaceCount++;
            }
        }

        int pointer = trueLen + spaceCount * 2;

        if(trueLen < s_array.length)
        {
            s_array[trueLen] = '\0';
        }

        for(int i = trueLen - 1; i >= 0; i--) {
            if (s_array[i] == ' ') {
                s_array[pointer - 1] = '0';
                s_array[pointer - 2] = '2';
                s_array[pointer - 3] = '%';
                pointer = pointer - 3;
            } else {
                s_array[pointer - 1] = s_array[i];
                pointer--;
            }
        }
        String res = new String(s_array);

        return res;
    }

    /*
     * Solution is the simplest but doesnt meet full requirements
     */
    public static String replace(String s)
    {
        return s.replaceAll("[\\s]", "%20");
    }
}
