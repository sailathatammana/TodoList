import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * This class contains methods to read all the tasks from
 * file and to write the task back to the file
 * @author NagaVenkata SaiLatha Tammana
 * @version 1.0
 * @Date 03/03/2021
 */
public class InputOutputFile {
    //ArrayList to maintain the tasks
    ArrayList<Task> taskArrayList;
    //Passing the ArrayList of task to work on same list everywhere
    public InputOutputFile(ArrayList<Task> taskArrayList){
        this.taskArrayList = taskArrayList;
    }

    /**
     *This method is to read the Tasks from the data file
     * @param fileName a String which holds the name of the file
     */

    public ArrayList<Task> readTasksFromFile(String fileName) {
        try {
            if (!Files.isReadable(Paths.get(fileName))) {
                System.out.println(Display.RED_TEXT + " The data file does not exist, Creating a new data file " + Display.RESET_TEXT);
            }
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream stream = new ObjectInputStream(file);

            // read tasks from the stream
            taskArrayList = (ArrayList<Task>) stream.readObject( );

            stream.close( );
            file.close( );
        } catch (IOException e) {
            System.out.println("File doesn't found " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("problems inside the file " + e);
        }
        return taskArrayList;
    }

    /**
     * This method is to write all tasks added by the user into the data file
     * @param filename in which data is to be written
     */
    public void writeTaskObj(String filename,ArrayList<Task> taskList) {
        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream output = new ObjectOutputStream(file);

            output.writeObject(taskArrayList);
            output.close( );
            file.close( );

            System.out.println("Tasks saved to the file");

        } catch (IOException e) {
            System.out.println("File doesn't found" + e);

        }
    }
}