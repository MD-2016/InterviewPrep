package Chapter_8;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Permutations_With_Dups
{
    /*
     * My Approach up to printAllPermutations
     * Time: O(n * m) collectively where n is array size and m is permutations possible
     * Space: O(n)
     */
    static int factorial(int n)
    {
        int f = 1;
        for(int i = 1; i <= n; i++)
        {
            f = f * i;
        }
        return f;
    }

    static void print(char[] temp)
    {
        for(int i = 0; i < temp.length; i++)
        {
            System.out.print(temp[i]);
        }
        System.out.println();
    }

    static int calcTotalPerms(char[] temp, int n)
    {
        int f = factorial(n);
        HashMap<Character, Integer> hm = new HashMap<>();

        for(int i = 0; i < temp.length; i++)
        {
            if(hm.containsKey(temp[i]))
            {
                hm.put(temp[i], hm.get(temp[i]) + 1);
            }
            else
            {
                hm.put(temp[i], 1);
            }
        }

        for(Map.Entry e : hm.entrySet())
        {
            Integer x = (Integer) e.getValue();
            if(x > 1)
            {
                int dupTemp = factorial(x);
                f = f / dupTemp;
            }
        }
        return f;
    }

    static void nextPerm(char[] temp)
    {
        int i;
        for(i = temp.length - 1; i > 0; i--)
        {
            if(temp[i] > temp[i - 1])
            {
                break;
            }
        }

        int min = i;
        int j, x = temp[i - 1];
        for(j = i + 1; j < temp.length; j++)
        {
            if((temp[j] < temp[min]) && (temp[j] > x))
            {
                min = j;
            }
        }

        char tempToSwap;
        tempToSwap = temp[i - 1];
        temp[i - 1] = temp[min];
        temp[min] = tempToSwap;

        Arrays.sort(temp, i, temp.length);

        print(temp);
    }

    static void printAllPerms(String s)
    {
        char[] temp = s.toCharArray();
        Arrays.sort(temp);

        print(temp);

        int total = calcTotalPerms(temp, temp.length);
        for(int i = 1; i < total; i++)
        {
            nextPerm(temp);
        }
    }

    /*
     * Book Approach
     * Time: O(n * n!)
     * Space: O(n)
     */
    public static HashMap<Character, Integer> buildFreqTable(String s)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray())
        {
            if(!map.containsKey(c))
            {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        return map;
    }

    public static void printPerms(HashMap<Character, Integer> map, String prefix, int remain, ArrayList<String> res)
    {
        if(remain == 0)
        {
            res.add(prefix);
            return;
        }

        for(Character c: map.keySet())
        {
            int count = map.get(c);
            if(count > 0)
            {
                map.put(c, count - 1);
                printPerms(map, prefix + c, remain - 1, res);
                map.put(c, count);
            }
        }
    }

    public static ArrayList<String> printPerms(String s)
    {
        ArrayList<String> result = new ArrayList<>();
        HashMap<Character, Integer> map = buildFreqTable(s);
        printPerms(map, "", s.length(), result);
        return result;
    }



    public static void main(String[] args)
    {

    }
}
