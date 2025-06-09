package cmpt213.assignment.importantdaystracker.model;
import java.time.LocalDate;
import java.time.Period;

/**
 * This is a subclass of ImportantDay
 * It contains an additional String attribute: personName
 * Also contains a constructor, getPersonName, getAge, getType, and toString method
 */
public class Birthday extends ImportantDay{
    private String personName;

    /**
     * Constructor for Birthday, initialize the name, description, date, and personName
     * @param name the name of the important day
     * @param description the description of the important day
     * @param personName the name of the person
     * @param date the date of the important day
     */
    public Birthday(String name, String description, String personName, LocalDate date){
        super(name, description, date);
        if(personName == null || personName.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.personName = personName;
    }

    /**
     * Getter for personName
     * @return personName
     */
    public String getPersonName(){
        return personName;
    }

    /**
     * Getter for age
     * @return age
     */
    public int getAge(){
        return Period.between(getDate(), LocalDate.now()).getYears() + 1;
    }

    /**
     * Getter for type
     * @return type
     */
    public String getType(){
        return "Birthday";
    }

    /**
     * Override toString() method for Birthday
     * @return appropriate string representation for Birthday object
     */
    @Override
    public String toString(){
        return "Type: Birthday" + "\n"
        + getName() + " on " + getDate() + "\n"
        + getDescription() + "\n"
        + "Note: " + getPersonName() + " is turning " + getAge() + " year(s) old." + "\n";
    }
}
