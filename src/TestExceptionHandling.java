import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TestExceptionHandling {
	
	   @Test
	    public void testBookSpeakerWithExceptionHandling() {
	        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
	        Speaker speaker = new Speaker(1, "Akash Dhillon", "English");
	        assertAll("Booking Speaker",
	            () -> {
	                try {
	                    assertTrue(UserMethods.bookSpeaker(user, speaker));
	                } catch (IllegalArgumentException e) {
	                    fail("Unexpected IllegalArgumentException: " + e.getMessage());
	                } catch (Exception e) {
	                    fail("Unexpected Exception: " + e.getMessage());
	                }
	            },
	            () -> assertTrue(speaker.isBooked()),
	            () -> assertEquals(1, user.getSessions().size())
	        );
	    }
	    	
	    
	    @Test
	    public void testViewSessionsWithExceptionHandling() {
	        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
	        Speaker speaker1 = new Speaker(1, "Akash Dhillon", "English");
	        Speaker speaker2 = new Speaker(2, "Junyi", "English");
	        speaker2.setBooked(true);
	        UserMethods.bookSpeaker(user, speaker1); // speaker 1 can be booked since not booked before.
	        UserMethods.bookSpeaker(user, speaker2); // speaker 2 can not be booked since already booked.

	        try {
	            assertEquals(1, UserMethods.viewSessions(user));
	        } catch (IllegalArgumentException e) {
	            fail("Unexpected IllegalArgumentException: " + e.getMessage());
	        } catch (Exception e) {
	            fail("Unexpected Exception: " + e.getMessage());
	        }
	    }
	    
	    
	    @Test
	    public void testCancelBookingWithExceptionHandling() {
	        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
	        Speaker speaker = new Speaker(1, "Junyi", "English");
	        Session session = new Session(1, user, speaker);
	        session.setStatus("booked");
	        user.getSessions().add(session);
	        assertAll("Cancelling Booking",
	            () -> {
	                try {
	                    UserMethods.cancelBooking(session);
	                } catch (IllegalArgumentException e) {
	                    fail("Unexpected IllegalArgumentException: " + e.getMessage());
	                } catch (Exception e) {
	                    fail("Unexpected Exception: " + e.getMessage());
	                }
	            },
	            () -> assertEquals("cancelled", session.getStatus()),
	            () -> assertFalse(speaker.isBooked())
	        );
	    }

	
}
