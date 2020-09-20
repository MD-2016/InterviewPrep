import java.util.Random;

public class RandomNode {

    static class Node {
        int value;
        int children;
        Node left, right;
        private int size = 0;

        public Node(int v)
        {
           value = v;
           size = 1;
        }

        public void insertInOrder(int v)
        {
            if(v <= value)
            {
                if(left == null)
                {
                    left = new Node(v);
                }
                else
                {
                    left.insertInOrder(v);
                }
            }
            else
            {
                if(right == null)
                {
                    right = new Node(v);
                }
                else
                {
                    right.insertInOrder(v);
                }
            }
            size++;
        }

        public int size()
        {
            return size;
        }

        public Node find(int v)
        {
            if(v == value)
            {
                return this;
            }
            else if(v <= value)
            {
                return left != null ? left.find(v) : null;
            }
            else if(v > value)
            {
                return right != null ? right.find(v) : null;
            }

            return null;
        }

        public Node getRandomNode()
        {
            int leftSubSize = left == null ? 0 : left.size();
            Random ran = new Random();
            int index = ran.nextInt(size);
            if(index < leftSubSize)
            {
                return left.getRandomNode();
            }
            else if(index == leftSubSize)
            {
                return this;
            }
            else
            {
                return right.getRandomNode();
            }
        }

        public Node getIthNode(int i)
        {
            int leftSubSize = left == null ? 0 : left.size();
            if(i < leftSubSize)
            {
                return left.getIthNode(i);
            }
            else if(i == leftSubSize)
            {
                return this;
            }
            else
            {
                return right.getIthNode(i - (leftSubSize + 1));
            }
        }

    }

    public static class Tree {
        Node root = null;

        public void insertInOrder(int v)
        {
            if(root == null)
            {
                root = new Node(v);
            }
            else
            {
                root.insertInOrder(v);
            }
        }

        public int size()
        {
            return root == null ? 0 : root.size();
        }

        public Node getRandomNode()
        {
            if(root == null)
            {
                return null;
            }

            Random ran = new Random();
            int i = ran.nextInt(size());
            return root.getIthNode(i);

        }

    }

    /*
     * Get random node methods are the main
     * Time: O(D) where D is the max depth of tree
     * Space: O(n) for storing the n nodes of tree but cost of random node removal is constant
     */

    public static void main(String[] args)
    {
        int[] counts = new int[10];
        for(int i = 0; i < 1000000; i++)
        {
            Tree bt = new Tree();
            int[] arr = {1,0,6,2,3,9,4,5,8,7};
            for(int x : arr)
            {
                bt.insertInOrder(x);
            }
            int d = bt.getRandomNode().value;
            counts[d]++;
        }

        for(int i = 0; i < counts.length; i++)
        {
            System.out.println(i + ": " + counts[i]);
        }

    }

}
