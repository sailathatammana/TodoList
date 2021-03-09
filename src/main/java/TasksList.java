import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class contains the actual task List as an arraylist
 *
 * @author NagaVenkata SaiLatha Tammana
 * @version 1.0
 * @Date 26/02/2021
 */


public class TasksList {
    // An array list of task objects
    private ArrayList<Task> taskArrayList;
    ReadFromUser readFromUser;

    /**
     * creating an taskList object
     */
    public TasksList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
        readFromUser = new ReadFromUser(taskArrayList);
    }

    /**
     * Method to display all the task from list in the sorted order
     * It either sorts the task by due date or by Project name
     * @param choice choice chosen by the user whether to sort the list by date or project
     */
    public void displaySortedTasksList(String choice) {
        Display.printIterator('-', 75);
        System.out.println("Total Tasks = " + taskArrayList.size( ) +
                "\t\t" + Display.GREEN_TEXT + "Tasks Completed = " + completedTaskCount( ) + "\t\t" +
                Display.RED_TEXT + " Tasks Not Completed = " + notCompletedCount( ) + Display.RESET_TEXT);
        Display.printIterator('-', 75);
        //If User choose to view the task list sorted by Project
        if (choice.equals("2")) {
            String lineFormat = "%-20s %-50s %-13s %-10s";

            if (taskArrayList.size( ) > 0) {
                System.out.println( );
                System.out.printf((lineFormat) + "%n", "Project", "Title", "DueDate", "Completed");
                System.out.printf((lineFormat) + "%n", "-------", "------", "--------", "-------");
            } else {
                System.out.println(Display.RED_TEXT + "task list is empty" + Display.RESET_TEXT);
            }

            List<Task> sortedTaskList = sortByProject();
            //Prints the sorted list using set and get methods of Task class
            for (Task task : sortedTaskList)
                System.out.printf((lineFormat) + "%n", task.getProject( ), task.getTitle( ), task.getDueDate( ), task.getStatus( ) ? "Yes" : "No");
            System.out.println( );
        } else {
            //If User choose to view the task list sorted by Date
            String lineFormat = "%-13s %-25s %-20s %-10s";

            if (taskArrayList.size() > 0) {
                System.out.println( );
                System.out.printf((lineFormat) + "%n", "DueDate", "Title", "Project", "Completed");
                System.out.printf((lineFormat) + "%n", "-------", "-----", "-------", "---------");
            } else {
                System.out.println(Display.RED_TEXT + "task list is empty" + Display.RESET_TEXT);
            }

            List<Task> sortedTaskList = sortByDate();
            //Prints the sorted list using set and get methods of Task class
            for (Task task : sortedTaskList)
                System.out.printf((lineFormat) + "%n", task.getDueDate( ), task.getTitle( ), task.getProject( ), task.getStatus( ) ? "Yes" : "No");
            System.out.println( );
        }
    }

    /**
     * This method will sort the Task list by date
     * @return sorted task list
     */
    public List<Task> sortByDate(){
        List<Task> sortedTaskList = taskArrayList.stream( )
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect((Collectors.toList( )));
        return sortedTaskList;
    }

    /**
     * This method will sort the task list by project
     * @return sorted task list
     */
    public List<Task> sortByProject(){
        List<Task> sortedTaskList = taskArrayList.stream( )
                .sorted(Comparator.comparing(Task::getProject))
                .collect((Collectors.toList( )));
        return sortedTaskList;
    }
    /**
     * Displays all the tasks with index
     * @return a boolean true if list has tasks ; false if list is empty
     */
    public boolean displayAllTasksWithIndex(){
        String lineFormat = "%-5s %-25s %-20s %-13s %-10s";
        if (taskArrayList.size( ) > 0) {
            System.out.println("\n Choose a Task from the below List : " );
            System.out.printf((lineFormat) + "%n", "Num", "Title", "Project", "DueDate", "Completed");
            System.out.printf((lineFormat) + "%n", "---", "-----", "-------", "-------", "---------");
        } else {
            System.out.println(Display.RED_TEXT + "\n task list is empty" + Display.RESET_TEXT);
            return false;
        }
        for (Task task : taskArrayList)
            System.out.printf((lineFormat) + "%n",taskArrayList.indexOf(task)+1, task.getTitle( ), task.getProject( ), task.getDueDate( ), task.getStatus( ) ? "Yes" : "No");
        System.out.println( );
        return true;
    }

    /**
     * shows edit task menu and get the choice from the user to dot he appropriate action
     * @param taskChoice task number selected by the user to update(edit)
     * @throws NullPointerException when user doesn't enter any task number
     */
    public void editTask(String taskChoice) throws NullPointerException{
        try{

            // Checking if the taskChoice given is not null or empty
            if(taskChoice.trim().equals("")){
                throw new NullPointerException("Empty task Number: Returning to main menu");
            }
            int taskIndex = Integer.parseInt(taskChoice) -1;
            if(taskIndex < 0 || taskIndex > taskArrayList.size()){
                throw new ArrayIndexOutOfBoundsException("Task selected is not in the List:returning to main menu");
            }

            Task task = taskArrayList.get(taskIndex);
            System.out.println("\nSelected task is  :"+ taskChoice + "\n" + task );

            Display.editTaskMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            switch(choice) {
                case "1":
                    //If User choose to mark a task as done
                    task.markTaskCompleted();
                    System.out.println(Display.GREEN_TEXT+"Task Number " + taskChoice + " marked as done"+Display.RESET_TEXT );
                    break;
                case "2":
                    //If user wants to update the details of the task
                    readFromUser.readTasksFromUserToUpdate(task);
                    break;
                case "3":
                    //If user wants to remove a task
                    taskArrayList.remove(task);
                    System.out.println(Display.GREEN_TEXT + "\nTask Number "+ taskChoice + " is removed from the List"+Display.RESET_TEXT);
                    break;
                default:
                    System.out.println(Display.RED_TEXT + "Unexpected choice : Returning to main menu " + choice + Display.RESET_TEXT);
            }
        }catch (Exception e) {
            System.out.println(Display.RED_TEXT + e.getMessage() + Display.RESET_TEXT);
        }
    }

    /**
     * to count number of completed tasks
     * @return number completed
     */
    public int completedTaskCount(){
        int count = 0;
        for (Task task : taskArrayList) {
            if (task.getStatus( ))
                count++;
        }
        return count;
    }

    /**
     * to count number of incomplete tasks
     * @return number not completed
     */
    public int notCompletedCount() {
        int count = 0;
        for (Task task : taskArrayList) {
            if (!task.getStatus( ))
                count++;
        }
        return count;
    }

}