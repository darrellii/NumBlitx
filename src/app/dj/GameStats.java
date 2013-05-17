package app.dj;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class GameStats extends Activity
  implements View.OnClickListener
{
  private AdView ad;
  private Button done;
  private TextView[] tvs;
  private TextView winner;
  private TextView you;

  public void onClick(View paramView)
  {
    for (int i = 0; i < 4; i++)
      ((MyReferences)getApplication()).setPlaya(i, null);
    startActivity(new Intent("app.dj.MAINACTIVITY"));
  }

  @SuppressLint({"NewApi"})
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903043);
    this.tvs = new TextView[4];
    this.winner = ((TextView)findViewById(2131099708));
    this.you = ((TextView)findViewById(2131099711));
    int i = 0;
    int j = 0;
    for (int k = 0; k < 4; k++)
    {
      int m = getResources().getIdentifier("tvplayer" + k, "id", "app.dj");
      this.tvs[k] = ((TextView)findViewById(m));
      if (((MyReferences)getApplication()).getPlaya(k) == null)
        continue;
      m = ((MyReferences)getApplication()).getPlaya(k).getscore() - ((MyReferences)getApplication()).getPlaya(k).getshort().pntLeft();
      this.tvs[k].setText(m);
      if (m < j)
        continue;
      j = m;
      i = k;
    }
    if (((MyReferences)getApplication()).getPlaya(0) != null)
    {
      this.tvs[0].setText("Yellow: " + this.tvs[0].getText());
      this.tvs[0].setTextColor(Game.myColor(0));
      this.tvs[0].setVisibility(0);
      this.tvs[0].setTextSize(2.0F * new Button(this).getTextSize());
    }
    if (((MyReferences)getApplication()).getPlaya(1) != null)
    {
      this.tvs[1].setText("Purple: " + this.tvs[1].getText());
      this.tvs[1].setTextColor(Game.myColor(1));
      this.tvs[1].setVisibility(0);
      this.tvs[1].setTextSize(2.0F * new Button(this).getTextSize());
    }
    if (((MyReferences)getApplication()).getPlaya(2) != null)
    {
      this.tvs[2].setText("Orange: " + this.tvs[2].getText());
      this.tvs[2].setTextColor(Game.myColor(2));
      this.tvs[2].setVisibility(0);
      this.tvs[2].setTextSize(2.0F * new Button(this).getTextSize());
    }
    if (((MyReferences)getApplication()).getPlaya(3) != null)
    {
      this.tvs[3].setText("Green: " + this.tvs[3].getText());
      this.tvs[3].setTextColor(Game.myColor(3));
      this.tvs[3].setVisibility(0);
      this.tvs[3].setTextSize(2.0F * new Button(this).getTextSize());
    }
    this.winner.setText(this.tvs[i].getText().toString().split(":")[0] + " won with: " + j + "pts!!!!");
    this.winner.setBackgroundColor(Game.myColor(i));
    this.winner.setTextSize(2.0F * new Button(this).getTextSize());
    this.done = ((Button)findViewById(2131099710));
    this.done.setOnClickListener(this);
    this.done.setBackgroundColor(Game.myColor(((MyReferences)getApplication()).getColor()));
    this.you.setTextColor(Game.myColor(((MyReferences)getApplication()).getColor()));
    if (((MyReferences)getApplication()).getColor() != i)
      this.you.setText("YOU LOST!!!");
    else
      this.you.setText("YOU WON!!!");
    this.done.getRootView().setBackgroundColor(Color.rgb(207, 207, 207));
    this.ad = new AdView(this, AdSize.BANNER, "a150c3f58ddb76e");
    ((LinearLayout)findViewById(2131099709)).addView(this.ad);
    this.ad.loadAd(new AdRequest());
  }

  public void onDestroy()
  {
    if (this.ad != null)
      this.ad.destroy();
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
    for (int i = 0; i < 4; i++)
      ((MyReferences)getApplication()).setPlaya(i, null);
    startActivity(new Intent("app.dj.MAINACTIVITY"));
    finish();
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.GameStats
 * JD-Core Version:    0.6.0
 */