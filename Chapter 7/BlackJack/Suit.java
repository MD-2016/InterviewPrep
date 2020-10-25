package Chapter_7.BlackJack;

public enum Suit {
    Club(0),
    Diamond(1),
    Heart(2),
    Spade(3);

    private int val;
    private Suit(int v)
    {
        val = v;
    }

    public int getVal()
    {
        return val;
    }

    public static Suit getSuitFromVal(int v)
    {
        switch(v)
        {
            case 0:
                return Suit.Club;
            case 1:
                return Suit.Diamond;
            case 2:
                return Suit.Heart;
            case 3:
                return Spade;
            default:
                return null;
        }
    }

}
