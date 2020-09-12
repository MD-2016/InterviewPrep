import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSTSequences {

    public static class Node{
        int value;
        Node left;
        Node right;
        public Node(int val)
        {
            this.value = val;
            left = null;
            right = null;
        }
    }

    public static class BST {
        Node root;

        //Support code
        public static void weaveList(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> res, LinkedList<Integer> prefix)
        {
            //One list is empty, add remainder to cloned prefix and store it.
            if(first.size() == 0 || second.size() == 0)
            {
                LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
                result.addAll(first);
                result.addAll(second);
                res.add(result);
                return;
            }

            //recurse with head of first added to prefix. Removing head will damage first list
            //so we need to put it back where it came from afterwards
            int firstHead = first.removeFirst();
            prefix.addLast(firstHead);
            weaveList(first, second, res, prefix);
            prefix.removeLast();
            first.addFirst(firstHead);

            //repeat above for second list
            int secondHead = second.removeFirst();
            prefix.addLast(secondHead);
            weaveList(first, second, res, prefix);
            prefix.removeLast();
            second.addFirst(secondHead);

        }


        /*
         * Function that gives the solution to the given problem
         * Time: O(
         */
        public static ArrayList<LinkedList<Integer>> bstSequences(Node node)
        {
            ArrayList<LinkedList<Integer>> res = new ArrayList<>();

            if(node == null)
            {
                res.add(new LinkedList<Integer>());
                return res;
            }

            LinkedList<Integer> prefix = new LinkedList<>();
            prefix.add(node.value);

            //recurse left and right subtrees
            ArrayList<LinkedList<Integer>> leftSeq = bstSequences(node.left);
            ArrayList<LinkedList<Integer>> rightSeq = bstSequences(node.right);

            for(LinkedList<Integer> left : leftSeq)
            {
                for(LinkedList<Integer> right : rightSeq)
                {
                    ArrayList<LinkedList<Integer>> weave = new ArrayList<>();
                    weaveList(left, right, weave, prefix);
                    res.addAll(weave);
                }
            }
            return res;

        }

    }

    public static void main(String[] args)
    {
       
    }

}
