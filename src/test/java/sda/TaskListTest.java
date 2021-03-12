package sda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private ArrayList<Task> taskList = new ArrayList<>( );
    TasksList tList;

    String validTitle = "Test Title";
    String validProject = "Test Project";
    LocalDate validDueDate = LocalDate.parse("2022-03-31");

    /**
     * This method will execute before executing any Test.
     * This method will initialize the taskList object with valid test parameters.
     * @throws Exception
     */
    @BeforeEach
    public void setUp() {
        try {
            taskList.add(new Task(validTitle, validProject, validDueDate));
            taskList.add(new Task("Pay Bill", "Grocery", LocalDate.parse("2021-10-30")));
            taskList.add(new Task("Finish IP", "SDA", LocalDate.parse("2021-04-01")));
            tList = new TasksList(taskList);
        } catch (Exception e) {
            System.out.println("Following is the error on testing of SDA.com.TaskList class");
            System.out.println(e.getMessage( ));
        }
    }

    /**
     * Tests the completed count is correct
     */
    @Test
    public void TestCompletedCount() {
        assertEquals(tList.completedTaskCount(),0);
    }

    /**
     * Tests the incomplete count is correct
     */
    @Test
    public void notCompletedCount() {
        assertEquals(tList.notCompletedCount(),3);
    }
}
