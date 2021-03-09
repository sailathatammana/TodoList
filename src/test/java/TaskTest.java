import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Task class
 *
 * Implemented unit testing for all the methods of Task class
 * @author NagaVenkata SaiLatha Tammana
 * @version 1.0
 * @since 2021-03-08
 */


public class TaskTest {
    Task task;

    String validTitle="Test Title";
    String validProject="Test Project";
    LocalDate validDueDate=LocalDate.parse("2022-03-31");
    String validStatus="Incomplete";

    /**
     * This method will execute before executing any Test.
     * This method will initialize the task object with valid test parameters.
     * @throws Exception
     */

    @BeforeEach
    public void setUp() throws Exception{
        try{
            task = new Task(validTitle, validProject, validDueDate);
        }catch(Exception e){
            System.out.println("Following is the error on testing of SDA.com.Task class" );
            System.out.println(e.getMessage());
        }
    }

    /**
     * Assert the task.getTitle() return the correct title
     */
    @Test
    public void testValidTitle() {
        task = new Task(validTitle, validProject, validDueDate);
        assertEquals(validTitle,task.getTitle());
    }

    /**
     * Checks if the Title string is not null if null throws NullPointerException
     */
    @Test
    public void testEmptySetTitle() {
        boolean success = false;

        try{
            task.setTitle("");
            fail("NullPointerException should have been thrown");
        }catch(NullPointerException e){
            success = true;
        }
        assertEquals(true,success);
    }

    /**
     * Test whether the trimming of empty title String returns Null PointerException
     *
     */

    @Test
    public void testTrimEmptySetTitle() {
        boolean success = false;

        try {
            task.setTitle("           ");
            fail("NullPointerException should have been thrown");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }

    /**
     * Testing if setTitle() throwsNullPointerException when trying to to set null to title.
     */
    @Test
    public void testNullSetTitle() {
        boolean success=false;

        try {
            task.setTitle(null);
            fail("NullPointerException should have been thrown");
        } catch (NullPointerException e) {
            success = true;
        }

        assertEquals(true,success);
    }

    /**
     * Assert the task.getProject() return the correct project
     */
    @Test
    public void testValidProject() {
        assertEquals(validProject,task.getProject());
    }

    /**
     * Testing setProject() method fro empty String "".
     */
    @Test
    public void testEmptyProject() {
        task.setProject("");
        assertEquals("",task.getProject());
    }

    /**
     * Assert the task.getDueDate() return the correct Date
     */
    @Test
    public void testValidDueDate() {
        assertEquals(validDueDate,task.getDueDate());
    }

    /**
     * Testing the validation of  date field format
     */
    @Test
    public void testIncorrectFormatDueDate() {
        boolean success=false;
        try {
            task.setDueDate(LocalDate.parse("2020-31-12"));
            fail("DateTimeParseException should have been thrown");
        } catch (DateTimeParseException e) {
            success = true;
        }
        assertEquals(true,success);
    }

    /**
     * Test the setDate method for past date entry. it should throw exception when it gets past date.
     *
     */
    @Test
    public void testPastDueDate() {
        boolean success=false;

        try {
            task.setDueDate(LocalDate.parse("2018-07-08"));
            fail("DateTimeParseException should have been thrown");
        } catch (DateTimeException e) {
            success = true;
        }

        assertEquals(true,success);
    }

}
