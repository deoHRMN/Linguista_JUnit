
public class Speaker {
	
	private int speakerId;
	private String name;
	private String language;
	private boolean booked;
	
	public Speaker(int speakerId, String name, String language) {
		this.speakerId = speakerId;
		this.name = name;
		this.language = language;
		this.setBooked(false);
	}
	
	public int getSpeakerId() {
		return speakerId;
	}
	public void setSpeakerId(int speakerId) {
		this.speakerId = speakerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public boolean isBooked() {
		return booked;
	}
	public void setBooked(boolean booked) {
		this.booked = booked;
	}
	
	
}
