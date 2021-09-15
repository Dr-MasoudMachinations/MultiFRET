package plugins.MasoudR.multifreticy.DataObjects;

import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import icy.roi.ROI2D;

/* Holds ROI and describing data */

public class VarIntRoi {
	public ROI2D Roi2D;
	private Set<JCheckBox> EVIBool;
	private JComboBox<?> EVINum;
	private JComboBox<?> EVIDiv;
	public String position;

	/**
	 * 
	 * @param EVIR       Holds the ROI2D
	 * @param calcChecks Holds the boolean indicating background
	 * @param EVIN       Holds the Numerator name, tells which channel to use as numerator for this ROI.
	 * @param EVID       Holds the Divisor name, tells which channel to use as Divisor for this ROI.
	 * @param pos        Holds the Position name, tells which MDA position this ROI belongs to.
	 */
	public VarIntRoi(ROI2D EVIR, Set<JCheckBox> calcChecks, JComboBox<?> EVIN, JComboBox<?> EVID, String pos) {
		Roi2D = EVIR;
		EVIBool = calcChecks;
		EVINum = EVIN;
		EVIDiv = EVID;
		position = pos;
	}

	public Boolean getBgBool() {
		for (JCheckBox j : EVIBool) {
			if (j.getName().equals("bg541a364")) {
				return j.isSelected();
			}
		}
		System.out.println("Couldn't find background during evibool get");
		return false;
	}

	public Boolean getCalcBool() {
		for (JCheckBox j : EVIBool) {
			if (!j.getName().equals("Background") && j.isSelected()) {
				return true;
			}
		}
		System.out.println("Couldn't find custom-calc during evibool get");
		return false;
	}

	public Set<JCheckBox> getCheckBoxList() {
		return EVIBool;
	}

	public String getNumerator() {
		return (String) EVINum.getSelectedItem();
	}

	public String getDivisor() {
		return (String) EVIDiv.getSelectedItem();
	}
}