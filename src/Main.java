import java.util.ArrayList;
//import java.util.Collections;
import java.util.Scanner;



/** Main Class with main method of the project
 * @author NagaVenkata SaiLatha Tammana
 * @version 1.0
 * @Date 24/02/2021
 */


public class Main {
    // A string variable to hold the data file name.
    public static String fileName = "tasks_database.txt";

    /**
     * main method to run the command line based To Do List application
     * @param args array of String holding command line parameters
     */
    public static void main(String[] args) {
        int choice = -5;
        try {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            InputOutputFile ioFile = new InputOutputFile(taskArrayList);
            //Reading old tasks data from the data file. If it is the first time
            //of running the application , a message will be displayed.
            taskArrayList = ioFile.readTasksFromFile(fileName);
            ReadFromUser readFromUser = new ReadFromUser(taskArrayList);

            // An object to hold all tasks and its details
            TasksList taskList = new TasksList(taskArrayList);
            //To store choice chosen by the user
            Scanner scanner = new Scanner(System.in);



            //To display the welcome message
            Display.welcomeMsg();

            while (choice != 4) {

                Display.mainMenu(taskList.notCompletedCount(), taskList.completedTaskCount());
                choice = readAndValidateChoice();
                boolean  listHasTasks;


                switch(choice){
                    case 1:
                        // View task list option
                        Display.listAllMenuDisplay();
                        taskList.displaySortedTasksList(scanner.nextLine());
                        break;
                    case 2:
                        //Add a task option
                        readFromUser.readNewTasksFromUser();
                        break;
                    case 3:
                        //Edit or update a task
                        listHasTasks = taskList.displayAllTasksWithIndex();
                        if(listHasTasks) {
                            Display.displayEditTaskSelection( );
                            taskList.editTask(scanner.nextLine( ));
                        }
                        break;
                    case 4:
                        //save and quit
                        ioFile.writeTaskObj(fileName, taskArrayList);
                        Display.goodByeMessage();
                        break;
                    default:
                        Display.wrongChoice();
                }
            }

        }catch(Exception e){
            System.out.println("Exception Caught: " + e);

            System.out.println("The application quits with error, some data may lost");
        }
    }
    /** Reading the choice from user using scanner nad validating it as a number
     * @return returning the user's choice
     */
    public static int readAndValidateChoice(){
        Scanner sc = new Scanner(System.in);
        int choice=50;
        do {

            if(choice<=0)
                System.out.println("Please enter a number between 1 to 4!");

            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next();
            }
            choice = sc.nextInt();
        } while (choice <= 0);
        return choice;
    }

}
