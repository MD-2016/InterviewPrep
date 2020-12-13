package Chapter_10;

import java.util.*;

public class GroupAnagrams
{


    /*
     * My Solution
     * Time: O(nm log m + mn log n) where n is words and m is the maximum number of characters for each n
     */
    private static void groupAnagrams(String arr[])
    {
        HashMap<String, List<String>> map = new HashMap<>();

        for(int i = 0; i < arr.length; i++)
        {
            String word = arr[i];
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String newWord = new String(letters);

            if(map.containsKey(newWord))
            {
                map.get(newWord).add(word);
            }
            else
            {
                List<String> words = new ArrayList<>();
                words.add(word);
                map.put(newWord,words);
            }
        }

        for(String s : map.keySet())
        {
            List<String> vals = map.get(s);
            if(vals.size() > 1)
            {
                System.out.print(vals);
            }
        }

    }



    /*
     * Book Solution
     * Time: O(n log n)
     */
    public static void sort(String[] array) {
        HashMapList<String, String> mapList = new HashMapList<String, String>();

        /* Group words by anagram */
        for (String s : array) {
            String key = sortChars(s);
            mapList.put(key, s);
        }

        /* Convert hash table to array */
        int index = 0;
        for (String key : mapList.keySet()) {
            ArrayList<String> list = mapList.get(key);
            for (String t : list) {
                array[index] = t;
                index++;
            }
        }
    }

    public static String sortChars(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    //Support code
    public static String stringArrayToString(String[] array) {
        StringBuilder sb = new StringBuilder();
        for (String v : array) {
            sb.append(v + ", ");
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        groupAnagrams(array);
        sort(array);
        System.out.println(stringArrayToString(array));
    }
}
