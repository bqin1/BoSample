package pctest.apps.sarbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

/**
 * This activity displays a Logo Screen and some terms of use before moving the user to the main class
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Activity_SplashScreen extends Activity {

	private static int timeOut = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);

		// Show users the terms, must click to proceed into app
		String toastMsg = getResources().getString(R.string.toastmessage_splash);
		new AlertDialog.Builder(this)
				.setTitle(getResources().getString(R.string.app_name))
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
