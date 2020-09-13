public class CheckSubtree {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            value = val;
            left = null;
            right = null;
        }
    }

    /*
     * Traverse and Equals are used to check for subtrees
     * Time: O(m * n) where m is one tree and n is other
     * Space: O(n) where depth of recursion can go up to n.
     */

    public static class BST {

        public boolean isSubtree(Node s, Node t) {
            return traverse(s, t);
        }

        public boolean equals(Node x, Node y) {
            if (x == null && y == null) {
                return true;
            }
            if (x == null || y == null) {
                return false;
            }
            return x.value == y.value && equals(x.left, y.left) && equals(x.right, y.right);
        }

        public boolean traverse(Node s, Node t) {
            return s != null && (equals(s, t) || traverse(s.left, t) || traverse(s.right, t));
        }

        public boolean isSubtree2(Node s, Node t)
        {
            if(s == null) return false;

            if(!isSame(s,t))
            {
                return isSubtree2(s.left, t) || isSubtree2(s.right, t);
            }

            return true;
        }

        private boolean isSame(Node s, Node t)
        {
            if(s == null && t == null)
            {
                return true;
            }

            if(s == null || t == null)
            {
                return false;
            }

            return s.value == t.value && isSame(s.left, t.left) && isSame(s.right, t.right);

        }

    }

    /*
     * Book support class
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

    /*
     * book approach A
     * Time: O(n + m) where n are m are nodes in tree 1 and tree 2
     * Space: O(n + m) ... same as above for time
     */

    public static boolean containsTree1(TreeNode t1, TreeNode t2) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(t1, string1);
        getOrderString(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    public static void getOrderString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X");             // Add null indicator
            return;
        }
        sb.append(node.data);           // Add root
        getOrderString(node.left, sb);  // Add left
        getOrderString(node.right, sb); // Add right
    }

    /*
     * Book approach B
     * Time: O(n + m) for tree n nodes and tree m nodes
     * Space: O(log n + log m)
     */
    public static boolean containsTree(TreeNode t1, TreeNode t2) {
        if (t2 == null) {
            return true; // The empty tree is a subtree of every tree.
        }
        return subTree(t1, t2);
    }

    /* Checks if the binary tree rooted at r1 contains the binary tree
     * rooted at r2 as a subtree somewhere within it.
     */
    public static boolean subTree(TreeNode r1, TreeNode r2) {
        if (r1 == null) {
            return false; // big tree empty & subtree still not found.
        } else if (r1.data == r2.data && matchTree(r1, r2)) {
            return true;
        }
        return subTree(r1.left, r2) || subTree(r1.right, r2);
    }

    /* Checks if the binary tree rooted at r1 contains the
     * binary tree rooted at r2 as a subtree starting at r1.
     */
    public static boolean matchTree(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true; // nothing left in the subtree
        } else if (r1 == null || r2 == null) {
            return false; // exactly one tree is empty, therefore trees don't match
        } else if (r1.data != r2.data) {
            return false;  // data doesn't match
        } else {
            return matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right);
        }
    }

    /* Creates tree by mapping the array left to right, top to bottom. */
    public static TreeNode createTreeFromArray(int[] array) {
        if (array.length > 0) {
            TreeNode root = new TreeNode(array[0]);
            java.util.Queue<TreeNode> queue = new java.util.LinkedList<TreeNode>();
            queue.add(root);
            boolean done = false;
            int i = 1;
            while (!done) {
                TreeNode r = (TreeNode) queue.element();
                if (r.left == null) {
                    r.left = new TreeNode(array[i]);
                    i++;
                    queue.add(r.left);
                } else if (r.right == null) {
                    r.right = new TreeNode(array[i]);
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

    public static void main(String[] args) {

    }
}
