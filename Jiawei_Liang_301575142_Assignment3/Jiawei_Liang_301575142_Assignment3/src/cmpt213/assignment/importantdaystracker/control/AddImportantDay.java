package cmpt213.assignment.importantdaystracker.control;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import cmpt213.assignment.importantdaystracker.model.ImportantDay;
import cmpt213.assignment.importantdaystracker.model.Anniversary;
import cmpt213.assignment.importantdaystracker.model.Birthday;
import cmpt213.assignment.importantdaystracker.model.Occasion;

/**
 * This class is responsible for creating new ImportantDay objects based on user input.
 * It validates the input parameters and creates the appropriate type of ImportantDay
 * (Birthday, Anniversary, or Occasion) based on the specified type.
 * @author Jiawei Liang
 */
public class AddImportantDay {
    private ImportantDay importantDay;

    /**
     * Constructor for AddImportantDay class.
     * Creates a new ImportantDay object based on the provided parameters.
     * @param name The name of the important day
     * @param type The type of important day (Birthday, Anniversary, or Occasion)
     * @param date The date of the important day
     * @param description The description of the important day
     * @param specificInput Additional info based on type 
     * (person name for Birthday, location for Anniversary, frequency for Occasion)
     * @throws IllegalArgumentException if any required parameter is missing or invalid
     * @throws NumberFormatException if frequency for Occasion type is not a positive integer
     */
    public AddImportantDay(String name, 
    String type, 
    LocalDate date, 
    String description, 
    String specificInput) {
        if (name.isEmpty() ||
            type.isEmpty() ||
            date == null ||
            specificInput.isEmpty()) {
            throw new IllegalArgumentException("Something missing.\n");
        }

        try {
            switch (type) {
                case "Birthday":
                    importantDay = new Birthday(name, description, specificInput, date);
                    break;
                case "Anniversary":
                    importantDay = new Anniversary(name, description, date, specificInput);
                    break;
                case "Occasion":
                    int frequency = Integer.parseInt(specificInput);
                    if (frequency <= 0) {
                        throw new NumberFormatException("Frequency must be a positive integer.");
                    }
                    importantDay = new Occasion(name, description, date, frequency);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid important day type.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, 
            "Invalid frequency. Please enter a positive integer.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
            importantDay = null;
        }
    }

    /**
     * Getter method for the important day object
     * @return The ImportantDay object that was created, or null if creation failed
     */
    public ImportantDay getImportantDay() {
        return importantDay;
    }
}
