import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ TestExceptionHandling.class, TestUserMethods.class, UserMethodsFSMTest.class })
public class AllTests {

}
