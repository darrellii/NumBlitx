package app.dj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import java.lang.reflect.Array;

public class Game extends Activity implements OnClickListener {
	private BlitzAI[] ai;
	private int ailvl;
	private Button[][] bpiles;
	private Button brick0;
	private Button brick1;
	private Button brick2;
	private boolean firstclick;
	private Button firstclicked;
	private boolean gameover;
	private Button longp;
	private int numplayaz;
	private Player[] oponants;
	private Gamepiles[][] piles;
	private PowerManager pm;
	private Button shortp;
	private Thread[] tai;
	private int textsize;
	private Player thisp;
	private LinearLayout vp2;
	private LinearLayout vp3;
	private LinearLayout vp4;
	private PowerManager.WakeLock wl;

	private void creatOponents() {
		int m = ((MyReferences) getApplication()).getColor();
		int i = ((MyReferences) getApplication()).getNumplayers();
		this.oponants = new Player[i - 1];
		this.ai = new BlitzAI[i - 1];
		this.thisp = new Player(m, new Deck(40, 10, 4));
		int k = 0;
		for (int j = 0; j < i - 1; j++) {
			if (k == m)
				k++;
			this.oponants[j] = new Player(k, new Deck(40, 10, 4));
			k++;
			((MyReferences) getApplication()).setPlaya(
					this.oponants[j].getteam(), this.oponants[j]);
			this.ai[j] = new BlitzAI(this.oponants[j], this.piles, this,
					this.ailvl);
		}
		((MyReferences) getApplication()).setPlaya(this.thisp.getteam(),
				this.thisp);
	}

	private synchronized boolean gameend() {
		int i = 0;
		if ((this.ai[0].getgameend()) || (this.ai[0].getgameend())
				|| (this.ai[0].getgameend()) || (this.gameover))
			i = 1;
		return i == 1;
	}

	private void linkonclick() {
		switch (this.numplayaz) {
		case 2:
			this.vp2 = ((LinearLayout) findViewById(2131099655));
			this.vp2.setVisibility(0);
			break;
		case 3:
			this.vp3 = ((LinearLayout) findViewById(2131099664));
			this.vp3.setVisibility(0);
			break;
		case 4:
			this.vp4 = ((LinearLayout) findViewById(2131099677));
			this.vp4.setVisibility(0);
		}
		for (int i = 0; i < this.piles.length; i++)
			for (int j = 0; j < this.piles[0].length; j++) {
				int k = getResources().getIdentifier(
						"b" + this.numplayaz + Integer.toString(i)
								+ Integer.toString(j), "id", "app.dj");
				this.bpiles[i][j] = ((Button) findViewById(k));
				this.bpiles[i][j].setOnClickListener(this);
			}
		this.longp = ((Button) findViewById(app.dj.R.id.blongp));
		this.shortp = ((Button) findViewById(app.dj.R.id.bshortp));
		this.brick0 = ((Button) findViewById(app.dj.R.id.bbrick0));
		this.brick1 = ((Button) findViewById(app.dj.R.id.bbrick1));
		this.brick2 = ((Button) findViewById(app.dj.R.id.bbrick2));
		this.longp.setOnClickListener(this);
		this.shortp.setOnClickListener(this);
		this.brick0.setOnClickListener(this);
		this.brick1.setOnClickListener(this);
		this.brick2.setOnClickListener(this);
	}

	private void makegamepiles() {
		int[] arrayOfInt;
		switch (this.numplayaz) {
		case 2:
			arrayOfInt = new int[2];
			arrayOfInt[0] = 2;
			arrayOfInt[1] = 4;
			this.piles = ((Gamepiles[][]) Array.newInstance(Gamepiles.class,
					arrayOfInt));
			arrayOfInt = new int[2];
			arrayOfInt[0] = 2;
			arrayOfInt[1] = 4;
			this.bpiles = ((Button[][]) Array.newInstance(Button.class,
					arrayOfInt));
			break;
		case 3:
			arrayOfInt = new int[2];
			arrayOfInt[0] = 3;
			arrayOfInt[1] = 4;
			this.piles = ((Gamepiles[][]) Array.newInstance(Gamepiles.class,
					arrayOfInt));
			arrayOfInt = new int[2];
			arrayOfInt[0] = 3;
			arrayOfInt[1] = 4;
			this.bpiles = ((Button[][]) Array.newInstance(Button.class,
					arrayOfInt));
			break;
		case 4:
			arrayOfInt = new int[2];
			arrayOfInt[0] = 3;
			arrayOfInt[1] = 5;
			this.piles = ((Gamepiles[][]) Array.newInstance(Gamepiles.class,
					arrayOfInt));
			arrayOfInt = new int[2];
			arrayOfInt[0] = 3;
			arrayOfInt[1] = 5;
			this.bpiles = ((Button[][]) Array.newInstance(Button.class,
					arrayOfInt));
		}
	}

	public static int myColor(int paramInt) {
		int i = 0;
		switch (paramInt) {
		case 0:
			i = Color.rgb(255, 204, 51);
			break;
		case 1:
			i = Color.rgb(186, 85, 211);
			break;
		case 2:
			i = Color.rgb(249, 77, 0);
			break;
		case 3:
			i = Color.rgb(152, 251, 152);
		}
		return i;
	}

	private synchronized void onsecondclick(View firstClick, View secondClick) {
		
		if(firstClick == null){
			this.firstclick = true;
			onClick(secondClick);
			return;
		}
		
		Deck.Card localCard = null;
		((Button) firstClick).setTextSize(this.textsize);
		switch (firstClick.getId()) {
		case app.dj.R.id.bbrick0:
			if (this.thisp.getbrick(0).isempty())
				break;
			localCard = this.thisp.getbrick(0).getCard();
			break;
		case app.dj.R.id.bbrick1:
			if (this.thisp.getbrick(1).isempty())
				break;
			localCard = this.thisp.getbrick(1).getCard();
			break;
		case app.dj.R.id.bbrick2:
			if (this.thisp.getbrick(2).isempty())
				break;
			localCard = this.thisp.getbrick(2).getCard();
			break;
		case app.dj.R.id.blongp:
			localCard = this.thisp.getlong().poptopcard();
			break;
		case app.dj.R.id.bshortp:
			localCard = this.thisp.getshort().poptop();
		}
		int i = 0;

		// handle when second = piles
		switch (secondClick.getId()) {
		case app.dj.R.id.bbrick0:
			if (!this.thisp.getbrick(0).isempty())
				return;
			this.thisp.addtobrick(0, localCard);
			secondClick.setBackgroundColor(myColor(localCard.getsuit()));
			((Button) findViewById(app.dj.R.id.bbrick0)).setText(Integer
					.toString(localCard.getvalue()));
			resetfirstclick(firstClick);
			return;
		case app.dj.R.id.bbrick1:
			if (!this.thisp.getbrick(1).isempty())
				return;
			this.thisp.addtobrick(1, localCard);
			secondClick.setBackgroundColor(myColor(localCard.getsuit()));
			((Button) findViewById(app.dj.R.id.bbrick1)).setText(Integer
					.toString(localCard.getvalue()));
			resetfirstclick(firstClick);
			return;
		case app.dj.R.id.bbrick2:
			if (!this.thisp.getbrick(2).isempty())
				return;
			this.thisp.addtobrick(2, localCard);
			secondClick.setBackgroundColor(myColor(localCard.getsuit()));
			((Button) findViewById(app.dj.R.id.bbrick2)).setText(Integer
					.toString(localCard.getvalue()));
			resetfirstclick(firstClick);
			return;
		case app.dj.R.id.blongp:
			if (firstClick.getId() != app.dj.R.id.blongp)
				return;
			this.thisp.addtolong(localCard);
			this.thisp.getlong().slide();
			this.longp.setText(Integer.toString(this.thisp.getlong()
					.gettopvalue()));
			this.longp.setBackgroundColor(myColor(this.thisp.getlong()
					.gettopcolor()));
			this.longp.setTextSize(this.textsize);
			resetfirstclick(firstClick);
			return;
		case app.dj.R.id.bshortp:
			if (this.thisp.getshort().getSize() == 0)
				endGame();
			return;

		}

		while (i < this.piles.length) {
			int j = 0;
			while (j < this.piles[0].length) {
				if (this.bpiles[i][j].getId() != secondClick.getId()) {
					j++;
					continue;
				}
				if (this.piles[i][j].gettopvalue() != 0
						&& this.piles[i][j].getcolor() != localCard.getsuit()) {
					return;
				}
				if (1 + this.piles[i][j].gettopvalue() != localCard.getvalue()) {
					// TODO reset first
					return;
				}

				this.piles[i][j].add(localCard);
				this.bpiles[i][j].setText(Integer.toString(this.piles[i][j]
						.gettopvalue()));
				this.bpiles[i][j].setBackgroundColor(myColor(this.piles[i][j]
						.getcolor()));
				resetfirstclick(firstClick);
				this.thisp.scorepp();
				this.gameover = this.thisp.getshort().isempty();
				return;
			}
			i++;
		}

	}

	private synchronized void resetfirstclick(View paramView) {
		switch (paramView.getId()) {
		case app.dj.R.id.bbrick0:
			this.brick0.setText("");
			this.brick0.setBackgroundColor(-1);
			this.thisp.getbrick(0).remove();
			this.brick0.setTextSize(this.textsize);
			break;
		case app.dj.R.id.bbrick1:
			this.brick1.setText("");
			this.brick1.setBackgroundColor(-1);
			this.thisp.getbrick(1).remove();
			this.brick1.setTextSize(this.textsize);
			break;
		case app.dj.R.id.bbrick2:
			this.brick2.setText("");
			this.brick2.setBackgroundColor(-1);
			this.thisp.getbrick(2).remove();
			this.brick2.setTextSize(this.textsize);
			break;
		case app.dj.R.id.blongp:
			this.longp.setBackgroundColor(myColor(this.thisp.getlong()
					.gettopcolor()));
			this.longp.setText(Integer.toString(this.thisp.getlong()
					.gettopvalue()));
			this.longp.setTextSize(this.textsize);

			break;
		case app.dj.R.id.bshortp:
			if (this.thisp.getshort().getSize() == 0) {
				this.shortp.setBackgroundColor(Color.rgb(207, 207, 207));
				this.shortp.setText("BLITZ IT!");
			} else {
				this.shortp.setBackgroundColor(myColor(this.thisp.getshort()
						.peektop().getsuit()));
				this.shortp.setText(Integer.toString(this.thisp.getshort()
						.peektop().getvalue()));
				this.shortp.setTextSize(this.textsize);
			}
		}

		this.firstclicked = null;
	}

	private void setGame() {
		this.brick0.setBackgroundColor(myColor(this.thisp.getbrick(0)
				.getcolor()));
		this.brick1.setBackgroundColor(myColor(this.thisp.getbrick(1)
				.getcolor()));
		this.brick2.setBackgroundColor(myColor(this.thisp.getbrick(2)
				.getcolor()));
		this.longp.setBackgroundColor(myColor(this.thisp.getlong()
				.gettopcolor()));
		this.shortp.setBackgroundColor(myColor(this.thisp.getshort().peektop()
				.getsuit()));
		this.brick0
				.setText(Integer.toString(this.thisp.getbrick(0).getvalue()));
		this.brick1
				.setText(Integer.toString(this.thisp.getbrick(1).getvalue()));
		this.brick2
				.setText(Integer.toString(this.thisp.getbrick(2).getvalue()));
		this.longp
				.setText(Integer.toString(this.thisp.getlong().gettopvalue()));
		this.shortp.setText(Integer.toString(this.thisp.getshort().peektop()
				.getvalue()));
		for (int i = 0; i < this.piles.length; i++)
			for (int j = 0; j < this.piles[0].length; j++) {
				this.piles[i][j] = new Gamepiles();
				this.bpiles[i][j].setBackgroundColor(-1);
				this.bpiles[i][j].setText("0");
			}
	}

	public synchronized void endGame() {
		for (int i = 0; i < -1 + this.numplayaz; i++) {
			this.ai[i].zerolevel();
			this.ai[i].endGame();
			//while (this.tai[i].isAlive());
		}
		startActivity(new Intent("app.dj.GAMESTATS"));
	}

	public Button[][] getButtonMap() {
		return this.bpiles;
	}

	public int getNumplayaz() {
		return this.numplayaz;
	}

	public void onClick(View paramView) {
		boolean bool = false;
		if (gameend())
			endGame();
		if ((paramView.getId() == app.dj.R.id.bshortp)
				&& (this.thisp.getshort().getSize() == 0))
			endGame();

		if (!firstclick) {// secondclick
			onsecondclick(this.firstclicked, paramView);
			bool = !firstclick;
		} else {// first click
			if (paramView.getId() == (app.dj.R.id.bbrick0)
					|| paramView.getId() == (app.dj.R.id.bbrick1)
					|| paramView.getId() == (app.dj.R.id.bbrick2)
					|| paramView.getId() == (app.dj.R.id.bshortp)
					|| paramView.getId() == (app.dj.R.id.blongp)) {
				this.firstclicked = ((Button) paramView);
				this.firstclicked.setTextSize(2 * this.textsize);
				bool = !firstclick;
			} else {
				// do nothing
			}
		}
		this.firstclick = bool;
	}

	public void onCreate(Bundle paramBundle) {

		this.pm = ((PowerManager) getSystemService("power"));
		this.wl = this.pm.newWakeLock(6, "My Tag");
		this.numplayaz = ((MyReferences) getApplication()).getNumplayers();
		this.ailvl = ((MyReferences) getApplication()).getLevel();
		this.gameover = false;
		super.onCreate(paramBundle);
		setContentView(app.dj.R.layout.game);
		this.firstclick = true;
		makegamepiles();
		linkonclick();
		creatOponents();
		setGame();
		findViewById(2131099694).setBackgroundColor(
				myColor(((MyReferences) getApplication()).getColor()));
		findViewById(2131099695).setBackgroundColor(
				myColor(((MyReferences) getApplication()).getColor()));
		findViewById(2131099696).setBackgroundColor(
				myColor(((MyReferences) getApplication()).getColor()));
		this.tai = new Thread[-1 + this.numplayaz];
		for (int i = 0; i < -1 + this.numplayaz; i++) {
			this.tai[i] = new Thread(this.ai[i]);
			this.tai[i].start();
		}
		this.textsize = (-2 + (int) this.brick0.getTextSize());
	}

	protected void onPause() {
		super.onPause();
		finish();
	}

	public void onstuck() {
		int i;
		for (i = 0; i < -1 + this.numplayaz; i++)
			this.ai[i].pause();
		new AlertDialog.Builder(this)
				.setTitle("The AI is stuck!")
				.setMessage(
						"One of the AI you are playing is unable to place any cards and is stuck! \n\n Are you stuck? If so click 'shuffle' and I'll suffle everyones cards quick but not mess up the game piles!\n\n \tGOOD LUCK!!!")
				.setPositiveButton("SHUFFLE!",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface paramDialogInterface,
									int paramInt) {
								Game.this.thisp.shuffle();
								for (int i = 0; i < -1 + Game.this.numplayaz; i++)
									Game.this.ai[i].getplayer().shuffle();
								Game.this.brick0.setBackgroundColor(Game
										.myColor(Game.this.thisp.getbrick(0)
												.getcolor()));
								Game.this.brick1.setBackgroundColor(Game
										.myColor(Game.this.thisp.getbrick(1)
												.getcolor()));
								Game.this.brick2.setBackgroundColor(Game
										.myColor(Game.this.thisp.getbrick(2)
												.getcolor()));
								Game.this.longp.setBackgroundColor(Game
										.myColor(Game.this.thisp.getlong()
												.gettopcolor()));
								Game.this.shortp.setBackgroundColor(Game
										.myColor(Game.this.thisp.getshort()
												.peektop().getsuit()));
								Game.this.brick0.setText(Integer
										.toString(Game.this.thisp.getbrick(0)
												.getvalue()));
								Game.this.brick1.setText(Integer
										.toString(Game.this.thisp.getbrick(1)
												.getvalue()));
								Game.this.brick2.setText(Integer
										.toString(Game.this.thisp.getbrick(2)
												.getvalue()));
								Game.this.longp.setText(Integer
										.toString(Game.this.thisp.getlong()
												.gettopvalue()));
								Game.this.shortp.setText(Integer
										.toString(Game.this.thisp.getshort()
												.peektop().getvalue()));
							}
						})
				.setNegativeButton("KEEP BEATING THE AI!",
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface paramDialogInterface,
									int paramInt) {
							}
						}).show();
		for (i = 0; i < -1 + this.numplayaz; i++)
			this.ai[i].pause();
	}

	public void stuckGame() {
		int j = 0;
		int i;
		for (i = 0; i < this.ai.length; i++) {
			this.ai[i].isStuck();
			j++;
		}
		if ((j == this.ai.length) && (this.thisp.isStuck())) {
			this.thisp.getlong().slide();
			this.longp.setText(Integer.toString(this.thisp.getlong()
					.gettopvalue()));
			this.longp.setBackgroundColor(myColor(this.thisp.getlong()
					.gettopcolor()));
			this.thisp.setStuck(false);
			for (i = 0; i < this.ai.length; i++)
				this.ai[i].setStuck(false);
		}
	}
}

/*
 * Location: C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name: app.dj.Game JD-Core Version: 0.6.0
 */