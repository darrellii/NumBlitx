package app.numblitz.activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Array;

import app.numblitz.NumBlitzApplication;
import app.numblitz.R;
import app.numblitz.models.Deck;
import app.numblitz.models.Gamepiles;
import app.numblitz.models.Player;
import app.numblitz.tasks.BlitzAI;

public class GameActivity extends Activity implements View.OnClickListener {

    private BlitzAI[] ai;
    private int ailvl;
    private Button[][] bpiles;
    private Button brick0;
    private Button brick1;
    private Button brick2;
    private boolean isFirstClick;
    private Button viewFirstClicked;
    private boolean isGameOver;
    private Button longp;
    private int numplayaz;
    private Gamepiles[][] piles;
    private Button shortp;
    private int textsize;
    private Player thisp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //PowerManager pm = ((PowerManager) getSystemService("power"));
        //PowerManager.WakeLock wl = pm.newWakeLock(6, "My Tag");
        // TODO: Use intent to pass this sort of information. No need for system wide singleton.
        numplayaz = ((NumBlitzApplication) getApplication()).getNumplayers();
        ailvl = ((NumBlitzApplication) getApplication()).getLevel();
        isGameOver = false;
        isFirstClick = true;

        longp = (Button) findViewById(R.id.button_longp);
        shortp = (Button) findViewById(R.id.button_shortp);
        brick0 = (Button) findViewById(R.id.button_brick0);
        brick1 = (Button) findViewById(R.id.button_brick1);
        brick2 = (Button) findViewById(R.id.button_brick2);

        init();
        findViewById(R.id.tvgamebrick).setBackgroundColor(myColor(((NumBlitzApplication) getApplication()).getColor()));
        findViewById(R.id.tvgamelong).setBackgroundColor(myColor(((NumBlitzApplication) getApplication()).getColor()));
        findViewById(R.id.tvgameshort).setBackgroundColor(myColor(((NumBlitzApplication) getApplication()).getColor()));
        Thread[] tai = new Thread[numplayaz - 1];
        for (int i = 0; i < numplayaz - 1; i++) {
            tai[i] = new Thread(ai[i]);
            tai[i].start();
        }
        textsize = (int) brick0.getTextSize() - 2;
    }

    private void init() {
        createGamePiles();
        setListeners();
        createOpponents();
        setGame();
    }

    private void createGamePiles() {
        int[] arrayOfInt;
        switch (numplayaz) {
            case 2:
                arrayOfInt = new int[2];
                arrayOfInt[0] = 2;
                arrayOfInt[1] = 4;
                piles = ((Gamepiles[][]) Array.newInstance(Gamepiles.class, arrayOfInt));
                arrayOfInt = new int[2];
                arrayOfInt[0] = 2;
                arrayOfInt[1] = 4;
                bpiles = ((Button[][]) Array.newInstance(Button.class, arrayOfInt));
                break;
            case 3:
                arrayOfInt = new int[2];
                arrayOfInt[0] = 3;
                arrayOfInt[1] = 4;
                piles = ((Gamepiles[][]) Array.newInstance(Gamepiles.class, arrayOfInt));
                arrayOfInt = new int[2];
                arrayOfInt[0] = 3;
                arrayOfInt[1] = 4;
                bpiles = ((Button[][]) Array.newInstance(Button.class, arrayOfInt));
                break;
            case 4:
                arrayOfInt = new int[2];
                arrayOfInt[0] = 3;
                arrayOfInt[1] = 5;
                piles = ((Gamepiles[][]) Array.newInstance(Gamepiles.class, arrayOfInt));
                arrayOfInt = new int[2];
                arrayOfInt[0] = 3;
                arrayOfInt[1] = 5;
                bpiles = ((Button[][]) Array.newInstance(Button.class, arrayOfInt));
                break;
        }
    }

    private void setListeners() {
        switch (numplayaz) {
            case 2:
                LinearLayout vp2 = (LinearLayout) findViewById(R.id.layout_player2);
                vp2.setVisibility(View.VISIBLE);
                break;
            case 3:
                LinearLayout vp3 = (LinearLayout) findViewById(R.id.layout_player3);
                vp3.setVisibility(View.VISIBLE);
                break;
            case 4:
                LinearLayout vp4 = (LinearLayout) findViewById(R.id.layout_player4);
                vp4.setVisibility(View.VISIBLE);
                break;
        }
        for (int i = 0; i < piles.length; i++) {
            for (int j = 0; j < piles[0].length; j++) {
                int k = getResources().getIdentifier("button_" + numplayaz + Integer.toString(i) + Integer.toString(j), "id", "app.dj");
                bpiles[i][j] = (Button) findViewById(k);
                bpiles[i][j].setOnClickListener(this);
            }
        }
        longp.setOnClickListener(this);
        shortp.setOnClickListener(this);
        brick0.setOnClickListener(this);
        brick1.setOnClickListener(this);
        brick2.setOnClickListener(this);
    }

    private void createOpponents() {
        int color = ((NumBlitzApplication) getApplication()).getColor();
        int numberOfPlayers = ((NumBlitzApplication) getApplication()).getNumplayers();
        Player[] opponents = new Player[numberOfPlayers - 1];
        ai = new BlitzAI[numberOfPlayers - 1];
        thisp = new Player(color, new Deck(40, 10, 4));
        int nextTeam = 0;
        for (int j = 0; j < numberOfPlayers - 1; j++) {
            if (nextTeam == color) nextTeam++;
            opponents[j] = new Player(nextTeam, new Deck(40, 10, 4));
            nextTeam++;
            ((NumBlitzApplication) getApplication()).setPlaya(opponents[j].getteam(), opponents[j]);
            ai[j] = new BlitzAI(opponents[j], piles, this, ailvl);
        }
        ((NumBlitzApplication) getApplication()).setPlaya(thisp.getteam(), thisp);
    }

    private void setGame() {
        brick0.setBackgroundColor(myColor(thisp.getbrick(0).getcolor()));
        brick1.setBackgroundColor(myColor(thisp.getbrick(1).getcolor()));
        brick2.setBackgroundColor(myColor(thisp.getbrick(2).getcolor()));

        longp.setBackgroundColor(myColor(thisp.getlong().gettopcolor()));
        shortp.setBackgroundColor(myColor(thisp.getshort().peektop().getsuit()));

        brick0.setText(Integer.toString(thisp.getbrick(0).getvalue()));
        brick1.setText(Integer.toString(thisp.getbrick(1).getvalue()));
        brick2.setText(Integer.toString(thisp.getbrick(2).getvalue()));

        longp.setText(Integer.toString(thisp.getlong().gettopvalue()));
        shortp.setText(Integer.toString(thisp.getshort().peektop().getvalue()));

        for (int i = 0; i < piles.length; i++) {
            for (int j = 0; j < piles[0].length; j++) {
                piles[i][j] = new Gamepiles();
                bpiles[i][j].setBackgroundColor(Color.WHITE);
                bpiles[i][j].setText("0");
            }
        }
    }

    private synchronized boolean isGameOver() {
        return (ai[0].getgameend()) || (ai[0].getgameend()) || (ai[0].getgameend()) || (isGameOver);
    }

    public synchronized void endGame() {
        for (int i = 0; i < numplayaz - 1; i++) {
            ai[i].zerolevel();
            ai[i].endGame();
            //while (this.tai[i].isAlive());
        }
        Intent intent = new Intent(this, GameStatsActivity.class);
        startActivity(intent);
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

    private synchronized void onSecondClick(View firstClick, View secondClick) {

        if (firstClick == null) {
            isFirstClick = true;
            onClick(secondClick);
            return;
        }

        Deck.Card localCard = null;
        ((Button) firstClick).setTextSize(textsize);
        switch (firstClick.getId()) {
            case R.id.bbrick0:
                if (!thisp.getbrick(0).isempty()) {
                    localCard = thisp.getbrick(0).getCard();
                }
                break;
            case R.id.bbrick1:
                if (!thisp.getbrick(1).isempty()) {
                    localCard = thisp.getbrick(1).getCard();
                }
                break;
            case R.id.bbrick2:
                if (thisp.getbrick(2).isempty()) {
                    localCard = thisp.getbrick(2).getCard();
                }
                break;
            case R.id.blongp:
                localCard = thisp.getlong().poptopcard();
                break;
            case R.id.bshortp:
                localCard = thisp.getshort().poptop();
        }
        // handle when second = piles
        switch (secondClick.getId()) {
            case R.id.bbrick0:
                if (thisp.getbrick(0).isempty()) {
                    thisp.addtobrick(0, localCard);
                    secondClick.setBackgroundColor(myColor(localCard.getsuit()));
                    ((Button) findViewById(app.numblitz.R.id.bbrick0)).setText(Integer.toString(localCard.getvalue()));
                    resetFirstClick(firstClick);
                }
                return;
            case R.id.bbrick1:
                if (thisp.getbrick(1).isempty()) {
                    thisp.addtobrick(1, localCard);
                    secondClick.setBackgroundColor(myColor(localCard.getsuit()));
                    ((Button) findViewById(app.numblitz.R.id.bbrick1)).setText(Integer.toString(localCard.getvalue()));
                    resetFirstClick(firstClick);
                }
                return;
            case R.id.bbrick2:
                if (thisp.getbrick(2).isempty()) {
                    thisp.addtobrick(2, localCard);
                    secondClick.setBackgroundColor(myColor(localCard.getsuit()));
                    ((Button) findViewById(app.numblitz.R.id.bbrick2)).setText(Integer.toString(localCard.getvalue()));
                    resetFirstClick(firstClick);
                }
                return;
            case R.id.blongp:
                if (firstClick.getId() == R.id.blongp) {
                    thisp.addtolong(localCard);
                    thisp.getlong().slide();
                    longp.setText(Integer.toString(thisp.getlong().gettopvalue()));
                    longp.setBackgroundColor(myColor(thisp.getlong().gettopcolor()));
                    longp.setTextSize(textsize);
                    resetFirstClick(firstClick);
                }
                return;
            case R.id.bshortp:
                if (thisp.getshort().getSize() == 0) {
                    endGame();
                }
                return;
            default:
                for (int i = 0; i < piles.length; i++) {
                    for (int j = 0; j < piles[0].length; j++) {
                        if (bpiles[i][j].getId() == secondClick.getId()) {
                            if (piles[i][j].gettopvalue() + 1 == localCard.getvalue()) {
                                piles[i][j].add(localCard);
                                bpiles[i][j].setText(Integer.toString(piles[i][j].gettopvalue()));
                                bpiles[i][j].setBackgroundColor(myColor(piles[i][j].getcolor()));
                                resetFirstClick(firstClick);
                                thisp.scorepp();
                                isGameOver = thisp.getshort().isempty();
                            }
                            return;
                        }
                    }
                }
                break;
        }
    }

    private synchronized void resetFirstClick(View view) {
        switch (view.getId()) {
            case R.id.bbrick0:
                brick0.setText("");
                brick0.setBackgroundColor(Color.WHITE);
                thisp.getbrick(0).remove();
                brick0.setTextSize(textsize);
                break;
            case R.id.bbrick1:
                brick1.setText("");
                brick1.setBackgroundColor(Color.WHITE);
                thisp.getbrick(1).remove();
                brick1.setTextSize(textsize);
                break;
            case R.id.bbrick2:
                brick2.setText("");
                brick2.setBackgroundColor(Color.WHITE);
                thisp.getbrick(2).remove();
                brick2.setTextSize(textsize);
                break;
            case R.id.blongp:
                longp.setBackgroundColor(myColor(thisp.getlong().gettopcolor()));
                longp.setText(Integer.toString(thisp.getlong().gettopvalue()));
                longp.setTextSize(textsize);
                break;
            case R.id.bshortp:
                if (thisp.getshort().getSize() == 0) {
                    shortp.setBackgroundColor(Color.rgb(207, 207, 207));
                    shortp.setText("BLITZ IT!");
                } else {
                    shortp.setBackgroundColor(myColor(thisp.getshort().peektop().getsuit()));
                    shortp.setText(Integer.toString(thisp.getshort().peektop().getvalue()));
                    shortp.setTextSize(textsize);
                }
                break;
        }
        viewFirstClicked = null;
    }

    public Button[][] getButtonMap() {
        return this.bpiles;
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
                                GameActivity.this.thisp.shuffle();
                                for (int i = 0; i < -1 + GameActivity.this.numplayaz; i++)
                                    GameActivity.this.ai[i].getplayer().shuffle();
                                GameActivity.this.brick0.setBackgroundColor(GameActivity
                                        .myColor(GameActivity.this.thisp.getbrick(0)
                                                .getcolor()));
                                GameActivity.this.brick1.setBackgroundColor(GameActivity
                                        .myColor(GameActivity.this.thisp.getbrick(1)
                                                .getcolor()));
                                GameActivity.this.brick2.setBackgroundColor(GameActivity
                                        .myColor(GameActivity.this.thisp.getbrick(2)
                                                .getcolor()));
                                GameActivity.this.longp.setBackgroundColor(GameActivity
                                        .myColor(GameActivity.this.thisp.getlong()
                                                .gettopcolor()));
                                GameActivity.this.shortp.setBackgroundColor(GameActivity
                                        .myColor(GameActivity.this.thisp.getshort()
                                                .peektop().getsuit()));
                                GameActivity.this.brick0.setText(Integer
                                        .toString(GameActivity.this.thisp.getbrick(0)
                                                .getvalue()));
                                GameActivity.this.brick1.setText(Integer
                                        .toString(GameActivity.this.thisp.getbrick(1)
                                                .getvalue()));
                                GameActivity.this.brick2.setText(Integer
                                        .toString(GameActivity.this.thisp.getbrick(2)
                                                .getvalue()));
                                GameActivity.this.longp.setText(Integer
                                        .toString(GameActivity.this.thisp.getlong()
                                                .gettopvalue()));
                                GameActivity.this.shortp.setText(Integer
                                        .toString(GameActivity.this.thisp.getshort()
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

    @Override
    public void onClick(View view) {
        if (isGameOver()) {
            endGame();
        }
        if ((view.getId() == R.id.bshortp) && (thisp.getshort().getSize() == 0)) {
            endGame();
        }
        if (!isFirstClick) {
            // SecondClick
            onSecondClick(viewFirstClicked, view);
            isFirstClick = !isFirstClick;
        } else {
            if ((view.getId() == R.id.bbrick0)
                    || (view.getId() == R.id.bbrick1)
                    || (view.getId() == R.id.bbrick2)
                    || (view.getId() == R.id.bshortp)
                    || (view.getId() == R.id.blongp)) {
                viewFirstClicked = (Button) view;
                viewFirstClicked.setTextSize(2 * textsize);
                isFirstClick = !isFirstClick;
            }
        }
    }

}