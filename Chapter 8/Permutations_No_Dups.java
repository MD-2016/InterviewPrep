package Chapter_8;

import java.util.ArrayList;

public class Permutations_No_Dups
{

    /*
     * My Approach up to swap method
     * Time: O(n * n!)
     */
    static boolean shouldSwap(char str[], int start, int curr)
    {
        for(int i = start; i < curr; i++)
        {
            if(str[i] == str[curr])
            {
                return false;
            }
        }
        return true;
    }

    static void findPermutations(char str[], int index, int n)
    {
        if(index >= n)
        {
            System.out.println(str);
            return;
        }

        for(int i = index; i < n; i++)
        {
            boolean check = shouldSwap(str, index, i);
            if(check)
            {
                swap(str, index, i);
                findPermutations(str, index + 1, n);
                swap(str, index, i);
            }
        }
    }

    static void swap(char[] str, int i, int j)
    {
        char c = str[i];
        str[i] = str[j];
        str[j] = c;
    }

    /*
     * Book Approaches
     * Time: O(n * n!)
     */
    public static ArrayList<String> getPerms(String str)
    {
        if(str == null)
        {
            return null;
        }

        ArrayList<String> permutations = new ArrayList<>();
        if(str.length() == 0)
        {
            permutations.add("");
            return permutations;
        }

        char first = str.charAt(0);
        String remainder = str.substring(1);
        ArrayList<String> words = getPerms(remainder);
        for(String word : words)
        {
            for(int j = 0; j <= word.length(); j++)
            {
                String s = insertCharAt(word, first, j);
                permutations.add(s);
            }
        }

        return permutations;
    }

    public static String insertCharAt(String word, char c, int i)
    {
        String start = word.substring(0, i);
        String end = word.substring(i);
        return start + c + end;
    }

    /*
     * Book Approach 2
     * Time: O(n * n!)
     */
    public static ArrayList<String> getPerms2(String rem)
    {
        int len = rem.length();
        ArrayList<String> result = new ArrayList<>();

        if(len == 0)
        {
            result.add("");
            return result;
        }

        for(int i = 0; i < len; i++)
        {
            String before = rem.substring(0, i);
            String after = rem.substring(i + 1, len);
            ArrayList<String> partials = getPerms2(before + after);

            for(String s : partials)
            {
                result.add(rem.charAt(i) + s);
            }

        }

        return result;
    }

    /*
     *   Book Approach 3
     */
    public static void getPerms3(String prefix, String rem, ArrayList<String> res)
    {
        if(rem.length() == 0)
        {
            res.add(prefix);
        }
        int len = rem.length();

        for(int i = 0; i < len; i++)
        {
            String before = rem.substring(0, i);
            String after = rem.substring(i + 1, len);
            char c = rem.charAt(i);
            getPerms3(prefix + c, before + after, res);
        }
    }

    public static ArrayList<String> getPerms3(String str)
    {
        ArrayList<String> res = new ArrayList<>();
        getPerms3("", str, res);
        return res;
    }

    public static void main(String[] args)
    {

    }
}
