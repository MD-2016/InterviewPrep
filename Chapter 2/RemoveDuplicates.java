import java.util.HashSet;
//assumption: unsorted list
public class RemoveDuplicates {


    public static void main(String[] args)
    {
        LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
        LinkedListNode head = first;
        LinkedListNode second = first;
        for (int i = 1; i < 8; i++) {
            second = new LinkedListNode(i % 2, null, null);
            first.setNext(second);
            second.setPrevious(first);
            first = second;
        }
        System.out.println(head.printForward());

        LinkedListNode cloneA = head.clone();
        LinkedListNode cloneB = head.clone();
        LinkedListNode cloneC = head.clone();
        removeDup(cloneA);
        removeDup(cloneB);
        removeDup(cloneC);

        System.out.println(cloneA.printForward());
        System.out.println(cloneB.printForward());
        System.out.println(cloneC.printForward());

    }

    /*
     * Time complexity is O(n) on average assuming hash access time is O(1) on average
     * Space is O(n) for n elements in linked list and size of the hashset can grow
     */
    public static void removeDup(LinkedListNode n)
    {
        HashSet<Integer> set = new HashSet<>();

        LinkedListNode current = n;
        LinkedListNode prev = null;

        while(current != null)
        {
            int curval = current.data;

            if(set.contains(curval))
            {
                prev.next = current.next;
            }
            else
            {
                set.add(curval);
                prev = current;
            }
            current = current.next;
        }

    }

    /*
     * Book solution
     * Time is O(n)
     * Space is O(n) for n elements in linked list and size of the hashset can grow
     * Compared to my solution just uses less variables.
     */
    public static void deleteDups(LinkedListNode n)
    {
        HashSet<Integer> set = new HashSet<>();
        LinkedListNode previous = null;
        while(n != null)
        {
            if(set.contains(n.data))
            {
                previous.next = n.next;
            }
            else
            {
                set.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    /*
     * No buffer solution
     * Time is O(n^2) from the two loops and checking all nodes for duplicates with two pointers.
     * Space is O(1)
     */
    public static void deleteDupsNoBuff(LinkedListNode head)
    {
        LinkedListNode current = head;
        while(current != null)
        {
            LinkedListNode runner = current;
            while(runner.next != null)
            {
                if(runner.next.data == current.data)
                {
                    runner.next = runner.next.next;
                }
                else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }

        /*
         * Other possible solution is to sort the list using merge sort O(n log n) time then removing duplicates
         * This would result in O(n) for together total time is O(n log n)
         */
    }

}
