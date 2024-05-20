import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UserMethodsFSMTest {

    private UserMethodsFSM userMethodsFSM;
    private User user;

    @BeforeEach
    void setUp() {
        userMethodsFSM = new UserMethodsFSM();
        user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
    }

    @Test
    void testCreateGroup() {
        assertTrue(userMethodsFSM.createGroup(user, "Study Group 1", "English"));
    }

    @Test
    void testViewGroups() {
        assertTrue(userMethodsFSM.createGroup(user, "Study Group 1", "English"));
        assertEquals(1, userMethodsFSM.viewGroups(user));
    }

    @Test
    void testStartCourse() {
        Course course = new Course(1, "English", 10);
        assertTrue(userMethodsFSM.startCourse(user, course));
    }

    @Test
    void testViewCourses() {
        assertTrue(userMethodsFSM.createGroup(user, "Study Group 1", "English"));
        Course course = new Course(1, "English", 10);
        userMethodsFSM.startCourse(user, course);
        userMethodsFSM.viewCourses(user); // Just assert no exceptions occur
    }

    @Test
    void testTrackProgress() {
        assertTrue(userMethodsFSM.createGroup(user, "Study Group 1", "English"));
        Course course = new Course(1, "English", 10);
        userMethodsFSM.startCourse(user, course);
        userMethodsFSM.trackProgress(user, 1); // Just assert no exceptions occur
    }

    @Test
    void testBookSpeaker() {
        Speaker speaker = new Speaker(1, "Akash Dhillon", "English");
        assertTrue(userMethodsFSM.bookSpeaker(user, speaker));
    }

    @Test
    void testCancelBooking() {
        Speaker speaker = new Speaker(1, "Akash Dhillon", "English");
        userMethodsFSM.bookSpeaker(user, speaker);
        Session session = user.getSessions().get(0);
        userMethodsFSM.cancelBooking(session);
        assertEquals("cancelled", session.getStatus());
    }

    @Test
    void testViewSessions() {
        Speaker speaker = new Speaker(1, "Akash Dhillon", "English");
        userMethodsFSM.bookSpeaker(user, speaker);
        assertEquals(1, userMethodsFSM.viewSessions(user));
    }

    @Test
    void testBack() {
        userMethodsFSM.createGroup(user, "Study Group 1", "English");
        userMethodsFSM.back(); // Should transition back to LOGGED_IN state
        assertEquals(UserMethodsFSM.State.LOGGED_IN, userMethodsFSM.getCurrentState());
    }
}
