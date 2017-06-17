package pctest.apps.sarbox;

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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This fragment opens the SAR exclusion calculator
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Fragment_Calc_Exclusion extends Fragment {

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
	Button button_target1g10g;
	EditText edittext_MaxAllowedPower;
	EditText edittext_Frequency;
	EditText edittext_Distance;
	EditText edittext_DutyFactor;
	TextView textview_Answer;
	TextView textview_DBMtoMW;
	TextView textview_AnswerIC;
	ImageView imageview_FCCDisplay;
	ImageView imageview_ICDisplay;

	// The reason we don't use drawable is because Android is terrible at
	// handling bitmaps in drawable and the
	// asset folder method creates less memory crashing
	ImageView imageview_ICExclusionTable;
	ImageView imageview_PageDivider;

	TextView textview_MaxAllowedPower;
	TextView textview_Distance;
	TextView textview_Frequency;
	TextView textview_DutyFactor;

	boolean isLocked;

	private boolean isTablet = true;
	boolean equalClicked = false;

	// this variable determines which button is being populated
	String textSelect = "maxallowedpower";
	double FCCexclusionLimit = 3.0;
	double ICexclusionMultiplier = 1.0;

	private String error_nonNumber = Util_GlobalHandler.getGlobalHandler()
			.get_Error_NonNumber();
	private String error_largeResult = Util_GlobalHandler.getGlobalHandler()
			.get_Error_LargeResult();
	private String error_missingField = Util_GlobalHandler.getGlobalHandler()
			.get_Error_MissingField();
	private String error_decimal = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Decimal();
	private String error_negative = Util_GlobalHandler.getGlobalHandler()
			.get_Error_Negative();
	private String error_missingDBM = Util_GlobalHandler.getGlobalHandler()
			.get_Error_MissingDBM();

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
					R.layout.fragment_calc_exclusion, container, false);

			imageview_ICExclusionTable = (ImageView) view
					.findViewById(R.id.exclusion_imageview_icexclusiontable);
			imageview_PageDivider = (ImageView) view
					.findViewById(R.id.scaling_imageview_pagedivider);

			textview_MaxAllowedPower = (TextView) view
					.findViewById(R.id.exclusion_textfield_maxallowedpower);
			textview_Distance = (TextView) view
					.findViewById(R.id.exclusion_textfield_distance);
			textview_Frequency = (TextView) view
					.findViewById(R.id.exclusion_textfield_frequency);

			edittext_MaxAllowedPower = (EditText) view
					.findViewById(R.id.exclusion_edittext_maxallowedpower);
			edittext_Frequency = (EditText) view
					.findViewById(R.id.exclusion_edittext_frequency);
			edittext_Distance = (EditText) view
					.findViewById(R.id.exclusion_edittext_distance);
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
			button_target1g10g = (Button) view
					.findViewById(R.id.exclusion_button_target1g10gbutton);

			if (isTablet) {
				textview_DutyFactor = (TextView) view
						.findViewById(R.id.exclusion_textfield_dutyfactor);
				edittext_DutyFactor = (EditText) view
						.findViewById(R.id.exclusion_edittext_dutyfactor);

				textview_DBMtoMW = (TextView) view
						.findViewById(R.id.exclusion_textview_dbmtomw);
				textview_AnswerIC = (TextView) view
						.findViewById(R.id.exclusion_textview_answeric);

				// image rendering
				imageview_FCCDisplay = (ImageView) view
						.findViewById(R.id.exclusion_imageview_fccvisual);
				imageview_ICDisplay = (ImageView) view
						.findViewById(R.id.exclusion_imageview_icvisual);

				try {
					imageview_FCCDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_neutralfcc"));
					imageview_ICDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_neutralic"));
					textview_DutyFactor.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_label_dutyfactor"));
				} catch (IOException e) {
					// TODO: generate better error messages
					e.printStackTrace();
				}

				edittext_DutyFactor.setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						textboxReset();
						edittext_DutyFactor
								.setBackgroundResource(R.drawable.calc_textbox_clicked);
						setTextSelect("dutyfactor");
						setDutyFactor("");
						return false;
					}
				});
			}

			try {
				imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_exclusion_icexclusiontable"));
				imageview_PageDivider.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_pagedivider"));

				textview_MaxAllowedPower.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_maxallowedpower"));
				textview_Distance.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_distance"));
				textview_Frequency.setBackgroundDrawable(getAssetImage(
						getActivity(), "calc_label_frequency"));

			} catch (IOException e) {
				// TODO: generate better error messages
				e.printStackTrace();
			}

			loadPrevious();

			edittext_MaxAllowedPower.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_MaxAllowedPower
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("maxallowedpower");
					setMaxAllowedPower("");
					return false;
				}
			});

			edittext_Frequency.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					textboxReset();
					edittext_Frequency
							.setBackgroundResource(R.drawable.calc_textbox_clicked);
					setTextSelect("frequency");
					setFrequency("");
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

			button_target1g10g.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					buttonTargetSwitch();
				}
			});

			return view;
		}
	}

	/**
	 * loads up previous values if the user has switched fragments
	 */
	public void loadPrevious() {
		// load up previous values
		setMaxAllowedPower(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_MaxAllowedPower());
		setFrequency(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_Frequency());
		setDistance(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_Distance());
		setAnswer(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_Equation(), "");

		if (isTablet) {
			setDutyFactor(Util_GlobalHandler.getGlobalHandler()
					.get_Scaling_DutyFactor());
		}

		setDBMtoMW(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_DBMtoMW(), "");
		setAnswerIC(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_AnswerIC(), "");
		setFCCImage(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_FCCVisual());
		setICImage(Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_ICVisual());

		textSelect = Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_TextSelect();
		FCCexclusionLimit = Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_ExclusionLimit();
		ICexclusionMultiplier = Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_ExclusionMultiplier();

		textboxReset();

		if (textSelect.equals("maxallowedpower")) {
			edittext_MaxAllowedPower
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("frequency")) {
			edittext_Frequency
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("distance")) {
			edittext_Distance
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		} else if (textSelect.equals("dutyfactor") && isTablet) {
			edittext_DutyFactor
					.setBackgroundResource(R.drawable.calc_textbox_clicked);
		}

		if (FCCexclusionLimit == 3.0)
			button_target1g10g
					.setBackgroundResource(R.drawable.calc_button_exclusion_1g);
		else if (FCCexclusionLimit == 7.5)
			button_target1g10g
					.setBackgroundResource(R.drawable.calc_button_exclusion_10g);
	}

	public void setTextSelect(String text) {
		Util_GlobalHandler.getGlobalHandler().set_Exclusion_TextSelect(text);
		textSelect = text;
	}

	public void textboxReset() {
		edittext_MaxAllowedPower
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_Frequency
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		edittext_Distance
				.setBackgroundResource(R.drawable.calc_textbox_unclicked);

		if (isTablet) {
			edittext_DutyFactor
					.setBackgroundResource(R.drawable.calc_textbox_unclicked);
		}

	}

	public void answerReset() {
		setDBMtoMW("", "");
		setAnswerIC("", "");
		setAnswer("", "");
	}

	public void neutralReset() {
		setFCCImage("neutral");
		setICImage("neutral");
	}

	public void buttonAC() {
		answerReset();
		setMaxAllowedPower("");
		setFrequency("");
		setDistance("");
		neutralReset();
		setDutyFactor("100");
		try {
			imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
					getActivity(), "calc_exclusion_icexclusiontable"));
		} catch (IOException e) {
			// TODO: generate better error messages
			e.printStackTrace();
		}
		equalClicked = false;
	}

	@SuppressWarnings("deprecation")
	public void setFCCImage(String FCCVisualInput) {

		if (isTablet) {
			try {
				if (FCCVisualInput.equals("neutral")) {

					imageview_FCCDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_neutralfcc"));

				} else if (FCCVisualInput.equals("pass")) {
					imageview_FCCDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_passfcc"));
				} else if (FCCVisualInput.equals("fail")) {
					imageview_FCCDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_failfcc"));
				} else {
					imageview_FCCDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_neutralfcc"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util_GlobalHandler.getGlobalHandler().set_Exclusion_FCCVisual(
					FCCVisualInput);
		}
	}

	@SuppressWarnings("deprecation")
	public void setICImage(String ICVisualInput) {
		if (isTablet) {
			try {
				if (ICVisualInput.equals("neutral")) {
					imageview_ICDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_neutralic"));
				} else if (ICVisualInput.equals("pass")) {
					imageview_ICDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_passic"));
				} else if (ICVisualInput.equals("fail")) {
					imageview_ICDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_failic"));
				} else {
					imageview_ICDisplay.setBackgroundDrawable(getAssetImage(
							getActivity(), "calc_exclusion_neutralic"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Util_GlobalHandler.getGlobalHandler().set_Exclusion_ICVisual(
					ICVisualInput);
		}
	}

	public void setAnswer(String temp, String modifier) {
		textview_Answer.setText(temp + modifier);
		Util_GlobalHandler.getGlobalHandler().set_Exclusion_Equation(
				temp + modifier);
	}

	public void setAnswerIC(String temp, String modifier) {
		if (isTablet) {
			textview_AnswerIC.setText(temp + modifier);
			Util_GlobalHandler.getGlobalHandler().set_Exclusion_AnswerIC(
					temp + modifier);
		}
	}

	public void setDBMtoMW(String temp, String modifier) {
		if (isTablet) {
			textview_DBMtoMW.setText(temp + modifier);
			Util_GlobalHandler.getGlobalHandler().set_Exclusion_DBMtoMW(
					temp + modifier);
		}
	}

	public void setMaxAllowedPower(String temp) {
		edittext_MaxAllowedPower.setText(temp + " dBm");
		Util_GlobalHandler.getGlobalHandler().set_Exclusion_MaxAllowedPower(
				temp);
	}

	public void setFrequency(String temp) {
		edittext_Frequency.setText(temp + " GHz");
		Util_GlobalHandler.getGlobalHandler().set_Exclusion_Frequency(temp);
	}

	public void setDutyFactor(String temp) {
		if (isTablet) {
			edittext_DutyFactor.setText(temp + " %");
			Util_GlobalHandler.getGlobalHandler()
					.set_Exclusion_DutyFactor(temp);
		}

	}

	public void setDistance(String temp) {
		edittext_Distance.setText(temp + " mm");
		Util_GlobalHandler.getGlobalHandler().set_Exclusion_Distance(temp);
	}

	/**
	 * determine which edittext was selected and return the value in it
	 */
	public String determineClicked() {
		if (textSelect.equals("maxallowedpower"))
			return Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_MaxAllowedPower();
		else if (textSelect.equals("frequency"))
			return Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_Frequency();
		else if (textSelect.equals("distance"))
			return Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_Distance();
		else if (textSelect.equals("dutyfactor"))
			return Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_DutyFactor();

		return "";
	}

	public void setClicked(String temp) {
		if (textSelect.equals("maxallowedpower"))
			setMaxAllowedPower(temp);
		else if (textSelect.equals("frequency"))
			setFrequency(temp);
		else if (textSelect.equals("distance"))
			setDistance(temp);
		else if (textSelect.equals("dutyfactor"))
			setDutyFactor(temp);
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

	public void buttonTargetSwitch() {
		answerReset();
		neutralReset();

		if (FCCexclusionLimit == 3.0) {
			Util_GlobalHandler.getGlobalHandler()
					.set_Exclusion_ExclusionMultiplier(2.5);
			ICexclusionMultiplier = Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_ExclusionMultiplier();

			Util_GlobalHandler.getGlobalHandler().set_Exclusion_ExclusionLimit(
					7.5);
			FCCexclusionLimit = Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_ExclusionLimit();
			button_target1g10g
					.setBackgroundResource(R.drawable.calc_button_exclusion_10g);
			Toast.makeText(getActivity(), "10 gram mode", Toast.LENGTH_SHORT)
					.show();
		} else if (FCCexclusionLimit == 7.5) {
			Util_GlobalHandler.getGlobalHandler()
					.set_Exclusion_ExclusionMultiplier(1);
			ICexclusionMultiplier = Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_ExclusionMultiplier();

			Util_GlobalHandler.getGlobalHandler().set_Exclusion_ExclusionLimit(
					3.0);
			FCCexclusionLimit = Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_ExclusionLimit();
			button_target1g10g
					.setBackgroundResource(R.drawable.calc_button_exclusion_1g);
			Toast.makeText(getActivity(), "1 gram mode", Toast.LENGTH_SHORT)
					.show();
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

	public void buttonExclusion() {
		equalClicked = true;
		String temp1 = Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_MaxAllowedPower();
		String temp2 = Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_Frequency();
		String temp3 = Util_GlobalHandler.getGlobalHandler()
				.get_Exclusion_Distance();
		String temp4 = "";
		
		if (isTablet) {
			temp4 = Util_GlobalHandler.getGlobalHandler()
					.get_Exclusion_DutyFactor();

			// dutycycle field can never be empty, defaults to 100
			if (temp4.equals("")) {
				// when duty factor is changed here, it will be changed
				// down in all calculation too
				temp4 = "100";
				setDutyFactor("100");
			} else {
				double dutyfactor = Double.parseDouble(temp4);

				if (dutyfactor < 0 || dutyfactor > 100) {
					temp4 = "100";
					setDutyFactor("100");
				}
			}
		}

		answerReset();
		neutralReset();

		try {

			if (isTablet) {
				if (temp1.equals("")) {
					setDBMtoMW(error_missingDBM, "");
				} else {
					double maxallowedpower = Double.parseDouble(temp1);
					double dutyfactor = Double.parseDouble(temp4);
					double mWValue = Util_Calculator.dbmToWatt(maxallowedpower,
							dutyfactor);
					setDBMtoMW("(1mW * 10^(" + maxallowedpower
							+ " dBm / 10)) * " + dutyfactor + "% = " + mWValue,
							" mW");
				}
			}

			// Performs the regular calculation
			if (temp1.equals("") || temp2.equals("") || temp3.equals("")) {
				setAnswer(error_missingField, "");
				setAnswerIC(error_missingField, "");
			} else {
				double maxallowedpower = Double.parseDouble(temp1);
				double frequency = Double.parseDouble(temp2);
				double distance = Double.parseDouble(temp3);
				double dutyfactor = Double.parseDouble(temp4);
				double mWValue = Util_Calculator.dbmToWatt(maxallowedpower,
						dutyfactor);
				double roundedMWValue = Math.round(mWValue);

				// distance = 5mm if 0
				if (distance < 5) {
					distance = 5;
					setDistance("5");
				}

				double finalValue = Util_Calculator.Exclusion(roundedMWValue,
						frequency, distance);

				if (finalValue > 1000000) {
					setAnswer(error_largeResult, "");
				} else {
					if (finalValue < FCCexclusionLimit) {
						setAnswer(
								("(" + roundedMWValue + " mW / " + distance
										+ " mm) * SQRT(" + frequency
										+ " GHz) = " + finalValue + "<= "
										+ FCCexclusionLimit + ": "),
								"FCC Excluded");
						setFCCImage("pass");
					} else if (finalValue >= FCCexclusionLimit) {
						setAnswer(
								("(" + roundedMWValue + " mW / " + distance
										+ " mm) * SQRT(" + frequency
										+ " GHz) = " + finalValue + ">= "
										+ FCCexclusionLimit + ": "),
								"FCC Not Excluded");
						setFCCImage("fail");
					}

				}

				if (isTablet) {
					// IC here
					double ICExemptionLimits = 0;

					if (frequency <= 0.3) {
						ICExemptionLimits = Util_Calculator
								.ICExclusionTableLookup300mhz(distance);
						try {
							imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
									getActivity(), "calc_exclusion_300mghz"));
						} catch (IOException e) {
							// TODO: generate better error messages
							e.printStackTrace();
						}
					} else if (frequency > 0.3 && frequency <= .450) {
						ICExemptionLimits = Util_Calculator
								.ICExclusionTableLookup450mhz(distance);
						try {
							imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
									getActivity(), "calc_exclusion_450mghz"));
						} catch (IOException e) {
							// TODO: generate better error messages
							e.printStackTrace();
						}
					} else if (frequency > 0.45 && frequency <= .835) {
						ICExemptionLimits = Util_Calculator
								.ICExclusionTableLookup835mhz(distance);
						try {
							imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
									getActivity(), "calc_exclusion_835mghz"));
						} catch (IOException e) {
							// TODO: generate better error messages
							e.printStackTrace();
						}
					} else if (frequency > 0.835 && frequency <= 1.9) {
						ICExemptionLimits = Util_Calculator
								.ICExclusionTableLookup1900mhz(distance);
						try {
							imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
									getActivity(), "calc_exclusion_1900mghz"));
						} catch (IOException e) {
							// TODO: generate better error messages
							e.printStackTrace();
						}
					} else if (frequency > 1.9 && frequency <= 2.45) {
						ICExemptionLimits = Util_Calculator
								.ICExclusionTableLookup2450mhz(distance);
						try {
							imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
									getActivity(), "calc_exclusion_2450mghz"));
						} catch (IOException e) {
							// TODO: generate better error messages
							e.printStackTrace();
						}
					} else if (frequency > 2.45 && frequency <= 3.5) {
						ICExemptionLimits = Util_Calculator
								.ICExclusionTableLookup3500mhz(distance);
						try {
							imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
									getActivity(), "calc_exclusion_3500mghz"));
						} catch (IOException e) {
							// TODO: generate better error messages
							e.printStackTrace();
						}
					} else if (frequency > 3.5) {
						ICExemptionLimits = Util_Calculator
								.ICExclusionTableLookup5800mhz(distance);
						try {
							imageview_ICExclusionTable.setBackgroundDrawable(getAssetImage(
									getActivity(), "calc_exclusion_5800mghz"));
						} catch (IOException e) {
							// TODO: generate better error messages
							e.printStackTrace();
						}
					}
					ICExemptionLimits = ICExemptionLimits
							* ICexclusionMultiplier;

					if (mWValue < ICExemptionLimits) {
						setAnswerIC(
								(mWValue + " mW < " + ICExemptionLimits
										+ " mW at " + distance + " mm and "
										+ frequency + " GHz:"), " IC Excluded");
						setICImage("pass");
					} else if (mWValue >= ICExemptionLimits) {
						setAnswerIC(
								(mWValue + " mW >= " + ICExemptionLimits
										+ " mW at " + distance + " mm and "
										+ frequency + " GHz:"),
								" IC Not Excluded");
						setICImage("fail");
					}
				}

			}
		} catch (NumberFormatException e) {
			setDBMtoMW(error_nonNumber, "");
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
				buttonExclusion();
			} else {
				buttonAppendToValue(num);
			}

			return true;
		default:
			return true;
		}
	}
}
