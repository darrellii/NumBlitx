package app.numblitz.views;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

@SuppressLint({"NewApi"})
public class Rulez extends DialogFragment
{
  private Rulez tis = this;

  public Dialog onCreateDialog(Bundle paramBundle)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(getActivity());
    localBuilder.setMessage("Goal:  Play all ten nums from the short pile.\n\n"+
    						"Game Play: Nums can be played from the short pile, long pile, and brick piles onto game piles. \n "+
    						"Nums are selected by touching the num, then touching the where you want to play.\n" +
    						"    The game piles start with a 1 and increase to 10, and must be the same color.  \n\n"+
    						"When nums are played from the brick piles, you can move nums from the short pile to the brick piles.  Then you get rid of more nums from the short pile.\n\n"+
    						"When you cannot play from the short pile or the brick piles, play from the long pile.  You can shuffle through the long pile by double-tapping.\n\n"+
    						"When you reach the end of the short pile, tap the Blitzit!"+
    						" \n\nScoring: +1 for every num you play and -2 for every num left in the short pile. ")
    						.setPositiveButton("okay", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
      }
    });
    return localBuilder.create();
  }
}

/* Location:           C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     app.dj.Rulz
 * JD-Core Version:    0.6.0
 */