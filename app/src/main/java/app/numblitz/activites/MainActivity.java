package app.numblitz.activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.numblitz.R;

//import android.view.View.OnClickListener;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String RULZ = "Goal:  Play all ten nums from the short pile.\n\nGame Play: Nums can be played from the short pile, long pile, and brick piles onto game piles. \n    Nums are selected by touching the num, then touching the where you want to play.\n    The game piles start with a 1 and increase to 10, and must be the same color.  \n\nWhen nums are played from the brick piles, you can move nums from the short pile to the brick piles.  Then you get rid of more nums from the short pile.\n\nWhen you cannot play from the short pile or the brick piles, play from the long pile.  You can shuffle through the long pile by double-tapping.\n\nWhen you reach the end of the short pile, tap the Blitzit! \n\nScoring: +1 for every num you play and -2 for every num left in the short pile. ";
    private Thread colorchanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button single = (Button) findViewById(R.id.button_main_single);
        Button multi = (Button) findViewById(R.id.button_main_multi);
        Button start1 = (Button) findViewById(R.id.button_main_start1);
        Button start2 = (Button) findViewById(R.id.button_main_start2);
        Button rulz = (Button) findViewById(R.id.button_main_rulez);

        single.setOnClickListener(this);
        multi.setOnClickListener(this);
        start1.setOnClickListener(this);
        start2.setOnClickListener(this);
        rulz.setOnClickListener(this);

        single.setBackgroundColor(GameActivity.myColor(0));
        start1.setBackgroundColor(GameActivity.myColor(1));
        start2.setBackgroundColor(GameActivity.myColor(2));
        multi.setBackgroundColor(GameActivity.myColor(3));
        //rulz.setBackgroundColor(-16776961);
        rulz.getRootView().setBackgroundColor(Color.rgb(207, 207, 207));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_main_rulez:
                new AlertDialog.Builder(this)
                        .setTitle("Rulez")
                        .setMessage("Goal:  Play all ten nums from the short pile.\n\nGame Play: Nums can be played from the short pile, long pile, and brick piles onto game piles. \n    Nums are selected by touching the num, then touching the where you want to play.\n    The game piles start with a 1 and increase to 10, and must be the same color.  \n\nWhen nums are played from the brick piles, you can move nums from the short pile to the brick piles.  Then you get rid of more nums from the short pile.\n\nWhen you cannot play from the short pile or the brick piles, play from the long pile.  You can shuffle through the long pile by double-tapping.\n\nWhen you reach the end of the short pile, tap the Blitzit! \n\nScoring: +1 for every num you play and -2 for every num left in the short pile. ")
                        .setPositiveButton("TIME TO WIN!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            default:
                Intent intent = new Intent(this, ColorChooserActivity.class);
                startActivity(intent);
                break;
        }
    }
}