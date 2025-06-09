package cmpt213.assignment.importantdaystracker.model;

import java.time.LocalDate;

/**
 * This is a subclass of ImportantDay
 * It contains an additional int attribute: frequency
 * Also contains a constructor, getFrequency, getType, and toString method
 */
public class Occasion extends ImportantDay{
    private int frequency;

    /**
     * Constructor for Occasion, initialize the name, description, date, and frequency
     * @param name the name of the important day    
     * @param description the description of the important day
     * @param date the date of the important day
     * @param frequency the frequency of the important day
     */
    public Occasion(String name, String description, LocalDate date, int frequency){
        super(name, description, date);
        if(frequency <= 0){
            throw new IllegalArgumentException("Frequency must be a positive number.");
        }
        this.frequency = frequency;
    }

    /**
     * Getter for frequency
     * @return frequency
     */
    public int getFrequency(){
        return frequency;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType(){
        return "Occasion";
    }

    /**
     * Override toString() method for Occasion
     * @return appropriate string representation for Occasion object
     */
    @Override
    public String toString(){
        return "Type: Occasion" + "\n"
        + getName() + " on " + getDate() + "\n"
        + getDescription() + "\n"
        + "Frequency: every " + getFrequency() + " years\n";
    }

}
