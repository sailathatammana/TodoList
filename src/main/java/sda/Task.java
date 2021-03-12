package sda;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/** This Class is a model for task object.
 * It contains all the fields and methods to update a task.
 * @author NagaVenkata SaiLatha Tammana
 * @version 1.0
 * @Date 24/02/2021
 */

public class Task implements Serializable {
    // A String that holds the title of a task and it cannot be empty or null.
    private String title;
    // A Date field which holds due date of the task as yyyy-mm-dd format
    private LocalDate dueDate;
    // A boolean value stores the status of the task, if completed true else false.
    private boolean status;
        // A String that holds the name of project, it can be an empty string.
    private String project;

    /**
     * Creating an object of the SDA.com.Task Class
     * @param title A String that holds the title of a task and it cannot be empty or null.
     * @param project A String that holds the name of project, it can be an empty string.
     * @param dueDate A Date field which holds due date of the task as yyyy-mm-dd format
     */
    public Task(String title, String project, LocalDate dueDate) {
        this.setTitle(title);
        this.setDueDate(dueDate);
        this.setProject(project);
        this.status = false;
    }

    /**
     * A set method for Title
     * @param title A String that holds the title of a task and it cannot be empty or null.
     * @throws NullPointerException if Title is empty or Null
     */
    public void setTitle(String title) throws NullPointerException {
        if( title==null || title.trim().equals(""))
            throw new NullPointerException( "Title cannot be empty" );
        this.title = title.trim();
    }

    /**
     * A method to get title string
     * @return  title of the task
     */
    public String getTitle(){
        return this.title;
    }


    /**
     * Set method to write the dueDate
     * @param dueDate Date when the task should be done
     * @throws DateTimeException if given date is past or wrong date format
     */
    public void setDueDate(LocalDate dueDate) throws DateTimeException {
        //Throw DateTimeException if the date is past date
        if (dueDate.compareTo(LocalDate.now( )) < 0)
            throw new DateTimeException("The entered date is past already,not allowed!!");

        //Save dueDate in the yyyy-MM-dd format
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dueDate = LocalDate.parse(dueDate.format(formattedDate));
    }

    /**
     * to get the due date
     * @return  date in local date format
     */
    public LocalDate getDueDate(){
        return this.dueDate;
    }

    /**
     * Sets the project name
     * @param project  String stores Project name
     */
    public void setProject(String project){
        this.project = project.trim();
    }

    /**
     * to get the Project name
     * @return a String with project name
     */
    public String getProject(){
        return this.project;
    }

    /**
     * sets the status of the project
     * @param status  true if completed; false if not completed
     */
    public void setStatus(boolean status){
        this.status = status;
    }


    /**
     * to get the status of the task done or not
     * @return  true or false
     */
    public boolean getStatus(){
        return this.status;
    }

    /**
     * To print the task formatted
     * @return whole task in a String
     */
    @Override
    public String toString(){
        String s = "Title: " + title + "\n" +
                "Due Date : " + dueDate + "\n" +
                "Project: " + project + "\n" +
                "Status: " ;
        String status = getStatus( ) ? "Completed" : "Not Completed";
        return s + status;

    }

    /**
     * Method to update the task as completed
     */
    public void markTaskCompleted() {
        if (!this.getStatus())
            this.setStatus(true);
    }

    /**
     * Method to update the task as not completed
     */
    public void markTaskInComplete() {
        if (this.getStatus())
            this.setStatus(false);
    }
}