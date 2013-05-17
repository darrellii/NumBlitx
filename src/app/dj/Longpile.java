package app.dj;

import java.util.Stack;

public class Longpile
{
  public Stack<Deck.Card> deck;

  public Longpile(Stack<Deck.Card> paramStack)
  {
    this.deck = paramStack;
  }

  public void add(Deck.Card paramCard)
  {
    this.deck.add(paramCard);
  }

  public int getSize()
  {
    return this.deck.size();
  }

  public int gettopcolor()
  {
    return ((Deck.Card)this.deck.peek()).getsuit();
  }

  public int gettopvalue()
  {
    return ((Deck.Card)this.deck.peek()).getvalue();
  }

  public Deck.Card peektopcard()
  {
    return (Deck.Card)this.deck.peek();
  }

  public Deck.Card poptopcard()
  {
    return (Deck.Card)this.deck.pop();
  }

  public boolean slide()
  {
    this.deck.add(0, (Deck.Card)this.deck.pop());
    return true;
  }

  public boolean slidethree()
  {
    for (int i = 0; i < 3; i++)
      this.deck.add(0, (Deck.Card)this.deck.pop());
    return true;
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.Longpile
 * JD-Core Version:    0.6.0
 */