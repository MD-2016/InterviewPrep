package Chapter_8;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Power_Set
{
    //Assumptions: Integers

    /*
     * Brute Force Approach
     * Time: O(n * 2^n)
     * Space: O(n * 2 ^ n)
     */

    public static void powerSet(int[] S, Deque<Integer> set, int n)
    {
        if(n == 0)
        {
            System.out.println(set);
            return;
        }

        set.addLast(S[n-1]);
        powerSet(S, set, n - 1);

        set.removeLast();
        powerSet(S, set, n - 1);
    }

    /*
     * Brute Force Book Approach
     * Time: O(n * 2^n)
     * Space: O(n * 2 ^ n)
     */
    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index)
    {
        ArrayList<ArrayList<Integer>> allsubsets;
        if(set.size() == index) //Base case - add empty set
        {
            allsubsets = new ArrayList<ArrayList<Integer>>();
            allsubsets.add(new ArrayList<Integer>());
        }
        else
        {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset : allsubsets)
            {
                ArrayList<Integer> newsubset = new ArrayList<>();
                newsubset.addAll(subset);
                newsubset.add(item);
                moresubsets.add(newsubset);
            }
            allsubsets.addAll(moresubsets);
        }
        return allsubsets;
    }

    /*
     * Binary Approach
     * Time: O(n * 2 ^ n)
     * Space: O(1)
     */
    public static <T> Set<Set<T>> binaryPowerSet(Set<T> set)
    {
       Set<Set<T>> powerSet = new HashSet<>();
       T[] a = (T[]) set.toArray();
       int n = a.length;

       int powerSetSize = (int) Math.pow(2, n);

       for(int i = 0; i < powerSetSize; i++)
       {
           String bin = Integer.toBinaryString(i);
           while(bin.length() < n)
           {
               bin = "0" + bin;
           }
           Set<T> temp = new HashSet<>();
           for(int j = 0; j < n; j++)
           {
               if(bin.charAt(j) == '1')
               {
                   temp.add(a[j]);
               }
           }
           powerSet.add(temp);
       }
       return powerSet;
    }

    /*
     * Book Combinatorics Approach
     * Time: O(n * 2^n)
     * Space: O(n * 2 ^ n)
     */
    public static ArrayList<Integer> convertIntToSet(int x, ArrayList<Integer> set) {
        ArrayList<Integer> subset = new ArrayList<Integer>();
        int index = 0;
        for (int k = x; k > 0; k >>= 1) {
            if ((k & 1) == 1) {
                subset.add(set.get(index));
            }
            index++;
        }
        return subset;
    }

    public static ArrayList<ArrayList<Integer>> getSubsets2(ArrayList<Integer> set) {
        ArrayList<ArrayList<Integer>> allsubsets = new ArrayList<ArrayList<Integer>>();
        int max = 1 << set.size(); /* Compute 2^n */
        for (int k = 0; k < max; k++) {
            ArrayList<Integer> subset = convertIntToSet(k, set);
            allsubsets.add(subset);
        }
        return allsubsets;
    }



}
