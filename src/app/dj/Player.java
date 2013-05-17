package app.dj;

public class Player
{
  private Brickpiles[] bricks;
  private Deck deck;
  private Longpile longp;
  private int rank;
  private int score;
  private Shortpile shortp;
  private boolean stuck;
  private int team;

  public Player(int paramInt, Deck paramDeck)
  {
    this.team = paramInt;
    this.bricks = new Brickpiles[3];
    for (int i = 0; i < 3; i++)
      this.bricks[i] = new Brickpiles();
    this.deck = paramDeck;
    setpiles();
  }

  private void setpiles()
  {
    this.deck.shuffle();
    this.shortp = new Shortpile(this.deck.getnum(10));
    for (int i = 0; i < 3; i++)
      this.bricks[i].add(this.deck.gettop());
    this.longp = new Longpile(this.deck.getdeck());
  }

  public void addtobrick(int paramInt, Deck.Card paramCard)
  {
    this.bricks[paramInt].add(paramCard);
  }

  public void addtolong(Deck.Card paramCard)
  {
    this.longp.add(paramCard);
  }

  public Brickpiles getbrick(int paramInt)
  {
    return this.bricks[paramInt];
  }

  public Longpile getlong()
  {
    return this.longp;
  }

  public int getrank()
  {
    return this.rank;
  }

  public int getscore()
  {
    return this.score;
  }

  public Shortpile getshort()
  {
    return this.shortp;
  }

  public int getteam()
  {
    return this.team;
  }

  public boolean isStuck()
  {
    return this.stuck;
  }

  public void scorepp()
  {
    this.score = (1 + this.score);
  }

  public void setStuck(boolean paramBoolean)
  {
    this.stuck = paramBoolean;
  }

  public void setrank(int paramInt)
  {
    this.rank = paramInt;
  }

  public void setteam(int paramInt)
  {
    this.team = paramInt;
  }

  public void shuffle()
  {
	  int i;
    int j = this.shortp.getSize();
    for (i = 0; i < 3; i++)
      this.deck.add(getbrick(i).getCard());
    while (!this.shortp.isempty())
      this.deck.add(this.shortp.poptop());
    this.deck.shuffle();
    this.shortp = new Shortpile(this.deck.getnum(j));
    for (i = 0; i < 3; i++)
      this.bricks[i].add(this.deck.gettop());
    this.longp = new Longpile(this.deck.getdeck());
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.Player
 * JD-Core Version:    0.6.0
 */