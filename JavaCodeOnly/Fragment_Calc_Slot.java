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

/**
 * This fragment opens the GPRS Slot calculator
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Fragment_Calc_Slot extends Fragment {

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
	EditText edittext_burstSlot1;
	EditText edittext_burstSlot2;
	EditText edittext_burstSlot3;
	EditText edittext_burstSlot4;
	TextView textview_slot_frameSlot1;
	TextView textview_slot_frameSlot2;
	TextView textview_slot_frameSlot3;
	TextView textview_slot_frameSlot4;
	TextView textview_Answer;

	// The reason we don't use drawable is because Android is terrible at
	// handling bitmaps in drawable and the
	// asset folder method creates less memory crashing
	TextView textview_burstslot1;
	TextView textview_burstslot2;
	TextView textview_burstslot3;
	TextView textview_burstslot4;
	TextView textview_frameslot1;
	TextView textview_frameslot2;
	TextView textview_frameslot3;
	TextView textview_frameslot4;

	boolean isLocked;

	// this variable determines which button is being populated
	String textSelect = "burstslot1";

	private String error_nonNumber = Util_GlobalHandler.getGlobalHandler()
			.get_Error_NonNumber();
	private String error_success = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Success();
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
			final View view = inflater.inflate(R.layout.fragment_calc_slot,
					container, false);

			textview_burstslot1 = (TextView) view
					.findViewById(R.id.slot_textfield_burstslot1);
			textview_burstslot2 = (TextView) view
					.findViewById(R.id.slot_textfield_burstslot2);
			textview_burstslot3 = (TextView) view
					.findViewById(R.id.slot_textfield_burstslot3);
			textview_burstslot4 = (TextView) view
					.findViewById(R.id.slot_textfield_burstslot4);
			textview_frameslot1 = (TextView) view
					.findViewById(R.id.slot_textfield_frameslot1);
			textview_frameslot2 = (TextView) view
					.findViewById(R.id.slot_textfield_frameslot2);
			textview_frameslot3 = (TextView) view
					.findViewById(R.id.slot_textfield_frameslot3);
			textview_frameslot4 = (TextView) view
					.findViewById(R.id.slot_textfield_frameslot4);
			
			edittext_burstSlot1 = (EditText) view
					.findViewById(R.id.slot_edittext_burstslot1);
			edittext_burstSlot2 = (EditText) view
					.findViewById(R.id.slot_edittext_burstslot2);
			edittext_burstSlot3 = (EditText) view
					.findViewById(R.id.slot_edittext_burstslot3);
			edittext_burstSlot4 = (EditText) view
					.findViewById(R.id.slot_edittext_burstslot4);

			textview_slot_frameSlot1 = (TextView) view
					.findViewById(R.id.slot_textview_frameslot1);
			textview_slot_frameSlot2 = (TextView) view
					.findViewById(R.id.slot_textview_frameslot2);
			textview_slot_frameSlot3 = (TextView) view
					.findViewById(R.id.slot_textview_frameslot3);
			textview_slot_frameSlot4 = (TextView) view
					.findViewById(R.id.slot_textview_frameslot4);

			textview_Answer = (TextView) view
					.findViewById(R.id.slot_textview_answer);

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
				textview_burstslot1.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_burstslot1"));
				textview_burstslot2.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_burstslot2"));
				textview_burstslot3.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_burstslot3"));
				textview_burstslot4.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_burstslot4"));
				textview_frameslot1.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_frameslot1"));
				textview_frameslot2.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_frameslot2"));
				textview_frameslot3.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_frameslot3"));
				textview_frameslot4.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_frameslot4"));
				
			} catch (IOException e) {
				// TODO: generate better error messages
				e.printStackTrace();
			}
			
			loadPrevious();

			// handle edittext listner
			edittext_burstSlot1.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_burstSlot1
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("burstslot1");
					setBurstSlot1("0");
					setFrameSlot1("0");
					buttonSlot();
					return false;
				}
			});

			edittext_burstSlot2.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_burstSlot2
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("burstslot2");
					setBurstSlot2("0");
					setFrameSlot2("0");
					buttonSlot();
					return false;
				}
			});

			edittext_burstSlot3.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_burstSlot3
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("burstslot3");
					setBurstSlot3("0");
					setFrameSlot3("0");
					buttonSlot();
					return false;
				}
			});

			edittext_burstSlot4.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_burstSlot4
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("burstslot4");
					setBurstSlot4("0");
					setFrameSlot4("0");
					buttonSlot();
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

	/**
	 * loads up previous values if the user has switched fragments
	 */
	public void loadPrevious() {
		setBurstSlot1(Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot1());
		setBurstSlot2(Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot2());
		setBurstSlot3(Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot3());
		setBurstSlot4(Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot4());

		setAnswer(Util_GlobalHandler.getGlobalHandler().get_Slot_Answer());

		textSelect = Util_GlobalHandler.getGlobalHandler()
				.get_Slot_TextSelect();

		if (textSelect.equals("burstslot1")) {
			edittext_burstSlot1
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("burstslot1")) {
			edittext_burstSlot2
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("burstslot1")) {
			edittext_burstSlot3
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("burstslot4")) {
			edittext_burstSlot4
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		}

		buttonSlot();
	}

	public void setTextSelect(String text) {
		Util_GlobalHandler.getGlobalHandler().set_Slot_TextSelect(text);
		textSelect = text;
	}

	public void textboxReset() {
		edittext_burstSlot1
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_burstSlot2
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_burstSlot3
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_burstSlot4
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
	}

	public void buttonAC() {
		setBurstSlot1("0");
		setBurstSlot2("0");
		setBurstSlot3("0");
		setBurstSlot4("0");
		setAnswer(error_success);

		buttonSlot();
	}

	public void setAnswer(String temp) {
		textview_Answer.setText(temp);
		Util_GlobalHandler.getGlobalHandler().set_Slot_Answer(temp);
	}

	public void setFrameSlot1(String temp) {
		textview_slot_frameSlot1.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_FrameSlot1(temp);
	}

	public void setFrameSlot2(String temp) {
		textview_slot_frameSlot2.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_FrameSlot2(temp);
	}

	public void setFrameSlot3(String temp) {
		textview_slot_frameSlot3.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_FrameSlot3(temp);
	}

	public void setFrameSlot4(String temp) {
		textview_slot_frameSlot4.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_FrameSlot4(temp);
	}

	public void setBurstSlot1(String temp) {
		edittext_burstSlot1.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_BurstSlot1(temp);
	}

	public void setBurstSlot2(String temp) {
		edittext_burstSlot2.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_BurstSlot2(temp);
	}

	public void setBurstSlot3(String temp) {
		edittext_burstSlot3.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_BurstSlot3(temp);
	}

	public void setBurstSlot4(String temp) {
		edittext_burstSlot4.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Slot_BurstSlot4(temp);
	}

	/**
	 * determine which edittext was selected and return the value in it
	 */
	public String determineClicked() {
		if (textSelect.equals("burstslot1"))
			return Util_GlobalHandler.getGlobalHandler().get_Slot_BurstSlot1();
		else if (textSelect.equals("burstslot2"))
			return Util_GlobalHandler.getGlobalHandler().get_Slot_BurstSlot2();
		else if (textSelect.equals("burstslot3"))
			return Util_GlobalHandler.getGlobalHandler().get_Slot_BurstSlot3();
		else if (textSelect.equals("burstslot4"))
			return Util_GlobalHandler.getGlobalHandler().get_Slot_BurstSlot4();

		return "";
	}

	public void setClicked(String temp) {
		if (textSelect.equals("burstslot1"))
			setBurstSlot1(temp);
		else if (textSelect.equals("burstslot2"))
			setBurstSlot2(temp);
		else if (textSelect.equals("burstslot3"))
			setBurstSlot3(temp);
		else if (textSelect.equals("burstslot4"))
			setBurstSlot4(temp);
	}

	public void buttonNegative() {
		String temp = determineClicked();

		// if no values are saved, exit
		if (temp.equals("") || temp.equals(".")) {
			setAnswer(error_negative);
		} else {
			double tempdouble = Double.parseDouble(temp) * -1;
			temp = tempdouble + "";
			setClicked(temp);
			buttonSlot();
		}
	}

	public void buttonBackspace() {
		String temp = determineClicked();

		if (temp.length() > 0) {
			temp = temp.substring(0, temp.length() - 1);
			setClicked(temp);
			buttonSlot();
		}
	}

	public void buttonAppendToValue(String num) {
		String temp = determineClicked();

		if (temp.length() < 6) {

			if (temp.equals("0")) {
				temp = "";
			}

			if (num.equals(".") && temp.contains(".")) {
				setAnswer(error_decimal);
			} else {
				setClicked(temp + num);
				buttonSlot();
			}
		}
	}

	public void buttonSlot() {
		String temp1 = Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot1();
		String temp2 = Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot2();
		String temp3 = Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot3();
		String temp4 = Util_GlobalHandler.getGlobalHandler()
				.get_Slot_BurstSlot4();

		if (temp1.equals("")) {
			setBurstSlot1("0");
			temp1 = "0";
		}

		if (temp2.equals("")) {
			setBurstSlot2("0");
			temp1 = "0";
		}

		if (temp3.equals("")) {
			setBurstSlot3("0");
			temp1 = "0";
		}

		if (temp4.equals("")) {
			setBurstSlot4("0");
			temp1 = "0";
		}

		try {
			double burstslot1Value = Double.parseDouble(temp1);
			double burstslot2Value = Double.parseDouble(temp2);
			double burstslot3Value = Double.parseDouble(temp3);
			double burstslot4Value = Double.parseDouble(temp4);

			double frameslot1Value = Util_Calculator.BursttoFrame(
					burstslot1Value, 1);
			double frameslot2Value = Util_Calculator.BursttoFrame(
					burstslot2Value, 2);
			double frameslot3Value = Util_Calculator.BursttoFrame(
					burstslot3Value, 3);
			double frameslot4Value = Util_Calculator.BursttoFrame(
					burstslot4Value, 4);

			if (frameslot1Value > 1000000 || frameslot2Value > 1000000
					|| frameslot3Value > 1000000 || frameslot4Value > 1000000) {

			} else {
				setFrameSlot1(frameslot1Value + "");
				setFrameSlot2(frameslot2Value + "");
				setFrameSlot3(frameslot3Value + "");
				setFrameSlot4(frameslot4Value + "");
				setAnswer(error_success);
			}

		} catch (NumberFormatException e) {
			// TODO: generate better error messages
			setAnswer(error_nonNumber);
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
				buttonSlot();
			} else {
				buttonAppendToValue(num);
			}

			return true;
		default:
			return true;
		}
	}
}
