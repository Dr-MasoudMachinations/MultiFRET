package plugins.MasoudR.multifreticy.DataObjects;

/* Holds Milestones generated through the Milestone button
 * - name holds an identifier entered by the user
 * - frame holds the frame number on which this milestone was set
 */

public class Milestone {
	private String name;
	private long frame;

	public Milestone(String n, long f) {
		name = n;
		frame = f;
	}

	public String getName() {
		return name;
	}

	public long getFrame() {
		return frame;
	}

}
