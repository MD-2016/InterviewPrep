import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckPermutation {

    public static void main(String[] args) {

        String a = "redrum";
        String b = "murder";
        //System.out.println(myCheckPerm(a,b));
        System.out.println(permMap(a,b));
        //freqIsPerm(a,b);

    }

    /*
     * O(1) Time for different length strings
     * O(N Log N) for different lengths and O(N) for sorting
     * Space is O(N)
     */

    public static boolean myCheckPerm(String main, String second) {
        int mainLen = main.length();
        int secondLen = second.length();

        if (mainLen != secondLen) {
            return false;
        }

        char[] mainCharArray = main.toCharArray();
        char[] secondCharArray = second.toCharArray();

        Arrays.sort(mainCharArray);
        Arrays.sort(secondCharArray);

        return Arrays.equals(mainCharArray, secondCharArray);
    }

    /*
     * Potentially better solution
     * Time O(N) Space(256) aka O(1)
     */
    public static boolean freqIsPerm(String str1, String str2)
    {
        if(str1.length() != str2.length())
        {
            return false;
            //System.out.println("Not Equal");
        }

        int[] freqCountStr1 = new int[256];
        int[] freqCountStr2 = new int[256];

        for(int i = 0; i < str1.length(); ++i)
        {
            int char1 = str1.charAt(i);
            int char2 = str2.charAt(i);
            ++freqCountStr1[char1];
            ++freqCountStr2[char2];
        }

        //System.out.println(Arrays.toString(freqCountStr1));
        //System.out.println(Arrays.toString(freqCountStr2));

        for(int j = 0; j < str1.length(); ++j)
        {
            if (freqCountStr1[str1.charAt(j)] != freqCountStr2[str1.charAt(j)])
            {
                return false;
                //System.out.println("Bad");
            }

        }
        return true;
        //System.out.println("Good");
    }

    public static boolean permMap(String s, String t)
    {
        HashMap<String,Integer> map = new HashMap<String, Integer>();
        int len1 = s.length();
        int len2 = t.length();

        if(len1 != len2)
        {
            return false;
        }

        for(int i = 0; i < s.length(); i++)
        {
            if(map.containsKey(String.valueOf(s.charAt(i))))
            {
                map.put(String.valueOf(s.charAt(i)), map.get(String.valueOf(s.charAt(i))) + 1);
            }
            else
            {
                map.put(String.valueOf(s.charAt(i)), 1);
            }
        }

        for(int i = 0; i < t.length(); i++)
        {
            if(map.containsKey(String.valueOf(t.charAt(i))))
            {
                map.put(String.valueOf(t.charAt(i)), map.get(String.valueOf(t.charAt(i))) - 1);
            }
            else
            {
                return false;
            }
        }

        for(Map.Entry<String, Integer> entry: map.entrySet())
        {
            if(entry.getValue() != 0)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Book Solution 1
     */
    public String sort(String s)
    {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    public boolean permutation(String s, String t)
    {
        if(s.length() != t.length())
        {
            return false;
        }

        return sort(s).equals(sort(t));
    }

    /**
     * Book Solution 2
     */
    boolean perm(String s, String t)
    {
        if(s.length() != t.length())
        {
            return false;
        }

        int[] letters = new int[128]; //Assumption

        char[] s_array = s.toCharArray();

        for(char c: s_array)
        {
            letters[c]++;
        }

        for(int i = 0; i < t.length(); i++)
        {
            int c = t.charAt(i);
            letters[c]--;
            if(letters[c] < 0)
            {
                return false;
            }
        }

        return true;
    }

}

