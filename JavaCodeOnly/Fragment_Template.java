package pctest.apps.sarbox;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fragment_Template extends Fragment {

	Button buttonzero;
	Button buttonone;
	Button buttontwo;
	Button buttonthree;
	Button buttonfour;
	Button buttonfive;
	Button buttonsix;
	Button buttonseven;
	Button buttoneight;
	Button buttonnine;
	Button buttondot;
	Button buttonnegative;
	Button buttonac;
	Button buttonbackspace;
	Button buttonequal;
	EditText edittextx1;
	EditText edittextx2;
	EditText edittexty1;
	EditText edittexty2;
	EditText edittextxi;
	TextView textviewAnswer;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View view = inflater.inflate(R.layout.fragment_template,
				container, false);

		edittextx1 = (EditText) view.findViewById(R.id.interpolate_edittext_x1);
		edittextx2 = (EditText) view.findViewById(R.id.interpolate_edittext_x2);
		edittexty1 = (EditText) view.findViewById(R.id.interpolate_edittext_y1);
		edittexty2 = (EditText) view.findViewById(R.id.interpolate_edittext_y2);
		edittextxi = (EditText) view.findViewById(R.id.interpolate_edittext_xi);

		textviewAnswer = (TextView) view.findViewById(R.id.converter_textview_answer);

		buttonzero = (Button) view.findViewById(R.id.calc_button_zero);
		buttonone = (Button) view.findViewById(R.id.calc_button_one);
		buttontwo = (Button) view.findViewById(R.id.calc_button_two);
		buttonthree = (Button) view.findViewById(R.id.calc_button_three);
		buttonfour = (Button) view.findViewById(R.id.calc_button_four);
		buttonfive = (Button) view.findViewById(R.id.calc_button_five);
		buttonsix = (Button) view.findViewById(R.id.calc_button_six);
		buttonseven = (Button) view.findViewById(R.id.calc_button_seven);
		buttoneight = (Button) view.findViewById(R.id.calc_button_eight);
		buttonnine = (Button) view.findViewById(R.id.calc_button_nine);
		buttondot = (Button) view.findViewById(R.id.calc_button_dot);
		buttonnegative = (Button) view.findViewById(R.id.calc_button_negative);
		buttonac = (Button) view.findViewById(R.id.calc_button_ac);
		buttonbackspace = (Button) view.findViewById(R.id.calc_button_backspace);
		buttonequal = (Button) view.findViewById(R.id.calc_button_equal);

		loadPrevious();

		// handle edittext listner
		edittextx1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				textboxReset();
				edittextx1.setBackgroundResource(R.drawable.calc_textbox_clicked);
				setTextSelect("x1");
				setX1("");
				return false;
			}
		});

		edittextx2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				textboxReset();
				edittextx2.setBackgroundResource(R.drawable.calc_textbox_clicked);
				setTextSelect("x2");
				setX2("");
				return false;
			}
		});

		edittexty1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				textboxReset();
				edittexty1.setBackgroundResource(R.drawable.calc_textbox_clicked);
				setTextSelect("y1");
				setY1("");
				return false;
			}
		});

		edittexty2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				textboxReset();
				edittexty2.setBackgroundResource(R.drawable.calc_textbox_clicked);
				setTextSelect("y2");
				setY2("");
				return false;
			}
		});

		edittextxi.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				textboxReset();
				edittextxi.setBackgroundResource(R.drawable.calc_textbox_clicked);
				setTextSelect("xi");
				setXi("");
				return false;
			}
		});

		// handle buttons
		buttonzero.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonzero, R.drawable.calc_button_0_clicked,
						R.drawable.calc_button_0_unclicked, event.getAction(),
						"0");
			}
		});

		buttonone.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonone, R.drawable.calc_button_1_clicked,
						R.drawable.calc_button_1_unclicked, event.getAction(),
						"1");
			}
		});

		buttontwo.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttontwo, R.drawable.calc_button_2_clicked,
						R.drawable.calc_button_2_unclicked, event.getAction(),
						"2");
			}
		});

		buttonthree.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				return buttonClick(buttonthree, R.drawable.calc_button_3_clicked,
						R.drawable.calc_button_3_unclicked, event.getAction(),
						"3");
			}
		});

		buttonfour.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				return buttonClick(buttonfour, R.drawable.calc_button_4_clicked,
						R.drawable.calc_button_4_unclicked, event.getAction(),
						"4");
			}
		});

		buttonfive.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				return buttonClick(buttonfive, R.drawable.calc_button_5_clicked,
						R.drawable.calc_button_5_unclicked, event.getAction(),
						"5");
			}
		});

		buttonsix.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonsix, R.drawable.calc_button_6_clicked,
						R.drawable.calc_button_6_unclicked, event.getAction(),
						"6");
			}
		});

		buttonseven.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonseven, R.drawable.calc_button_7_clicked,
						R.drawable.calc_button_7_unclicked, event.getAction(),
						"7");
			}
		});

		buttoneight.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttoneight, R.drawable.calc_button_8_clicked,
						R.drawable.calc_button_8_unclicked, event.getAction(),
						"8");
			}
		});

		buttonnine.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonnine, R.drawable.calc_button_9_clicked,
						R.drawable.calc_button_9_unclicked, event.getAction(),
						"9");
			}
		});

		buttondot.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttondot, R.drawable.calc_button_dot_clicked,
						R.drawable.calc_button_dot_unclicked, event.getAction(),
						".");
			}
		});

		buttonnegative.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonnegative,
						R.drawable.calc_button_negative_clicked,
						R.drawable.calc_button_negative_unclicked,
						event.getAction(), "-");
			}
		});

		buttonac.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonac, R.drawable.calc_button_ac_clicked,
						R.drawable.calc_button_ac_unclicked, event.getAction(),
						"ac");
			}
		});

		buttonbackspace.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonbackspace,
						R.drawable.calc_button_backspace_clicked,
						R.drawable.calc_button_backspace_unclicked,
						event.getAction(), "<-");
			}
		});

		buttonequal.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return buttonClick(buttonequal, R.drawable.calc_button_equal_clicked,
						R.drawable.calc_button_equal_unclicked,
						event.getAction(), "=");
			}
		});

		return view;
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
			edittextx1.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("x2")) {
			edittextx2.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("y1")) {
			edittexty1.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("y2")) {
			edittexty2.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("yi")) {
			edittextxi.setBackgroundResource(R.drawable.calc_textbox_clicked);
		}
	}

	public void setTextSelect(String text) {
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_TextSelect(text);
		textSelect = text;
	}

	public void textboxReset() {
		edittextx1.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittextx2.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittexty1.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittexty2.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittextxi.setBackgroundResource(R.drawable.calc_textbox_unclicked);
	}

	public void buttonAC() {
		setAnswer("", "");
		setX1("");
		setX2("");
		setY1("");
		setY2("");
		setXi("");
	}

	public void setAnswer(String temp, String modifier) {
		textviewAnswer.setText(temp + modifier);
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_Equation(
				temp + modifier);
	}

	public void setX1(String temp) {
		edittextx1.setText(temp + " GHz");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_x1(temp);
	}

	public void setX2(String temp) {
		edittextx2.setText(temp + " GHz");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_x2(temp);
	}

	public void setY1(String temp) {
		edittexty1.setText(temp + " ");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_y1(temp);
	}

	public void setY2(String temp) {
		edittexty2.setText(temp + " ");
		Util_GlobalHandler.getGlobalHandler().set_Interpolate_y2(temp);
	}

	public void setXi(String temp) {
		edittextxi.setText(temp + " GHz");
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
		String temp = determineClicked();

		if (temp.length() > 0) {
			temp = temp.substring(0, temp.length() - 1);
			setClicked(temp);
		}
	}

	public void buttonAppendToValue(String num) {
		String temp = determineClicked();

		if (temp.length() < 6) {
			if (num.equals(".") && temp.contains(".")) {
				setAnswer(error_decimal, "");
			} else {
				setClicked(temp + num);
			}
		}
	}

	public void buttonSARScaling() {
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

					setAnswer("interpolated value: " + interpolatedValue[0], "");
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
				buttonSARScaling();
			} else {
				buttonAppendToValue(num);
			}

			return true;
		default:
			return true;
		}
	}
}
