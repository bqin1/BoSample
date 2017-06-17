package pctest.apps.sarbox;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This fragment opens the settings page, where user to update app or log in
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Fragment_Setting extends Fragment {

	Button buttonLock;
	Button buttonUpdate; 

	boolean isLocked;

	String inputUsername; 
	String inputPassword;

	String username;;
	String password;;

	String username2;;
	String password2;;

	String message_login_fail;
	String message_login_success;
	String message_logout_success;

	String textfield_label_loggedin = "Logged In";
	String textfield_label_loggedout = "Logged Out";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_setting,
				container, false);

		isLocked = Util_GlobalHandler.getGlobalHandler().getIsLocked();
		message_login_fail = Util_GlobalHandler.getGlobalHandler()
				.get_Message_Login_Fail();
		message_login_success = Util_GlobalHandler.getGlobalHandler()
				.get_Message_Login_Success();
		message_logout_success = Util_GlobalHandler.getGlobalHandler()
				.get_Message_Logout_Success();

		Resources res = getResources();
		username = res.getString(R.string.username1);
		password = res.getString(R.string.password1);
		username2 = res.getString(R.string.username2);
		password2 = res.getString(R.string.password2);

		final TextView textviewLock = (TextView) view
				.findViewById(R.id.setting_textview_unlock);

		buttonLock = (Button) view.findViewById(R.id.setting_button_unlock);

		if (isLocked) {
			textviewLock.setText(textfield_label_loggedout);
			buttonLock
					.setBackgroundResource(R.drawable.setting_button_lock_unclicked);
		} else {
			textviewLock.setText(textfield_label_loggedin);
			buttonLock
					.setBackgroundResource(R.drawable.setting_button_lock_clicked);
		}

		// Handle Button click
		buttonLock.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				isLocked = Util_GlobalHandler.getGlobalHandler().getIsLocked();

				if (isLocked) {
					// get prompts.xml view
					LayoutInflater li = LayoutInflater.from(getActivity());
					View promptsView = li.inflate(R.layout.misc_lockprompt,
							null);

					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							getActivity());

					// set prompts.xml to alertdialog builder
					alertDialogBuilder.setView(promptsView);

					final EditText userInput = (EditText) promptsView
							.findViewById(R.id.misc_lockprompt_edittext_username);
					final EditText passInput = (EditText) promptsView
							.findViewById(R.id.misc_lockprompt_edittext_password);

					// set dialog message
					alertDialogBuilder
							.setCancelable(false)
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											// get user input and set it to
											// result
											// edit text
											inputUsername = userInput.getText()
													.toString();
											inputPassword = passInput.getText()
													.toString();

											MessageDigest digest;
											try {
												digest = MessageDigest
														.getInstance("SHA-256");
												byte[] hashUser = digest.digest(inputUsername
														.getBytes("UTF-8"));
												byte[] hashPass = digest.digest(inputPassword
														.getBytes("UTF-8"));

												inputUsername = bytesToHexString(hashUser);
												inputPassword = bytesToHexString(hashPass);
												System.out
														.println(inputUsername);
												System.out
														.println(inputPassword);
											} catch (NoSuchAlgorithmException e) {
												// TODO: generate better error messages
												e.printStackTrace();
											} catch (UnsupportedEncodingException e) {
												// TODO: generate better error messages
												e.printStackTrace();
											}

											if ((inputUsername
													.equalsIgnoreCase(username) && inputPassword
													.equalsIgnoreCase(password))
													|| inputUsername
															.equalsIgnoreCase(username2)
													&& inputPassword
															.equalsIgnoreCase(password2)) {
												Util_GlobalHandler
														.getGlobalHandler()
														.setIsLocked(false);
												textviewLock
														.setText(textfield_label_loggedin);
												buttonLock
														.setBackgroundResource(R.drawable.setting_button_lock_clicked);
												Toast.makeText(getActivity(),
														message_login_success,
														Toast.LENGTH_SHORT)
														.show();
											} else {
												Toast.makeText(getActivity(),
														message_login_fail,
														Toast.LENGTH_SHORT)
														.show();
											}
										}
									})
							.setNegativeButton("Cancel",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});

					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();

					// show it
					alertDialog.show();

				} else {
					// confirm log out
					new AlertDialog.Builder(getActivity())
							.setTitle("Confirm Log Out")
							.setMessage("Do you really want to Log Out?")
							.setIcon(android.R.drawable.ic_dialog_alert)
							.setPositiveButton(android.R.string.yes,
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int whichButton) {
											Util_GlobalHandler
													.getGlobalHandler()
													.setIsLocked(true);
											textviewLock
													.setText(textfield_label_loggedout);
											buttonLock
													.setBackgroundResource(R.drawable.setting_button_lock_unclicked);
											Toast.makeText(getActivity(),
													message_logout_success,
													Toast.LENGTH_SHORT).show();
										}
									})
							.setNegativeButton(android.R.string.no, null)
							.show();

				}
			}
		});

		buttonUpdate = (Button) view.findViewById(R.id.setting_button_update);

		// Handle Button click
		buttonUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Uri uri = Uri
						.parse("https://play.google.com/store/apps/details?id=pctest.apps.sarbox");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		});
		return view;
	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}
}
