package cmpt213.assignment.importantdaystracker.model;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * This class is a superclass for Anniversary, Birthday, and Occasion
 * It contains the common attributes: name, description, and date,
 * and the common methods: getName, getDescription, getDate, getType, toString
 * It also contains a static method: sortDaysByDates, which sorts the list of ImportantDay by date
 * @author Jiawei Liang
 */
public class ImportantDay {
    private String name;
    private String description;
    private LocalDate date;

    /**
     * Constructor for ImportantDay
     * @param aName the name of the important day   
     * @param aDescription the description of the important day
     * @param aDate the date of the important day
     */
    public ImportantDay(String aName, 
    String aDescription, LocalDate aDate){
        if(aName != null && !aName.trim().isEmpty()){
            name = aName;
            description = aDescription;
            date = aDate;
        }else{
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    /**
     * It sorts the array by date, from earliest to lastest
     * @param importantDays the list of stored important days
     */
    public static void sortDaysByDates(List<ImportantDay> importantDays){
        Collections.sort(importantDays, Comparator.comparing(ImportantDay::getDate));
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Getter for description
     * @return description
     */
    public String getDescription(){
        return description;
    }

    /**
     * Getter for date
     * @return date
     */
    public LocalDate getDate(){
        return date;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType(){
        return "default";
    }

    /**
     * toString method for ImportantDay
     * @return appropriate string representation of ImportantDay
     */
    public String toString(){
        return "Type: " + getType() + "\n"
        + getName() + " on " + getDate() + "\n"
        + getDescription() + "\n";
    }
}





