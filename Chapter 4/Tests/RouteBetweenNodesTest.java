import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//used this link to draw each graph: https://csacademy.com/app/graph_editor/

class RouteBetweenNodesTest {

    RouteBetweenNodes.Graph directedGraphWithPath;
    RouteBetweenNodes.Graph directedGraphNoPath;
    RouteBetweenNodes.Graph directedGraphThreeNodes;

    @BeforeEach
    void setUp() {
        directedGraphWithPath = new RouteBetweenNodes.Graph(4);
        directedGraphNoPath = new RouteBetweenNodes.Graph(2);
        directedGraphThreeNodes = new RouteBetweenNodes.Graph(3);
    }

    @Test
    public void testPathExists()
    {
        directedGraphWithPath.addEdge(0, 1);
        directedGraphWithPath.addEdge(0, 2);
        directedGraphWithPath.addEdge(1, 2);
        directedGraphWithPath.addEdge(2, 0);
        directedGraphWithPath.addEdge(2, 3);
        directedGraphWithPath.addEdge(3, 3);
        assertEquals(true, directedGraphWithPath.isReachable(1,3));
        assertEquals(false, directedGraphWithPath.isReachable(3,1));
    }

    @Test
    public void testPathDoesExistSmallGraph()
    {
        directedGraphNoPath.addEdge(0,1);
        assertEquals(true, directedGraphNoPath.isReachable(0,1));
        assertEquals(false, directedGraphNoPath.isReachable(1,0));
    }

    @Test
    public void testPathThreeNodesPass()
    {
        directedGraphThreeNodes.addEdge(0 ,1);
        directedGraphThreeNodes.addEdge(1, 2);
        directedGraphThreeNodes.addEdge(2, 0);
        directedGraphThreeNodes.addEdge(0 ,2);
        assertEquals(true, directedGraphThreeNodes.isReachable(0,2));
        assertEquals(true, directedGraphThreeNodes.isReachable(0,1));
        assertEquals(true, directedGraphThreeNodes.isReachable(1,2));
        assertEquals(true, directedGraphThreeNodes.isReachable(2,0));
        assertEquals(true, directedGraphThreeNodes.isReachable(1,0));
    }

    @Test
    public void testPathThreeNodesFalse()
    {
        directedGraphThreeNodes.addEdge(0 ,1);
        directedGraphThreeNodes.addEdge(1, 2);
        directedGraphThreeNodes.addEdge(0 ,2);
        assertEquals(true, directedGraphThreeNodes.isReachable(0,2));
        assertEquals(true, directedGraphThreeNodes.isReachable(0,1));
        assertEquals(true, directedGraphThreeNodes.isReachable(1,2));
        assertEquals(false, directedGraphThreeNodes.isReachable(2,0));
        assertEquals(false, directedGraphThreeNodes.isReachable(1,0));
    }

}