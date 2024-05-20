import java.util.ArrayList;
import java.util.HashMap;

public class User {
	
	
	private int userId;
	private String email;
	private String password;
	private String name;
	private ArrayList<Group> groups;
	private ArrayList<Course> courses;
	private ArrayList<HashMap<Course, String>> progress;
	private ArrayList<Session> sessions;
	
	public User(int userId, String email, String password, String name) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.courses = new ArrayList<Course>();
		this.groups = new ArrayList<Group>();
		this.sessions = new ArrayList<Session>();
		this.setProgress(new ArrayList<>());
	}
	
	// Getters and Setters
	public int getId() {
		return userId;
	}
	public void setId(int id) {
		this.userId = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public ArrayList<Group> getGroups() {
		return groups;
	}
	public void setGroups(ArrayList<Group> groups) {
		this.groups = groups;
	}
	public ArrayList<Course> getCourses() {
		return courses;
	}
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	public ArrayList<HashMap<Course, String>> getProgress() {
		return progress;
	}
	public void setProgress(ArrayList<HashMap<Course, String>> progress) {
		this.progress = progress;
	}

	public ArrayList<Session> getSessions() {
		return sessions;
	}

	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}

}
