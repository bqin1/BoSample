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
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This fragment opens the SAR value scaler
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */
public class Fragment_Calc_Scaling extends Fragment {

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

    EditText edittext_MeasuredPower;
    EditText edittext_MaxAllowedPower;
    EditText edittext_DutyFactor;
    EditText edittext_MeasuredSAR;
    TextView textview_Answer;

    EditText edittext_MeasuredPower2;
    EditText edittext_MaxAllowedPower2;
    EditText edittext_DutyFactor2;
    EditText edittext_MeasuredSAR2;
    TextView textview_Answer2;

    EditText edittext_MeasuredPower3;
    EditText edittext_MaxAllowedPower3;
    EditText edittext_DutyFactor3;
    EditText edittext_MeasuredSAR3;
    TextView textview_Answer3;

    EditText edittext_MeasuredPower4;
    EditText edittext_MaxAllowedPower4;
    EditText edittext_DutyFactor4;
    EditText edittext_MeasuredSAR4;
    TextView textview_Answer4;

    // The reason we don't use drawable is because Android is terrible at
    // handling bitmaps in drawable and the
    // asset folder method creates less memory crashing

    ImageView imageview_PageDivider;

    TextView textview_MeasuredPower;
    TextView textview_MaxAllowedPower;
    TextView textview_MeasuredSAR;
    TextView textview_DutyFactor;

    TextView textview_MeasuredPower2;
    TextView textview_MaxAllowedPower2;
    TextView textview_MeasuredSAR2;
    TextView textview_DutyFactor2;

    TextView textview_MeasuredPower3;
    TextView textview_MaxAllowedPower3;
    TextView textview_MeasuredSAR3;
    TextView textview_DutyFactor3;

    TextView textview_MeasuredPower4;
    TextView textview_MaxAllowedPower4;
    TextView textview_MeasuredSAR4;
    TextView textview_DutyFactor4;

    boolean isLocked;
    boolean equalClicked = false;
    int savedAnswer = 2;
    // this variable determines which button is being populated
    String textSelect = "measuredpower";
    private boolean isTablet = true;
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

        isLocked = false;

        if (isLocked) {
            final View view = inflater.inflate(R.layout.fragment_loginlock,
                    container, false);
            return view;
        } else {
            final View view = inflater.inflate(R.layout.fragment_calc_scaling,
                    container, false);

            imageview_PageDivider = (ImageView) view
                    .findViewById(R.id.scaling_imageview_pagedivider);

            textview_MeasuredPower = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredpower);
            textview_MaxAllowedPower = (TextView) view
                    .findViewById(R.id.scaling_textfield_maxallowedpower);
            textview_MeasuredSAR = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredsar);
            edittext_MeasuredPower = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredpower);
            edittext_MaxAllowedPower = (EditText) view
                    .findViewById(R.id.scaling_edittext_maxallowedpower);
            edittext_MeasuredSAR = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredsar);

            textview_MeasuredPower2 = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredpower2);
            textview_MaxAllowedPower2 = (TextView) view
                    .findViewById(R.id.scaling_textfield_maxallowedpower2);
            textview_MeasuredSAR2 = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredsar2);
            edittext_MeasuredPower2 = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredpower2);
            edittext_MaxAllowedPower2 = (EditText) view
                    .findViewById(R.id.scaling_edittext_maxallowedpower2);
            edittext_MeasuredSAR2 = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredsar2);

            textview_MeasuredPower3 = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredpower3);
            textview_MaxAllowedPower3 = (TextView) view
                    .findViewById(R.id.scaling_textfield_maxallowedpower3);
            textview_MeasuredSAR3 = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredsar3);
            edittext_MeasuredPower3 = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredpower3);
            edittext_MaxAllowedPower3 = (EditText) view
                    .findViewById(R.id.scaling_edittext_maxallowedpower3);
            edittext_MeasuredSAR3 = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredsar3);

            textview_MeasuredPower4 = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredpower4);
            textview_MaxAllowedPower4 = (TextView) view
                    .findViewById(R.id.scaling_textfield_maxallowedpower4);
            textview_MeasuredSAR4 = (TextView) view
                    .findViewById(R.id.scaling_textfield_measuredsar4);
            edittext_MeasuredPower4 = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredpower4);
            edittext_MaxAllowedPower4 = (EditText) view
                    .findViewById(R.id.scaling_edittext_maxallowedpower4);
            edittext_MeasuredSAR4 = (EditText) view
                    .findViewById(R.id.scaling_edittext_measuredsar4);

            textview_Answer = (TextView) view
                    .findViewById(R.id.global_textview_answer);

            textview_Answer2 = (TextView) view
                    .findViewById(R.id.global_textview_answer2);

            textview_Answer3 = (TextView) view
                    .findViewById(R.id.global_textview_answer3);

            textview_Answer4 = (TextView) view
                    .findViewById(R.id.global_textview_answer4);


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

            // permanent tablet form if i comment out the following line
            // isTablet = Util_GlobalHandler.getGlobalHandler().getIsTablet();

            if (isTablet) {
                textview_DutyFactor = (TextView) view
                        .findViewById(R.id.scaling_textfield_dutyfactor);
                edittext_DutyFactor = (EditText) view
                        .findViewById(R.id.scaling_edittext_dutyfactor);

                textview_DutyFactor2 = (TextView) view
                        .findViewById(R.id.scaling_textfield_dutyfactor2);
                edittext_DutyFactor2 = (EditText) view
                        .findViewById(R.id.scaling_edittext_dutyfactor2);

                textview_DutyFactor3 = (TextView) view
                        .findViewById(R.id.scaling_textfield_dutyfactor3);
                edittext_DutyFactor3 = (EditText) view
                        .findViewById(R.id.scaling_edittext_dutyfactor3);

                textview_DutyFactor4 = (TextView) view
                        .findViewById(R.id.scaling_textfield_dutyfactor4);
                edittext_DutyFactor4 = (EditText) view
                        .findViewById(R.id.scaling_edittext_dutyfactor4);

                try {
                    textview_DutyFactor.setBackgroundDrawable(getAssetImage(
                            getActivity(), "calc_label_dutyfactor"));

                    textview_DutyFactor2.setBackgroundDrawable(getAssetImage(
                            getActivity(), "calc_label_dutyfactor"));

                    textview_DutyFactor3.setBackgroundDrawable(getAssetImage(
                            getActivity(), "calc_label_dutyfactor"));

                    textview_DutyFactor4.setBackgroundDrawable(getAssetImage(
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
                textview_MeasuredPower.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredpower"));
                textview_MaxAllowedPower.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_maxallowedpower"));
                textview_MeasuredSAR.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredsar"));

                textview_MeasuredPower2.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredpower"));
                textview_MaxAllowedPower2.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_maxallowedpower"));
                textview_MeasuredSAR2.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredsar"));

                textview_MeasuredPower3.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredpower"));
                textview_MaxAllowedPower3.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_maxallowedpower"));
                textview_MeasuredSAR3.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredsar"));

                textview_MeasuredPower4.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredpower"));
                textview_MaxAllowedPower4.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_maxallowedpower"));
                textview_MeasuredSAR4.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_label_measuredsar"));

                imageview_PageDivider.setBackgroundDrawable(getAssetImage(
                        getActivity(), "calc_pagedivider"));

            } catch (IOException e) {
                // TODO: generate better error messages
                e.printStackTrace();
            }

            loadPrevious();

            // handle edittext listner
            edittext_MeasuredPower.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    textboxReset();
                    edittext_MeasuredPower
                            .setBackgroundResource(R.drawable.calc_textbox_clicked);
                    setTextSelect("measuredpower");
                    setMeasuredPower("");
                    return false;
                }
            });

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

            edittext_MeasuredSAR.setOnTouchListener(new OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    textboxReset();
                    edittext_MeasuredSAR
                            .setBackgroundResource(R.drawable.calc_textbox_clicked);
                    setTextSelect("measuredsar");
                    setMeasuredSAR("");
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
        setMeasuredPower(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredPower());
        setMaxAllowedPower(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MaxAllowedPower());
        setMeasuredSAR(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredSAR());
        setAnswer(Util_GlobalHandler.getGlobalHandler().get_Scaling_Equation(),
                "");

        setMeasuredPower2(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredPower2());
        setMaxAllowedPower2(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MaxAllowedPower2());
        setMeasuredSAR2(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredSAR2());
        setAnswer2(Util_GlobalHandler.getGlobalHandler().get_Scaling_Equation2(),
                "");

        setMeasuredPower3(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredPower3());
        setMaxAllowedPower3(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MaxAllowedPower3());
        setMeasuredSAR3(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredSAR3());
        setAnswer3(Util_GlobalHandler.getGlobalHandler().get_Scaling_Equation3(),
                "");

        setMeasuredPower4(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredPower4());
        setMaxAllowedPower4(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MaxAllowedPower4());
        setMeasuredSAR4(Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredSAR4());
        setAnswer4(Util_GlobalHandler.getGlobalHandler().get_Scaling_Equation4(),
                "");

        if (isTablet) {
            setDutyFactor(Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_DutyFactor());
            setDutyFactor2(Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_DutyFactor2());
            setDutyFactor3(Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_DutyFactor3());
            setDutyFactor4(Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_DutyFactor4());
        }

        textSelect = Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_TextSelect();

        textboxReset();

        if (textSelect.equals("measuredpower")) {
            edittext_MeasuredPower
                    .setBackgroundResource(R.drawable.calc_textbox_clicked);
        } else if (textSelect.equals("maxallowedpower")) {
            edittext_MaxAllowedPower
                    .setBackgroundResource(R.drawable.calc_textbox_clicked);
        } else if (textSelect.equals("measuredsar")) {
            edittext_MeasuredSAR
                    .setBackgroundResource(R.drawable.calc_textbox_clicked);
        } else if (textSelect.equals("dutyfactor") && isTablet) {
            edittext_DutyFactor
                    .setBackgroundResource(R.drawable.calc_textbox_clicked);
        }
    }

    public void setTextSelect(String text) {
        Util_GlobalHandler.getGlobalHandler().set_Scaling_TextSelect(text);
        textSelect = text;
    }

    public void textboxReset() {
        edittext_MeasuredPower
                .setBackgroundResource(R.drawable.calc_textbox_unclicked);
        edittext_MaxAllowedPower
                .setBackgroundResource(R.drawable.calc_textbox_unclicked);
        edittext_MeasuredSAR
                .setBackgroundResource(R.drawable.calc_textbox_unclicked);
        if (isTablet) {
            edittext_DutyFactor
                    .setBackgroundResource(R.drawable.calc_textbox_unclicked);
        }
    }

    public void buttonAC() {
        setAnswer("", "");
        setMeasuredPower("");
        setMaxAllowedPower("");
        setMeasuredSAR("");
        setDutyFactor("100");
        equalClicked = false;
    }

    public void setAnswer(String temp, String modifier) {
        textview_Answer.setText(temp + modifier);
        Util_GlobalHandler.getGlobalHandler().set_Scaling_Equation(
                temp + modifier);
    }

    public void setMeasuredPower(String temp) {
        edittext_MeasuredPower.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredPower(temp);
    }

    public void setMaxAllowedPower(String temp) {
        edittext_MaxAllowedPower.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MaxAllowedPower(temp);
    }

    public void setMeasuredSAR(String temp) {
        edittext_MeasuredSAR.setText(temp + " W/kg");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredSAR(temp);
    }

    public void setDutyFactor(String temp) {
        if (isTablet) {
            edittext_DutyFactor.setText(temp + " %");
            Util_GlobalHandler.getGlobalHandler()
                    .set_Scaling_DutyFactor(temp);
        }

    }

    public void setAnswer2(String temp, String modifier) {
        textview_Answer2.setText(temp + modifier);
        Util_GlobalHandler.getGlobalHandler().set_Scaling_Equation2(
                temp + modifier);
    }

    public void setMeasuredPower2(String temp) {
        edittext_MeasuredPower2.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredPower2(temp);
    }

    public void setMaxAllowedPower2(String temp) {
        edittext_MaxAllowedPower2.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MaxAllowedPower2(temp);
    }

    public void setMeasuredSAR2(String temp) {
        edittext_MeasuredSAR2.setText(temp + " W/kg");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredSAR2(temp);
    }

    public void setDutyFactor2(String temp) {
        if (isTablet) {
            edittext_DutyFactor2.setText(temp + " %");
            Util_GlobalHandler.getGlobalHandler()
                    .set_Scaling_DutyFactor2(temp);
        }

    }

    public void setAnswer3(String temp, String modifier) {
        textview_Answer3.setText(temp + modifier);
        Util_GlobalHandler.getGlobalHandler().set_Scaling_Equation3(
                temp + modifier);
    }

    public void setMeasuredPower3(String temp) {
        edittext_MeasuredPower3.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredPower3(temp);
    }

    public void setMaxAllowedPower3(String temp) {
        edittext_MaxAllowedPower3.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MaxAllowedPower3(temp);
    }

    public void setMeasuredSAR3(String temp) {
        edittext_MeasuredSAR3.setText(temp + " W/kg");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredSAR3(temp);
    }

    public void setDutyFactor3(String temp) {
        if (isTablet) {
            edittext_DutyFactor3.setText(temp + " %");
            Util_GlobalHandler.getGlobalHandler()
                    .set_Scaling_DutyFactor3(temp);
        }

    }

    public void setAnswer4(String temp, String modifier) {
        textview_Answer4.setText(temp + modifier);
        Util_GlobalHandler.getGlobalHandler().set_Scaling_Equation4(
                temp + modifier);
    }

    public void setMeasuredPower4(String temp) {
        edittext_MeasuredPower4.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredPower4(temp);
    }

    public void setMaxAllowedPower4(String temp) {
        edittext_MaxAllowedPower4.setText(temp + " dBm");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MaxAllowedPower4(temp);
    }

    public void setMeasuredSAR4(String temp) {
        edittext_MeasuredSAR4.setText(temp + " W/kg");
        Util_GlobalHandler.getGlobalHandler().set_Scaling_MeasuredSAR4(temp);
    }

    public void setDutyFactor4(String temp) {
        if (isTablet) {
            edittext_DutyFactor4.setText(temp + " %");
            Util_GlobalHandler.getGlobalHandler()
                    .set_Scaling_DutyFactor4(temp);
        }

    }

    /**
     * determine which edittext was selected and return the value in it
     */
    public String determineClicked() {
        if (textSelect.equals("measuredpower"))
            return Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_MeasuredPower();
        else if (textSelect.equals("maxallowedpower"))
            return Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_MaxAllowedPower();
        else if (textSelect.equals("measuredsar"))
            return Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_MeasuredSAR();
        else if (textSelect.equals("dutyfactor"))
            return Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_DutyFactor();

        return "";
    }

    public void setClicked(String temp) {
        if (textSelect.equals("measuredpower"))
            setMeasuredPower(temp);
        else if (textSelect.equals("maxallowedpower"))
            setMaxAllowedPower(temp);
        else if (textSelect.equals("measuredsar"))
            setMeasuredSAR(temp);
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

    public void buttonSARScaling() {
        equalClicked = true;
        String temp1 = Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredPower();
        String temp2 = Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MaxAllowedPower();
        String temp3 = Util_GlobalHandler.getGlobalHandler()
                .get_Scaling_MeasuredSAR();
        String temp4 = "";
        String temp5 = "";

        if (isTablet) {
            temp4 = Util_GlobalHandler.getGlobalHandler()
                    .get_Scaling_DutyFactor();

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

        // clear answer boxes
        setAnswer("", "");

        if (temp1.equals("") || temp2.equals("") || temp3.equals("")) {
            setAnswer(error_missingField, "");
        } else {
            try {
                double dutyfactor = 100;
                double dutyscaling = 1;

                if (isTablet) {
                    dutyfactor = Double.parseDouble(temp4);
                    dutyscaling = 100 / dutyfactor;
                }

                double powerValue = Double.parseDouble(temp1);
                double targetValue = Double.parseDouble(temp2);
                double sarValue = Double.parseDouble(temp3);

                double finalValue = Util_Calculator.SARScaling(powerValue,
                        targetValue, sarValue, dutyscaling);

                if (finalValue > 1000000) {
                    setAnswer(error_largeResult, "");
                } else {
                    temp5 = (sarValue + " W/kg * 10^((" + targetValue
                            + " dBm - " + powerValue + " dBm) / 10) * (100%" + "/" + dutyfactor + "%)= " + finalValue);
                    setAnswer(temp5, " W/kg");

                    saveAnswer(temp1, temp2, temp3, temp4, temp5);
                }

            } catch (NumberFormatException e) {
                // TODO: generate better error messages
                setAnswer(error_nonNumber, "");
            }
        }
    }

    /**
     * saves previous values in a rotation fashion
     */
    public void saveAnswer(String temp1, String temp2, String temp3, String temp4, String temp5) {
        if (savedAnswer < 2 || savedAnswer > 4) {
            savedAnswer = 2;
        }

        if (savedAnswer == 2) {
            setMeasuredPower2(temp1);
            setMaxAllowedPower2(temp2);
            setMeasuredSAR2(temp3);
            setDutyFactor2(temp4);
            setAnswer2(temp5, " W/kg");
            savedAnswer++;
        } else if (savedAnswer == 3) {
            setMeasuredPower3(temp1);
            setMaxAllowedPower3(temp2);
            setMeasuredSAR3(temp3);
            setDutyFactor3(temp4);
            setAnswer3(temp5, " W/kg");
            savedAnswer++;
        } else if (savedAnswer == 4) {
            setMeasuredPower4(temp1);
            setMaxAllowedPower4(temp2);
            setMeasuredSAR4(temp3);
            setDutyFactor4(temp4);
            setAnswer4(temp5, " W/kg");
            savedAnswer++;
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
