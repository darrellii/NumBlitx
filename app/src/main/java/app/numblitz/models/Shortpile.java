package app.numblitz.models;

import java.util.Stack;

public class Shortpile {
    private boolean empty;
    private Stack<Deck.Card> shorty;

    public Shortpile(Stack<Deck.Card> paramStack) {
        if (paramStack.size() == 10) {
            this.shorty = paramStack;
            this.empty = false;
        }
    }

    public int getSize() {
        return this.shorty.size();
    }

    public boolean isempty() {
        return this.empty;
    }

    public Deck.Card peektop() {
        return (Deck.Card) this.shorty.peek();
    }

    public int pntLeft() {
        return 2 * this.shorty.size();
    }

    public Deck.Card poptop() {
        if (this.shorty.size() == 1)
            this.empty = true;
        return (Deck.Card) this.shorty.pop();
    }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.models.Shortpile
 * JD-Core Version:    0.6.0
 */