import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathsWithSumTest {

    PathsWithSum.Node root;
    PathsWithSum.BST tree;
    PathsWithSum.BinaryTree bst;
    PathsWithSum.BT testTree;

    @BeforeEach
    public void setUp()
    {
        tree = new PathsWithSum.BST();
        bst = new PathsWithSum.BinaryTree();
    }

    @Test
    public void testThreeSumPathsForEight()
    {
        PathsWithSum.Node root = new PathsWithSum.Node(10);
        root.left = new PathsWithSum.Node(5);
        root.right = new PathsWithSum.Node(-3);
        root.right.right = new PathsWithSum.Node(11);
        root.left.right = new PathsWithSum.Node(2);
        root.left.right.right = new PathsWithSum.Node(1);
        root.left.left = new PathsWithSum.Node(3);
        root.left.left.left = new PathsWithSum.Node(3);
        root.left.left.right = new PathsWithSum.Node(-2);
        assertEquals(PathsWithSum.BT.countPathsWithSum(root, 8), tree.pathSum(root, 8));
        assertEquals(PathsWithSum.BT.countPathsWithSum(root, 8), bst.pathSum(root, 8));
    }

    @Test
    public void testThreeSumPathsForTen()
    {
        PathsWithSum.Node root = new PathsWithSum.Node(1);
        root.left = new PathsWithSum.Node(2);
        root.right = new PathsWithSum.Node(3);
        root.left.left = new PathsWithSum.Node(7);
        root.left.right = new PathsWithSum.Node(5);
        root.right.left = new PathsWithSum.Node(6);
        root.right.right = new PathsWithSum.Node(7);
        assertEquals(PathsWithSum.BT.countPathsWithSum(root,10), tree.pathSum(root, 10));
        assertEquals(PathsWithSum.BT.countPathsWithSum(root,10), bst.pathSum(root,10));
    }

    @Test
    public void testTwoSumPathforTwentyTwo()
    {
        PathsWithSum.Node root = new PathsWithSum.Node(5);
        root.left = new PathsWithSum.Node(4);
        root.right = new PathsWithSum.Node(8);
        root.left.left = new PathsWithSum.Node(11);
        root.right.left = new PathsWithSum.Node(13);
        root.right.right = new PathsWithSum.Node(4);
        root.left.left.left = new PathsWithSum.Node(7);
        root.left.left.right = new PathsWithSum.Node(2);
        root.right.right.right = new PathsWithSum.Node(1);
        assertEquals(PathsWithSum.BT.countPathsWithSum(root,22), tree.pathSum(root,22));
        assertEquals(PathsWithSum.BT.countPathsWithSum(root,22), bst.pathSum(root,22));
    }

    @Test
    public void testSumPathForTwentyOne()
    {
        PathsWithSum.Node root = new PathsWithSum.Node(10);
        root.left = new PathsWithSum.Node(8);
        root.right = new PathsWithSum.Node(2);
        root.left.left = new PathsWithSum.Node(3);
        root.left.right = new PathsWithSum.Node(5);
        root.right.left = new PathsWithSum.Node(2);
        assertEquals(PathsWithSum.BT.countPathsWithSum(root,21), tree.pathSum(root,21));
        assertEquals(PathsWithSum.BT.countPathsWithSum(root,21), bst.pathSum(root,21));
    }

    @Test
    public void testZeroPath()
    {
        PathsWithSum.Node root = new PathsWithSum.Node(0);
        assertEquals(0, tree.pathSum(root,3));
        assertEquals(0, bst.pathSum(root,3));
    }

    @Test
    public void testNullRoot()
    {
        PathsWithSum.Node root = null;
        assertNull(root);
        assertEquals(0, tree.pathSum(root,3));
        assertEquals(0, bst.pathSum(root,3));
    }


}