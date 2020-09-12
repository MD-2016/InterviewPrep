public class FirstCommonAncestor {

    public static class Node {
        int data;
        Node left, right;

        public Node(int val)
        {
            data = val;
            left = right = null;
        }
    }

    public static class BST{

        Node root;

        /*
         * This is the main code for completing the problem
         * This code will be tested
         * Time: O(n)
         * Space: O(h) for height of tree
         */

        Node findFirst(int n1, int n2)
        {
            return findFirst(root, n1,n2);
        }

        Node findFirst(Node node, int n1, int n2)
        {
            //base
            if(node == null)
            {
                return null;
            }

            //n1 or n2 matches root key
            //report the presence by returning root
            //if key is ancestor of other, then ancestor becomes first
            if(node.data == n1 || node.data == n2)
            {
                return node;
            }

            //check both subtrees for keys
            Node leftFirstAncestor = findFirst(node.left,n1,n2);
            Node rightFirstAncestor = findFirst(node.right, n1, n2);

            //if both above return non null then one key is present in one subtree and other is in other tree
            //this is first
            if(leftFirstAncestor != null && rightFirstAncestor != null)
            {
                return node;
            }

            //otherwise check if left or right subtree is first ancestor
            return (leftFirstAncestor != null) ? leftFirstAncestor : rightFirstAncestor;

        }

    }

    /*
     * Book approach
     */

     //support class
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

     /*
      * Book Approach Function for solution
      * Time: O(n)
      * Space: O(n)
      */
    public static TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((p == null) || (q == null)) {
            return null;
        }

        TreeNode ap = p.parent;
        while (ap != null) {
            TreeNode aq = q.parent;
            while (aq != null) {
                if (aq == ap) {
                    return aq;
                }
                aq = aq.parent;
            }
            ap = ap.parent;
        }
        return null;
    }


    public static void main(String[] args)
    {

    }


}
