import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestUserMethods {

	@ParameterizedTest
	@CsvSource({
	    "Harman@1999, true",
	    "WrongPassword, false"
	})
	public void testLoginUser(String passwordInput, boolean expectedResult) {
	    // Create a user object
	    User harman = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
	    // Call the loginUser method with the given password input
	    boolean result = UserMethods.loginUser(passwordInput, harman);
	    // Verify if the result matches the expected result
	    assertEquals(expectedResult, result);
	}

	@Test
	public void testCreateGroup() {
	    // Create a user object
	    User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
	    // Call the createGroup method to create a new group
	    boolean result = UserMethods.createGroup(user, "Study Group 1", "English");
	    boolean result2 = UserMethods.createGroup(user, "Study Group 2", "French");

	    // Assert various conditions related to group creation
	    assertAll("Group Creation",
	            () -> assertTrue(result, "Group creation should return true"),
	            () -> assertEquals("Study Group 1", user.getGroups().get(0).getGroupName(), "Group name should match"),
	            () -> assertEquals("English", user.getGroups().get(0).getLanguage(), "Language should match"),
	            () -> assertTrue(result2, "Group creation should return true"),
	            () -> assertTrue(user.getGroups().size() == 2, "User should have two group now"),
	            () -> assertEquals("Study Group 2", user.getGroups().get(1).getGroupName(), "Group name should match"),
	            () -> assertEquals("French", user.getGroups().get(1).getLanguage(), "Language should match")
	        );
	}

	@Test
	public void testViewGroups() {
	    // Create a user object
	    User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");

	    // Create groups and add members
	    Group group1 = new Group(1, user, "Group 1", "English");
	    Group group2 = new Group(2, user, "Group 2", "French");

	    group1.getMembers().add(new User(2, "member1@example.com", "password1", "Member 1"));
	    group1.getMembers().add(new User(3, "member2@example.com", "password2", "Member 2"));
	    group2.getMembers().add(new User(4, "member3@example.com", "password3", "Member 3"));

	    user.getGroups().add(group1);
	    user.getGroups().add(group2);

	    // Call the viewGroups method
	    int numGroups = UserMethods.viewGroups(user);

	    // Verify the number of groups
	    assertEquals(2, numGroups, "Number of groups should match");
	}

    
    
    // Parameterized test for checkCourse method
    @ParameterizedTest
    @CsvSource({
        "1, true",
        "2, true",
        "3, false"
    })
    public void testCheckCourse(int courseId, boolean expectedResult) {
        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
        Course course1 = new Course(1, "English", 10); // Adding chapters parameter
        Course course2 = new Course(2, "French", 15);  // Adding chapters parameter
        user.getCourses().add(course1);
        user.getCourses().add(course2);

        boolean result = UserMethods.checkCourse(user, new Course(courseId, "Spanish", 12)); // Passing 0 for chapters
        assertTrue(result == expectedResult);
    }

    // Parameterized test for startCourse method
    @ParameterizedTest
    @CsvSource({
        "1, 'English', 12, false",
        "2, 'French', 8, false",
        "3, 'Italian', 15, true"
    })
    public void testStartCourse(int courseId, String language, int chapters, boolean expectedResult) {
        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
        Course course1 = new Course(1, "English", 10);
        Course course2 = new Course(2, "French", 15);
        user.getCourses().add(course1);
        user.getCourses().add(course2);

        boolean result = UserMethods.startCourse(user, new Course(courseId, language, chapters));
        assertAll("startCourse",
            () -> assertEquals(expectedResult, result, "Result should match expected"),
            () -> assertTrue(user.getCourses().stream().anyMatch(c -> c.getCourseId() == courseId), "Course should be added if not already present"),
            () -> assertTrue(user.getCourses().stream().anyMatch(c -> c.getLanguage().equals(language)), "User should have the new course in the list")
        );
    }
    
    @Test
    void testTrackProgress() {
        // Create a sample user with courses and progress
        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
        Course course1 = new Course(1, "English", 10);
        Course course2 = new Course(2, "French", 15);
        user.getCourses().add(course1);
        user.getCourses().add(course2);

        HashMap<Course, String> progressMap1 = new HashMap<>();
        progressMap1.put(course1, "3/10"); // Progress for English course
        progressMap1.put(course2, "7/15"); // Progress for French course

        user.getProgress().add(progressMap1);

        // Test case 1: Course progress available
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        UserMethods.trackProgress(user, 1); // Track progress for English course

        String output = outputStream.toString().trim();
        assertEquals("Progress for Course English: 3/10 chapters", output);

        // Test case 2: Course progress not available
        outputStream.reset(); // Reset the output stream
        UserMethods.trackProgress(user, 2); // Track progress for French course

        output = outputStream.toString().trim();
        assertEquals("Progress data not available for the course.", output);
    }
    
    @Test
    public void testBookSpeaker() {
        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
        Speaker speaker = new Speaker(1, "Akash Dhillon", "English");
        assertAll("Booking Speaker",
            () -> assertTrue(UserMethods.bookSpeaker(user, speaker)),
            () -> assertTrue(speaker.isBooked()),
            () -> assertEquals(1, user.getSessions().size())
        );
    }

    @Test
    public void testCancelBooking() {
        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
        Speaker speaker = new Speaker(1, "Junyi", "English");
        Session session = new Session(1, user, speaker);
        session.setStatus("booked");
        user.getSessions().add(session);
        assertAll("Cancelling Booking",
            () -> UserMethods.cancelBooking(session),
            () -> assertEquals("cancelled", session.getStatus()),
            () -> assertFalse(speaker.isBooked())
        );
    }

    @Test
    public void testViewSessions() {
        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
        Speaker speaker1 = new Speaker(1, "Akash Dhillon", "English");
        Speaker speaker2 = new Speaker(2, "Junyi", "English");
        speaker2.setBooked(true);
        UserMethods.bookSpeaker(user, speaker1); // speaker 1 can be booked since not booked before.
        UserMethods.bookSpeaker(user, speaker2); // speaker 2 can not be booked since already booked.

        assertEquals(1, UserMethods.viewSessions(user));
    }


}
