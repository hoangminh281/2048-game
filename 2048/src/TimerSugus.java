
import java.io.Serializable;

import javax.swing.JLabel;

public class TimerSugus implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 403975866792114990L;
	private int minute;
	private int second;

	public TimerSugus() {
		minute = 0;
		second = 0;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public JLabel timeLabel() {
		JLabel label = new JLabel();
		return label;
	}

	public String toTime() {
		return String.format("%02d", minute) + ":" + String.format("%02d", second);
	}
}
