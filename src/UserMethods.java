import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMethods {

	public static boolean loginUser(String passwordInput, User user) {
		if(passwordInput.equals(user.getPassword())) {
			System.out.println(user.getName() + " has successfully logged in.");
			return true;
		} else {
			System.out.println("Invalid credentials");
			return false;
		}
	}
	
	public static boolean createGroup(User user, String groupName, String language) {
		int groupId = user.getGroups().size() + 1;
		Group group = new Group(groupId, user, groupName, language);
		user.getGroups().add(group);
		if (user.getGroups().get(groupId - 1).getGroupName().equals(groupName)) {
			return true;
		} else {
			return false;
		}

	}
	
	public static int viewGroups(User user) {
		System.out.println(user.getName() + "'s groups");
		int numGroups = user.getGroups().size();
		if (numGroups == 0) {
			System.out.println(user.getName() + " currently has no groups.");
		} else {
			System.out.printf("%-10s %-20s %-10s\n", "Group ID", "Group Name", "Members");
			for(int i = 0; i < numGroups; i++ ) {
				Group group = user.getGroups().get(i);
				System.out.printf("%-10d %-20s %-10d\n", group.getGroupId(), group.getGroupName(), group.getMembers().size());
			}
			
		}
		return numGroups;
	}
	
	public static boolean checkCourse(User user, Course course) {
		boolean hasCourse = false;
		ArrayList<Course> courses = user.getCourses();
		for(int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourseId() == course.getCourseId()) {
				hasCourse = true;
				break;
			}
		}
		return hasCourse;
	}
	
	public static boolean startCourse(User user, Course course) {
	    if (checkCourse(user, course)) {
	        System.out.println("User " + user.getName() + " is already registered for the course.");
	        return false;
	    } else {
	        user.getCourses().add(course);
	        HashMap<Course, String> progressMap = new HashMap<>();
	        progressMap.put(course, "0"); // Initialize progress to 0
	        user.getProgress().add(progressMap);
	        System.out.println("Course: " + course.getLanguage() + " is successfully registered.");
	        System.out.println("Start your course!");
	        return true;
	    }
	}
	
    public static void viewCourses(User user) {
        System.out.println("User " + user.getName() + "'s courses and progress:");

        // Print table header
        System.out.printf("%-20s | %-10s | %-10s%n", "Course", "Chapters", "Progress");
        System.out.println("-".repeat(45));

        // Iterate over each course and its progress
        for (int i = 0; i < user.getCourses().size(); i++) {
            Course course = user.getCourses().get(i);
            HashMap<Course, String> progressMap = user.getProgress().get(i);
            String progress = progressMap.get(course);

            System.out.printf("%-20s | %-10d | %-10s%n", course.getLanguage(), course.getChapters(), progress);
        }
    }
    
    public static void trackProgress(User user, int courseId) {
        // Find the index of the course in the user's courses list
        int courseIndex = -1;
        for (int i = 0; i < user.getCourses().size(); i++) {
            if (user.getCourses().get(i).getCourseId() == courseId) {
                courseIndex = i;
                break;
            }
        }

        // If the course is not found, print an error message and return
        if (courseIndex == -1) {
            System.out.println("Course not found for the user.");
            return;
        }

        // Ensure the progress list contains an entry for the course
        if (courseIndex >= user.getProgress().size()) {
            System.out.println("Progress data not available for the course.");
            return;
        }

        // Get the progress map for the specified course
        HashMap<Course, String> progressMap = user.getProgress().get(courseIndex);

        // Get the course from the progress map
        Course course = user.getCourses().get(courseIndex);

        // Print the progress of the course
        String progress = progressMap.getOrDefault(course, "Progress not available");
        System.out.println("Progress for Course " + course.getLanguage() + ": " + progress + " chapters");
    }

    public static boolean increaseProgress(User user, Course course) {
        List<HashMap<Course, String>> progress = user.getProgress();
        for (HashMap<Course, String> map : progress) {
            if (map.containsKey(course)) {
                int currentProgress = Integer.parseInt(map.get(course));
                int chapters = course.getChapters();
                if (currentProgress + 1 > chapters) {
                    System.out.println("Course finished");
                    return false;
                } else {
                    currentProgress++;
                    map.put(course, String.valueOf(currentProgress));
                    System.out.println("Now you are on chapter " + currentProgress);
                    return true;
                }
            }
        }
        // If course not found in progress map, return false
        return false;
    }

    
    public static boolean bookSpeaker( User user, Speaker speaker) {
		if(speaker.isBooked()) {
			System.out.println("Speaker is already booked.");
			return false;
		} else {
			int sessionId = user.getSessions().size() + 1;
			Session session = new Session(sessionId, user, speaker);
			user.getSessions().add(session);
			speaker.setBooked(true);
			System.out.println("Speaker " + speaker.getName() + " has been booked.");
			return true;
		}
    	
    }
    
    
    public static boolean cancelBooking(Session session) {
    	boolean cancelled = false;
    	switch(session.getStatus()) {
    		case "booked": 
    			session.setStatus("cancelled");
    			session.getSpeaker().setBooked(false);
    			System.out.println("Session with " + session.getSpeaker().getName() + " has been cancelled successfully.");
    			cancelled = true;
    			break;
    		case "finished":
    			System.out.println("Session has aleady finished.");
    			cancelled = false;
    			break;
    		case "cancelled":
    			System.out.println("Session is already cancelled.");
    			cancelled = false;
    			break;
    	}
		return cancelled;
    }
    
    public static int viewSessions(User user) {
    	if (user.getSessions().size() == 0) {
    		System.out.println("You have not registered for any sessions, " + user.getName());
    		return 0;
    	} else {
    		System.out.printf("%-20s %-20s %-20s %-10s\n", "ID", "Speaker", "Language", "Status");
        	for (int i = 0; i < user.getSessions().size(); i++) {
        		Session session = user.getSessions().get(i);
                System.out.printf("%-20s | %-20s | %-20s | %-10s%n", session.getSessionId(), session.getSpeaker().getName(), session.getSpeaker().getLanguage(), session.getStatus());
        	}
        	return user.getSessions().size();
    	}
    }
    

    

}
