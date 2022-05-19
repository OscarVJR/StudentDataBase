
public class Assignment {

	// assignment number is not included, decided to associate that attribute
	// with its respective index position in Student's hw[] array. ex: assnNumber 1 is at index 0.
	int maxPoints;
	int earnedPoints;
	
	public Assignment(int score) {
		maxPoints = 50;
		earnedPoints = score;
	}

	
	// getters and setters
	public int getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(int maxPoints) {
		this.maxPoints = maxPoints;
	}

	public int getEarnedPoints() {
		return earnedPoints;
	}

	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = earnedPoints;
	}
	
	

}
