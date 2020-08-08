public class OneAway {

    public static void main(String[] args) {
        String str = "gfg";
        String str2 = "gf";
        String s1 = "";
        String s2 = "";
        System.out.println(oneEdit(str,str2));
        System.out.println(isOneEdit(str,str2));
        System.out.println(isOneEdit(s1,s2));
        System.out.println(oneEdit(s1,s2));
    }

    /*
     * Checks if absolute distance is not greater than 1, then iterates through the strings and checks for only one char difference
     * Time is O(n) for 2 n length strings but only one loop is being used and termination is quick if very different lengths (1)
     * Space is O(1) since we are only incrementing counters and a simple one pass.
     */

    public static boolean oneEdit(String s1, String s2)
    {
        if(Math.abs(s1.length() - s2.length()) > 1)
        {
            return false;
        }

        int count = 0;

        int i = 0, j = 0;

        while(i < s1.length() && j < s2.length())
        {
            if(s1.charAt(i) != s2.charAt(j))
            {
                if(count == 1)
                {
                    return false;
                }

                if(s1.length() > s2.length())
                {
                    i++;
                }
                else if(s1.length() < s2.length())
                {
                    j++;
                }

                else
                {
                    i++;
                    j++;
                }

                count++;

            }
            else
            {
                i++;
                j++;
            }
        }

        if(i < s1.length() || j < s2.length())
        {
            count++;
        }
        return count == 1;
    }

    /* Better Approach?
     * Time is O(n)
     * Space is O(1)
     */
    public static boolean isOneEdit(String s1, String s2)
    {
        int in1 = s1.length();
        int in2 = s2.length();

        if(Math.abs(in1 - in2) == 2 || s1.equals(s2))
        {
            return false;
        }
        else
        {
            for(int i = 0; i < Math.min(in1,in2); i++)
            {
                if(s1.charAt(i) != s2.charAt(i))
                {
                    if(in1 == in2)
                    {
                        return s1.substring(i + 1).equals(s2.substring(i + 1));
                    }
                    else if(in1 > in2)
                    {
                        return s1.substring(i + 1).equals(s2.substring(i));
                    }
                    else if(in1 < in2)
                    {
                        return s1.substring(i).equals(s2.substring(i + 1));
                    }
                }
            }
        }
        return Math.abs(in1 - in2) == 1;
    }

}