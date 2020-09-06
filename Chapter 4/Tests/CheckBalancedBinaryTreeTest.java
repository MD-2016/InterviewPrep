import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckBalancedBinaryTreeTest {

    CheckBalancedBinaryTree.BST binaryTree;
    CheckBalancedBinaryTree.Height height;

    @BeforeEach
    void setUp()
    {
        binaryTree = new CheckBalancedBinaryTree.BST();
        height = new CheckBalancedBinaryTree.Height();
    }

    @Test
    public void testTreeThreeLevelsBalanced()
    {
        binaryTree.root = new CheckBalancedBinaryTree.Node(1);
        binaryTree.root.left = new CheckBalancedBinaryTree.Node(2);
        binaryTree.root.right = new CheckBalancedBinaryTree.Node(3);
        binaryTree.root.left.left = new CheckBalancedBinaryTree.Node(4);
        binaryTree.root.left.right = new CheckBalancedBinaryTree.Node(5);
        binaryTree.root.right.right = new CheckBalancedBinaryTree.Node(6);
        binaryTree.root.left.left.left = new CheckBalancedBinaryTree.Node(7);

        assertEquals(true, binaryTree.isBalanced(binaryTree.root, height));

    }

    @Test
    public void testTreeThreeLevelNotBalanced()
   {
       binaryTree.root = new CheckBalancedBinaryTree.Node(1);
       binaryTree.root.left = new CheckBalancedBinaryTree.Node(2);
       binaryTree.root.right = new CheckBalancedBinaryTree.Node(3);
       binaryTree.root.right.left = new CheckBalancedBinaryTree.Node(4);
       binaryTree.root.right.right = new CheckBalancedBinaryTree.Node(5);
       binaryTree.root.right.left.left = new CheckBalancedBinaryTree.Node(6);
       binaryTree.root.right.left.right = new CheckBalancedBinaryTree.Node(7);

       assertEquals(false, binaryTree.isBalanced(binaryTree.root, height));

   }

   @Test
   public void testTreeTwoLevelUnbalancedRight()
   {
       binaryTree.root = new CheckBalancedBinaryTree.Node(5);
       binaryTree.root.right = new CheckBalancedBinaryTree.Node(7);
       binaryTree.root.right.right = new CheckBalancedBinaryTree.Node(12);

       assertEquals(false, binaryTree.isBalanced(binaryTree.root, height));
   }

   @Test
   public void testTreeWithRootBalanced()
   {
       binaryTree.root = new CheckBalancedBinaryTree.Node(2);
       assertEquals(true, binaryTree.isBalanced(binaryTree.root, height));
   }

   @Test
    public void testTreeTwoLevelUnbalancedLeft()
   {
       binaryTree.root = new CheckBalancedBinaryTree.Node(7);
       binaryTree.root.left = new CheckBalancedBinaryTree.Node(8);
       binaryTree.root.left.left = new CheckBalancedBinaryTree.Node(9);

       assertEquals(false, binaryTree.isBalanced(binaryTree.root, height));
   }

   @Test
    public void testTreeOneLevelBalanced()
   {
       binaryTree.root = new CheckBalancedBinaryTree.Node(1);
       binaryTree.root.left = new CheckBalancedBinaryTree.Node(2);
       binaryTree.root.right = new CheckBalancedBinaryTree.Node(3);

       assertEquals(true, binaryTree.isBalanced(binaryTree.root, height));
   }

   @Test
    public void testTreeIsBalancedRootNull()
   {
       binaryTree.root = null;
       assertEquals(true, binaryTree.isBalanced(binaryTree.root, height));
   }

}