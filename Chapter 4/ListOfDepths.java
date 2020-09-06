import java.util.*;

public class ListOfDepths {

    /*
     * Support code
     */
    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    class ListNode {
        int data;
        ListNode next;
        public ListNode(int data)
        {
            this.data = data;
            this.next = null;
        }
    }

    private ArrayList<ListNode> nodeList = new ArrayList<>();

    public void levelOrder(Node root)
    {
        int h = height(root);
        for(int i = 1; i <= h; i++)
        {
            printLevel(root, i);
            System.out.println("");
        }
    }

    public void printLevel(Node root, int height)
    {
        if(root == null) return;
        if(height == 1) System.out.print(" " + root.data);
        else
        {
            printLevel(root.left, height-1);
            printLevel(root.right, height-1);

        }
    }

    /*
     * Method that meets problem requirements
     * Time: O(n)
     * Space: O(n)
     * Only testing this method
     */
    public void levelOrderQueue(Node root)
    {
        Queue queue = new LinkedList();
        int levelNodes = 0;
        if(root == null) return;
        queue.add(root);
        while(!queue.isEmpty())
        {
            levelNodes = queue.size();
            ListNode head = null;
            ListNode curr = null;
            while(levelNodes > 0)
            {
                Node node = (Node) queue.remove();
                ListNode listnode = new ListNode(node.data);
                if(head == null)
                {
                    head = listnode;
                    curr = listnode;
                }
                else
                {
                    curr.next = listnode;
                    curr = curr.next;
                }
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                levelNodes--;
            }
            nodeList.add(head);
        }
        display(nodeList);
    }

    //Support methods
    public void display(ArrayList<ListNode> al)
    {
        Iterator it = al.iterator();
        while(it.hasNext())
        {
            ListNode head = (ListNode)it.next();

            while(head != null)
            {
                System.out.print("->" + head.data);
                head = head.next;
            }
            System.out.println("");
        }
    }

    public int height(Node root)
    {
        if(root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /*
     * Book Approaches
     * Time: O(n)
     * Space: O(log n)
     */
    public static void createLevelLinkedList(Node root, ArrayList<LinkedList<Node>> lists, int level) {
        if (root == null) return;
        LinkedList<Node> list = null;
        if (lists.size() == level) { // Level not contained in list
            list = new LinkedList<Node>();
            /* Levels are always traversed in order. So, if this is the first time we've visited level i,
             * we must have seen levels 0 through i - 1. We can therefore safely add the level at the end. */
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    public static ArrayList<LinkedList<Node>> createLevelLinkedList(Node root) {
        ArrayList<LinkedList<Node>> lists = new ArrayList<LinkedList<Node>>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    public static void printResult(ArrayList<LinkedList<Node>> result){
        int depth = 0;
        for(LinkedList<Node> entry : result) {
            Iterator<Node> i = entry.listIterator();
            System.out.print("Link list at depth " + depth + ":");
            while(i.hasNext()){
                System.out.print(" " + ((Node)i.next()).data);
            }
            System.out.println();
            depth++;
        }
    }

    public static Node createTreeFromArray(int[] array) {
        if (array.length > 0) {
            Node root = new Node(array[0]);
            java.util.Queue<Node> queue = new java.util.LinkedList<Node>();
            queue.add(root);
            boolean done = false;
            int i = 1;
            while (!done) {
                Node r = (Node) queue.element();
                if (r.left == null) {
                    r.left = new Node(array[i]);
                    i++;
                    queue.add(r.left);
                } else if (r.right == null) {
                    r.right = new Node(array[i]);
                    i++;
                    queue.add(r.right);
                } else {
                    queue.remove();
                }
                if (i == array.length) {
                    done = true;
                }
            }
            return root;
        } else {
            return null;
        }
    }

    public static void main(String[] args)
    {


    }

}
