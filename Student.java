
public class Student {

	// has a relationship with multiple Assignments
	private Assignment[] hw;

	// attributes
	private String firstName;
	private String lastName;
	private String studentId;
	
	
	// constructor parameters specify every attribute + the 3 assignment grades
	public Student(String fName,String lName, String studId, int assn1Grade, int assn2Grade, int assn3Grade) {
		
		firstName = fName;
		lastName = lName;
		
		hw = new Assignment[3];
		hw[0] = new Assignment(assn1Grade);
		hw[1] = new Assignment(assn1Grade);
		hw[3] = new Assignment(assn1Grade);
	}
	
	
	// getters and setters for the private values
	public Assignment[] getHw() {
		return hw;
	}

	public void setHw(Assignment[] hw) {
		this.hw = hw;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	

}