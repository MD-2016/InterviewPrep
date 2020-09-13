import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class CheckSubtreeTest {

    CheckSubtree.BST tree;

    @BeforeEach
    public void setUp()
    {
        tree = new CheckSubtree.BST();
    }

    @Test
    public void testRightSubtreeTrue()
    {
        CheckSubtree.Node root = new CheckSubtree.Node(1);
        root.left = new CheckSubtree.Node(2);
        root.right = new CheckSubtree.Node(3);
        root.left.left = new CheckSubtree.Node(4);
        root.left.right = new CheckSubtree.Node(5);
        root.right.left = new CheckSubtree.Node(6);
        root.right.right = new CheckSubtree.Node(7);
        CheckSubtree.Node root2 = new CheckSubtree.Node(3);
        root2.left = new CheckSubtree.Node(6);
        root2.right = new CheckSubtree.Node(7);

        assertEquals(true, tree.isSubtree(root, root2));
    }

    @Test
    public void testLeftSubtreeTrue()
    {
        CheckSubtree.Node root = new CheckSubtree.Node(1);
        root.left = new CheckSubtree.Node(2);
        root.right = new CheckSubtree.Node(3);
        root.left.left = new CheckSubtree.Node(4);
        root.left.right = new CheckSubtree.Node(5);
        root.right.left = new CheckSubtree.Node(6);
        root.right.right = new CheckSubtree.Node(7);
        CheckSubtree.Node root2 = new CheckSubtree.Node(2);
        root2.left = new CheckSubtree.Node(4);
        root2.right = new CheckSubtree.Node(5);

        assertEquals(true, tree.isSubtree(root, root2));
    }

    @Test
    public void testLeftSubtreeFalse()
    {
        CheckSubtree.Node root = new CheckSubtree.Node(1);
        root.left = new CheckSubtree.Node(2);
        root.right = new CheckSubtree.Node(3);
        root.left.left = new CheckSubtree.Node(4);
        root.left.right = new CheckSubtree.Node(5);
        root.right.left = new CheckSubtree.Node(6);
        root.right.right = new CheckSubtree.Node(7);
        CheckSubtree.Node root2 = new CheckSubtree.Node(2);
        root2.left = new CheckSubtree.Node(4);
        root2.right = new CheckSubtree.Node(8);

        assertEquals(false, tree.isSubtree(root, root2));
    }

    @Test
    public void testRightSubtreeFalse()
    {
        CheckSubtree.Node root = new CheckSubtree.Node(1);
        root.left = new CheckSubtree.Node(2);
        root.right = new CheckSubtree.Node(3);
        root.left.left = new CheckSubtree.Node(4);
        root.left.right = new CheckSubtree.Node(5);
        root.right.left = new CheckSubtree.Node(6);
        root.right.right = new CheckSubtree.Node(7);
        CheckSubtree.Node root2 = new CheckSubtree.Node(3);
        root2.left = new CheckSubtree.Node(6);
        root2.right = new CheckSubtree.Node(2);

        assertEquals(false, tree.isSubtree(root, root2));
    }

    @Test
    public void testSmallTreeFalse()
    {
        CheckSubtree.Node root = new CheckSubtree.Node(1);
        root.left = new CheckSubtree.Node(0);
        root.right = new CheckSubtree.Node(2);
        CheckSubtree.Node root2 = new CheckSubtree.Node(1);
        assertEquals(false, tree.isSubtree(root, root2));
    }

    @Test
    public void testRootAndTwoLeafNodesTreeFalse()
    {
        CheckSubtree.Node root = new CheckSubtree.Node(3);
        root.left = new CheckSubtree.Node(4);
        root.left.left = new CheckSubtree.Node(1);
        root.right = new CheckSubtree.Node(5);
        root.right.left = new CheckSubtree.Node(2);
        CheckSubtree.Node root2 = new CheckSubtree.Node(3);
        root2.left = new CheckSubtree.Node(1);
        root2.right = new CheckSubtree.Node(2);
        assertEquals(false, tree.isSubtree(root, root2));
    }

}