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
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This fragment opens the dbm to mw converter, also provides validation powers given a target
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Fragment_Calc_Converter extends Fragment {

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
    EditText edittext_DBM;
    EditText edittext_MW;
    EditText edittext_Target;
    TextView textview_Answer;
    EditText edittext_validation4;
    EditText edittext_validation2;
    EditText edittext_validation1;
    EditText edittext_validationpoint2;
    EditText edittext_validation1point6;

    ImageView imageview_PageDivider;

    // The reason we don't use drawable is because Android is terrible at
    // handling bitmaps in drawable and the
    // asset folder method creates less memory crashing
    TextView textview_ValidationText;
    TextView textview_DBMLabel;
    TextView textview_MWLabel;
    TextView textview_TargetLabel;
    TextView textview_TargetWKGLabel;
    TextView textview_Validation4Label;
    TextView textview_Validation2Label;
    TextView textview_Validation1Label;
    TextView textview_Validationpoint2Label;
    TextView textview_Validation1point6Label;

    private boolean isTablet = true;

    //equalClicked is a variable that determines whether to compute new values or append to existing
    boolean equalClicked = false;
    boolean isLocked;

    String textSelect = "dbm";
    private String error_nonNumber = Util_GlobalHandler.getGlobalHandler()
            .get_Error_NonNumber();
    private String error_largeResult = Util_GlobalHandler.getGlobalHandler()
            .get_Error_LargeResult();
    private String error_missingField = Util_GlobalHandler.getGlobalHandler()
            .get_Error_MissingField();
    private String error_decimal = Util_GlobalHandler.getGlobalHandler()
            .get_Error_Decimal();
    private String error_unrealNumber = Util_GlobalHandler.getGlobalHandler()
            .get_Error_UnrealNumber();
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

        // permanently unlocked for now
        isLocked = false;

        if (isLocked) {
            final View view = inflater.inflate(R.layout.fragment_loginlock,
                    container, false);
            return view;
        } else {
            final View view = inflater.inflate(R.layout.fragment_calc_converter, container, false);

            edittext_DBM = (EditText) view.findViewById(R.id.converter_edittext_dbm);
            edittext_MW = (EditText) view
                    .findViewById(R.id.converter_edittext_mw);
            textview_Answer = (TextView) view
                    .findViewById(R.id.global_textview_answer);

            textview_DBMLabel = (TextView) view
                    .findViewById(R.id.converter_textfield_dbm);
            textview_MWLabel = (TextView) view
                    .findViewById(R.id.converter_textfield_mw);

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

            if (isTablet) {
                edittext_validation4 = (EditText) view
                        .findViewById(R.id.converter_edittext_validation4);
                edittext_validation2 = (EditText) view
                        .findViewById(R.id.converter_edittext_validation2);
                edittext_validation1 = (EditText) view
                        .findViewById(R.id.converter_edittext_validation1);
                edittext_validationpoint2 = (EditText) view
                        .findViewById(R.id.converter_edittext_validationpoint2);
                edittext_validation1point6 = (EditText) view
                        .findViewById(R.id.converter_edittext_validation1point6);

                textview_TargetWKGLabel = (TextView) view
                        .findViewById(R.id.converter_textview_targetwkg);
                textview_TargetLabel = (TextView) view
                        .findViewById(R.id.converter_textfield_target);
                textview_ValidationText = (TextView) view
                        .findViewById(R.id.converter_textview_validationtext);
                imageview_PageDivider = (ImageView) view
                        .findViewById(R.id.converter_imageview_pagedivider);
                textview_Validation4Label = (TextView) view
                        .findViewById(R.id.converter_textview_validation4);
                textview_Validation2Label = (TextView) view
                        .findViewById(R.id.converter_textview_validation2);
                textview_Validation1Label = (TextView) view
                        .findViewById(R.id.converter_textview_validation1);
                textview_Validationpoint2Label = (TextView) view
                        .findViewById(R.id.converter_textview_validationpoint2);
                textview_Validation1point6Label = (TextView) view
                        .findViewById(R.id.converter_textview_validation1point6);

                edittext_Target = (EditText) view
                        .findViewById(R.id.converter_edittext_target);
                try {
                    imageview_PageDivider.setBackgroundDrawable(getAssetImage(
                            getActivity(), "calc_pagedivider"));

                    textview_TargetWKGLabel
                            .setBackgroundDrawable(getAssetImage(getActivity(),
                                    "calc_label_wkg"));
                    textview_TargetLabel.setBackgroundDrawable(getAssetImage(
                            getActivity(), "calc_label_dipoletarget"));
                    textview_ValidationText
                            .setBackgroundDrawable(getAssetImage(getActivity(),
                                    "calc_label_validationtext"));
                    textview_Validation4Label
                            .setBackgroundDrawable(getAssetImage(getActivity(),
                                    "calc_label_validation4"));
                    textview_Validation2Label
                            .setBackgroundDrawable(getAssetImage(getActivity(),
                                    "calc_label_validation2"));
                    textview_Validation1Label
                            .setBackgroundDrawable(getAssetImage(getActivity(),
                                    "calc_label_validation1"));
                    textview_Validationpoint2Label
                            .setBackgroundDrawable(getAssetImage(getActivity(),
                                    "calc_label_validationpoint2"));
                    textview_Validation1point6Label
                            .setBackgroundDrawable(getAssetImage(getActivity(),
                                    "calc_label_validation1point6"));
                } catch (IOException e) {
                    // TODO: generate better error messages
                    e.printStackTrace();
                }
            }

            try {

                textview_DBMLabel.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_dbm"));
                textview_MWLabel.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_mw"));

            } catch (IOException e) {
                // TODO: generate better error messages
                e.printStackTrace();
            }

            loadPrevious();

            // handle edittext listner
            edittext_DBM.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    textboxReset();
                    clearValidation();
                    edittext_DBM
                            .setBackgroundResource(R.drawable.calc_textbox_clicked);
                    setTextSelect("dbm");
                    buttonAC();
                    return false;
                }
            });

            edittext_MW.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    textboxReset();
                    clearValidation();
                    edittext_MW
                            .setBackgroundResource(R.drawable.calc_textbox_clicked);
                    setTextSelect("mw");
                    buttonAC();
                    return false;
                }
            });

            edittext_Target.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    textboxReset();
                    clearValidation();
                    edittext_Target
                            .setBackgroundResource(R.drawable.calc_textbox_clicked);
                    setTextSelect("target");
                    buttonAC();
                    return false;
                }
            });

            // handle buttons on touch listeners
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
        setDBM(Util_GlobalHandler.getGlobalHandler().get_Converter_dbm());
        setMW(Util_GlobalHandler.getGlobalHandler().get_Converter_mw());
        setTarget(Util_GlobalHandler.getGlobalHandler().get_Converter_Target());
        setAnswer(Util_GlobalHandler.getGlobalHandler()
                .get_Converter_Equation(), "");

        textboxReset();

        setValidation4(Util_GlobalHandler.getGlobalHandler()
                .get_Converter_Validation4());
        setValidation2(Util_GlobalHandler.getGlobalHandler()
                .get_Converter_Validation2());
        setValidation1(Util_GlobalHandler.getGlobalHandler()
                .get_Converter_Validation1());
        setValidationpoint2(Util_GlobalHandler.getGlobalHandler()
                .get_Converter_Validationpoint2());
        setValidation1point6(Util_GlobalHandler.getGlobalHandler()
                .get_Converter_Validation1point6());

        textSelect = Util_GlobalHandler.getGlobalHandler()
                .get_Converter_TextSelect();

        if (textSelect.equals("dbm")) {
            edittext_DBM.setBackgroundResource(R.drawable.calc_textbox_clicked);
        } else if (textSelect.equals("mw")) {
            edittext_MW.setBackgroundResource(R.drawable.calc_textbox_clicked);
        } else if (textSelect.equals("target")) {
            edittext_Target.setBackgroundResource(R.drawable.calc_textbox_clicked);
        }
    }

    public void setTextSelect(String text) {
        Util_GlobalHandler.getGlobalHandler().set_Converter_TextSelect(text);
        textSelect = text;
    }

    public void textboxReset() {
        edittext_DBM.setBackgroundResource(R.drawable.calc_textbox_unclicked);
        edittext_MW.setBackgroundResource(R.drawable.calc_textbox_unclicked);
        edittext_Target.setBackgroundResource(R.drawable.calc_textbox_unclicked);
    }

    public void clearValidation() {
        setValidation4("");
        setValidation2("");
        setValidation1("");
        setValidationpoint2("");
        setValidation1point6("");
        setTarget("");
    }

    public void buttonAC() {
        setAnswer("", "");
        setMW("");
        setDBM("");
        setTarget("");
        clearValidation();
        equalClicked = false;
    }

    public void setValidation4(String temp) {
        if (isTablet) {
            edittext_validation4.setText(temp);
            Util_GlobalHandler.getGlobalHandler().set_Converter_Validation4(
                    temp);
        }
    }

    public void setValidation2(String temp) {
        if (isTablet) {
            edittext_validation2.setText(temp);
            Util_GlobalHandler.getGlobalHandler().set_Converter_Validation2(
                    temp);
        }
    }

    public void setValidation1(String temp) {
        if (isTablet) {
            edittext_validation1.setText(temp);
            Util_GlobalHandler.getGlobalHandler().set_Converter_Validation1(
                    temp);
        }
    }

    public void setValidationpoint2(String temp) {
        if (isTablet) {
            edittext_validationpoint2.setText(temp);
            Util_GlobalHandler.getGlobalHandler()
                    .set_Converter_Validationpoint2(temp);
        }
    }

    public void setValidation1point6(String temp) {
        if (isTablet) {
            edittext_validation1point6.setText(temp);
            Util_GlobalHandler.getGlobalHandler()
                    .set_Converter_Validation1point6(temp);
        }
    }

    /**
     * Show the validation numbers given a value in mW
     */
    public void setAnswerValidation(double mwValue) {
        double validation4 = Util_Calculator.numRound(mwValue / 1000, 10000);
        double validation2 = Util_Calculator.numRound(mwValue / 2000, 10000);
        double validation1 = Util_Calculator.numRound(mwValue / 4000, 10000);
        double validationpoint2 = Util_Calculator.numRound(mwValue / 20000, 10000);
        double validation1point6 = Util_Calculator.numRound(mwValue / 2500, 10000);

        double answerValidation4 = Util_Calculator.MWtoDBM(mwValue / 1);
        double answerValidation2 = Util_Calculator.MWtoDBM(mwValue / 2);
        double answerValidation1 = Util_Calculator.MWtoDBM(mwValue / 4);
        double answerValidationpoint2 = Util_Calculator.MWtoDBM(mwValue / 20);
        double answerValidation1point6 = Util_Calculator.MWtoDBM(mwValue / 2.5);

        setValidation4(validation4 + " W = " + answerValidation4 + " dBm");
        setValidation2(validation2 + " W = " + answerValidation2 + " dBm");
        setValidation1(validation1 + " W = " + answerValidation1 + " dBm");
        setValidationpoint2(validationpoint2 + " W = " + answerValidationpoint2
                + " dBm");
        setValidation1point6(validation1point6 + " W = "
                + answerValidation1point6 + " dBm");
    }

    public void setAnswer(String temp, String modifier) {
        textview_Answer.setText(temp + modifier);
        Util_GlobalHandler.getGlobalHandler().set_Conveter_Equation(
                temp + modifier);
    }

    public void setTarget(String temp) {
        if (isTablet) {
            edittext_Target.setText(temp + " W/kg");
            Util_GlobalHandler.getGlobalHandler().set_Converter_Target(
                    temp);
        }
    }

    public void setMW(String temp) {
        edittext_MW.setText(temp + " mW");
        Util_GlobalHandler.getGlobalHandler().set_Converter_mw(temp);
    }

    public void setDBM(String temp) {
        edittext_DBM.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Converter_dbm(temp);
    }

    /**
     * determine which edittext was selected and return the value in it
     */
    public String returnEditTextValue() {
        if (textSelect.equals("mw"))
            return Util_GlobalHandler.getGlobalHandler().get_Converter_mw();
        else if (textSelect.equals("dbm"))
            return Util_GlobalHandler.getGlobalHandler().get_Converter_dbm();
        else if (textSelect.equals("target"))
            return Util_GlobalHandler.getGlobalHandler().get_Converter_Target();

        return "";
    }

    public void setClicked(String temp) {
        if (textSelect.equals("mw"))
            setMW(temp);
        else if (textSelect.equals("dbm"))
            setDBM(temp);
        else if (textSelect.equals("target"))
            setTarget(temp);
    }

    public void buttonNegative() {
        equalClicked = false;
        String temp = returnEditTextValue();

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
        String temp = returnEditTextValue();

        if (temp.length() > 0) {
            temp = temp.substring(0, temp.length() - 1);
            setClicked(temp);
        }
    }

    public void buttonAppendToValue(String num) {

        String temp = returnEditTextValue();

        if (equalClicked) {
            temp = "";
            equalClicked = false;
        }

        if (temp.length() < 10) {
            if (num.equals(".") && temp.contains(".")) {
                setAnswer(error_decimal, "");
            } else {
                setClicked(temp + num);
            }
        }

    }

    public void buttonMWtoDBM() {
        equalClicked = true;
        String temp = Util_GlobalHandler.getGlobalHandler().get_Converter_mw();

        // clear answer boxes
        setAnswer("", "");

        if (temp.equals("")) {
            setAnswer(error_missingField, "");
            setDBM("");
            setTarget("");
        } else {
            // determine if the value is a double
            try {
                double mwValue = Double.parseDouble(temp);

                if (mwValue <= 0) {
                    setAnswer(error_unrealNumber, "");
                    setDBM("");
                } else {
                    // calculates
                    double finalValue = Util_Calculator.MWtoDBM(mwValue);

                    if (finalValue > 1000000) {
                        setAnswer(error_largeResult, "");
                    } else {
                        setDBM(finalValue + "");
                        setAnswer("10 * log10(" + mwValue + " mW / 1mW) = "
                                + finalValue, " dBm");
                        setAnswerValidation(mwValue);
                    }

                }
            } catch (NumberFormatException e) {
                setAnswer(error_nonNumber, "");
                setDBM("");
                setTarget("");
            }

        }
    }

    public void buttonDBMtoMW() {
        equalClicked = true;
        String temp = Util_GlobalHandler.getGlobalHandler().get_Converter_dbm();

        // clear answer boxes
        setAnswer("", "");

        if (temp.equals("")) {
            setAnswer(error_missingField, "");
            setMW("");
            setTarget("");
        } else {
            try {
                double dbmValue = Double.parseDouble(temp);
                double finalValue = Util_Calculator.dbmToWatt(dbmValue, 100);

                if (finalValue > 1000000) {
                    setAnswer(error_largeResult, "");
                    setMW("");
                } else {
                    setMW(finalValue + "");
                    setAnswer("1mW * 10^(" + dbmValue + " dBm / 10) = "
                            + finalValue, " mW");

                    setAnswerValidation(finalValue);
                }

            } catch (NumberFormatException e) {
                setAnswer(error_nonNumber, "");
                setMW("");
                setTarget("");
            }

        }
    }

    public void buttonTarget() {
        equalClicked = true;
        String temp = Util_GlobalHandler.getGlobalHandler().get_Converter_Target();

        // clear answer boxes
        setAnswer("", "");

        if (temp.equals("")) {
            setAnswer(error_missingField, "");
            setDBM("");
            setMW("");
        } else {
            // determine if the value is a double
            try {
                //divide by 4
                double targetValue = Double.parseDouble(temp);
                double targetwValue = 4 / targetValue;
                double targetmwValue = targetwValue * 1000;

                double dbmValue = Util_Calculator.MWtoDBM(targetmwValue);

                if (dbmValue <= 0) {
                    setAnswer(error_unrealNumber, "");
                    setDBM("");
                    setMW("");
                } else {

                    if (dbmValue > 1000000) {
                        setAnswer(error_largeResult, "");
                    } else {
                        setTarget(targetValue + "");
                        setAnswer("4 / " + targetValue + " = "
                                + Util_Calculator.numRound(targetwValue, 10000), " * 1000 = " + Util_Calculator.numRound(targetmwValue, 10000) + " mW");
                        setAnswerValidation(targetmwValue);
                    }

                }
            } catch (NumberFormatException e) {
                setAnswer(error_nonNumber, "");
                setDBM("");
                setMW("");
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
                    if (textSelect.equals("mw"))
                        buttonMWtoDBM();
                    else if (textSelect.equals("dbm"))
                        buttonDBMtoMW();
                    else if (textSelect.equals("target"))
                        buttonTarget();
                } else {
                    buttonAppendToValue(num);
                }

                return true;
            default:
                return true;
        }
    }
}
