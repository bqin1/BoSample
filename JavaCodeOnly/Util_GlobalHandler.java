/**
 * Created by Bo Qin on 6/16/2014
 */

package pctest.apps.sarbox;

/*
 * Class Description:   
 *  
 * Singleton Class that holds global variable accessible from all classes.  
 * Acts like a database, used to retain user values between fragment switches 
 *   
 */
public class Util_GlobalHandler {
	private static Util_GlobalHandler globalHandler;

	private boolean isTablet;
	private boolean isLocked = true;

	private String converter_textSelect = "dbm";
	private String converter_dbm = "";
	private String converter_mw = "";
	private String converter_equation = "";
	private String converter_validation4 = "";
	private String converter_validation2 = "";
	private String converter_validation1 = "";
	private String converter_validationpoint2 = "";
	private String converter_validation1point6 = "";
	private String converter_target = "";

	private String scaling_textSelect = "measuredpower";
	private String scaling_measuredPower = "";
	private String scaling_maxAllowedPower = "";
	private String scaling_measuredSAR = "";
	private String scaling_equation = "";
	private String scaling_dutyFactor = "100";
	private String scaling_measuredPower2 = "";
	private String scaling_maxAllowedPower2 = "";
	private String scaling_measuredSAR2 = "";
	private String scaling_equation2 = "";
	private String scaling_dutyFactor2 = "100";
	private String scaling_measuredPower3 = "";
	private String scaling_maxAllowedPower3 = "";
	private String scaling_measuredSAR3 = "";
	private String scaling_equation3 = "";
	private String scaling_dutyFactor3 = "100";
	private String scaling_measuredPower4 = "";
	private String scaling_maxAllowedPower4 = "";
	private String scaling_measuredSAR4 = "";
	private String scaling_equation4 = "";
	private String scaling_dutyFactor4 = "100";
	
	private String slot_textSelect = "burstslot1";
	private String slot_burstslot1 = "0";
	private String slot_burstslot2 = "0";
	private String slot_burstslot3 = "0";
	private String slot_burstslot4 = "0";
	private String slot_frameslot1 = "";
	private String slot_frameslot2 = "";
	private String slot_frameslot3 = "";
	private String slot_frameslot4 = "";
	private String slot_answer = "";
	
	private String spls_textSelect = "wlansar";
	private String spls_wlanSAR = "";
	private String spls_pceSAR = "";
	private String spls_distance = "";
	private String spls_equation = "";

	private String exclusion_textSelect = "maxallowedpower";
	private String exclusion_maxAllowedPower = "";
	private String exclusion_frequency = "";
	private String exclusion_distance = "";
	private String exclusion_equation = "";
	private String exclusion_answerIC = "";
	private String exclusion_dbmtomw = "";
	private String exclusion_dutyFactor = "100";
	private String exclusion_FCCVisual = "neutral";
	private String exclusion_ICVisual = "neutral";
	private double exclusion_exclusionLimit = 3.0;
	private double exclusion_exclusionMultiplier = 1.0;

	private String interpolate_textSelect = "x1";
	private String interpolate_x1 = "";
	private String interpolate_x2 = "";
	private String interpolate_y1 = "";
	private String interpolate_y2 = "";
	private String interpolate_xi = "";
	private String interpolate_equation = "";

	private String error_nonNumber = "Error: Not a number";
	private String error_largeResult = "Error: Result too large";
	private String error_missingField = "Error: All fields required";
	private String error_missingDBM = "Error: dBm field required";
	private String error_distance = "Error: Distance unreal";
	private String error_decimal = "Error: Invalid decimals";
	private String error_unrealNumber = "Error: Unreal Number";
	private String error_negative = "Error: Enter number first";
	private String error_freqOutBound = "Error: Desired frequency out of bounds";
	private String error_freqTooSmall = "Error: Frequency x1 must be smaller than x2";
	private String error_success = "Error: None";

	private String message_login_required = "Log in to use feature";
	private String message_login_fail = "Incorrect Username or Password";
	private String message_login_success = "Login Successful";
	private String message_logout_success = "Logout Successful";

	public boolean isTabletMsg = false;

	private Util_GlobalHandler() {

	}

	public static synchronized Util_GlobalHandler getGlobalHandler() {
		if (globalHandler == null) {
			globalHandler = new Util_GlobalHandler();
		}
		return globalHandler;
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/*
	 * Global Booleans
	 */

	public boolean getIsTablet() {
		return isTablet;
	}

	public void setIsTablet(boolean bool) {
		isTablet = bool;
	}

	public boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(boolean bool) {
		isLocked = bool;
	}

	/*
	 * Login Related Messages
	 */

	public String get_Message_Login_Fail() {
		return message_login_fail;
	}

	public String get_Message_Login_Required() {
		return message_login_required;
	}

	public String get_Message_Login_Success() {
		return message_login_success;
	}

	public String get_Message_Logout_Success() {
		return message_logout_success;
	}

	/*
	 * Error Messages
	 */

	public String get_Error_Success() {
		return error_success;
	}
	
	public String get_Error_FreqTooSmall() {
		return error_freqTooSmall;
	}

	public String get_Error_FreqOutBound() {
		return error_freqOutBound;
	}

	public String get_Error_NonNumber() {
		return error_nonNumber;
	}

	public String get_Error_LargeResult() {
		return error_largeResult;
	}

	public String get_Error_MissingField() {
		return error_missingField;
	}

	public String get_Error_MissingDBM() {
		return error_missingDBM;
	}

	public String get_Error_Distance() {
		return error_distance;
	}

	public String get_Error_Decimal() {
		return error_decimal;
	}

	public String get_Error_UnrealNumber() {
		return error_unrealNumber;
	}

	public String get_Error_Negative() {
		return error_negative;
	}

	/*
	 * Slot converter
	 */

	public String get_Slot_TextSelect() {
		return slot_textSelect;
	}

	public void set_Slot_TextSelect(String input) {
		slot_textSelect = input;
	}
	
	public String get_Slot_BurstSlot1() {
		return slot_burstslot1;
	}

	public void set_Slot_BurstSlot1(String input) {
		slot_burstslot1 = input;
	}
	
	public String get_Slot_BurstSlot2() {
		return slot_burstslot2;
	}

	public void set_Slot_BurstSlot2(String input) {
		slot_burstslot2 = input;
	}
	
	public String get_Slot_BurstSlot3() {
		return slot_burstslot3;
	}

	public void set_Slot_BurstSlot3(String input) {
		slot_burstslot3 = input;
	}
	
	public String get_Slot_BurstSlot4() {
		return slot_burstslot4;
	}

	public void set_Slot_BurstSlot4(String input) {
		slot_burstslot4 = input;
	}
	
	public String get_Slot_FrameSlot1() {
		return slot_frameslot1;
	}

	public void set_Slot_FrameSlot1(String input) {
		slot_frameslot1 = input;
	}
	
	public String get_Slot_FrameSlot2() {
		return slot_frameslot2;
	}

	public void set_Slot_FrameSlot2(String input) {
		slot_frameslot2 = input;
	}
	
	public String get_Slot_FrameSlot3() {
		return slot_frameslot3;
	}

	public void set_Slot_FrameSlot3(String input) {
		slot_frameslot3 = input;
	}
	
	public String get_Slot_FrameSlot4() {
		return slot_frameslot4;
	}

	public void set_Slot_FrameSlot4(String input) {
		slot_frameslot4 = input;
	}
	
	public String get_Slot_Answer() {
		return slot_answer;
	}

	public void set_Slot_Answer(String input) {
		slot_answer = input;
	}
	
	/*
	 * DBM and MW Converter
	 */

	public String get_Converter_Target() {
		return converter_target;
	}

	public void set_Converter_Target(String input) {
		converter_target = input;
	}
	
	public String get_Converter_Validation4() {
		return converter_validation4;
	}

	public void set_Converter_Validation4(String input) {
		converter_validation4 = input;
	}
	
	public String get_Converter_Validation2() {
		return converter_validation2;
	}

	public void set_Converter_Validation2(String input) {
		converter_validation2 = input;
	}
	
	public String get_Converter_Validation1() {
		return converter_validation1;
	}

	public void set_Converter_Validation1(String input) {
		converter_validation1 = input;
	}
	
	public String get_Converter_Validationpoint2() {
		return converter_validationpoint2;
	}

	public void set_Converter_Validationpoint2(String input) {
		converter_validationpoint2 = input;
	}
	
	public String get_Converter_Validation1point6() {
		return converter_validation1point6;
	}

	public void set_Converter_Validation1point6(String input) {
		converter_validation1point6 = input;
	}
	
	public String get_Converter_dbm() {
		return converter_dbm;
	}

	public void set_Converter_dbm(String input) {
		converter_dbm = input;
	}

	public String get_Converter_mw() {
		return converter_mw;
	}

	public void set_Converter_mw(String input) {
		converter_mw = input;
	}

	public String get_Converter_Equation() {
		return converter_equation;
	}

	public void set_Conveter_Equation(String input) {
		converter_equation = input;
	}

	public String get_Converter_TextSelect() {
		return converter_textSelect;
	}

	public void set_Converter_TextSelect(String input) {
		converter_textSelect = input;
	}

	/*
	 * SAR SCALING
	 */

	public String get_Scaling_TextSelect() {
		return scaling_textSelect;
	}

	public void set_Scaling_TextSelect(String input) {
		scaling_textSelect = input;
	}

	public String get_Scaling_MeasuredPower() {
		return scaling_measuredPower;
	}

	public void set_Scaling_MeasuredPower(String input) {
		scaling_measuredPower = input;
	}

	public String get_Scaling_MaxAllowedPower() {
		return scaling_maxAllowedPower;
	}

	public void set_Scaling_MaxAllowedPower(String input) {
		scaling_maxAllowedPower = input;
	}

	public String get_Scaling_MeasuredSAR() {
		return scaling_measuredSAR;
	}

	public void set_Scaling_MeasuredSAR(String input) {
		scaling_measuredSAR = input;
	}

	public String get_Scaling_Equation() {
		return scaling_equation;
	}

	public void set_Scaling_Equation(String input) {
		scaling_equation = input;
	}

	public String get_Scaling_DutyFactor() {
		return scaling_dutyFactor;
	}

	public void set_Scaling_DutyFactor(String input) {
		scaling_dutyFactor = input;
	}

	public String get_Scaling_MeasuredPower2() {
		return scaling_measuredPower2;
	}

	public void set_Scaling_MeasuredPower2(String input) {
		scaling_measuredPower2 = input;
	}

	public String get_Scaling_MaxAllowedPower2() {
		return scaling_maxAllowedPower2;
	}

	public void set_Scaling_MaxAllowedPower2(String input) {
		scaling_maxAllowedPower2 = input;
	}

	public String get_Scaling_MeasuredSAR2() {
		return scaling_measuredSAR2;
	}

	public void set_Scaling_MeasuredSAR2(String input) {
		scaling_measuredSAR2 = input;
	}

	public String get_Scaling_Equation2() {
		return scaling_equation2;
	}

	public void set_Scaling_Equation2(String input) {
		scaling_equation2 = input;
	}

	public String get_Scaling_DutyFactor2() {
		return scaling_dutyFactor2;
	}

	public void set_Scaling_DutyFactor2(String input) {
		scaling_dutyFactor2 = input;
	}

	public String get_Scaling_MeasuredPower3() {
		return scaling_measuredPower3;
	}

	public void set_Scaling_MeasuredPower3(String input) {
		scaling_measuredPower3 = input;
	}

	public String get_Scaling_MaxAllowedPower3() {
		return scaling_maxAllowedPower3;
	}

	public void set_Scaling_MaxAllowedPower3(String input) {
		scaling_maxAllowedPower3 = input;
	}

	public String get_Scaling_MeasuredSAR3() {
		return scaling_measuredSAR3;
	}

	public void set_Scaling_MeasuredSAR3(String input) {
		scaling_measuredSAR3 = input;
	}

	public String get_Scaling_Equation3() {
		return scaling_equation3;
	}

	public void set_Scaling_Equation3(String input) {
		scaling_equation3 = input;
	}

	public String get_Scaling_DutyFactor3() {
		return scaling_dutyFactor3;
	}

	public void set_Scaling_DutyFactor3(String input) {
		scaling_dutyFactor3 = input;
	}

	public String get_Scaling_MeasuredPower4() {
		return scaling_measuredPower4;
	}

	public void set_Scaling_MeasuredPower4(String input) {
		scaling_measuredPower4 = input;
	}

	public String get_Scaling_MaxAllowedPower4() {
		return scaling_maxAllowedPower4;
	}

	public void set_Scaling_MaxAllowedPower4(String input) {
		scaling_maxAllowedPower4 = input;
	}

	public String get_Scaling_MeasuredSAR4() {
		return scaling_measuredSAR4;
	}

	public void set_Scaling_MeasuredSAR4(String input) {
		scaling_measuredSAR4 = input;
	}

	public String get_Scaling_Equation4() {
		return scaling_equation4;
	}

	public void set_Scaling_Equation4(String input) {
		scaling_equation4 = input;
	}

	public String get_Scaling_DutyFactor4() {
		return scaling_dutyFactor4;
	}

	public void set_Scaling_DutyFactor4(String input) {
		scaling_dutyFactor4 = input;
	}
	
	/*
	 * SPLS Ratio
	 */

	public String get_SPLS_TextSelect() {
		return spls_textSelect;
	}

	public void set_SPLS_TextSelect(String input) {
		spls_textSelect = input;
	}

	public String get_SPLS_WlanSAR() {
		return spls_wlanSAR;
	}

	public void set_SPLS_WLANSAR(String input) {
		spls_wlanSAR = input;
	}

	public String get_SPLS_PceSAR() {
		return spls_pceSAR;
	}

	public void set_SPLS_PceSAR(String input) {
		spls_pceSAR = input;
	}

	public String get_SPLS_Distance() {
		return spls_distance;
	}

	public void set_SPLS_Distance(String input) {
		spls_distance = input;
	}

	public String get_SPLS_Equation() {
		return spls_equation;
	}

	public void set_SPLS_Equation(String input) {
		spls_equation = input;
	}

	/*
	 * 1g 10g IC exclusion
	 */

	public String get_Exclusion_FCCVisual() {
		return exclusion_FCCVisual;
	}

	public void set_Exclusion_FCCVisual(String input) {
		exclusion_FCCVisual = input;
	}
	
	public String get_Exclusion_ICVisual() {
		return exclusion_ICVisual;
	}

	public void set_Exclusion_ICVisual(String input) {
		exclusion_ICVisual = input;
	}
	
	public String get_Exclusion_AnswerIC() {
		return exclusion_answerIC;
	}

	public void set_Exclusion_AnswerIC(String input) {
		exclusion_answerIC = input;
	}

	public String get_Exclusion_DBMtoMW() {
		return exclusion_dbmtomw;
	}

	public void set_Exclusion_DBMtoMW(String input) {
		exclusion_dbmtomw = input;
	}

	public String get_Exclusion_TextSelect() {
		return exclusion_textSelect;
	}

	public void set_Exclusion_TextSelect(String input) {
		exclusion_textSelect = input;
	}

	public String get_Exclusion_MaxAllowedPower() {
		return exclusion_maxAllowedPower;
	}

	public void set_Exclusion_MaxAllowedPower(String input) {
		exclusion_maxAllowedPower = input;
	}

	public String get_Exclusion_Frequency() {
		return exclusion_frequency;
	}

	public void set_Exclusion_Frequency(String input) {
		exclusion_frequency = input;
	}

	public String get_Exclusion_Distance() {
		return exclusion_distance;
	}

	public void set_Exclusion_Distance(String input) {
		exclusion_distance = input;
	}

	public String get_Exclusion_Equation() {
		return exclusion_equation;
	}

	public void set_Exclusion_Equation(String input) {
		exclusion_equation = input;
	}

	public double get_Exclusion_ExclusionLimit() {
		return exclusion_exclusionLimit;
	}

	public void set_Exclusion_ExclusionLimit(double input) {
		exclusion_exclusionLimit = input;
	}
	
	public double get_Exclusion_ExclusionMultiplier() {
		return exclusion_exclusionMultiplier;
	}

	public void set_Exclusion_ExclusionMultiplier(double input) {
		exclusion_exclusionMultiplier = input;
	}

	public String get_Exclusion_DutyFactor() {
		return exclusion_dutyFactor;
	}

	public void set_Exclusion_DutyFactor(String input) {
		exclusion_dutyFactor = input;
	}

	/*
	 * Liquid interpolation
	 */

	public String get_Interpolate_TextSelect() {
		return interpolate_textSelect;
	}

	public void set_Interpolate_TextSelect(String input) {
		interpolate_textSelect = input;
	}

	public String get_Interpolate_x1() {
		return interpolate_x1;
	}

	public void set_Interpolate_x1(String input) {
		interpolate_x1 = input;
	}

	public String get_Interpolate_x2() {
		return interpolate_x2;
	}

	public void set_Interpolate_x2(String input) {
		interpolate_x2 = input;
	}

	public String get_Interpolate_y1() {
		return interpolate_y1;
	}

	public void set_Interpolate_y1(String input) {
		interpolate_y1 = input;
	}

	public String get_Interpolate_y2() {
		return interpolate_y2;
	}

	public void set_Interpolate_y2(String input) {
		interpolate_y2 = input;
	}

	public String get_Interpolate_xi() {
		return interpolate_xi;
	}

	public void set_Interpolate_xi(String input) {
		interpolate_xi = input;
	}

	public String get_Interpolate_Equation() {
		return interpolate_equation;
	}

	public void set_Interpolate_Equation(String input) {
		interpolate_equation = input;
	}
}
