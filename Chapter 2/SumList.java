public class SumList {

    /*
     *  Time O(m + n)
     * Space O(m + n)
     */

    public static LinkedListNode sumLinkedList(LinkedListNode list1, LinkedListNode list2, int carry)
    {
        if(list1 == null && list2 == null && carry == 0)
        {
            return null;
        }

        LinkedListNode res = new LinkedListNode();
        int val = carry;
        if(list1 != null)
        {
            val += list1.data;
        }
        if(list2 != null)
        {
            val += list2.data;
        }

        res.data = val % 10;

        if(list1 != null || list2 == null)
        {
            LinkedListNode otherNodes = sumLinkedList(list1 == null ? null : list1.next, list2 == null ? null : list2.next, val >= 10 ? 1 : 0);
            res.setNext(otherNodes);
        }
        return res;
    }

    /*
     * Solving the follow up
     * Book solution
     * Time O(m+n)
     * Space O(m+n)
     */

    //partial sum wrapper class
    public static class PartialSum {
        public LinkedListNode sum = null;
        public int carry = 0;
    }

    private static int length(LinkedListNode l)
    {
        if(l == null)
        {
            return 0;
        }
        else {
            return 1 + length(l.next);
        }
    }

    private static PartialSum listHelper(LinkedListNode l1, LinkedListNode l2)
    {
        if(l1 == null && l2 == null)
        {
            PartialSum sum = new PartialSum();
            return sum;
        }
        PartialSum sum = listHelper(l1.next, l2.next);
        int val = sum.carry + l1.data + l2.data;
        LinkedListNode fullRes = insertBefore(sum.sum, val % 10);
        sum.sum = fullRes;
        sum.carry = val / 10;
        return sum;
    }

    private static LinkedListNode padList(LinkedListNode l, int padding) {
        LinkedListNode head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    private static LinkedListNode insertBefore(LinkedListNode list, int data) {
        LinkedListNode node = new LinkedListNode(data);
        if (list != null) {
            node.next = list;
        }
        return node;
    }

    static LinkedListNode addLists(LinkedListNode listOne, LinkedListNode listTwo)
    {
        int lenListOne = length(listOne);
        int lenListTwo = length(listTwo);

        if(lenListOne < lenListTwo)
        {
            listOne = padList(listOne, lenListTwo - lenListOne);

        }
        else {
            listTwo = padList(listTwo, lenListOne - lenListTwo);
        }
        PartialSum sum = listHelper(listOne, listTwo);
        if(sum.carry == 0)
        {
            return sum.sum;
        }
        else {
            LinkedListNode res = insertBefore(sum.sum, sum.carry);
            return res;
        }
    }

    public static int linkedListToInt(LinkedListNode node) {
        int value = 0;
        while (node != null) {
            value = value * 10 + node.data;
            node = node.next;
        }
        return value;
    }

    public static void main(String[] args)
    {
        LinkedListNode lA1 = new LinkedListNode(9, null, null);
        LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
        LinkedListNode lA3 = new LinkedListNode(9, null, lA2);

        LinkedListNode lB1 = new LinkedListNode(1, null, null);
        LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
        LinkedListNode lB3 = new LinkedListNode(0, null, lB2);

        LinkedListNode l3 = sumLinkedList(lA1, lB1, 0);

        System.out.println("  " + lA1.printForward());
        System.out.println("+ " + lB1.printForward());
        System.out.println("= " + l3.printForward());

        //book tests
        LinkedListNode l4 = addLists(lA1, lB1);
        System.out.println(" " + lA1.printForward());
        System.out.println(" " + lB1.printForward());

        int linkedInt1 = linkedListToInt(lA1);
        int linkedInt2 = linkedListToInt(lB1);
        int linkedRes = linkedListToInt(l4);

        System.out.println(linkedInt1 + " + " + linkedInt2 + " = " + linkedRes + "\n");
        System.out.println(linkedInt1 + " + " + linkedInt2 + " = " + (linkedInt1 + linkedInt2));
    }
}
