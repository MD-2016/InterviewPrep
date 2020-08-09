public class ReturnKthToLastElement {

    public static void main(String[] args)
    {
        int[] array = {0,1,2,3};
        LinkedListNode head = createLinkedListFromArray(array);
        for(int i = 0; i <= array.length + 1; i++)
        {
            LinkedListNode node = kthToLast(head, i);
            String nodeVal = node == null ? "null" : "" + node.data;
            System.out.println(i + ": " + nodeVal);
        }
    }

    public static LinkedListNode createLinkedListFromArray(int[] values)
    {
        LinkedListNode head = new LinkedListNode(values[0], null, null);
        LinkedListNode current = head;
        for(int i = 1; i < values.length; i++)
        {
            current = new LinkedListNode(values[i], null, current);
        }
        return head;
    }
    
    //wrapper class
    public static class Index {
        public int val = 0;
    }

    public static LinkedListNode kthToLast(LinkedListNode head, int kth)
    {
        Index dex = new Index();
        return kthToLast(head, kth, dex);
    }

    /*
     * Time complexity is O(n)
     * Space complexity is O(n) for the recursive calls
     */
    public static LinkedListNode kthToLast(LinkedListNode head, int kth, Index dex)
    {
        if(head == null)
        {
            return null;
        }

        LinkedListNode current = kthToLast(head.next, kth, dex);
        dex.val = dex.val + 1;
        if(dex.val == kth)
        {
            return head;
        }
        return current;

    }

    /*
     * Book Iterative solution is more optimal
     * Time is O(n)
     * Space is O(1)
     */
    public static LinkedListNode nthToLast(LinkedListNode head, int k)
    {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        //Move p1 k nodes into list
        for(int i = 0; i < k; i++)
        {
            if(p1 == null)
            {
                return null;
            }
            p1 = p1.next;
        }

        //Move them at same pace when p1 hits end, p2 will be at the right
        while(p1 != null)
        {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

}
