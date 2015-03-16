package app.numblitz.activites;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.numblitz.NumBlitzApplication;

public class NumPlayerActivity extends Activity implements View.OnClickListener {
    private Button blvl;
    private float[] grav = new float[2];
    private int lvl;
    private int num;
    private Button player2;
    private Button player3;
    private Button player4;
    private TextView title;

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case 2131099721:
                if (this.lvl < 2) {
                    this.grav[0] = 15.0F;
                    this.grav[1] = 85.0F;
                    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
                    localLayoutParams.weight = this.grav[0];
                    findViewById(2131099720).setLayoutParams(localLayoutParams);
                    localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
                    localLayoutParams.weight = this.grav[1];
                    findViewById(2131099721).setLayoutParams(localLayoutParams);
                    this.lvl = 5;
                    return;
                }
                this.grav[0] = (15.0F + this.grav[0]);
                this.grav[1] -= 15.0F;
                LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
                localLayoutParams.weight = this.grav[0];
                findViewById(2131099720).setLayoutParams(localLayoutParams);
                localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
                localLayoutParams.weight = this.grav[1];
                findViewById(2131099721).setLayoutParams(localLayoutParams);
                this.lvl = (-1 + this.lvl);
                break;
            case 2131099723:
                this.num = 4;
                ((NumBlitzApplication) getApplication()).setNumplayers(this.num);
                ((NumBlitzApplication) getApplication()).setLevel(this.lvl);
                startActivity(new Intent("app.dj.GAME"));
                break;
            case 2131099725:
                this.num = 3;
                ((NumBlitzApplication) getApplication()).setNumplayers(this.num);
                ((NumBlitzApplication) getApplication()).setLevel(this.lvl);
                startActivity(new Intent("app.dj.GAME"));
                break;
            case 2131099727:
                this.num = 2;
                ((NumBlitzApplication) getApplication()).setNumplayers(this.num);
                ((NumBlitzApplication) getApplication()).setLevel(this.lvl);
                startActivity(new Intent("app.dj.GAME"));
                break;
            case 2131099722:
            case 2131099724:
            case 2131099726:
        }

    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2130903046);
        this.lvl = 5;
        this.grav[0] = 15.0F;
        this.grav[1] = 85.0F;
//    this.ad = new AdView(this, AdSize.BANNER, "a150c3f58ddb76e");
//    ((LinearLayout)findViewById(2131099726)).addView(this.ad);
//    this.ad.loadAd(new AdRequest());
        this.player2 = ((Button) findViewById(2131099727));
        this.player3 = ((Button) findViewById(2131099725));
        this.player4 = ((Button) findViewById(2131099723));
        this.blvl = ((Button) findViewById(2131099721));
        this.title = ((TextView) findViewById(2131099724));
        this.title.setTextSize(2.0F * new Button(this).getTextSize());
        this.player2.setOnClickListener(this);
        this.player3.setOnClickListener(this);
        this.player4.setOnClickListener(this);
        this.blvl.setOnClickListener(this);
        int i = ((NumBlitzApplication) getApplication()).getColor();
        this.player2.setBackgroundColor(GameActivity.myColor(0));
        this.player3.setBackgroundColor(GameActivity.myColor(1));
        this.player4.setBackgroundColor(GameActivity.myColor(2));
        this.blvl.setBackgroundColor(GameActivity.myColor(3));
        this.title.setBackgroundColor(GameActivity.myColor(i));
        this.player2.getRootView().setBackgroundColor(Color.rgb(207, 207, 207));
    }
}