package Chapter_10;

import java.util.Comparator;

public class RankFromStream
{
    //Support Class
    static class Node
    {
        int data;
        Node left, right;
        int leftSize;
    }

    static Node newNode(int data)
    {
        Node temp = new Node();
        temp.data = data;
        temp.left = null;
        temp.right = null;
        temp.leftSize = 0;
        return temp;
    }

    static Node insert(Node root, int data)
    {
        if(root == null)
            return newNode(data);

        if(data <= root.data)
        {
            root.left = insert(root.left, data);
            root.leftSize++;
        }
        else
        {
            root.right = insert(root.right, data);
        }

        return root;
    }

    static int getRank(Node root, int x)
    {
        if(root.data == x)
        {
            return root.leftSize;
        }

        if(x < root.data)
        {
            if (root.left == null)
            {
                return -1;
            }
            else
            {
                return getRank(root.left, x);
            }
        }
        else
        {
            if(root.right == null)
            {
                return -1;
            }
            else
            {
                int rightSize = getRank(root.right, x);
                if(rightSize == -1)
                {
                    return -1;
                }
                return root.leftSize + 1 + rightSize;
            }
        }

    }

    private static RankNode root = null;

    /*
     * Book Solution
     * Time: O(log n)
     */

    public static void track(int number) {
        if (root == null) {
            root = new RankNode(number);
        } else {
            root.insert(number);
        }
    }

    public static int getRankOfNumber(int number) {
        return root.getRank(number);
    }


    public static void main(String[] args)
    {

    }
}

//Book Support Class
class RankNode {
    public int left_size = 0;
    public RankNode left;
    public RankNode right;
    public int data = 0;
    public RankNode(int d) {
        data = d;
    }

    public void insert(int d) {
        if (d <= data) {
            if (left != null) {
                left.insert(d);
            } else {
                left = new RankNode(d);
            }
            left_size++;
        } else {
            if (right != null) {
                right.insert(d);
            } else {
                right = new RankNode(d);
            }
        }
    }

    public int getRank(int d) {
        if (d == data) {
            return left_size;
        } else if (d < data) {
            if (left == null) {
                return -1;
            } else {
                return left.getRank(d);
            }
        } else {
            int right_rank = right == null ? -1 : right.getRank(d);
            if (right_rank == -1) {
                return -1;
            } else {
                return left_size + 1 + right_rank;
            }
        }
    }
}

//Book Support Class
class IntComparable implements Comparator<Integer>
{
    @Override
    public int compare(Integer o1, Integer o2)
    {
        return o1.compareTo(o2);
    }
}
