package app.numblitz.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.numblitz.NumBlitzApplication;
import app.numblitz.R;

public class GameStatsActivity extends Activity implements View.OnClickListener {
    private Button done;
    private TextView[] tvs;
    private TextView winner;
    private TextView you;

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_gamestats);
        tvs = new TextView[4];
        winner = (TextView) findViewById(R.id.text_stats_win);
        you = (TextView) findViewById(R.id.text_stat_you);
        int winnerId = 0;
        int totalWinningPoints = 0;
        for (int k = 0; k < 4; k++) {
            int m = getResources().getIdentifier("text_stats_player" + k, "id", "app.dj");
            tvs[k] = ((TextView) findViewById(m));
            if (((NumBlitzApplication) getApplication()).getPlaya(k) != null) {
                m = ((NumBlitzApplication) getApplication()).getPlaya(k).getscore() - ((NumBlitzApplication) getApplication()).getPlaya(k).getshort().pntLeft();
                tvs[k].setText(Integer.toString(m));
                if (m >= totalWinningPoints) {
                    totalWinningPoints = m;
                    winnerId = k;
                }
            }
        }
        if (((NumBlitzApplication) getApplication()).getPlaya(0) != null) {
            tvs[0].setText("Yellow: " + tvs[0].getText());
            tvs[0].setTextColor(GameActivity.myColor(0));
            tvs[0].setVisibility(View.VISIBLE);
            tvs[0].setTextSize(2.0F * new Button(this).getTextSize());
        }
        if (((NumBlitzApplication) getApplication()).getPlaya(1) != null) {
            tvs[1].setText("Purple: " + tvs[1].getText());
            tvs[1].setTextColor(GameActivity.myColor(1));
            tvs[1].setVisibility(View.VISIBLE);
            tvs[1].setTextSize(2.0F * new Button(this).getTextSize());
        }
        if (((NumBlitzApplication) getApplication()).getPlaya(2) != null) {
            tvs[2].setText("Orange: " + tvs[2].getText());
            tvs[2].setTextColor(GameActivity.myColor(2));
            tvs[2].setVisibility(View.VISIBLE);
            tvs[2].setTextSize(2.0F * new Button(this).getTextSize());
        }
        if (((NumBlitzApplication) getApplication()).getPlaya(3) != null) {
            tvs[3].setText("Green: " + tvs[3].getText());
            tvs[3].setTextColor(GameActivity.myColor(3));
            tvs[3].setVisibility(View.VISIBLE);
            tvs[3].setTextSize(2.0F * new Button(this).getTextSize());
        }
        winner.setText(tvs[winnerId].getText().toString().split(":")[0] + " won with: " + totalWinningPoints + "pts!");
        winner.setBackgroundColor(GameActivity.myColor(winnerId));
        winner.setTextSize(2.0F * new Button(this).getTextSize());
        done = (Button) findViewById(R.id.button_stat_done);
        done.setOnClickListener(this);
        done.setBackgroundColor(GameActivity.myColor(((NumBlitzApplication) getApplication()).getColor()));
        you.setTextColor(GameActivity.myColor(((NumBlitzApplication) getApplication()).getColor()));
        if (((NumBlitzApplication) getApplication()).getColor() != winnerId) {
            you.setText("YOU LOST!");
        } else {
            you.setText("YOU WON!");
        }
        done.getRootView().setBackgroundColor(Color.rgb(207, 207, 207));
//    this.ad = new AdView(this, AdSize.BANNER, "a150c3f58ddb76e");
//    ((LinearLayout)findViewById(2131099709)).addView(this.ad);
//    this.ad.loadAd(new AdRequest());
    }

    @Override
    public void onClick(View paramView) {
        for (int i = 0; i < 4; i++) {
            ((NumBlitzApplication) getApplication()).setPlaya(i, null);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public void onPause() {
        super.onPause();
        for (int i = 0; i < 4; i++) {
            ((NumBlitzApplication) getApplication()).setPlaya(i, null);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}