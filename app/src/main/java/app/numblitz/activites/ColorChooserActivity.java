package app.numblitz.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import app.numblitz.NumBlitzApplication;
import app.numblitz.R;

public class ColorChooserActivity extends Activity implements View.OnClickListener {
    private int colorc;
    private int lvl;
    private TextView tvPLevel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorchooser);
        // this.ad = new AdView(this, AdSize.BANNER, "a150c3f58ddb76e");
        // ((LinearLayout)findViewById(2131099649)).addView(this.ad);
        // this.ad.loadAd(new AdRequest());
        TextView title = (TextView) findViewById(R.id.text_color_title);
        Button yellow = (Button) findViewById(R.id.button_color_yellow);
        Button purple = (Button) findViewById(R.id.button_color_purple);
        Button orange = (Button) findViewById(R.id.button_color_orange);
        Button green = (Button) findViewById(R.id.button_color_green);
        tvPLevel = (TextView) findViewById(R.id.text_color_plevel);
        SeekBar plrLvl = (SeekBar) findViewById(R.id.seek_color_plevel);

        tvPLevel.setTextColor(Color.rgb(0, 178, 238));

        yellow.setOnClickListener(this);
        purple.setOnClickListener(this);
        orange.setOnClickListener(this);
        green.setOnClickListener(this);

        yellow.setBackgroundColor(GameActivity.myColor(0));
        purple.setBackgroundColor(GameActivity.myColor(1));
        orange.setBackgroundColor(GameActivity.myColor(2));
        green.setBackgroundColor(GameActivity.myColor(3));

        title.setTextColor(Color.rgb(0, 178, 238));
        title.setTextSize(2.0F * new Button(this).getTextSize());
        yellow.getRootView().setBackgroundColor(Color.rgb(207, 207, 207));


        plrLvl.getProgressDrawable().setColorFilter(Color.rgb(0, 178, 238), PorterDuff.Mode.OVERLAY);
        plrLvl.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                tvPLevel.setText("Player Lvl: " + ((int) (progress / 10) + 1));
                lvl = (int) (progress / 10) * -1 + 10 + 1;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                tvPLevel.setText("Player Lvl: " + ((int) (seekBar.getProgress() / 10) + 1));
                lvl = (int) (seekBar.getProgress() / 10) * -1 + 10 + 1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                tvPLevel.setText("Player Lvl: " + ((int) (seekBar.getProgress() / 10) + 1));
                lvl = (int) (seekBar.getProgress() / 10) * -1 + 10 + 1;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_color_yellow:
                this.colorc = 0;
                break;
            case R.id.button_color_purple:
                this.colorc = 1;
                break;
            case R.id.button_color_orange:
                this.colorc = 2;
                break;
            case R.id.button_color_green:
                this.colorc = 3;
        }
        ((NumBlitzApplication) getApplication()).setColor(this.colorc);
        ((NumBlitzApplication) getApplication()).setLevel(this.lvl);
        ((NumBlitzApplication) getApplication()).setNumplayers(4);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}