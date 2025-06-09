package cmpt213.assignment.importantdaystracker.model;

import java.time.LocalDate;

/**
 * This is a subclass of ImportantDay
 * It contains an additional String attribute: location
 * Also contains a constructor, getLocation, getType, and toString method
 */
public class Anniversary extends ImportantDay{
    private String location;

    /**
     * Constructor for Anniversary, initialize the name, description, date, and location
     * @param name the name of the important day
     * @param description the description of the important day
     * @param date the date of the important day
     * @param location the location of the important day
     */
    public Anniversary(String name, String description, LocalDate date, String location){
        super(name, description, date);
        if(location == null || location.trim().isEmpty()){
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        this.location = location;
    }

    /**
     * Getter for location
     * @return location
     */
    public String getLocation(){
        return location;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType(){
        return "Anniversary";
    }

    /**
     * Override toString() method for Anniversary
     * @return appropriate string representation for Anniversary object
     */
    @Override
    public String toString(){
        return "Type: Anniversary" + "\n"
        + getName() + " on " + getDate() + "\n"
        + getDescription() + "\n"
        + "Located at: " + getLocation();
    }
}
