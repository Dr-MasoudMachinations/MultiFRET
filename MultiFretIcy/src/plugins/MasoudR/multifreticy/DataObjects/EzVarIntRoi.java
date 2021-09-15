package plugins.MasoudR.multifreticy.DataObjects;

import icy.roi.ROI2D;
import plugins.adufour.ezplug.EzVarBoolean;
import plugins.adufour.ezplug.EzVarInteger;
import plugins.adufour.ezplug.EzVarText;

/* An extension of the EZplug integer, for use with EZ classes.
 * Holds a ROI2D region of interest with data describing its purpose:
 * - EVINum tells which channel to use as numerator for this ROI.
 * - EVIDiv tells which channel to use as Divisor for this ROI.
 * - position tells which MDA position this ROI belongs to.
 */

public class EzVarIntRoi extends EzVarInteger {
	private ROI2D EVIRoi;
	private EzVarBoolean EVIBool;
	private EzVarText EVINum = new EzVarText("Numerator", 0);
	private EzVarText EVIDiv = new EzVarText("Divisor", 0);
	private String position;

	public EzVarIntRoi(String varName, int value, int min, int max, int step, ROI2D EVIR, EzVarBoolean EVIB,
			EzVarText EVIN, EzVarText EVID, String pos) {
		super(varName, value, min, max, step);
		EVIRoi = EVIR;
		EVIBool = EVIB;
		EVINum = EVIN;
		EVIDiv = EVID;
		position = pos;
	}
}