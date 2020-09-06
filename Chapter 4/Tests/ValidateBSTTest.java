import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateBSTTest {

    @BeforeEach
    public void setUp()
    {
    }

    @Test
    public void testValidBST()
    {
        ValidateBST.Node root = new ValidateBST.Node(5);
        root.left = new ValidateBST.Node(2);
        root.right = new ValidateBST.Node(15);
        root.left.left = new ValidateBST.Node(1);
        root.left.right = new ValidateBST.Node(4);

        assertEquals(true, ValidateBST.isBinarySearchTree(root));
    }

    @Test
    public void testNotValidBST()
    {
        ValidateBST.Node root = new ValidateBST.Node(5);
        root.left = new ValidateBST.Node(1);
        root.right = new ValidateBST.Node(4);
        root.right.left = new ValidateBST.Node(3);
        root.right.right = new ValidateBST.Node(6);

        assertEquals(false, ValidateBST.isBinarySearchTree(root));
    }

    @Test
    public void testValidBSTUtilMethod()
    {
        ValidateBST.Node root = new ValidateBST.Node(5);
        root.left = new ValidateBST.Node(2);
        root.right = new ValidateBST.Node(15);
        root.left.left = new ValidateBST.Node(1);
        root.left.right = new ValidateBST.Node(4);

        assertEquals(true, ValidateBST.isBinarySearchTreeUtil(root, 0));
    }

    @Test
    public void testIsBSTRootNull()
    {
        ValidateBST.Node root = null;
        assertEquals(true, ValidateBST.isBinarySearchTree(root));
    }

    @Test
    public void testIsBSTNotValidUtil()
    {
        ValidateBST.Node root = new ValidateBST.Node(1);
        root.left = new ValidateBST.Node(2);
        root.right = new ValidateBST.Node(3);
        assertEquals(false, ValidateBST.isBinarySearchTreeUtil(root, 100));
    }

    @Test
    public void testIsBSTUtilForLeftSubTreeFalse()
    {
        ValidateBST.Node root = new ValidateBST.Node(1);
        root.left = new ValidateBST.Node(2);
        assertEquals(false, ValidateBST.isBinarySearchTreeUtil(root, root.data));
    }

    @Test
    public void testIsBSTUtilRootLessThanPrev()
    {
        ValidateBST.Node root = new ValidateBST.Node(0);
        assertEquals(false, ValidateBST.isBinarySearchTreeUtil(root, 1));
    }

}