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

public class Fragment_Calc_Interpolate extends Fragment {

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
	EditText edittext_x1;
	EditText edittext_x2;
	EditText edittext_y1;
	EditText edittext_y2;
	EditText edittext_xi;
	TextView textview_Answer;

	// The reason we don't use drawable is because Android is terrible at
	// handling bitmaps in drawable and the
	// asset folder method creates less memory crashing
	TextView textview_x1;
	TextView textview_x2;
	TextView textview_xi;
	TextView textview_y1;
	TextView textview_y2;

	boolean isLocked;

	boolean equalClicked = false;

	// this variable determines which button is being populated
	String textSelect = "x1";
	private String error_nonNumber = Util_GlobalHandler.getGlobalHandler()
			.get_Error_NonNumber();
	private String error_missingField = Util_GlobalHandler.getGlobalHandler()
			.get_Error_MissingField();
	private String error_decimal = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Decimal();
	private String error_negative = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Negative();
	private String error_freqOutBound = Util_GlobalHandler.getGlobalHandler()
			.get_Error_FreqOutBound();
	private String error_freqTooSmall = Util_GlobalHandler.getGlobalHandler()
			.get_Error_FreqTooSmall();

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

			final View view = inflater.inflate(
					R.layout.fragment_calc_interpolate, container, false);

			textview_x1 = (TextView) view
					.findViewById(R.id.interpolate_textfield_x1);
			textview_x2 = (TextView) view
					.findViewById(R.id.interpolate_textfield_x2);
			textview_xi = (TextView) view
					.findViewById(R.id.interpolate_textfield_xi);
			textview_y1 = (TextView) view
					.findViewById(R.id.interpolate_textfield_y1);
			textview_y2 = (TextView) view
					.findViewById(R.id.interpolate_textfield_y2);

			edittext_x1 = (EditText) view
					.findViewById(R.id.interpolate_edittext_x1);
			edittext_x2 = (EditText) view
					.findViewById(R.id.interpolate_edittext_x2);
			edittext_y1 = (EditText) view
					.findViewById(R.id.interpolate_edittext_y1);
			edittext_y2 = (EditText) view
					.findViewById(R.id.interpolate_edittext_y2);
			edittext_xi = (EditText) view
					.findViewById(R.id.interpolate_edittext_xi);

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
				textview_x1.setBackgroundDrawable(getAssetImage(getActivity(),
						"calc_label_interpolate_x1"));
				textview_x2.setBackgroundDrawable(getAssetImage(getActivity(),
						"calc_label_interpolate_x2"));
				textview_xi.setBackgroundDrawable(getAssetImage(getActivity(),
						"calc_label_interpolate_xi"));
				textview_y1.setBackgroundDrawable(getAssetImage(getActivity(),
						"calc_label_interpolate_y1"));
				textview_y2.setBackgroundDrawable(getAssetImage(getActivity(),
						"calc_label_interpolate_y2"));

			} catch (IOException e) {
				e.printStackTrace();
			}

			loadPrevious();

			// handle edittext listner
			edittext_x1.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_x1
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("x1");
					setX1("");
					return false;
				}
			});

			edittext_x2.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_x2
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("x2");
					setX2("");
					return false;
				}
			});

			edittext_y1.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_y1
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("y1");
					setY1("");
					return false;
				}
			});

			edittext_y2.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_y2
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("y2");
					setY2("");
					return false;
				}
			});

			edittext_xi.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_xi
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("xi");
					setXi("");
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
		setX1(Util_GlobalHandler.getGlobalHandler().get_Interpolate_x1());
		setX2(Util_GlobalHandler.getGlobalHandler().get_Interpolate_x2());
		setY1(Util_GlobalHandler.getGlobalHandler().get_Interpolate_y1());
		setY2(Util_GlobalHandler.getGlobalHandler().get_Interpolate_y2());
		setXi(Util_GlobalHandler.getGlobalHandler().get_Interpolate_xi());
		setAnswer(Util_GlobalHandler.getGlobalHandler()
				.get_Interpolate_Equation(), "");

		textSelect = Util_GlobalHandler.getGlobalHandler()
				.get_Interpolate_TextSelect();

		textboxReset();

		if (textSelect.equals("x1")) {
			edittext_x1.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("x2")) {
			edittext_x2.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("y1")) {
			edittext_y1.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("y2")) {
			edittext_y2.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("yi")) {
			edittext_xi.setBackgroundResource(R.drawable.calc_textbox_clicked);
		}
	}

	public void setTextSelect(String text) {
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_TextSelect(text);
		textSelect = text;
	}

	public void textboxReset() {
		edittext_x1.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_x2.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_y1.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_y2.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_xi.setBackgroundResource(R.drawable.calc_textbox_unclicked);
	}

	public void buttonAC() {
		setAnswer("", "");
		setX1("");
		setX2("");
		setY1("");
		setY2("");
		setXi("");
		equalClicked = false;
	}

	public void setAnswer(String temp, String modifier) {
		textview_Answer.setText(temp + modifier);
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_Equation(
				temp + modifier);
	}

	public void setX1(String temp) {
		edittext_x1.setText(temp + " GHz");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_x1(temp);
	}

	public void setX2(String temp) {
		edittext_x2.setText(temp + " GHz");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_x2(temp);
	}

	public void setY1(String temp) {
		edittext_y1.setText(temp + " ");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_y1(temp);
	}

	public void setY2(String temp) {
		edittext_y2.setText(temp + " ");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_y2(temp);
	}

	public void setXi(String temp) {
		edittext_xi.setText(temp + " GHz");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_xi(temp);
	}

	// determine which textbox was selected
	public String determineClicked() {
		if (textSelect.equals("x1"))
			return Util_GlobalHandler.getGlobalHandler().get_Interpolate_x1();
		else if (textSelect.equals("x2"))
			return Util_GlobalHandler.getGlobalHandler().get_Interpolate_x2();
		else if (textSelect.equals("y1"))
			return Util_GlobalHandler.getGlobalHandler().get_Interpolate_y1();
		else if (textSelect.equals("y2"))
			return Util_GlobalHandler.getGlobalHandler().get_Interpolate_y2();
		else if (textSelect.equals("xi"))
			return Util_GlobalHandler.getGlobalHandler().get_Interpolate_xi();

		return "";
	}

	// set the right textbox
	public void setClicked(String temp) {
		if (textSelect.equals("x1"))
			setX1(temp);
		else if (textSelect.equals("x2"))
			setX2(temp);
		else if (textSelect.equals("y1"))
			setY1(temp);
		else if (textSelect.equals("y2"))
			setY2(temp);
		else if (textSelect.equals("xi"))
			setXi(temp);
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

	public void buttonInterpolate() {
		equalClicked = true;
		String tempx1 = Util_GlobalHandler.getGlobalHandler()
				.get_Interpolate_x1();
		String tempx2 = Util_GlobalHandler.getGlobalHandler()
				.get_Interpolate_x2();
		String tempy1 = Util_GlobalHandler.getGlobalHandler()
				.get_Interpolate_y1();
		String tempy2 = Util_GlobalHandler.getGlobalHandler()
				.get_Interpolate_y2();
		String tempxi = Util_GlobalHandler.getGlobalHandler()
				.get_Interpolate_xi();

		// clear answer boxes
		setAnswer("", "");

		if (tempx1.equals("") || tempx2.equals("") || tempy1.equals("")
				|| tempy2.equals("") || tempxi.equals("")) {
			setAnswer(error_missingField, "");
		} else {
			try {
				// calculates
				double x1Value = Double.parseDouble(tempx1);
				double x2Value = Double.parseDouble(tempx2);
				double y1Value = Double.parseDouble(tempy1);
				double y2Value = Double.parseDouble(tempy2);
				double xiValue = Double.parseDouble(tempxi);

				if (x1Value >= x2Value) {
					// error catch divide by 0 and ascending frequency
					setAnswer(error_freqTooSmall, "");
				}
				if (xiValue <= x1Value || xiValue >= x2Value) {
					// error catch frequency out of bounds
					setAnswer(error_freqOutBound, "");
				} else {
					double[] a = { x1Value, x2Value };
					double[] b = { y1Value, y2Value };
					double[] c = { xiValue };
					double[] interpolatedValue = Util_Calculator.interpLinear(
							a, b, c);

					setAnswer("Interpolated value at " + xiValue + " GHz: "
							+ interpolatedValue[0], "");
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
				buttonInterpolate();
			} else {
				buttonAppendToValue(num);
			}

			return true;
		default:
			return true;
		}
	}
}
