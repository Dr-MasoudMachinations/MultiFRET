package plugins.MasoudR.multifreticy.DataObjects;

import icy.image.IcyBufferedImage;
import mmcorej.TaggedImage;

/* AcquiredObject holds data for each MDA acquisition
 * - Image data in BufferedImage or TaggedImage format
 * - Time of capture
 * - Position name as found in the MDA PositionList
 * - Prenotation in case the user selects this capture to be a Milestone prior to starting analysis 
 */

public class AcquiredObject {
	public IcyBufferedImage acqImg;
	private TaggedImage tImg;
	public long time;
	public String position;
	public String prenotation;

	public AcquiredObject(IcyBufferedImage a, long t, String p) {
		acqImg = a;
		time = t;
		position = p;
	}

	public AcquiredObject(IcyBufferedImage a, String ano, long t) {
		acqImg = a;
		time = t;
		prenotation = ano;
	}

	public AcquiredObject(IcyBufferedImage a, long t) {
		acqImg = a;
		time = t;
	}

	public AcquiredObject(TaggedImage a, long t, String p) {
		tImg = a;
		time = t;
		position = p;
	}

	public AcquiredObject(TaggedImage a, long t) {
		tImg = a;
		time = t;
	}
}
