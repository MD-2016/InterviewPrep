package Chapter_7.JIgsaw;

public class Edge {
    private Shape shape;
    private String code;
    private Piece parentPiece;

    public Edge(Shape s, String code) {
        this.shape = s;
        this.code = code;
    }

    private String getCode() {
        return code;
    }

    public Edge _createMatchingEdge()
    {
        if(shape == Shape.FLAT)
        {
            return null;
        }
        return new Edge(shape.getOpposite(), getCode());
    }

    public boolean fitsWith(Edge edge)
    {
        return edge.getCode().equals(getCode());
    }

    public void setParentPiece(Piece parent)
    {
        this.parentPiece = parent;
    }

    public Piece getParentPiece()
    {
        return parentPiece;
    }

    public Shape getShape()
    {
        return shape;
    }

    public String toString()
    {
        return code;
    }

}
