public class LinkedListLoopDetect {

    /*
     * Time O(n)
     * Space O(1)
     */
    public static LinkedListNode loopNodeFound(LinkedListNode n)
    {
        LinkedListNode fastPtr = n;
        LinkedListNode slowPtr = n;

        boolean loopFound = false;
        while(fastPtr != null && fastPtr.next != null)
        {
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if(slowPtr == fastPtr)
            {
                loopFound = true;
                break;
            }
        }

        if(loopFound)
        {
            slowPtr = n;
            while(slowPtr != fastPtr)
            {
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next;
            }
        }
        else
        {
            slowPtr = null;
        }
        return slowPtr;

    }

    /*
     * Book approach
     */

    public static LinkedListNode FindBeginning(LinkedListNode head) {
        LinkedListNode slow = head;
        LinkedListNode fast = head;

        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        // Error check - there is no meeting point, and therefore no loop
        if (fast == null || fast.next == null) {
            return null;
        }

		/* Move slow to Head. Keep fast at Meeting Point. Each are k steps
		/* from the Loop Start. If they move at the same pace, they must
		 * meet at Loop Start. */
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // Both now point to the start of the loop.
        return fast;
    }

    /*
     * Borrowed testing code
     */
   public static void main(String[] args)
   {
       int list_length = 100;
       int k = 10;

       // Create linked list
       LinkedListNode[] nodes = new LinkedListNode[list_length];
       for (int i = 0; i < list_length; i++) {
           nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
       }

       // Create loop;
       nodes[list_length - 1].next = nodes[list_length - k];

       LinkedListNode loop = loopNodeFound(nodes[0]);
       if (loop == null) {
           System.out.println("No Cycle.");
       } else {
           System.out.println(loop.data);
       }
   }

}
