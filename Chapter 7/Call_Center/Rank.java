package Chapter_7.Call_Center;

public enum Rank
{
    Responder (0),
    Manager (1),
    Director (2);

    private int val;

    private Rank(int v)
    {
        val = v;
    }

    public int getVal()
    {
        return val;
    }

}
