
//Node support class
class Node{
    int data;
    Node left, right, parent;
    public Node(int d)
    {
        data = d;
        left = right = parent = null;
    }
}

public class BSTSuccessor {


    static class BST {
        static Node head;

        Node insert(Node node, int data) {
            //tree empty, return single node
            if (node == null) {
                return (new Node(data));
            } else {
                Node temp = null;

                //go down the tree recursively
                if (data <= node.data) {
                    temp = insert(node.left, data);
                    node.left = temp;
                    temp.parent = node;
                } else {
                    temp = insert(node.right, data);
                    node.right = temp;
                    temp.parent = node;
                }

                //return node pointer unchanged
                return node;
            }

        }

        /*
         * Function that completes the task at hand
         * Assumptions: Each node has a link to its parent
         * Time: O(h) where h is the height of the BST
         * Space: O(1) because we're not using a structure to store values.
         */

        Node inOrderSuccessor(Node root, Node node) {
            //right subtree not null, so return min in right subtree
            if (node.right != null) {
                return minVal(node.right);
            }

            //right subtree is null, so travel up for a left child of parent. That is successor
            Node parent = node.parent;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }

        //Given non empty bst, return min value found in tree. Dont need to see entire tree
        Node minVal(Node node) {
            Node curr = node;

            //loop down to find the leftmost leaf
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        }

    }

    /*
     * Book Approach
     */
    public static TreeNode inorderSucc(TreeNode n) {
        if (n == null) return null;

        // Found right children -> return left most node of right subtree
        if (n.parent == null || n.right != null) {
            return leftMostChild(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            // Go up until we're on left instead of right
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    public static TreeNode leftMostChild(TreeNode n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    //support code from book
    /* One node of a binary tree. The data element stored is a single
     * character.
     */
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

    public static void main(String[] args) {

    }
}
