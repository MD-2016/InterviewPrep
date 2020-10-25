package Chapter_7.JIgsaw;

import java.util.HashMap;
import java.util.Map.Entry;

public class Piece
{
    private final static int NUMBER_OF_EDGES = 4;
    private HashMap<Orientation, Edge> edges = new HashMap<Orientation, Edge>();

    public Piece(Edge[] edgeList)
    {
        Orientation[] orientations = Orientation.values();
        for(int i = 0; i < edgeList.length; i++)
        {
            Edge edge = edgeList[i];
            edge.setParentPiece(this);
            edges.put(orientations[i], edge);
        }
    }

    public void setEdgeAsOrientation(Edge edge, Orientation orientation)
    {
        Orientation current = getOrientation(edge);
        rotateEdgesBy(orientation.ordinal() - current.ordinal());
    }

    private  Orientation getOrientation(Edge e)
    {
        for(Entry<Orientation, Edge> entry : edges.entrySet())
        {
            if(entry.getValue() == e)
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public void rotateEdgesBy(int numRotations)
    {
        Orientation[] orientations = Orientation.values();
        HashMap<Orientation, Edge> rotated = new HashMap<Orientation, Edge>();

        numRotations = numRotations % NUMBER_OF_EDGES;
        if(numRotations < 0)
        {
            numRotations += NUMBER_OF_EDGES;
        }

        for(int i = 0; i < orientations.length; i++)
        {
            Orientation old = orientations[(i - numRotations + NUMBER_OF_EDGES) % NUMBER_OF_EDGES];
            Orientation newOrient = orientations[i];
            rotated.put(newOrient, edges.get(old));
        }
        edges = rotated;
    }

    public boolean isCorner()
    {
        Orientation[] orientations = Orientation.values();
        for(int i = 0; i < orientations.length; i++)
        {
            Shape curr = edges.get(orientations[i]).getShape();
            Shape next = edges.get(orientations[(i + 1) % NUMBER_OF_EDGES]).getShape();
            if(curr == Shape.FLAT && next == Shape.FLAT)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isBorder()
    {
        Orientation[] orient = Orientation.values();
        for(int i = 0; i < orient.length; i++)
        {
            if(edges.get(orient[i]).getShape() == Shape.FLAT)
            {
                return true;
            }
        }
        return false;
    }

    public Edge getEdgeWithOrientation(Orientation orientation)
    {
        return edges.get(orientation);
    }

    public Edge getMatchingEdge(Edge target)
    {
        for(Edge e: edges.values())
        {
            if(target.fitsWith(e))
            {
                return e;
            }
        }
        return null;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        Orientation[] orientations = Orientation.values();
        for(Orientation o : orientations)
        {
            sb.append(edges.get(o).toString() + ",");
        }
        return "[" + sb.toString() + "]";
    }

}
