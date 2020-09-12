import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTSuccessorTest {

    BSTSuccessor.BST tree;

    @BeforeEach
    public void setUp()
    {
        tree = new BSTSuccessor.BST();
    }

    @Test
    public void testSuccessorFoundCorrect()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 8);
        root = tree.insert(root, 22);
        root = tree.insert(root, 4);
        root = tree.insert(root, 12);
        root = tree.insert(root, 10);
        root = tree.insert(root, 14);
        temp = root.left.right.right;
        assertEquals(14, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertEquals(20, suc.data);
    }

    @Test
    public void testSuccessorForTwelveBeingTwenty()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 8);
        root = tree.insert(root, 22);
        root = tree.insert(root, 4);
        root = tree.insert(root, 12);
        root = tree.insert(root, 10);
        root = tree.insert(root, 14);
        temp = root.left.right;
        assertEquals(12, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertEquals(14, suc.data);
    }

    @Test
    public void testSuccessorOfFourIsEight()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 8);
        root = tree.insert(root, 22);
        root = tree.insert(root, 4);
        root = tree.insert(root, 12);
        root = tree.insert(root, 10);
        root = tree.insert(root, 14);
        temp = root.left.left;
        assertEquals(4, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertEquals(8, suc.data);
    }

    @Test
    public void testForNoSuccessor()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 8);
        root = tree.insert(root, 22);
        root = tree.insert(root, 4);
        root = tree.insert(root, 12);
        root = tree.insert(root, 10);
        root = tree.insert(root, 14);
        temp = root.right;
        assertEquals(22, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertNull(suc);
    }

    @Test
    public void testForEightSuccessorTen()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 8);
        root = tree.insert(root, 22);
        root = tree.insert(root, 4);
        root = tree.insert(root, 12);
        root = tree.insert(root, 10);
        root = tree.insert(root, 14);
        temp = root.left;
        assertEquals(8, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertEquals(10, suc.data);
    }

    @Test
    public void testForRootSuccessor()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 8);
        root = tree.insert(root, 22);
        root = tree.insert(root, 4);
        root = tree.insert(root, 12);
        root = tree.insert(root, 10);
        root = tree.insert(root, 14);
        temp = root;
        assertEquals(20, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertEquals(22, suc.data);
    }

    @Test
    public void testThreeNodesTreeRootSuccessor()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 22);
        root = tree.insert(root, 11);
        temp = root;
        assertEquals(20, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertEquals(22, suc.data);
    }

    @Test
    public void testThreeNodesTreeLeftSuccessor()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 22);
        root = tree.insert(root, 11);
        temp = root.left;
        assertEquals(11, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertEquals(20, suc.data);
    }

    @Test
    public void testThreeNodesTreeRightSuccessor()
    {
        Node root = null, temp = null, suc = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 22);
        root = tree.insert(root, 11);
        temp = root.right;
        assertEquals(22, temp.data);
        suc = tree.inOrderSuccessor(root, temp);
        assertNull(suc);
    }

    @Test
    public void testMinVal()
    {
        Node root = null, temp = null;
        root = tree.insert(root, 20);
        root = tree.insert(root, 11);
        root = tree.insert(root, 22);
        temp = tree.minVal(root);
        assertEquals(11, temp.data);
    }

}