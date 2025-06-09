package cmpt213.assignment.importantdaystracker;
import cmpt213.assignment.importantdaystracker.control.*;
import cmpt213.assignment.importantdaystracker.view.*;

import javax.swing.SwingUtilities;

/**
 * This class contains the main function that starts the Important Days Tracker application.
 * It loads saved data and initializes the main window of the application.
 * @author Jiawei Liang
 */
public class MainFunction {

    /**
     * Default constructor
     */
    public MainFunction(){}

    /**
     * The main method that starts the Important Days Tracker application.
     * It loads any previously saved important days from file and initializes
     * the main application window in the Event Dispatch Thread.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        LoadAndSave.loadFromFile();

        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            // ImportantDaysTracker.listAllDays();
            mainWindow.showFrame();
        });
    });
    }
}

