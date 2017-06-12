package pctest.apps.sarbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

/*
 * Logo Screen before moving into SARBox 
 */
public class Activity_SplashScreen extends Activity {

	private static int timeOut = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splashscreen);

		int screenSize = getResources().getConfiguration().screenLayout &
				Configuration.SCREENLAYOUT_SIZE_MASK;

		String toastMsg = "This tool for free and educational use only. By using this app, you agree that PCTESTLAB will NOT be liable for any results or data derived accidentally or intentional due to using this app.";;

		new AlertDialog.Builder(this)
				.setTitle("PCTESTLAB SARBOX")
				.setMessage(toastMsg)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						new Handler().postDelayed(new Runnable() {

							@Override
							public void run() {
								Intent i = new Intent(Activity_SplashScreen.this,
										Activity_SARbox.class);
								startActivity(i);

								finish();
							}
						}, timeOut);
					}
				})
				.show().setCancelable(false);;
	}

}
