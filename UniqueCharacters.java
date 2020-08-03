public class UniqueCharacters {

    public static void main(String[] args)
    {
        String test = "hello";
        String test2 = "ok";
        String test3 = "kk";
        System.out.println(isUnique(test));
        System.out.println(isUnique(test2));
        System.out.println(isUnique(test3));
        System.out.println(myIsUnique(test));
        System.out.println(myIsUnique(test2));
        System.out.println(myIsUnique(test3));
        System.out.println(betterIsUnique(test));
        System.out.println(betterIsUnique(test2));
        System.out.println(betterIsUnique(test3));
    }

    /**
     * My solution below is O(n^2)
     */
    public static boolean myIsUnique(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            for(int j = i + 1; j < str.length(); j++)
            {
                if(str.charAt(i) == str.charAt(j))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * compare each character and see which is unique
     * Time is O(n) and Space is O(1)
     */

    public static boolean isUnique(String str)
    {
        if(str.length() > 128) return false;

        boolean[] charFlag = new boolean[128];
        for(int i = 0; i < str.length(); i++)
        {
            int val = str.charAt(i);
            if(charFlag[val])
            {
                return false;
            }
            charFlag[val] = true;
        }
        return true;

    }

    /*
     * runtime here is Time O(n) and Space O(1).
     * Could argue that time is constant due to a fixed array
     * Ask if unicode or ascii
     * Is there a better solution? Yes, using bit vector
     */

    public static boolean betterIsUnique(String str)
    {
        int check = 0;
        for(int i = 0; i < str.length(); i++)
        {
            int val = str.charAt(i) - 'a';
            if((check & (1 << val)) > 0)
            {
                return false;
            }
            check |= (1 << val);
        }
        return true;
    }

    /*
     * The above code assumes that we only handle lowercase characters.
     * Reduces runtime by factor of 8.
     */

}
