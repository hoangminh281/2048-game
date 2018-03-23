
public class Score {

	private int score;
	private Record recordOb;
	private TimerSugus time;

	public Score() {
		score = 0;
		recordOb = new Record();
		time = new TimerSugus();
	}

	public TimerSugus getTime() {
		return time;
	}

	public void setTime(TimerSugus time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void updateScore(Sugus[][] sugus) {
		score = 0;
		for (Sugus[] life : sugus) {
			for (Sugus me : life) {
				if (me.getNum() != 0) {
					score += Math.pow(2, me.getNum());
				}
			}
		}
	}

	public void updateRecord(Score score) {
		if (score.getScore() > recordOb.getRecord()) {
			recordOb.setRecord(score.getScore(), time);
		}
	}

	public Record getRecordOb() {
		return recordOb;
	}
}
