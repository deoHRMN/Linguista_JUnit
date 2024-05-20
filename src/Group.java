import java.util.ArrayList;

public class Group {
	
	private int groupId;
	private User creator;
	private ArrayList<User> members;
	private String language;
	private String groupName;
	
	public Group(int groupId, User user, String groupName, String language) {
		this.groupId = groupId;
		this.creator = user;
		this.members = new ArrayList<User>();
		this.members.add(user);
		this.language = language;
		this.groupName = groupName;
	}
	
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public ArrayList<User> getMembers() {
		return members;
	}
	public void setMembers(ArrayList<User> members) {
		this.members = members;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
}
