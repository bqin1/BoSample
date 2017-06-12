package pctest.apps.sarbox;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment_Calc_Ratio extends Fragment {

	Button button_zero;
	Button button_one;
	Button button_two;
	Button button_three;
	Button button_four;
	Button button_five;
	Button button_six;
	Button button_seven;
	Button button_eight;
	Button button_nine;
	Button button_dot;
	Button button_negative;
	Button button_ac;
	Button button_backspace;
	Button button_equal;
	EditText edittext_WlanSAR;
	EditText edittext_PceSar;
	EditText edittext_Distance;
	TextView textview_Answer;

	// The reason we don't use drawable is because Android is terrible at
	// handling bitmaps in drawable and the
	// asset folder method creates less memory crashing
	TextView textview_WlanSAR;
	TextView textview_PceSar;
	TextView textview_Distance;
	
	boolean isLocked;

	boolean equalClicked = false;

	// this variable determines which button is being populated
	String textSelect = "wlansar";
	private String error_nonNumber = Util_GlobalHandler.getGlobalHandler()
			.get_Error_NonNumber();
	private String error_largeResult = Util_GlobalHandler.getGlobalHandler()
			.get_Error_LargeResult();
	private String error_missingField = Util_GlobalHandler.getGlobalHandler()
			.get_Error_MissingField();
	private String error_distance = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Distance();
	private String error_decimal = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Decimal();
	private String error_negative = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Negative();

	public static Drawable getAssetImage(Context context, String filename)
			throws IOException {
		AssetManager assets = context.getResources().getAssets();
		InputStream buffer = new BufferedInputStream((assets.open(filename
				+ ".png")));
		Bitmap bitmap = BitmapFactory.decodeStream(buffer);
		return new BitmapDrawable(context.getResources(), bitmap);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		isLocked = Util_GlobalHandler.getGlobalHandler().getIsLocked();

		if (isLocked) {
			final View view = inflater.inflate(R.layout.fragment_loginlock,
					container, false);
			return view;
		} else {
			final View view = inflater.inflate(R.layout.fragment_calc_ratio,
					container, false);

			textview_WlanSAR = (TextView) view
					.findViewById(R.id.ratio_textfield_wlansar);
			textview_PceSar = (TextView) view
					.findViewById(R.id.ratio_textfield_pcesar);
			textview_Distance = (TextView) view
					.findViewById(R.id.ratio_textfield_distance);
			
			edittext_WlanSAR = (EditText) view
					.findViewById(R.id.ratio_edittext_wlansar);
			edittext_PceSar = (EditText) view
					.findViewById(R.id.ratio_edittext_pcesar);
			edittext_Distance = (EditText) view
					.findViewById(R.id.ratio_edittext_distance);

			textview_Answer = (TextView) view
					.findViewById(R.id.global_textview_answer);

			button_zero = (Button) view.findViewById(R.id.calc_button_zero);
			button_one = (Button) view.findViewById(R.id.calc_button_one);
			button_two = (Button) view.findViewById(R.id.calc_button_two);
			button_three = (Button) view.findViewById(R.id.calc_button_three);
			button_four = (Button) view.findViewById(R.id.calc_button_four);
			button_five = (Button) view.findViewById(R.id.calc_button_five);
			button_six = (Button) view.findViewById(R.id.calc_button_six);
			button_seven = (Button) view.findViewById(R.id.calc_button_seven);
			button_eight = (Button) view.findViewById(R.id.calc_button_eight);
			button_nine = (Button) view.findViewById(R.id.calc_button_nine);
			button_dot = (Button) view.findViewById(R.id.calc_button_dot);
			button_negative = (Button) view
					.findViewById(R.id.calc_button_negative);
			button_ac = (Button) view.findViewById(R.id.calc_button_ac);
			button_backspace = (Button) view
					.findViewById(R.id.calc_button_backspace);
			button_equal = (Button) view.findViewById(R.id.calc_button_equal);

			try {
				textview_WlanSAR.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_wlansar"));
				textview_PceSar.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_pcesar"));
				textview_Distance.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_distance"));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			loadPrevious();

			// handle edittext listner
			edittext_WlanSAR.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_WlanSAR
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("wlansar");
					setWlanSAR("");
					return false;
				}
			});

			edittext_PceSar.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_PceSar
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("pcesar");
					setPceSAR("");
					return false;
				}
			});

			edittext_Distance.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_Distance
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("distance");
					setDistance("");
					return false;
				}
			});

			// handle buttons
			button_zero.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_zero,
							R.drawable.calc_button_0_clicked,
							R.drawable.calc_button_0_unclicked,
							event.getAction(), "0");
				}
			});

			button_one.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_one,
							R.drawable.calc_button_1_clicked,
							R.drawable.calc_button_1_unclicked,
							event.getAction(), "1");
				}
			});

			button_two.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_two,
							R.drawable.calc_button_2_clicked,
							R.drawable.calc_button_2_unclicked,
							event.getAction(), "2");
				}
			});

			button_three.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					return buttonClick(button_three,
							R.drawable.calc_button_3_clicked,
							R.drawable.calc_button_3_unclicked,
							event.getAction(), "3");
				}
			});

			button_four.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					return buttonClick(button_four,
							R.drawable.calc_button_4_clicked,
							R.drawable.calc_button_4_unclicked,
							event.getAction(), "4");
				}
			});

			button_five.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {

					return buttonClick(button_five,
							R.drawable.calc_button_5_clicked,
							R.drawable.calc_button_5_unclicked,
							event.getAction(), "5");
				}
			});

			button_six.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_six,
							R.drawable.calc_button_6_clicked,
							R.drawable.calc_button_6_unclicked,
							event.getAction(), "6");
				}
			});

			button_seven.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_seven,
							R.drawable.calc_button_7_clicked,
							R.drawable.calc_button_7_unclicked,
							event.getAction(), "7");
				}
			});

			button_eight.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_eight,
							R.drawable.calc_button_8_clicked,
							R.drawable.calc_button_8_unclicked,
							event.getAction(), "8");
				}
			});

			button_nine.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_nine,
							R.drawable.calc_button_9_clicked,
							R.drawable.calc_button_9_unclicked,
							event.getAction(), "9");
				}
			});

			button_dot.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_dot,
							R.drawable.calc_button_dot_clicked,
							R.drawable.calc_button_dot_unclicked,
							event.getAction(), ".");
				}
			});

			button_negative.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_negative,
							R.drawable.calc_button_negative_clicked,
							R.drawable.calc_button_negative_unclicked,
							event.getAction(), "-");
				}
			});

			button_ac.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_ac,
							R.drawable.calc_button_ac_clicked,
							R.drawable.calc_button_ac_unclicked,
							event.getAction(), "ac");
				}
			});

			button_backspace.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_backspace,
							R.drawable.calc_button_backspace_clicked,
							R.drawable.calc_button_backspace_unclicked,
							event.getAction(), "<-");
				}
			});

			button_equal.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return buttonClick(button_equal,
							R.drawable.calc_button_equal_clicked,
							R.drawable.calc_button_equal_unclicked,
							event.getAction(), "=");
				}
			});

			return view;
		}
	}

	// load up previous values
	public void loadPrevious() {
		// load up previous values
		setWlanSAR(Util_GlobalHandler.getGlobalHandler().get_SPLS_WlanSAR());
		setPceSAR(Util_GlobalHandler.getGlobalHandler().get_SPLS_PceSAR());
		setDistance(Util_GlobalHandler.getGlobalHandler().get_SPLS_Distance());
		setAnswer(Util_GlobalHandler.getGlobalHandler().get_SPLS_Equation(), "");

		textSelect = Util_GlobalHandler.getGlobalHandler()
				.get_SPLS_TextSelect();

		textboxReset();

		if (textSelect.equals("wlansar")) {
			edittext_WlanSAR
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("pcesar")) {
			edittext_PceSar
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("distance")) {
			edittext_Distance
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		}
	}

	public void setTextSelect(String text) {
		Util_GlobalHandler.getGlobalHandler().set_SPLS_TextSelect(text);
		textSelect = text;
	}

	public void textboxReset() {
		edittext_WlanSAR
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_PceSar
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_Distance
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
	}

	public void buttonAC() {
		setAnswer("", "");
		setWlanSAR("");
		setPceSAR("");
		setDistance("");
		equalClicked = false;
	}

	public void setAnswer(String temp, String modifier) {
		textview_Answer.setText(temp + modifier);
		Util_GlobalHandler.getGlobalHandler()
				.set_SPLS_Equation(temp + modifier);
	}

	public void setWlanSAR(String temp) {
		edittext_WlanSAR.setText(temp + " W/kg");
		Util_GlobalHandler.getGlobalHandler().set_SPLS_WLANSAR(temp);
	}

	public void setPceSAR(String temp) {
		edittext_PceSar.setText(temp + " W/kg");
		Util_GlobalHandler.getGlobalHandler().set_SPLS_PceSAR(temp);
	}

	public void setDistance(String temp) {
		edittext_Distance.setText(temp + " mm");
		Util_GlobalHandler.getGlobalHandler().set_SPLS_Distance(temp);
	}

	// determine which textbox was selected
	public String determineClicked() {
		if (textSelect.equals("wlansar"))
			return Util_GlobalHandler.getGlobalHandler().get_SPLS_WlanSAR();
		else if (textSelect.equals("pcesar"))
			return Util_GlobalHandler.getGlobalHandler().get_SPLS_PceSAR();
		else if (textSelect.equals("distance"))
			return Util_GlobalHandler.getGlobalHandler().get_SPLS_Distance();

		return "";
	}

	// set the right textbox
	public void setClicked(String temp) {
		if (textSelect.equals("wlansar"))
			setWlanSAR(temp);
		else if (textSelect.equals("pcesar"))
			setPceSAR(temp);
		else if (textSelect.equals("distance"))
			setDistance(temp);
	}

	public void buttonNegative() {
		equalClicked = false;
		String temp = determineClicked();

		// if no values are saved, exit
		if (temp.equals("") || temp.equals(".")) {
			setAnswer(error_negative, "");
		} else {
			double tempdouble = Double.parseDouble(temp) * -1;
			temp = tempdouble + "";
			setClicked(temp);
		}
	}

	public void buttonBackspace() {
		equalClicked = false;
		String temp = determineClicked();

		if (temp.length() > 0) {
			temp = temp.substring(0, temp.length() - 1);
			setClicked(temp);
		}
	}

	public void buttonAppendToValue(String num) {
		String temp = determineClicked();

		if (equalClicked) {
			temp = "";
			equalClicked = false;
		}

		if (temp.length() < 6) {
			if (num.equals(".") && temp.contains(".")) {
				setAnswer(error_decimal, "");
			} else {
				setClicked(temp + num);
			}
		}
	}

	public void buttonSPLSRatio() {
		equalClicked = true;
		String temp1 = Util_GlobalHandler.getGlobalHandler().get_SPLS_WlanSAR();
		String temp2 = Util_GlobalHandler.getGlobalHandler().get_SPLS_PceSAR();
		String temp3 = Util_GlobalHandler.getGlobalHandler()
				.get_SPLS_Distance();

		// clear answer boxes
		setAnswer("", "");

		if (temp1.equals("") || temp2.equals("") || temp3.equals("")) {
			setAnswer(error_missingField, "");
		} else {
			try {
				double wlanSAR = Double.parseDouble(temp1);
				double pceSAR = Double.parseDouble(temp2);
				double distance = Double.parseDouble(temp3);
				double sarSum = wlanSAR + pceSAR;

				// distance 0 check
				if (distance <= 0) {
					setAnswer(error_distance, "");
				} else {
					double finalValue = Util_Calculator.SPLS(sarSum, distance);
					if (finalValue > 1000000) {
						setAnswer(error_largeResult, "");
					} else {
						setAnswer(
								("((" + wlanSAR + " W/kg + " + pceSAR
										+ " W/kg) ^ 1.5) / " + distance
										+ " mm = " + finalValue), " SPLS Ratio");
					}
				}
			} catch (NumberFormatException e) {
				setAnswer(error_nonNumber, "");
			}
		}
	}

	public boolean buttonClick(Button b, int bclick, int bunclick, int event,
			String num) {
		switch (event) {
		case MotionEvent.ACTION_DOWN:
			b.setBackgroundResource(bclick);
			return true;
		case MotionEvent.ACTION_UP:
			b.setBackgroundResource(bunclick);

			if (num.equals("-")) {
				buttonNegative();
			} else if (num.equals("ac")) {
				buttonAC();
			} else if (num.equals("<-")) {
				buttonBackspace();
			} else if (num.equals("=")) {
				buttonSPLSRatio();
			} else {
				buttonAppendToValue(num);
			}

			return true;
		default:
			return true;
		}
	}
}
