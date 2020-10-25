package Chapter_7.JIgsaw;

import java.util.LinkedList;

public class Puzzle {
    private LinkedList<Piece> pieces;
    private Piece[][] solution;
    private int size;

    public Puzzle(int size, LinkedList<Piece> pieces)
    {
        this.pieces = pieces;
        this.size = size;
    }

    public void groupPieces(LinkedList<Piece> cornerPieces, LinkedList<Piece> borderPieces, LinkedList<Piece> insidePieces)
    {
        for(Piece p : pieces)
        {
            if(p.isCorner())
            {
                cornerPieces.add(p);
            }
            else if(p.isBorder())
            {
                borderPieces.add(p);
            }
            else
            {
                insidePieces.add(p);
            }
        }
    }

    public void orientTopLeftCorner(Piece p)
    {
        if(!p.isCorner())
        {
            return;
        }

        Orientation[] orientations = Orientation.values();
        for(int i = 0; i < orientations.length; i++)
        {
            Edge curr = p.getEdgeWithOrientation(orientations[i]);
            Edge next = p.getEdgeWithOrientation(orientations[(i + 1) % orientations.length]);
            if(curr.getShape() == Shape.FLAT && next.getShape() == Shape.FLAT)
            {
                p.setEdgeAsOrientation(curr, Orientation.LEFT);
                return;
            }
        }
    }

    public boolean isBorderIndex(int loc)
    {
        return loc == 0 || loc == size - 1;
    }

    private Edge getMatchingEdge(Edge target, LinkedList<Piece> pieces)
    {
        for(Piece p : pieces)
        {
            Edge matching = p.getMatchingEdge(target);
            if(matching != null)
            {
                return matching;
            }
        }
        return null;
    }

    private void setEdgeInSolution(LinkedList<Piece> pieces, Edge edge, int row, int column, Orientation orient)
    {
        Piece p = edge.getParentPiece();
        p.setEdgeAsOrientation(edge, orient);
        pieces.remove(p);
        solution[row][column] = p;
    }

    private LinkedList<Piece> getPieceListToSearch(LinkedList<Piece> cornerPieces, LinkedList<Piece> borderPieces, LinkedList<Piece> insidePieces, int row, int column)
    {
        if(isBorderIndex(row) && isBorderIndex(column))
        {
            return cornerPieces;
        }
        else if(isBorderIndex(row) || isBorderIndex(column))
        {
            return borderPieces;
        }
        else
        {
            return insidePieces;
        }
    }

    private boolean fitNextEdge(LinkedList<Piece> piecesToSearch, int row, int column)
    {
        if(row == 0 && column == 0)
        {
            Piece p = piecesToSearch.remove();
            orientTopLeftCorner(p);
            solution[0][0] = p;
        }
        else
        {
            Piece pieceToMatch = column == 0 ? solution[row - 1][0] : solution[row][column - 1];
            Orientation orientationToMatch = column == 0 ? Orientation.BOTTOM : Orientation.RIGHT;
            Edge edgeToMatch = pieceToMatch.getEdgeWithOrientation(orientationToMatch);

            Edge edge = getMatchingEdge(edgeToMatch, piecesToSearch);
            if(edge == null) return false;

            Orientation orientation = orientationToMatch.getOpposite();
            setEdgeInSolution(piecesToSearch, edge, row, column, orientation);
        }
        return true;
    }

    public boolean solve()
    {
        LinkedList<Piece> cornerPieces = new LinkedList<>();
        LinkedList<Piece> borderPieces = new LinkedList<>();
        LinkedList<Piece> insidePieces = new LinkedList<>();
        groupPieces(cornerPieces, borderPieces, insidePieces);

        solution = new Piece[size][size];
        for(int row = 0; row < size; row++)
        {
            for(int column = 0; column < size; column++)
            {
                LinkedList<Piece> piecesToSearch = getPieceListToSearch(cornerPieces, borderPieces, insidePieces, row, column);
                if(!fitNextEdge(piecesToSearch, row, column))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Piece[][] getCurrentSolution()
    {
        return solution;
    }

}
