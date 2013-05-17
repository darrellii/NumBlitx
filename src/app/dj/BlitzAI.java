package app.dj;

import android.widget.Button;
import java.util.Random;

public class BlitzAI implements Runnable {
	private Game game;
	private Boolean gameover;
	private int lvl;
	private boolean pause;
	private Gamepiles[][] piles;
	private int slides;
	private Player thisp;

	public BlitzAI(Player paramPlayer, Gamepiles[][] paramArrayOfGamepiles,
			Game paramGame, int paramInt) {
		this.thisp = paramPlayer;
		this.piles = paramArrayOfGamepiles;
		this.gameover = Boolean.valueOf(false);
		this.game = paramGame;
		this.slides = 0;
		this.lvl = paramInt;
		this.pause = false;
	}

	private boolean checkBricks() {
		int i = 0;
		while (i < 3) {
			if (!checkPiles(this.thisp.getbrick(i).getCard())) {
				i++;
				continue;
			}
			this.thisp.getbrick(i).remove();
			if (this.thisp.getshort().getSize() == 0)
				this.game.endGame();
			else
				this.thisp.getbrick(i).add(this.thisp.getshort().poptop());
			i = 1;
			break;
		}
		i = 0;
		return i == 1;
	}

	private boolean checkLong() {
		int i;
		if (!checkPiles(this.thisp.getlong().peektopcard())) {
			this.thisp.getlong().slide();
			i = 0;
		} else {
			this.thisp.getlong().poptopcard();
			i = 1;
		}
		return i == 1;
	}

	private boolean checkPiles(Deck.Card paramCard) {
		int k = 1;
		int j = 0;
		while (true) {
			if (j < this.piles.length) {
				int i = 0;
				while (i < this.piles[0].length) {
					if (((1 + this.piles[j][i].gettopvalue() != paramCard
							.getvalue()) || (this.piles[j][i].getcolor() != paramCard
							.getsuit()))
							&& ((this.piles[j][i].gettopvalue() != 0) || (paramCard
									.getvalue() != k))) {
						i++;
						continue;
					}
					this.piles[j][i].add(paramCard);
					play(j, i, paramCard);
					break;
				}
				j++;
				continue;
			}
			k = 0;
		}
	}

	private boolean checkShort() {
		int i = 0;
		if (!this.thisp.getshort().isempty()) {
			if (checkPiles(this.thisp.getshort().peektop())) {
				this.thisp.getshort().poptop();
				i = 1;
			}
		} else
			this.gameover = Boolean.valueOf(true);
		return i == 1;
	}

	private void play(final int i, final int j, final Deck.Card c) {
		this.slides = 0;
		this.game.getButtonMap()[i][j].post(new Runnable() {
			public void run() {
				BlitzAI.this.game.getButtonMap()[i][j].setText(c.getvalue());
				BlitzAI.this.game.getButtonMap()[i][j].setBackgroundColor(Game
						.myColor(c.getsuit()));
				BlitzAI.this.gameover = Boolean.valueOf(BlitzAI.this.thisp
						.getshort().isempty());
				if (BlitzAI.this.gameover.booleanValue())
					BlitzAI.this.game.endGame();
				if (BlitzAI.this.thisp.getshort().getSize() == 0)
					BlitzAI.this.game.endGame();
			}
		});
		try {
			Thread.sleep(1000 * (1 + new Random().nextInt(this.lvl)));
			return;
		} catch (InterruptedException localInterruptedException) {
			while (true)
				localInterruptedException.printStackTrace();
		}
	}

	public void checkstuck() {
		if (this.slides >= this.thisp.getlong().getSize() / 3)
			this.thisp.setStuck(true);
	}

	public void endGame() {
		this.gameover = Boolean.valueOf(true);
	}

	public boolean getgameend() {
		return this.gameover.booleanValue();
	}

	public Player getplayer() {
		return this.thisp;
	}

	public boolean getstate() {
		return this.pause;
	}

	public boolean isStuck() {
		return this.thisp.isStuck();
	}

	public void onpause() {
		while (this.pause)
			;
	}

	public void pause() {
		boolean bool;
		if (!this.pause)
			bool = true;
		else
			bool = false;
		this.pause = bool;
	}

	public void run() {
		while (true) {
			if (this.gameover.booleanValue())
				return;
			try {
				Thread.sleep(1000 * (1 + new Random().nextInt(this.lvl)));
				if (checkShort())
					this.thisp.scorepp();

				if (checkBricks()) {
					this.thisp.scorepp();

				}
				if (checkLong()) {
					this.thisp.scorepp();
					
				}
				this.thisp.getlong().slide();
				this.slides = (1 + this.slides);
			} catch (InterruptedException localInterruptedException) {

			}
		}

	}

	public void setStuck(boolean paramBoolean) {
		this.thisp.setStuck(paramBoolean);
	}

	public void zerolevel() {
		this.lvl = -1;
	}
}

/*
 * Location: C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name: app.dj.BlitzAI JD-Core Version: 0.6.0
 */