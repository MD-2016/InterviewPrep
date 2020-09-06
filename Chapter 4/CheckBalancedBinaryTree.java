public class CheckBalancedBinaryTree {

    //Support Class
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

    //wrapper class to modify height recursively
    static class Height {
        int height = 0;
    }

    /*
     * Testing Only isBalanced
     * Time: O(n)
     * Space: O(h)
     */
    static class BST {
        Node root;

        //return true if bst with root is height balanced
        boolean isBalanced(Node root, Height height)
        {
            //tree is empty
            if(root == null)
            {
                height.height = 0;
                return true;
            }

            //get heights of left and right sub tree
            Height leftHeight = new Height(), rightHeight = new Height();
            boolean leftBalance = isBalanced(root.left, leftHeight);
            boolean rightBalance = isBalanced(root.right, rightHeight);
            int lh = leftHeight.height, rh = rightHeight.height;

            //height of current node is max of heights of left and right subtrees plus 1
            height.height = (lh > rh ? lh : rh) + 1;

            //if difference between heights of left and right subtrees is more than 2 then tree is unbalanced
            if(Math.abs(lh-rh) >= 2)
            {
                return false;
            }

            //if left and right subtree are balanced then return true
            else
            {
                return leftBalance && rightBalance;
            }

        }
    }

    /*
     * Book Approach
     * Time: O(n)
     * Space: O(h)
     */
    static int checkHeight(TreeNode root)
    {
        if(root == null)
        {
            return -1;
        }

        int leftHeight = checkHeight(root.left);
        if(leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int rightHeight = checkHeight(root.right);
        if(rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int heightDiff = leftHeight - rightHeight;
        if(Math.abs(heightDiff) > 1)
        {
            return Integer.MIN_VALUE;
        }
        else
        {
            return Math.max(leftHeight, rightHeight) + 1;
        }

    }

    static boolean isBalanced(TreeNode root)
    {
        return checkHeight(root) != Integer.MIN_VALUE;
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

        private static TreeNode createMinimalBST(int arr[], int start, int end){
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
    }

}
