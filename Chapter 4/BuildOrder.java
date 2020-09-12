import java.util.ArrayList;
import java.util.HashMap;

public class BuildOrder {

    public static class Project {
        private ArrayList<Project> children = new ArrayList<>();
        private HashMap<String, Project> projectMap = new HashMap<>();
        private String projName;
        private int dependencies = 0;

        public Project(String n)
        {
            projName = n;
        }

        public void addNeighbor(Project node)
        {
            if(!projectMap.containsKey(node.getName()))
            {
                children.add(node);
                node.incrementDependencies();
            }
        }

        public void incrementDependencies()
        {
            ++dependencies;
        }

        public void decrementDependencies()
        {
            --dependencies;
        }

        public String getName()
        {
            return projName;
        }

        public ArrayList<Project> getChildren()
        {
            return children;
        }

        public int getNumberDependencies()
        {
            return dependencies;
        }
    }

    public static class Graph {

        private ArrayList<Project> nodes = new ArrayList<>();
        private HashMap<String, Project> projectMap = new HashMap<>();

        public Project getOrCreateNode(String name)
        {
            if(!projectMap.containsKey(name))
            {
                Project node = new Project(name);
                nodes.add(node);
                projectMap.put(name, node);
            }
            return projectMap.get(name);
        }

        public void addEdge(String startingName, String endingName)
        {
            Project start = getOrCreateNode(startingName);
            Project end = getOrCreateNode(endingName);
            start.addNeighbor(end);
        }

        public ArrayList<Project> getNodes()
        {
            return nodes;
        }
    }

     static Project[] findOrderOfBuild(String[] projects, String[][] dependencies)
    {
        Graph graph = buildGraph(projects, dependencies);
        return orderProjects(graph.getNodes());
    }

    static Graph buildGraph(String[] projects, String[][] dependencies)
    {
        Graph graph = new Graph();
        for(String proj : projects)
        {
            graph.getOrCreateNode(proj);
        }
        for(String[] dependency : dependencies)
        {
            graph.addEdge(dependency[0], dependency[1]);
        }
        return graph;
    }

    static Project[] orderProjects(ArrayList<Project> projects)
    {
        Project[] order = new Project[projects.size()];
        int endList = addNonDependent(order, projects, 0);
        int toBeProcessed = 0;
        while(toBeProcessed < order.length)
        {
            Project curr = order[toBeProcessed];

            if(curr == null)
            {
                return null;
            }

            ArrayList<Project> children = curr.getChildren();
            for(Project child : children)
            {
                child.decrementDependencies();
            }

            endList = addNonDependent(order, children, endList);
            ++toBeProcessed;
        }
        return order;
    }

    static int addNonDependent(Project[] order, ArrayList<Project> projs, int offset)
    {
        for(Project proj : projs)
        {
            if(proj.getNumberDependencies() == 0)
            {
                order[offset] = proj;
                ++offset;
            }
        }
        return offset;
    }

    /*
     * This is more involved problem so need to test multiple parts
     * Time: O( P + D) where P is projects and D is dependencies
     * Space: O(P + D) (not sure on this one)
     */

    //support
    public static String[] convertToStringList(Project[] projects) {
        String[] buildOrder = new String[projects.length];
        for (int i = 0; i < projects.length; i++) {
            buildOrder[i] = projects[i].getName();
        }
        return buildOrder;
    }

    public static String[] buildOrderWrapper(String[] projects, String[][] dependencies) {
        Project[] buildOrder = findOrderOfBuild(projects, dependencies);
        if (buildOrder == null) return null;
        String[] buildOrderString = convertToStringList(buildOrder);
        return buildOrderString;
    }

    public static void main(String[] args)
    {

    }
}
