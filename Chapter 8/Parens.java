package Chapter_8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Parens
{
    /*
     * My Approach
     * Time: O(2^n)
     * Space: O(n)
     */
    static void printParen(char[] str, int pos, int n, int open, int close)
    {
        if(close == n)
        {
            for(int i = 0; i < str.length; i++)
            {
                System.out.print(str[i]);
            }
            System.out.println();
            return;
        }
        else
        {
            if(open > close)
            {
                str[pos] = '}';
                printParen(str, pos + 1, n, open, close + 1);
            }
            if(open < n)
            {
                str[pos] = '{';
                printParen(str, pos + 1, n, open + 1, close);
            }
        }
    }

    static void printParentehsis(char[] str, int n)
    {
        if(n > 0)
        {
            printParen(str, 0, n, 0, 0);
            return;
        }
    }

    /*
     * Book Approach 1
     * Time: O(2^n)
     * Space: O(n)
     */
    public static String insertInside(String str, int leftIndex)
    {
        String left = str.substring(0, leftIndex + 1);
        String right = str.substring(leftIndex + 1, str.length());
        return left + "()" + right;
    }

    public static Set<String> generateParens(int remaining)
    {
        Set<String> set = new HashSet<>();
        if(remaining == 0)
        {
            set.add("");
        }
        else
        {
            Set<String> prev = generateParens(remaining - 1);
            for(String str : prev)
            {
                for(int i = 0; i < str.length(); i++)
                {
                    if(str.charAt(i) == '(')
                    {
                        String s = insertInside(str, i);
                        set.add(s);
                    }
                }
                set.add("()" + str);
            }
        }
        return set;
    }

    /*
     * Book Approach 2
     * Time: O(log n)
     * Space: O(n)
     */
    public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        if (leftRem < 0 || rightRem < leftRem) //invalid state
        {
            return;
        }

        if(leftRem == 0 && rightRem == 0) //all our of left and right parens
        {
            list.add(String.copyValueOf(str));
        }
        else
        {
            str[index] = '('; //add left and recurse
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')'; // add right and recurse
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

    public static ArrayList<String> generateParens2(int count)
    {
        char[] str = new char[count * 2];
        ArrayList<String> list = new ArrayList<>();
        addParen(list, count, count, str, 0);
        return list;
    }

}
