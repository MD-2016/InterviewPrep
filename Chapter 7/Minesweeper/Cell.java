package Chapter_7.Minesweeper;

public class Cell
{
    private int row;
    private int col;
    private int num;
    private boolean isBomb;
    private boolean isExposed = false;
    private boolean isGuess = false;

    public Cell(int r, int c)
    {
        isBomb = false;
        num = 0;
        row = r;
        col = c;
    }

    public void setRowAndColumn(int r, int c)
    {
        row = r;
        col = c;
    }

    public void setBomb(boolean bomb)
    {
        isBomb = bomb;
        num = -1;
    }

    public void incrementNumber()
    {
        num++;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return col;
    }

    public boolean isBomb()
    {
        return isBomb;
    }

    public boolean isBlank()
    {
        return num == 0;
    }

    public boolean isExposed()
    {
        return isExposed;
    }

    public boolean flip()
    {
        isExposed = true;
        return !isBomb;
    }

    public boolean toggleGuess()
    {
        if(!isExposed)
        {
            isGuess = !isGuess;
        }
        return isGuess;
    }

    public boolean isGuess()
    {
        return isGuess;
    }

    @Override
    public String toString()
    {
        return getStateUnder();
    }

    public String getSurface()
    {
        if(isExposed)
        {
            return getStateUnder();
        }
        else if(isGuess)
        {
            return "B ";
        }
        else
        {
            return "? ";
        }
    }

    public String getStateUnder()
    {
        if(isBomb)
        {
            return "* ";
        }
        else if(num > 0)
        {
            return Integer.toString(num) + " ";
        }
        else
        {
            return "  ";
        }
    }

}
