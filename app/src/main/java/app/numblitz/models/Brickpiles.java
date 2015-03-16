package app.numblitz.models;

public class Brickpiles {
	public int cardcolor = -1;
	public int cardvalue = -1;
	public Deck.Card held = null;
	public boolean isfull = false;

	public boolean add(Deck.Card paramCard) {
		boolean bool = true;
		if (!this.isfull) {
			this.held = paramCard;
			this.cardvalue = paramCard.getvalue();
			this.cardcolor = paramCard.getsuit();
			this.isfull = bool;
		} else {
			bool = false;
		}
		return bool;
	}

	public Deck.Card getCard() {
		return this.held;
	}

	public int getcolor() {
		return this.cardcolor;
	}

	public int getvalue() {
		return this.cardvalue;
	}

	public boolean isempty() {
		int i;
		if (!this.isfull)
			i = 1;
		else
			i = 0;
		return i == 1;
	}

	public boolean remove() {
		int i = 0;
		if (this.isfull) {
			this.held = null;
			this.cardvalue = -1;
			this.cardcolor = -1;
			this.isfull = false;
			i = 1;
		}
		return i == 1;
	}
}

/*
 * Location: C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name: app.dj.models.Brickpiles JD-Core Version: 0.6.0
 */