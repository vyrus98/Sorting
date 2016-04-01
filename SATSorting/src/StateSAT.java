
public class StateSAT {

	private String state;
	private int mathScore,englishScore;



	/**
	 * @param state
	 * @param mathScore
	 * @param englishScore
	 */
	public StateSAT(String state, int mathScore, int englishScore) {
		this.state = state;
		this.mathScore = mathScore;
		this.englishScore = englishScore;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	public int getEnglishScore() {
		return englishScore;
	}
	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}

	public String toString()
	{
		return state+" - Math:"+mathScore+" English:"+englishScore;
	}


}
