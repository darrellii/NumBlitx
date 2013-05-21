package app.dj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import app.dj.Deck.Card;

public class Deck
{
  Stack<Card> deck;
  int decksize;
  Stack<Card> discard;
  int max;
  int suits;

  public Deck()
  {
    this.decksize = 52;
    this.max = 12;
    this.suits = 4;
    this.deck = new Stack<Card>();
    this.discard = new Stack<Card>();
    initalize();
  }

  public Deck(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 == paramInt2 * paramInt3)
    {
      this.decksize = paramInt1;
      this.max = paramInt2;
      this.suits = paramInt3;
      this.deck = new Stack<Card>();
      this.discard = new Stack<Card>();
      initalize();
      shuffle();
      
      return;
    }
    throw new Error("max value and nuber of suits do not mach up with deck size.");
  }

  private void initalize()
  {
	
    int j = 0;
    for (int m = 0; m < this.suits; m++)
    {
      int i = 1;
      while (i <= this.max)
      {
        Stack<Card> localStack = this.deck;
        int k = j + 1;
        localStack.push(new Card(m, i, j));
        i++;
        j = k;
      }
    }
  }

  public void add(Card paramCard)
  {
    this.deck.push(paramCard);
  }

  public Stack<Card> getdeck()
  {
    return this.deck;
  }

  public Stack<Card> getnum(int paramInt)
  {
    Stack<Card> localStack = new Stack<Card>();
    for (int i = 0; i < paramInt; i++)
    {
      Card localCard = (Card)this.deck.pop();
      this.discard.push(localCard);
      localStack.push(localCard);
    }
    return localStack;
  }

  public Stack<Card> getthree()
  {
    return getnum(3);
  }

  public Card gettop()
  {
    Card localCard = (Card)this.deck.pop();
    this.discard.push(localCard);
    return localCard;
  }

  public void shuffle()
  {
    Random localRandom = new Random(System.currentTimeMillis());
    for (int i = 0; i < this.deck.size(); i++)
      this.deck.add(localRandom.nextInt(this.deck.size()), (Card)this.deck.remove(i));
  }

  public String toString()
  {
    String str = "";
    LinkedList<Card> localLinkedList = new LinkedList<Card>();
    for (int j = 0; j < this.decksize; j++)
    {
      Card localCard = (Card)this.deck.pop();
      str = str + "\n" + localCard.toString();
      localLinkedList.add(localCard);
    }
    for (int i = 0; i < this.decksize; i++)
      this.deck.push((Card)localLinkedList.poll());
    return str;
  }

  public class Card
  {
    int id;
    int suit;
    int value;

    public Card(int suit, int value, int id)
    {
      this.suit = suit;
      this.value = value;
      this.id = id;
    }

    public int getid()
    {
      return this.id;
    }

    public int getsuit()
    {
      return this.suit;
    }

    public int getvalue()
    {
      return this.value;
    }

    public void setid(int paramInt)
    {
      this.id = paramInt;
    }

    public void setsuit(int paramInt)
    {
      this.suit = paramInt;
    }

    public void setvalue(int paramInt)
    {
      this.value = paramInt;
    }

    public String toString()
    {
      return "**card id: " + this.id + "\n  suit: " + this.suit + "\n  value: " + this.value + "\n\n";
    }
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.Deck
 * JD-Core Version:    0.6.0
 */