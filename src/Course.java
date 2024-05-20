import java.util.ArrayList;

public class Course {
	
	private int courseId;
	private String language;
	private ArrayList<User> members;
	private int chapters;
	
	public Course(int courseId, String language, int chapters) {
		this.courseId = courseId;
		this.language = language;
		this.chapters = chapters;
		this.members = new ArrayList<User>();
	}
	
	public int getCourseId() {
		return courseId;
	}
	public void setId(int id) {
		courseId = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public ArrayList<User> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}
	public int getChapters() {
		return chapters;
	}
	public void setChapters(int chapters) {
		this.chapters = chapters;
	}
	
	
}
