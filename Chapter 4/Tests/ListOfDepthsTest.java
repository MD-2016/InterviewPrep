import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListOfDepthsTest {

    ListOfDepths depthTree;
    ListOfDepths resultTree;
    ListOfDepths.Node root;

    @BeforeEach
    void setUp() {
        depthTree = new ListOfDepths();
        resultTree = new ListOfDepths();
    }



    @Test
    public void testForThreeLevels()
    {
        root = new ListOfDepths.Node(5);
        assertNotNull(root);
        root.left = new ListOfDepths.Node(10);
        assertNotNull(root.left);
        root.right = new ListOfDepths.Node(15);
        assertNotNull(root.right);
        root.left.left = new ListOfDepths.Node(20);
        assertNotNull(root.left.left);
        root.left.right = new ListOfDepths.Node(25);
        assertNotNull(root.left.right);
        root.right.left = new ListOfDepths.Node(30);
        assertNotNull(root.right.left);
        root.right.right = new ListOfDepths.Node(35);
        assertNotNull(root.right.right);
        depthTree.levelOrderQueue(root);
        assertEquals("->5", "->"+root.data);
        assertEquals("->10->15", "->"+root.left.data+"->"+root.right.data);
        assertEquals("->20->25->30->35", "->"+root.left.left.data+"->"+root.left.right.data+"->"+root.right.left.data+"->"+root.right.right.data);
    }

    @Test
    public void testForDepthTwo()
    {
        root = new ListOfDepths.Node(5);
        root.left = new ListOfDepths.Node(6);
        root.right = new ListOfDepths.Node(7);
        root.left.right = new ListOfDepths.Node(8);
        depthTree.levelOrderQueue(root);
        assertEquals("->5", "->"+root.data);
        assertEquals("->6->7", "->"+root.left.data+"->"+root.right.data);
        assertEquals("->8", "->"+root.left.right.data);
    }

    @Test
    public void testForDepthOne()
    {
        root = new ListOfDepths.Node(5);
        root.left = new ListOfDepths.Node(6);
        depthTree.levelOrderQueue(root);
        assertEquals("->5", "->"+root.data);
        assertEquals("->6", "->"+root.left.data);
    }

    @Test
    public void testForDepthZero()
    {
        root = new ListOfDepths.Node(5);
        depthTree.levelOrderQueue(root);
        assertEquals("->5", "->"+root.data);
    }

}