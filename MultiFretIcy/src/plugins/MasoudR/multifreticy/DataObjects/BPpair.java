package plugins.MasoudR.multifreticy.DataObjects;

import java.util.ArrayList;

import icy.sequence.Sequence;

/* Bundle-Position pair holds a sequence list tied to an MDA position.
 * Sequence list contains every sequence for a position.
 */

public class BPpair {
	public ArrayList<Sequence> Bundle;
	public String Pos;

	public BPpair(ArrayList<Sequence> b, String p) {
		Bundle = b;
		Pos = p;
	}
}
