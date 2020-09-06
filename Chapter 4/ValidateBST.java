public class ValidateBST {

    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data)
        {
            this.data = data;
            left = null;
            right = null;
        }
    }

    /*
     * For both isBinarySearchTree functions are being tested
     * Time: O(n)
     * Space: O(n)
     */
    static boolean isBinarySearchTreeUtil(Node root, int prev)
    {
        if(root != null)
        {
            if(!isBinarySearchTreeUtil(root.left,prev))
            {
                return false;
            }
            if(root.data <= prev)
            {
                return false;
            }

            prev = root.data;

            return isBinarySearchTreeUtil(root.right, prev);
        }
        return true;
    }

    static boolean isBinarySearchTree(Node root)
    {
        int prev = Integer.MIN_VALUE;
        return isBinarySearchTreeUtil(root,prev);
    }

    /*
     * Book Approach
     * Time: O(n)
     * Space: O(log n)
     */
    public static boolean checkBST(TreeNode n, Integer min, Integer max)
    {
        if(n == null)
        {
            return true;
        }

        if((min != null && n.data <= min) || (max != null && n.data > max))
        {
            return false;
        }

        if(!checkBST(n.left, min, n.data) || !checkBST(n.right, n.data, max))
        {
            return false;
        }
        return true;
    }

    public static boolean checkBST(TreeNode n)
    {
        return checkBST(n, null, null);
    }

    public static class TreeNode {
        public int data;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;
        private int size = 0;

        public TreeNode(int d) {
            data = d;
            size = 1;
        }

        private void setLeftChild(TreeNode left) {
            this.left = left;
            if (left != null) {
                left.parent = this;
            }
        }

        private void setRightChild(TreeNode right) {
            this.right = right;
            if (right != null) {
                right.parent = this;
            }
        }

        public void insertInOrder(int d) {
            if (d <= data) {
                if (left == null) {
                    setLeftChild(new TreeNode(d));
                } else {
                    left.insertInOrder(d);
                }
            } else {
                if (right == null) {
                    setRightChild(new TreeNode(d));
                } else {
                    right.insertInOrder(d);
                }
            }
            size++;
        }

        public int size() {
            return size;
        }

        public boolean isBST() {
            if (left != null) {
                if (data < left.data || !left.isBST()) {
                    return false;
                }
            }

            if (right != null) {
                if (data >= right.data || !right.isBST()) {
                    return false;
                }
            }

            return true;
        }

        public int height() {
            int leftHeight = left != null ? left.height() : 0;
            int rightHeight = right != null ? right.height() : 0;
            return 1 + Math.max(leftHeight, rightHeight);
        }

        public TreeNode find(int d) {
            if (d == data) {
                return this;
            } else if (d <= data) {
                return left != null ? left.find(d) : null;
            } else if (d > data) {
                return right != null ? right.find(d) : null;
            }
            return null;
        }

        private static TreeNode createMinimalBST(int arr[], int start, int end) {
            if (end < start) {
                return null;
            }
            int mid = (start + end) / 2;
            TreeNode n = new TreeNode(arr[mid]);
            n.setLeftChild(createMinimalBST(arr, start, mid - 1));
            n.setRightChild(createMinimalBST(arr, mid + 1, end));
            return n;
        }

        public static TreeNode createMinimalBST(int[] array) {
            return createMinimalBST(array, 0, array.length - 1);
        }
    }

        public static void main(String[] args)
    {
        Node root = new Node(5);
        root.left = new Node(2);
        root.right = new Node(15);
        root.left.left = new Node(1);
        root.left.right = new Node(4);

        if(isBinarySearchTree(root))
        {
            System.out.println("Is Binary Search Tree");
        }
        else
        {
            System.out.println("Not a Binary Search Tree");
        }

        //book test
        /* Simple test -- create one */
        int[] array = {Integer.MIN_VALUE, 3, 5, 6, 10, 13, 15, Integer.MAX_VALUE};
        TreeNode node = TreeNode.createMinimalBST(array);
        //node.left.data = 6; // "ruin" the BST property by changing one of the elements
        //node.print();
        boolean isBst = checkBST(node);
        System.out.println(isBst);

    }
}
