package Chapter_7.BlackJack;

public abstract class Card {

    private boolean available = true;

    protected int faceVal;
    protected Suit suit;

    public Card(int c, Suit s)
    {
        faceVal = c;
        suit = s;
    }

    public abstract int value();

    public Suit suit()
    {
        return suit;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void markUnAvailable()
    {
        available = false;
    }

    public void markAvailable()
    {
        available = true;
    }

    public void print()
    {
        String[] faceValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
        System.out.print(faceValues[faceVal-1]);
        switch(suit)
        {
            case Club:
                System.out.print("c");
                break;
            case Heart:
                System.out.print("h");
                break;
            case Diamond:
                System.out.print("d");
                break;
            case Spade:
                System.out.print("s");
                break;
        }
        System.out.print(" ");
    }

}
