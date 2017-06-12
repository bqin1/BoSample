package pctest.apps.sarbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

// TODO: make button click only on the circle parts?
public class Activity_SARbox extends Activity {
	private static int timeOut = 1000;
	private boolean isTablet = true;
	Button button_converter;  
	Button button_exclusion;   
	Button button_target;
	Button button_interpolation; 
	Button button_equation;    
	Button button_reference;
	Button button_scaling;
	Button button_ratio;
	Button button_home;
	Button button_slot; 
	Button button_template;
	Button button_setting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// isTablet = getResources().getBoolean(R.bool.isTablet);
		Util_GlobalHandler.getGlobalHandler().setIsTablet(isTablet);

		// Works only for tablets, checks this and notifies user
		int screenSize = getResources().getConfiguration().screenLayout &
				Configuration.SCREENLAYOUT_SIZE_MASK;

		String toastMsg = "";
		switch(screenSize) {
			case Configuration.SCREENLAYOUT_SIZE_LARGE:
				Util_GlobalHandler.getGlobalHandler().isTabletMsg = false;
				break;
			case Configuration.SCREENLAYOUT_SIZE_NORMAL:
				toastMsg = "SARBOX is designed for tablets, normal screen detected, application icons and images will look distorted. It is highly recommended to re-download the app on your tablet.";
				Util_GlobalHandler.getGlobalHandler().isTabletMsg = true;
				break;
			case Configuration.SCREENLAYOUT_SIZE_SMALL:
				toastMsg = "SARBOX is designed for tablets, small screen detected, application icons and images will look distorted. It is highly recommended to re-download the app on your tablet.";
				Util_GlobalHandler.getGlobalHandler().isTabletMsg = true;
				break;
			default:
				Util_GlobalHandler.getGlobalHandler().isTabletMsg = false;
		}

		if (Util_GlobalHandler.getGlobalHandler().isTabletMsg)
		{
			new AlertDialog.Builder(this)
					.setTitle("PCTESTLAB SARBOX")
					.setMessage(toastMsg)
					.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									Intent i = new Intent(Activity_SARbox.this,
											Activity_SARbox.class);
									startActivity(i);

									finish();
								}
							}, timeOut);
						}
					})
					.show().setCancelable(false);
			Util_GlobalHandler.getGlobalHandler().isTabletMsg = false;
		}

		// for phones lock it in portrait, tablets lock in landscape
		if (!isTablet) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
		}
		setContentView(R.layout.activity_sarbox);

		button_converter = (Button) this
				.findViewById(R.id.sarbox_button_converter);
		button_exclusion = (Button) this
				.findViewById(R.id.sarbox_button_exclusion);
		button_target = (Button) this.findViewById(R.id.sarbox_button_target);
		button_interpolation = (Button) this
				.findViewById(R.id.sarbox_button_interpolate);
		button_equation = (Button) this
				.findViewById(R.id.sarbox_button_equation);
		button_reference = (Button) this
				.findViewById(R.id.sarbox_button_reference);
		button_scaling = (Button) this.findViewById(R.id.sarbox_button_scaling);
		button_ratio = (Button) this.findViewById(R.id.sarbox_button_ratio);
		button_home = (Button) this.findViewById(R.id.sarbox_button_home);
		button_template = (Button) this
				.findViewById(R.id.sarbox_button_template);
		button_slot = (Button) this.findViewById(R.id.sarbox_button_slot);
		button_setting = (Button) this.findViewById(R.id.sarbox_button_setting);

		Fragment fr = new Fragment_Home();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.sarbox_fragment_contentarea, fr);
		fragmentTransaction.commit();
		changeButtonBackground(button_home,
				R.drawable.fragment_button_home_clicked);
	}

	/*
	 * TODO: this is inefficient, a better way to do this is to remember the
	 * last button pressed and only change back the last button. However this
	 * has the added benefit of making sure no 2 buttons are ever clicked at the
	 * same time. If I ever optimize I need to change this
	 */
	public void changeButtonBackground(Button b, int image) {

		button_scaling
				.setBackgroundResource(R.drawable.fragment_button_scaling_unclicked);
		button_converter
				.setBackgroundResource(R.drawable.fragment_button_converter_unclicked);
		button_exclusion
				.setBackgroundResource(R.drawable.fragment_button_exclusion_unclicked);
		button_target
				.setBackgroundResource(R.drawable.fragment_button_target_unclicked);
		button_interpolation
				.setBackgroundResource(R.drawable.fragment_button_interpolate_unclicked);
		button_equation
				.setBackgroundResource(R.drawable.fragment_button_equation_unclicked);
		button_reference
				.setBackgroundResource(R.drawable.fragment_button_reference_unclicked);
		button_ratio
				.setBackgroundResource(R.drawable.fragment_button_ratio_unclicked);
		button_home
				.setBackgroundResource(R.drawable.fragment_button_home_unclicked);
		button_template
				.setBackgroundResource(R.drawable.fragment_button_template_unclicked);
		button_slot
				.setBackgroundResource(R.drawable.fragment_button_slot_unclicked);
		button_setting
				.setBackgroundResource(R.drawable.fragment_button_setting_unclicked);

		b.setBackgroundResource(image);
	}

	public void selectFrag(View view) {
		Fragment fr = new Fragment_Calc_Scaling();

		if (view == findViewById(R.id.sarbox_button_scaling)) {
			changeButtonBackground(button_scaling,
					R.drawable.fragment_button_scaling_clicked);
			fr = new Fragment_Calc_Scaling();
		} else if (view == findViewById(R.id.sarbox_button_ratio)) {
			fr = new Fragment_Calc_Ratio();
			changeButtonBackground(button_ratio,
					R.drawable.fragment_button_ratio_clicked);
		} else if (view == findViewById(R.id.sarbox_button_exclusion)) {
			fr = new Fragment_Calc_Exclusion();
			changeButtonBackground(button_exclusion,
					R.drawable.fragment_button_exclusion_clicked);
		} else if (view == findViewById(R.id.sarbox_button_interpolate)) {
			changeButtonBackground(button_interpolation,
					R.drawable.fragment_button_interpolate_clicked);
			fr = new Fragment_Calc_Interpolate();
		} else if (view == findViewById(R.id.sarbox_button_target)) {
			changeButtonBackground(button_target,
					R.drawable.fragment_button_target_clicked);
			fr = new Fragment_Target();
		} else if (view == findViewById(R.id.sarbox_button_reference)) {
			changeButtonBackground(button_reference,
					R.drawable.fragment_button_reference_clicked);
			fr = new Fragment_Reference();
		} else if (view == findViewById(R.id.sarbox_button_equation)) {
			changeButtonBackground(button_equation,
					R.drawable.fragment_button_equation_clicked);
			fr = new Fragment_Equation();
		} else if (view == findViewById(R.id.sarbox_button_home)) {
			changeButtonBackground(button_home,
					R.drawable.fragment_button_home_clicked);
			fr = new Fragment_Home();
		} else if (view == findViewById(R.id.sarbox_button_converter)) {
			changeButtonBackground(button_converter,
					R.drawable.fragment_button_converter_clicked);
			fr = new Fragment_Calc_Converter();
		} else if (view == findViewById(R.id.sarbox_button_setting)) {
			changeButtonBackground(button_setting,
					R.drawable.fragment_button_setting_clicked);
			fr = new Fragment_Setting();
		} else if (view == findViewById(R.id.sarbox_button_slot)) {
			changeButtonBackground(button_slot,
					R.drawable.fragment_button_slot_clicked);
			fr = new Fragment_Calc_Slot();
		} else if (view == findViewById(R.id.sarbox_button_template)) {
			boolean testing = false;

			if (testing) {
				if (isTablet) {
					changeButtonBackground(button_template,
							R.drawable.fragment_button_template_clicked);
					fr = new Fragment_UnderConstruction();
				} else {
					changeButtonBackground(button_template,
							R.drawable.fragment_button_template_clicked);
					fr = new Fragment_Template(); 
				}
			} else {
				changeButtonBackground(button_template,
						R.drawable.fragment_button_template_clicked);
				fr = new Fragment_UnderConstruction();
			}
		}

		FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.sarbox_fragment_contentarea, fr);
		fragmentTransaction.commit();

	}

}
