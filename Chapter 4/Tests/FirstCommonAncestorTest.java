import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FirstCommonAncestorTest {

    FirstCommonAncestor.BST tree;

    @BeforeEach
    public void setUp()
    {
        tree = new FirstCommonAncestor.BST();
    }

    @Test
    public void testCorrectAncestorAllSubtrees()
    {
        tree.root = new FirstCommonAncestor.Node(1);
        tree.root.left = new FirstCommonAncestor.Node(2);
        tree.root.right = new FirstCommonAncestor.Node(3);
        tree.root.left.left = new FirstCommonAncestor.Node(4);
        tree.root.left.right = new FirstCommonAncestor.Node(5);
        tree.root.right.left = new FirstCommonAncestor.Node(6);
        tree.root.right.right = new FirstCommonAncestor.Node(7);

        //first ancestor for 4,5
        assertEquals(2, tree.findFirst(4,5).data);

        //first ancestor for 4,6
        assertEquals(1, tree.findFirst(4,6).data);

        //first ancestor for 3,4
        assertEquals(1, tree.findFirst(3,4).data);

        //first ancestor for 2,4
        assertEquals(2, tree.findFirst(2,4).data);

        //first ancestor for 3,7
        assertEquals(3, tree.findFirst(3,7).data);

        //first ancestor 6,3
        assertEquals(3, tree.findFirst(6,3).data);

        //first ancestor 3,1
        assertEquals(1, tree.findFirst(3,1).data);

        //first ancestor 5,1
        assertEquals(1, tree.findFirst(5,1).data);

        //first ancestor 4,1
        assertEquals(1, tree.findFirst(1,4).data);

    }

    @Test
    public void testSmallTreeThreeNodesAncestors()
    {
        tree.root = new FirstCommonAncestor.Node(1);
        tree.root.left = new FirstCommonAncestor.Node(0);
        tree.root.right = new FirstCommonAncestor.Node(2);

        //first ancestor 0,1
        assertEquals(1, tree.findFirst(1,0).data);

        //first ancestor 1,2
        assertEquals(1, tree.findFirst(1,2).data);

        //first ancestor 0,2
        assertEquals(1, tree.findFirst(0,2).data);

        //root check
        assertEquals(1,tree.findFirst(1,1).data);

        //null check
        assertNull(tree.findFirst(3,3));

    }



}