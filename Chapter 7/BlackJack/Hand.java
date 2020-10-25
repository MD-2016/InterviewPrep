package Chapter_7.BlackJack;

import java.util.ArrayList;

public class Hand <T extends Card> {

    protected ArrayList<T> cards = new ArrayList<T>();

    public int score()
    {
        int score = 0;
        for(T card : cards)
        {
            score += card.value();
        }
        return score;
    }

    public void addCard(T c)
    {
        cards.add(c);
    }

    public void print()
    {
        for(Card c : cards)
        {
            c.print();
        }
    }



}
