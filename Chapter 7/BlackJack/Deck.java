package Chapter_7.BlackJack;

import java.util.ArrayList;

public class Deck <T extends Card> {

    //support methods
    public static int randomInt(int n)
    {
        return (int) (Math.random() * n);
    }

    public int randomIntInRange(int min, int max)
    {
        return randomInt(max + 1 - min) + min;
    }


    private ArrayList<T> cards;
    private int undealtCard = 0;

    public Deck()
    {

    }

    public void setDeckOfCards(ArrayList<T> deckOfCards)
    {
        cards = deckOfCards;
    }

    public void shuffle()
    {
        for(int i = 0; i < cards.size(); i++)
        {
            int j = randomIntInRange(i, cards.size() - i - 1);
            T c1 = cards.get(i);
            T c2 = cards.get(j);
            cards.set(i, c2);
            cards.set(j, c1);
        }
    }

    public int remainingCards()
    {
        return cards.size() - undealtCard;
    }

    public T[] dealHand(int n)
    {
        if(remainingCards() < n)
        {
            return null;
        }

        T[] hand = (T[]) new Card[n];
        int count = 0;
        while(count < n)
        {
            T card = dealCard();
            if(card != null)
            {
                hand[count] = card;
                count++;
            }
        }
        return hand;
    }

    public T dealCard()
    {
        if(remainingCards() == 0)
        {
            return null;
        }
        T card = cards.get(undealtCard);
        card.markUnAvailable();
        undealtCard++;

        return card;
    }

    public void print()
    {
        for(Card card : cards)
        {
            card.print();
        }
    }

}
