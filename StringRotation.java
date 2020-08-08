import java.util.Arrays;

public class StringRotation {

    public static void main(String[] args)
    {
        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
        for(String[] pair: pairs)
        {
            String w1 = pair[0];
            String w2 = pair[1];
            boolean is_rotation = isRotated(w1,w2);
            boolean isrotation = isRotation(w1,w2);
            System.out.println(w1 + ", " + w2 + ": " + is_rotation);
            System.out.println(w1 + ", " + w2 + ": " + isrotation);
        }
    }

    /*
     * My solution
     * Time is O(s+t) for is Substring and O(n) for isRotated
     * Space is O(n)
     */
    public static boolean isRotated(String s, String t)
    {
        int len = s.length();
        //check for equal length and not empty
        if(len == t.length() && len > 0)
        {
            String st = s + t;
            return isSubstring(st, t);
        }
        return false;
    }

    //Assumption that complexity is O(big+small)
    public static boolean isSubstring(String big, String small)
    {
        if(big.indexOf(small) >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    /*
     * Best solution?
     * Time is O(n)
     * Space is O(n)
     */
    public static boolean isRotation(String s, String t)
    {
        if(s.length() != t.length())
        {
            return false;
        }

        if(s.length() == 0)
        {
            return true;
        }

        //build shift table
        int len = s.length();
        int[] shift = new int[len + 1];
        Arrays.fill(shift, 1);
        int left = -1;

        for(int right = 0; right < len; ++right)
        {
            while(left >= 0 && (t.charAt(left) != t.charAt(right)))
            {
                left -= shift[left];
                shift[right + 1] = right - left++;
            }
        }

        //find match of t in S+S
        int match = 0;
        for(char c: (s+s).toCharArray())
        {
            while(match >= 0 && t.charAt(match) != c)
            {
                match -= shift[match];
                if(++match == len) return true;
            }
        }
        return false;
    }

}
