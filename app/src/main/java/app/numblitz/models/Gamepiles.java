package app.numblitz.models;

import java.util.Stack;

public class Gamepiles {
    public int color;
    public boolean empty = true;
    public Stack<Deck.Card> gamestack = new Stack();
    public int topvalue = 0;

    public boolean add(Deck.Card paramCard) {
        int i = 1;
        if (!this.empty) {
            if ((paramCard.getsuit() != this.color) || (paramCard.getvalue() != 1 + this.topvalue)) {
                i = 0;
            } else {
                this.gamestack.push(paramCard);
                this.topvalue = (1 + this.topvalue);
            }
        } else if (paramCard.getvalue() != i) {
            i = 0;
        } else {
            this.gamestack.push(paramCard);
            this.topvalue = (1 + this.topvalue);
            this.color = paramCard.getsuit();
            this.empty = false;
        }
        return i > 0;
    }

    public int getcolor() {
        return this.color;
    }

    public Deck.Card gettop() {
        return (Deck.Card) this.gamestack.peek();
    }

    public int gettopvalue() {
        return this.topvalue;
    }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.models.Gamepiles
 * JD-Core Version:    0.6.0
 */