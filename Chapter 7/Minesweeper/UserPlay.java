package Chapter_7.Minesweeper;

public class UserPlay
{
    private int row;
    private int col;
    private boolean isGuess;

    private UserPlay(int r, int c, boolean guess)
    {
        setRow(r);
        setCol(c);
        isGuess = guess;
    }

    public static UserPlay fromString(String input)
    {
        boolean isGuess = false;

        if(input.length() > 0 && input.charAt(0) == 'B')
        {
            isGuess = true;
            input = input.substring(1);
        }

        if(!input.matches("\\d* \\d+"))
        {
            return null;
        }

        String[] parts = input.split( " ");

        try
        {
            int r = Integer.parseInt(parts[0]);
            int c = Integer.parseInt(parts[1]);
            return new UserPlay(r, c, isGuess);
        }
        catch(NumberFormatException e)
        {
            return null;
        }

    }

    public boolean isGuess()
    {
        return isGuess;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setCol(int c)
    {
        this.col = c;
    }

    public int getCol()
    {
        return col;
    }

    public int getRow()
    {
        return row;
    }

    public boolean isMove()
    {
        return !isMove();
    }

}
