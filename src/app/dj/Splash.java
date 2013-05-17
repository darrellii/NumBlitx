package app.dj;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity implements DialogInterface.OnClickListener {
	public void onClick(DialogInterface paramDialogInterface, int paramInt) {
	}

	public void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(2130903048);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent localIntent3 = new Intent("app.dj.MAINACTIVITY");
		Splash.this.startActivity(localIntent3);

	}

	protected void onPause() {
		super.onPause();
		finish();
	}
}

/*
 * Location: C:\Users\Darrell\DEV\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name: app.dj.Splash JD-Core Version: 0.6.0
 */