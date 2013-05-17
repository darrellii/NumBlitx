package app.dj;

import android.app.Activity;
import android.content.Intent;
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

public class Colorchooser extends Activity
  implements View.OnClickListener
{
  private AdView ad;
  private int colorc;
  private Button green;
  private Button orange;
  private Button purple;
  private TextView title;
  private Button yellow;

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099651:
      this.colorc = 0;
      break;
    case 2131099652:
      this.colorc = 1;
      break;
    case 2131099653:
      this.colorc = 2;
      break;
    case 2131099654:
      this.colorc = 3;
    }
    ((MyReferences)getApplication()).setColor(this.colorc);
    startActivity(new Intent("app.dj.NUMPLAYER"));
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903041);
    this.ad = new AdView(this, AdSize.BANNER, "a150c3f58ddb76e");
    ((LinearLayout)findViewById(2131099649)).addView(this.ad);
    this.ad.loadAd(new AdRequest());
    this.title = ((TextView)findViewById(2131099650));
    this.yellow = ((Button)findViewById(2131099651));
    this.purple = ((Button)findViewById(2131099652));
    this.orange = ((Button)findViewById(2131099653));
    this.green = ((Button)findViewById(2131099654));
    this.yellow.setOnClickListener(this);
    this.purple.setOnClickListener(this);
    this.orange.setOnClickListener(this);
    this.green.setOnClickListener(this);
    this.yellow.setBackgroundColor(Game.myColor(0));
    this.purple.setBackgroundColor(Game.myColor(1));
    this.orange.setBackgroundColor(Game.myColor(2));
    this.green.setBackgroundColor(Game.myColor(3));
    this.title.setTextColor(Color.rgb(0, 178, 238));
    this.title.setTextSize(2.0F * new Button(this).getTextSize());
    this.yellow.getRootView().setBackgroundColor(Color.rgb(207, 207, 207));
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
    finish();
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.Colorchooser
 * JD-Core Version:    0.6.0
 */