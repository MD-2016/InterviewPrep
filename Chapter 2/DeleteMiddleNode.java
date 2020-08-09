public class DeleteMiddleNode {

    public static void main(String[] args)
    {
        LinkedListNode head = randomLinkedList(10,0,10);
        System.out.println(head.printForward());
        deleteMiddleNode(head.next.next.next.next); // delete node 4
        System.out.println(head.printForward());

        System.out.println();
        System.out.println();

        LinkedListNode test = randomLinkedList(10,0,10);
        System.out.println(test.printForward());
        deleteNode(test.next.next.next.next); // delete node 4
        System.out.println(test.printForward());

    }

    //borrowed driver code for linked list
    public static LinkedListNode randomLinkedList(int N, int min, int max) {
        LinkedListNode root = new LinkedListNode(randomIntInRange(min, max),
                null, null);
        LinkedListNode prev = root;
        for (int i = 1; i < N; i++) {
            int data = randomIntInRange(min, max);
            LinkedListNode next = new LinkedListNode(data, null, null);
            prev.setNext(next);
            prev = next;
        }
        return root;
    }

    public static int randomIntInRange(int min, int max) {
        return randomInt(max + 1 - min) + min;
    }

    public static int randomInt(int n) {
        return (int) (Math.random() * n);
    }


    /*
     * Deletes middle node and returns true if deleted or false otherwise
     * Time is O(n) for only one traversal of the list
     * Space is O(1) since no extra space is needed
     * We are not given access to head of linked list only access to the node
     */
    public static boolean deleteMiddleNode(LinkedListNode n)
    {
        //check common base cases
        if(n == null || n.next == null)
        {
            return false;
        }

        //initialize slow and fast pointers
        //to reach middle of linked list
        LinkedListNode pointerS = n;
        LinkedListNode pointerF = n;

        LinkedListNode previous = null;

        //store previous slow pointer
        while(pointerF != null && pointerF.next != null)
        {
            pointerF = pointerF.next.next;
            previous = pointerS;
            pointerS = pointerS.next;
        }

        //delete middle node
        previous.next = pointerS.next;


        return true;

        
    }

    /*
     * Book Solution
     * Time is O(n)
     * Space is O(1)
     */
    public static boolean deleteNode(LinkedListNode n)
    {
        if(n == null || n.next == null)
        {
            return false;
        }
        LinkedListNode next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;
    }

}
