public class UserMethodsFSM {

    public enum State {
        LOGGED_IN,
        GROUP_CREATED,
        GROUP_VIEWED,
        COURSE_STARTED,
        COURSE_VIEWED,
        PROGRESS_TRACKED,
        SESSION_BOOKED,
        BOOKING_CANCELLED,
        SESSION_VIEWED
    }

    private State currentState;

    public UserMethodsFSM() {
        this.currentState = State.LOGGED_IN;
    }

    public boolean createGroup(User user, String groupName, String language) {
        if (currentState == State.LOGGED_IN) {
            
            if(UserMethods.createGroup(user, groupName, language)) {
            	currentState = State.GROUP_CREATED;
            	return true;
            } else {
            	return false;
            }
        }
        return false; // Invalid transition
    }

    public int viewGroups(User user) {
        if (currentState == State.LOGGED_IN || currentState == State.GROUP_CREATED) {
            currentState = State.GROUP_VIEWED;
            return UserMethods.viewGroups(user);
        }
        return -1; // Invalid transition
    }

    public boolean startCourse(User user, Course course) {
        if (currentState == State.LOGGED_IN) {
            currentState = State.COURSE_STARTED;
            return UserMethods.startCourse(user, course);
        }
        return false; // Invalid transition
    }

    public void viewCourses(User user) {
        if (currentState == State.COURSE_STARTED || currentState == State.LOGGED_IN ) {
            currentState = State.COURSE_VIEWED;
            UserMethods.viewCourses(user);
        }
    }

    public void trackProgress(User user, int courseId) {
        if (currentState == State.COURSE_VIEWED) {
            currentState = State.PROGRESS_TRACKED;
            UserMethods.trackProgress(user, courseId);
        }
    }

    public boolean bookSpeaker(User user, Speaker speaker) {
        if (currentState == State.LOGGED_IN) {
            currentState = State.SESSION_BOOKED;
            return UserMethods.bookSpeaker(user, speaker);
        }
        return false; // Invalid transition
    }

    public void cancelBooking(Session session) {
        if (currentState == State.SESSION_BOOKED || currentState == State.SESSION_VIEWED) {
            currentState = State.BOOKING_CANCELLED;
            if (UserMethods.cancelBooking(session)) {
            	currentState = State.BOOKING_CANCELLED;
            }
        }
    }

    public int viewSessions(User user) {
        if (currentState == State.LOGGED_IN || currentState == State.BOOKING_CANCELLED || currentState == State.SESSION_BOOKED) {
            currentState = State.SESSION_VIEWED;
            return UserMethods.viewSessions(user);
        }
        return -1; // Invalid transition
    }

    public void back() {
        switch (currentState) {
            case BOOKING_CANCELLED:
                currentState = State.SESSION_VIEWED;
                break;
            default:
                currentState = State.LOGGED_IN;
                break;
        }
    }

	public UserMethodsFSM.State getCurrentState() {
		return currentState;
	}
}
