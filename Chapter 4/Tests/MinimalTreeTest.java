import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimalTreeTest {

    MinimalTree minimalTreeHeight;

    @BeforeEach
    void setUp() {
        minimalTreeHeight = new MinimalTree();
    }

    @Test
    public void testMinHeight4()
    {
        int[] arrHeight4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MinimalTree.MHBSTNode minTree = minimalTreeHeight.convert(arrHeight4, 0, arrHeight4.length - 1);
        assertEquals(5, minTree.data);
        assertEquals(4, minTree.height());
        assertEquals(true, minTree.isBST());

    }

    @Test
    public void testMinHeight2()
    {
        int[] arrHeight2 = {1,2};
        MinimalTree.MHBSTNode minTree = minimalTreeHeight.convert(arrHeight2, 0, arrHeight2.length-1);
        assertEquals(1, minTree.data);
        assertEquals(2, minTree.height());
        assertEquals(true, minTree.isBST());
    }

    @Test
    public void testMinHeight1()
    {
        int[] arrHeight1 = {1};
        MinimalTree.MHBSTNode minTree = minimalTreeHeight.convert(arrHeight1, 0, arrHeight1.length-1);
        assertEquals(1, minTree.data);
        assertEquals(1, minTree.height());
        assertEquals(true, minTree.isBST());
    }

    @Test
    public void testMinHeight3NotBST()
    {
        int[] arrNotBST = {2,5,6,6};
        MinimalTree.MHBSTNode minTree = minimalTreeHeight.convert(arrNotBST, 0,arrNotBST.length-1);
        assertEquals(5, minTree.data);
        assertEquals(3, minTree.height());
        assertEquals(false, minTree.isBST());
    }

}