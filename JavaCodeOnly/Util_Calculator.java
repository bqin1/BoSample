package pctest.apps.sarbox;

import java.util.Arrays;

/**
 * This utility class provides reused calculator functions
 *
 * @author  Bo Qin
 * @version 3.4
 * @since   2014-11-07
 */

public class Util_Calculator {

	public Util_Calculator() {

	}

	/**
	 * takes in a dbm value and returns the mW after dutyfactor calculation
	 */
	public static double dbmToWatt(double dbm, double dutyfactor) {
		double inter = dbm / 10.0;
		double duty = dutyfactor / 100.0;
		double finalValue = duty * Math.pow(10, inter);
		return numRound(finalValue, 10000);
	}

	/**
	 * takes in a mw value and returns the dbm
	 */
	public static double MWtoDBM(double mw) {
		double finalValue = 10 * Math.log10(mw);
		return numRound(finalValue, 10000);
	}

	/**
	 * takes in target and a slot and computer frame
	 */
	public static double BursttoFrame(double burstTarget, double slot) {
		double temp = burstTarget - 30.0;
		temp = temp / 10.0;
		temp = Math.pow(10.0, temp);
		temp = temp * 1000.0;
		temp = temp * slot;
		temp = temp / 8.0;
		temp = Math.log10(temp);
		double finalValue = temp * 10.0;
		return numRound(finalValue, 10000);
	}

	public static double ICExclusionTableLookup300mhz(double d) {
		return -0.00003*d*d*d+0.0026*d*d+6.0378*d+40.667;
	}

	public static double ICExclusionTableLookup450mhz(double d) {
		return 0.00006*d*d*d-0.0043*d*d+3.6499*d+33.9;
	}

	public static double ICExclusionTableLookup835mhz(double d) {
		return 0.00003*d*d*d- 0.0026*d*d+ 2.5622*d+ 4.3333;
	}

	public static double ICExclusionTableLookup1900mhz(double d) {
		return 0.0033*d*d*d+ 0.0067*d*d- 0.1033*d+ 6.9667;
	}

	public static double ICExclusionTableLookup2450mhz(double d) {
		return 0.0013*d*d*d+0.0712*d*d-0.7338*d+5.7667;
	}

	public static double ICExclusionTableLookup3500mhz(double d) {
		return 0.0006*d*d*d+ 0.0976*d*d- 0.7646*d+ 3.2667;
	}

	public static double ICExclusionTableLookup5800mhz(double d) {
		return -0.0013*d*d*d+ 0.12*d*d- 0.5667*d+ 1;
	}

	/**
	 * finds the 1g exclusion
	 */
	public static double Exclusion(double mWValue, double frequency,
			double distance) {
		double inter = (mWValue / distance);
		double finalValue = inter * Math.sqrt(frequency);
		return numRound(finalValue, 10000);
	}

	/**
	 * Finds the scaled sar
	 */
	public static double SARScaling(double powerValue, double targetValue,
			double sarValue, double dutyscaling) {

		double roundedsarValue = numRound(sarValue, 1000);

		double inter = numRound((targetValue - powerValue) / 10.0, 1000);
		inter = numRound(inter, 1000);
		double finalValue = numRound(roundedsarValue * Math.pow(10, inter) * dutyscaling, 1000);
		return finalValue;
	}

	/**
	 * Calculates the SPLS Ratio
	 */
	public static double SPLS(double sarSum, double distance) {
		double inter = Math.pow(sarSum, 1.5);
		double finalValue = inter / distance;
		return numRound(finalValue, 10000);
	}

	/**
	 * Rounds a double by the number of digits
	 */
	public static double numRound(double value, int digit) {
		return (double) Math.round(value * digit) / digit;
	}

	/**
	 * given x1, y1 find slope
	 */
	public static double[] findSlope(double[] x1, double[] y1) {

		double[] dx = new double[x1.length - 1];
		double[] dy = new double[x1.length - 1];
		double[] slope = new double[x1.length - 1];

		// Calculate the line equation (i.e. slope and intercept) between each
		// point
		for (int i = 0; i < x1.length - 1; i++) {
			dx[i] = x1[i + 1] - x1[i];
			if (dx[i] == 0) {
				throw new IllegalArgumentException(
						"X must be montotonic. A duplicate "
								+ "x-value was found");
			}
			if (dx[i] < 0) {
				throw new IllegalArgumentException("X must be sorted");
			}
			dy[i] = y1[i + 1] - y1[i];
			slope[i] = dy[i] / dx[i];
		}

		return slope;

	}

	/**
	 * given x1, y1 find intercept
	 */
	public static double[] findIntercept(double[] x1, double[] y1,
			double[] slope1) {

		double[] slope = slope1;
		double[] intercept = new double[x1.length - 1];

		// Calculate the line equation (i.e. slope and intercept) between each
		// point
		for (int i = 0; i < x1.length - 1; i++) {

			intercept[i] = y1[i] - x1[i] * slope[i];
		}

		return intercept;
	}

	/**
	 * given x, y, slope and intercept, interpolate at xi
	 */
	public static double[] interpolate(double[] x, double[] y, double[] xi,
			double[] slope, double[] intercept) {
		double[] yi = new double[xi.length];
		for (int i = 0; i < xi.length; i++) {
			if ((xi[i] > x[x.length - 1]) || (xi[i] < x[0])) {
				yi[i] = Double.NaN;
			} else {
				int loc = Arrays.binarySearch(x, xi[i]);
				if (loc < -1) {
					loc = -loc - 2;
					yi[i] = slope[loc] * xi[i] + intercept[loc];
				} else {
					yi[i] = y[loc];
				}
			}
		}

		return yi;

	}

	/**
	 * x is the 2 x values, y is the 2 y values, and xi is the value you are interpolating
	 */
	public static final double[] interpLinear(double[] x, double[] y,
			double[] xi) throws IllegalArgumentException {

		if (x.length != y.length) {
			throw new IllegalArgumentException(
					"X and Y must be the same length");
		}
		if (x.length == 1) {
			throw new IllegalArgumentException(
					"X must contain more than one value");
		}

		double[] slope = findSlope(x, y);
		double[] intercept = findIntercept(x, y, slope);
		double[] yi = interpolate(x, y, xi, slope, intercept);

		return yi;
	}
}
