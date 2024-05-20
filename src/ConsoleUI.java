import java.util.HashMap;
import java.util.Scanner;

// Password is 1234567

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);
    private static User user = null; // Assuming you have some User object to start with
    private static Course[] courses = {new Course(1, "Spanish", 125), new Course(2, "Japanese", 137), new Course(3, "French", 95)};
    private static Speaker[] speakers = {new Speaker(1, "Akash", "Spanish"), new Speaker(2, "Harman", "Japanese"), new Speaker(3, "Junyi", "French")};
    public static void main(String[] args) {
        
 

    	
        // Loop for the console interface
        while (true) {
        	System.out.println("-------------------------------------------");
            System.out.println("1. Login\n2. Create Group\n3. View Groups\n4. Start Course\n5. View Courses\n6. Track Progress\n7. Book Speaker\n8. Cancel Booking\n9. View Sessions\n10. Increase Progress\n11. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    createGroup();
                    break;
                case 3:
                    viewGroups();
                    break;
                case 4:
                    startCourse();
                    break;
                case 5:
                    viewCourses();
                    break;
                case 6:
                    trackProgress();
                    break;
                case 7:
                    bookSpeaker();
                    break;
                case 8:
                    cancelBooking();
                    break;
                case 9:
                    viewSessions();
                    break;
                case 10:
                    increaseProgress();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }
        }
    }

    
    private static int getValidInput(int min, int max) {
        int choice;
        while (true) {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (choice >= min && choice <= max) {
                break; // Valid input, break out of the loop
            } else {
                System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
            }
        }
        return choice;
    }
    
	private static void login() {
        if (user != null) {
            System.out.println("Already logged in.");
            return;
        }
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        user = new User(1, username + "@gmail.com", "1234567", username);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (UserMethods.loginUser(password, user)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed. Invalid credentials.");
            user = null;
        }
    }

    private static void createGroup() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter group name: ");
        String groupName = scanner.nextLine();
        System.out.print("Enter language: ");
        String language = scanner.nextLine();
        if (UserMethods.createGroup(user, groupName, language)) {
            System.out.println("Group created successfully.");
        } else {
            System.out.println("Failed to create group.");
        }
    }

    private static void viewGroups() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        UserMethods.viewGroups(user);
    }

    private static void startCourse() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        
    
        
        System.out.println("We have the following options: ");
        for (int i = 0; i < courses.length; i++) {
        	System.out.println(courses[i].getCourseId() + ". " + courses[i].getLanguage());
        }
        System.out.print("Enter the number for the language you wanna start: ");
        int courseNumber = getValidInput(1, courses.length);
        
        if (UserMethods.startCourse(user, courses[courseNumber - 1])) {
            System.out.println("Course started successfully.");
        } else {
            System.out.println("Failed to start course.");
        }
    }

    private static void viewCourses() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        UserMethods.viewCourses(user);
    }

    private static void trackProgress() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.print("Enter course ID: ");
        int courseId = getValidInput(1, courses.length);
        UserMethods.trackProgress(user, courseId);
    }

    private static void bookSpeaker() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        System.out.println("We have the following options: ");
        for (int i = 0; i < speakers.length; i++) {
        	System.out.println(speakers[i].getSpeakerId() + ". " + speakers[i].getName());
        }
        System.out.print("Enter the number for the language you wanna start: ");
        int speakerNum = getValidInput(1, speakers.length);
        if (UserMethods.bookSpeaker(user, speakers[(speakerNum) - 1])) {
            System.out.println("Speaker booked successfully.");
        } else {
            System.out.println("Failed to book speaker.");
        }
    }
    
   
    private static void cancelBooking() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        // Assuming you have a Session object to cancel
        UserMethods.viewSessions(user);
        System.out.print("Enter the Speaker Id you would like to cancel: ");
        int speakerNum = getValidInput(1, user.getSessions().size());
        
        UserMethods.cancelBooking(user.getSessions().get(speakerNum - 1));
    }

    private static void viewSessions() {
        if (user == null) {
            System.out.println("Please login first.");
            return;
        }
        UserMethods.viewSessions(user);
    }
    
    private static void increaseProgress() {
        UserMethods.viewCourses(user);
        if (user.getCourses().size() != 0) {
            for (int i = 0; i < user.getCourses().size(); i++) {
                System.out.println(user.getCourses().get(i).getCourseId() + ". " + user.getCourses().get(i).getLanguage() + ". Progress ID: " + String.valueOf(i + 1));
            }
            System.out.print("Enter the Progress number for the language you want to change progress of: ");
            int progNumber = getValidInput(1, user.getProgress().size());    

            if (progNumber >= 1 && progNumber <= user.getCourses().size()) {
                System.out.print("Enter the number of chapters completed: ");
                int chaptersCompleted = getValidInput(Integer.parseInt(user.getProgress().get(progNumber - 1).get(user.getCourses().get(progNumber - 1))), user.getCourses().get(progNumber - 1).getChapters());

                Course selectedCourse = user.getCourses().get(progNumber - 1);
                HashMap<Course, String> progressMap = user.getProgress().get(progNumber - 1);
                
                if (chaptersCompleted <= selectedCourse.getChapters()) {
                    progressMap.put(selectedCourse, String.valueOf(chaptersCompleted));
                    System.out.println("Progress saved.");
                } else {
                    System.out.println("Your course does not have that many chapters.");
                }
            } else {
                System.out.println("Invalid Progress number.");
            }
        } else {
            System.out.println("You have no courses started.");
        }
    }

}
