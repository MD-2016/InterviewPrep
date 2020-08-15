public class LinkedListIntersection{

    /*
     * Time O(n + m)
     * Space O(1)
     */
    public static LinkedListNode intersectionNode(LinkedListNode l1, LinkedListNode l2)
    {
        if(l1 == null || l2 == null)
        {
            return null;
        }

        LinkedListNode smallPtr = l1;
        LinkedListNode largePtr = l2;

        while(smallPtr != largePtr)
        {
            if(smallPtr == null)
            {
                smallPtr = l2;
            }
            else
            {
                smallPtr = smallPtr.next;
            }

            if(largePtr == null)
            {
                largePtr = l1;
            }
            else
            {
                largePtr = largePtr.next;
            }

        }

        return smallPtr;

    }

    /*
     * Helper method to create test cases
     */

    public static LinkedListNode createLinkedListFromArray(int[] vals) {
        LinkedListNode head = new LinkedListNode(vals[0], null, null);
        LinkedListNode current = head;
        for (int i = 1; i < vals.length; i++) {
            current = new LinkedListNode(vals[i], null, current);
        }
        return head;
    }

    /*
     * Book idea
     * Time O(A + B)
     * Space O(1)
     */
    public static class Res {
        public LinkedListNode tail;
        public int size;
        public Res(LinkedListNode tail, int size)
        {
            this.tail = tail;
            this.size = size;
        }
    }

    public static Res getTailandSize(LinkedListNode l1)
    {
        if(l1 == null)
        {
            return null;
        }
        int size = 1;
        LinkedListNode curr = l1;
        while(curr.next != null)
        {
            size++;
            curr = curr.next;
        }
        return new Res(curr, size);
    }

    public static LinkedListNode getKthNode(LinkedListNode head, int k)
    {
        LinkedListNode curr = head;
        while(k > 0 && curr != null)
        {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    public static LinkedListNode findIntersect(LinkedListNode l1, LinkedListNode l2)
    {
        //check for null lists
        if(l1 == null || l2 == null)
        {
            return null;
        }

        //get tail and size
        Res r1 = getTailandSize(l1);
        Res r2 = getTailandSize(l2);

        //different tail nodes, no intersection
        if(r1.tail != r2.tail)
        {
            return null;
        }

        //set pointers to start of each list
        LinkedListNode shortL = r1.size < r2.size ? l1 : l2;
        LinkedListNode longL = r1.size < r2.size ? l2 : l1;

        //advance pointer for longer list by difference in lengths
        longL = getKthNode(longL, Math.abs(r1.size - r2.size));

        //move both pointers til collision
        while(shortL != longL)
        {
            shortL = shortL.next;
            longL = longL.next;
        }

        //Return either one
        return longL;


    }

    public static void main(String[] args)
    {
        /* Create linked list */
        int[] vals = {-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        LinkedListNode list1 = createLinkedListFromArray(vals);

        int[] vals2 = {12, 14, 15};
        LinkedListNode list2 = createLinkedListFromArray(vals2);

        list2.next.next = list1.next.next.next.next;

        System.out.println(list1.printForward());
        System.out.println(list2.printForward());


        LinkedListNode intersection = findIntersect(list1, list2);

        System.out.println(intersection.printForward());

        //book
        System.out.println("Book approach below\n\n");

        /* Create linked list */
        int[] vals3 = {-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        LinkedListNode l1 = createLinkedListFromArray(vals);

        int[] vals4 = {12, 14, 15};
        LinkedListNode l2 = createLinkedListFromArray(vals2);

        l2.next.next = l1.next.next.next.next;

        System.out.println(l1.printForward());
        System.out.println(l2.printForward());


        LinkedListNode intersect = intersectionNode(l1, l2);

        System.out.println(intersect.printForward());

    }


}
