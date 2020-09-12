import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSTSequencesTest {

    BSTSequences.BST tree;

    @BeforeEach
    public void setUp()
    {
        tree = new BSTSequences.BST();
    }

    @Test
    public void testThreeNodesTreeSequences()
    {
        tree.root = new BSTSequences.Node(2);
        tree.root.left = new BSTSequences.Node(1);
        tree.root.right = new BSTSequences.Node(3);
        ArrayList<LinkedList<Integer>> seq = BSTSequences.BST.bstSequences(tree.root);
        ArrayList<LinkedList<Integer>> res = new ArrayList<>();
        Integer[] set1 = {2,1,3};
        Integer[] set2 = {2,3,1};
        LinkedList<Integer> linkedRes1 = new LinkedList<>(Arrays.asList(set1));
        LinkedList<Integer> linkedRes2 = new LinkedList<>(Arrays.asList(set2));
        res.add(linkedRes1);
        res.add(linkedRes2);
        assertEquals(res, seq);
    }

    @Test
    public void testTwoNodesLeftTreeSequences()
    {
        tree.root = new BSTSequences.Node(2);
        tree.root.left = new BSTSequences.Node(1);
        ArrayList<LinkedList<Integer>> seq = BSTSequences.BST.bstSequences(tree.root);
        ArrayList<LinkedList<Integer>> res = new ArrayList<>();
        Integer[] set1 = {2,1};
        LinkedList<Integer> linkedRes1 = new LinkedList<>(Arrays.asList(set1));
        res.add(linkedRes1);
        assertEquals(res, seq);
        assertEquals(linkedRes1, seq.remove(0));
    }

    @Test
    public void testTwoNodesRightTreeSequences()
    {
        tree.root = new BSTSequences.Node(2);
        tree.root.right = new BSTSequences.Node(3);
        ArrayList<LinkedList<Integer>> seq = BSTSequences.BST.bstSequences(tree.root);
        ArrayList<LinkedList<Integer>> res = new ArrayList<>();
        Integer[] set1 = {2,3};
        LinkedList<Integer> linkedRes1 = new LinkedList<>(Arrays.asList(set1));
        res.add(linkedRes1);
        assertEquals(res, seq);
        assertEquals(linkedRes1, seq.remove(0));
    }

    @Test
    public void testRootNodeOnlySequence()
    {
        tree.root = new BSTSequences.Node(2);
        ArrayList<LinkedList<Integer>> seq = BSTSequences.BST.bstSequences(tree.root);
        ArrayList<LinkedList<Integer>> res = new ArrayList<>();
        Integer[] set1 = {2};
        LinkedList<Integer> linkedRes1 = new LinkedList<>(Arrays.asList(set1));
        res.add(linkedRes1);
        assertEquals(res, seq);
        assertEquals(res.remove(0), seq.remove(0));
    }




}