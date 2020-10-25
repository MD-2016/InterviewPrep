package Chapter_7.BlackJack;

public class BlackJackCard extends Card {

    public BlackJackCard(int c, Suit s)
    {
        super(c,s);
    }

    public int value()
    {
        if(isAce())
        {
            return 1;
        }
        else if(isFaceCard())
        {
            return 10;
        }
        else
        {
            return faceVal;
        }

    }

    public int minValue()
    {
        if(isAce())
        {
            return 1;
        }
        else
        {
            return value();
        }
    }

    public int maxValue()
    {
        if(isAce())
        {
            return 11;
        }
        else
        {
            return value();
        }
    }

    public boolean isAce()
    {
        return faceVal == 1;
    }

    public boolean isFaceCard()
    {
        return faceVal >= 11 && faceVal <= 13;
    }



}
