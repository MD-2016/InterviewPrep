
import java.util.Iterator;
import java.util.LinkedList;

public class RouteBetweenNodes {

    //Class for graph before route checker algorithm is made

    /*
     * Problem: Route Between Nodes = Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
     * Assumptions: Integers are used only, graph is only directed
     * Time: O(V+E) where V is vertices and E is edges
     * Space: O(V) for at most V vertices in the queue
     */

    static class Graph {
        private int vertices;
        private LinkedList<Integer> adjacencyList[];

        Graph(int v)
        {
            vertices = v;
            adjacencyList = new LinkedList[v];
            for(int i = 0; i < v; i++)
            {
                adjacencyList[i] = new LinkedList<>();
            }
        }

        void addEdge(int v, int w)
        {
            adjacencyList[v].add(w);
        }

        //test method
        public String printEdges(int v, int w)
        {
            return adjacencyList[v].toString();
        }


        boolean isReachable(int s, int d)
        {
            LinkedList<Integer> queue = new LinkedList<>();

            boolean visited[] = new boolean[vertices];

            visited[s] = true;
            queue.add(s);
            Iterator<Integer> i;
            while(queue.size() != 0)
            {
                s = queue.poll();

                int n;
                i = adjacencyList[s].listIterator();

                while(i.hasNext())
                {
                    n = i.next();

                    if(n == d)
                    {
                        return true;
                    }

                    if(!visited[n])
                    {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
            return false;
        }
    }

    /*
     * Book approach
     */
    enum State {Unvisited, Visited, Visiting;}

    static class Node {
        private Node adjacent[];
        public int adjacentCount;
        private String vertex;
        public State state;
        public Node(String vertex, int adjaventLength)
        {
            this.vertex = vertex;
            adjacentCount = 0;
            adjacent = new Node[adjaventLength];
        }

        public void addAdjacent(Node x)
        {
            if(adjacentCount < adjacent.length)
            {
                this.adjacent[adjacentCount] = x;
                adjacentCount++;
            }
            else
            {
                System.out.println("No more adjacent can be added");
            }
        }

        public Node[] getAdjacent()
        {
            return adjacent;
        }

        public String getVertex()
        {
            return vertex;
        }

    }

    static class Graph2 {
        public static int MAX_VERTICES = 6;
        private Node vertices[];
        public int count;
        public Graph2()
        {
            vertices = new Node[MAX_VERTICES];
            count = 0;
        }

        public void addNode(Node x)
        {
            if(count < vertices.length)
            {
                vertices[count] = x;
                count++;
            }
            else
            {
                System.out.println("Graph full");
            }
        }

        public Node[] getNodes()
        {
            return vertices;
        }
    }

    public static Graph2 createNewGraph()
    {
        Graph2 g = new Graph2();
        Node[] temp = new Node[6];

        temp[0] = new Node("a", 3);
        temp[1] = new Node("b", 0);
        temp[2] = new Node("c", 0);
        temp[3] = new Node("d", 1);
        temp[4] = new Node("e", 1);
        temp[5] = new Node("f", 0);

        temp[0].addAdjacent(temp[1]);
        temp[0].addAdjacent(temp[2]);
        temp[0].addAdjacent(temp[3]);
        temp[3].addAdjacent(temp[4]);
        temp[4].addAdjacent(temp[5]);
        for (int i = 0; i < 6; i++) {
            g.addNode(temp[i]);
        }
        return g;
    }

    public static boolean search(Graph2 g,Node start,Node end) {
        LinkedList<Node> q = new LinkedList<Node>();
        for (Node u : g.getNodes()) {
            u.state = State.Unvisited;
        }
        start.state = State.Visiting;
        q.add(start);
        Node u;
        while(!q.isEmpty()) {
            u = q.removeFirst();
            if (u != null) {
                for (Node v : u.getAdjacent()) {
                    if (v.state == State.Unvisited) {
                        if (v == end) {
                            return true;
                        } else {
                            v.state = State.Visiting;
                            q.add(v);
                        }
                    }
                }
                u.state = State.Visited;
            }
        }
        return false;
    }


    public static void main(String[] args)
    {

    }

}
