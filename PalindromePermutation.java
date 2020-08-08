import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PalindromePermutation {

    public static void main(String[] args)
    {
        String test = "racecar";
        String test2 = "poop";
        String test3 = "cool";

        boolean res = checkPalindromePerm(test);
        boolean res2 = checkPalindromePerm2(test);
        boolean res3 = checkPalindromePerm(test2);
        boolean res4 = checkPalindromePerm2(test2);
        boolean res5 = checkPalindromePerm(test3);
        boolean res6 = checkPalindromePerm2(test3);
        System.out.println("Result one is " + res);
        System.out.println("Result two is " + res2);
        System.out.println("Result three is " + res3);
        System.out.println("Result four is " + res4);
        System.out.println("Result five is " + res5);
        System.out.println("Result six is " + res6);
    }

    /*
     * A more brute force like approach using arrays to check if a palindrome permutation exists in the current string.
     * Comes out as Time O(n) and Space O(1) using Arrays
     */

    public static boolean checkPalindromePerm(String str)
    {
        int[] charCount = new int[128];
        int count = 0;
        for(int i = 0; i < str.length(); i++)
        {
            charCount[str.charAt(i)]++;
        }

        for(int i = 0; i < 128; i++)
        {
            count += charCount[i] % 2;
        }

        return count <= 1;
    }

    /*
     * Using a hashmap to map counts of each character.
     * Time O(n) because of growing map size of n from the n size string
     * Space O(n) because the map can grow to a size of n based on the n size string.
     */

    public static boolean checkPalindromePerm2(String str)
    {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < str.length(); i++)
        {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }
        int count = 0;
        for(char k: map.keySet())
        {
            count += map.get(k) % 2;
        }

        return count <= 1;
    }

    /*
     * Uses a Set which when number of occurrences of a character are odd, then we add it.
     * If the occurrence is even, then it is removed.
     * Time is O(n) because of the traversal of the string of length n
     * Space is O(n) because the set can grow up to a size of n with distinct characters
     */

    public static boolean checkPalindromePerm3(String str)
    {
        Set<Character> charSet = new HashSet<>();
        for(int i = 0; i < str.length(); i++)
        {
            if(!charSet.add(str.charAt(i)))
            {
                charSet.remove(str.charAt(i));
            }
        }

        return charSet.size() <= 1;
    }

}
