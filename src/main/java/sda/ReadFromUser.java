package sda;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 *This class contains the methods to read task details from the user
 *while adding a new task and while editing an existing task
 * @author NagaVenkata SaiLatha Tammana
 * @version 1.0
 * @Date 25/02/2021
 */

public class ReadFromUser {
    //ArrayList to maintain the tasks
    ArrayList<Task> taskArrayList;
    //Passing the ArrayList of task to work on same list everywhere
    public ReadFromUser(ArrayList<Task> taskArrayList) {

        this.taskArrayList = taskArrayList;
    }

    /**
     * Method which reads task details from the user and adds
     * into the Tasks list.
     */
    public void readNewTasksFromUser(){
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n Please Enter task Details");
            System.out.println(" -------------------------");
            System.out.print("\n Enter Title of the task: ");
            String title = scanner.nextLine( );
            System.out.print("\n Enter due date(yyyy-MM-dd): ");
            LocalDate dueDate = LocalDate.parse(scanner.nextLine( ));
            System.out.print("\n Enter the project name: ");
            String project = scanner.nextLine();

            this.taskArrayList.add(new Task(title, project, dueDate));
            System.out.println(Display.GREEN_TEXT + "\n Task added to the list successfully" + Display.RESET_TEXT);

        } catch (Exception e) {
            System.out.println(Display.RED_TEXT + " task not added! Entered data is not valid \n" + e + Display.RESET_TEXT);
        }
    }

    /**
     * Reads the tasks details from user to edit the task
     * @param task chosen by user to edit
     */
    public void readTasksFromUserToUpdate(Task task) {
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Enter task Details to Update:");

            System.out.print("task Title(Press enter if you do not want to change the title): " );
            String title = scanner.nextLine();
            if(!title.trim().equals(""))
                task.setTitle(title);

            System.out.print("Project Name(Press enter if you do not want to change the project): " );
            String project = scanner.nextLine();
            if(!project.trim().equals(""))
                task.setProject(project);

            System.out.print("Due Date[yyyy-MM-dd] (Press enter if you do not want to change the due date): " );
            String dueDate = scanner.nextLine();
            if(!dueDate.trim().equals("")) {
                task.setDueDate(LocalDate.parse(dueDate));
                task.markTaskInComplete();
            }


            System.out.println(Display.GREEN_TEXT+ "Task updated successfully " + Display.RESET_TEXT );

        }catch(Exception e){
            System.out.println(Display.RED_TEXT+ "Task not Updated:"+ e.getMessage() + Display.RESET_TEXT );
        }
    }
}
