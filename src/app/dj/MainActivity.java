package app.dj;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends Activity
  implements View.OnClickListener
{
  private static final String RULZ = "Goal:  Play all ten nums from the short pile.\n\nGame Play: Nums can be played from the short pile, long pile, and brick piles onto game piles. \n    Nums are selected by touching the num, then touching the where you want to play.\n    The game piles start with a 1 and increase to 10, and must be the same color.  \n\nWhen nums are played from the brick piles, you can move nums from the short pile to the brick piles.  Then you get rid of more nums from the short pile.\n\nWhen you cannot play from the short pile or the brick piles, play from the long pile.  You can shuffle through the long pile by double-tapping.\n\nWhen you reach the end of the short pile, tap the Blitzit! \n\nScoring: +1 for every num you play and -2 for every num left in the short pile. ";
  private AdView ad;
  private Thread colorchanger;
  private Button multi;
  private Button rulz;
  private Button single;
  private Button start1;
  private Button start2;

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131099713:
      startActivity(new Intent("app.dj.COLORCHOOSER"));
      break;
    case 2131099714:
      startActivity(new Intent("app.dj.COLORCHOOSER"));
      break;
    case 2131099716:
    	AlertDialog newAlert = new AlertDialog.Builder(this).setTitle("RULZ").setMessage("Goal:  Play all ten nums from the short pile.\n\nGame Play: Nums can be played from the short pile, long pile, and brick piles onto game piles. \n    Nums are selected by touching the num, then touching the where you want to play.\n    The game piles start with a 1 and increase to 10, and must be the same color.  \n\nWhen nums are played from the brick piles, you can move nums from the short pile to the brick piles.  Then you get rid of more nums from the short pile.\n\nWhen you cannot play from the short pile or the brick piles, play from the long pile.  You can shuffle through the long pile by double-tapping.\n\nWhen you reach the end of the short pile, tap the Blitzit! \n\nScoring: +1 for every num you play and -2 for every num left in the short pile. ").setPositiveButton("TIME TO WIN!", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
        }
      }).show();
      break;
    case 2131099717:
      startActivity(new Intent("app.dj.COLORCHOOSER"));
      break;
    case 2131099718:
      startActivity(new Intent("app.dj.COLORCHOOSER"));
    case 2131099715:
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903044);
    this.single = ((Button)findViewById(2131099717));
    this.multi = ((Button)findViewById(2131099714));
    this.start1 = ((Button)findViewById(2131099713));
    this.start2 = ((Button)findViewById(2131099718));
    this.rulz = ((Button)findViewById(2131099716));
    this.single.setOnClickListener(this);
    this.multi.setOnClickListener(this);
    this.start1.setOnClickListener(this);
    this.start2.setOnClickListener(this);
    this.rulz.setOnClickListener(this);
    this.single.setBackgroundColor(Game.myColor(0));
    this.start1.setBackgroundColor(Game.myColor(1));
    this.start2.setBackgroundColor(Game.myColor(2));
    this.multi.setBackgroundColor(Game.myColor(3));
    this.rulz.setBackgroundColor(-16776961);
    this.rulz.getRootView().setBackgroundColor(Color.rgb(207, 207, 207));
    this.ad = new AdView(this, AdSize.BANNER, "a150c3f58ddb76e");
    ((LinearLayout)findViewById(2131099715)).addView(this.ad);
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
    finish();
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.MainActivity
 * JD-Core Version:    0.6.0
 */