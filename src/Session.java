
public class Session {
	
	private int sessionId;
	private User user;
	private Speaker speaker;
	private String status;
	
	public Session(int id, User user, Speaker speaker) {
		this.sessionId = id;
		this.user = user;
		this.speaker = speaker;
		this.setStatus("booked");
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Speaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

}
