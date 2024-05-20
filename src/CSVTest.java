import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CSVTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/languageGroupTestData.csv") 
    public void testCreateGroupWithCsvFileSource(String groupName, String language) {
        // Create a user object
        User user = new User(1, "harmanpaldeo@gmail.com", "Harman@1999", "Harman");
        // Call the createGroup method to create a new group
        boolean result = UserMethods.createGroup(user, groupName, language);

        // Assert that the group creation is successful
        assertTrue(result, "Group creation should return true");
   
    }
}
