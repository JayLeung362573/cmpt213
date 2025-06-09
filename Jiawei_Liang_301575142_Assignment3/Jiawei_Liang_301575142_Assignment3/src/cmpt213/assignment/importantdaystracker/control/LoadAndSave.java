package cmpt213.assignment.importantdaystracker.control;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import cmpt213.assignment.importantdaystracker.model.Anniversary;
import cmpt213.assignment.importantdaystracker.model.Birthday;
import cmpt213.assignment.importantdaystracker.model.ImportantDay;
import cmpt213.assignment.importantdaystracker.model.Occasion;

/**
 * This class is responsible for loading and saving important days data to/from a JSON file.
 * It uses Gson library for JSON serialization/deserialization and handles different types
 * of important days (Birthday, Anniversary, Occasion) appropriately.
 * @author Jiawei Liang
 */
public class LoadAndSave {
    /**
     * Default constructor
     */
    public LoadAndSave(){

    }
    
    /**
     * A list that stores all important days
     */
    public static List<ImportantDay> importantDays = new ArrayList<>();

    /**
     * This method saves the list of important days to a JSON file.
     * It handles different types of important days (Birthday, Anniversary, Occasion)
     * by including their specific attributes in the JSON output.
     * The file is saved as 'list.json' in the current directory.
     */
    public static void saveToFile() {
        Gson myGson = new GsonBuilder().registerTypeAdapter(LocalDate.class, 
            new TypeAdapter<LocalDate>() {
                @Override
                public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
                    jsonWriter.value(localDate.toString());
                }

                @Override
                public LocalDate read(JsonReader jsonReader) throws IOException {
                    return LocalDate.parse(jsonReader.nextString());
                }
            }).setPrettyPrinting().create();

        // File to save the important days list
        File file = new File("./list.json");

        // Create a JsonArray to hold the data
        JsonArray jsonArray = new JsonArray();
        
        for (ImportantDay day : importantDays) {
            JsonObject obj = new JsonObject();
            obj.addProperty("type", day.getType());  // Use the appropriate getter for type
            obj.addProperty("name", day.getName());
            obj.addProperty("description", day.getDescription());
            obj.addProperty("date", day.getDate().toString());
            
            // Add specific properties based on the type of ImportantDay
            if (day instanceof Anniversary) {
                obj.addProperty("location", ((Anniversary) day).getLocation());
            } else if (day instanceof Birthday) {
                obj.addProperty("personName", ((Birthday) day).getPersonName());
            } else if (day instanceof Occasion) {
                obj.addProperty("frequency", ((Occasion) day).getFrequency());
            }

            jsonArray.add(obj);
        }

        // Write the JsonArray to the file
        try (FileWriter writer = new FileWriter(file)) {
            myGson.toJson(jsonArray, writer);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }


    /**
     * This method saves the list of important days to a JSON file
     * Uses Gson to handle serialization of LocalDate and different ImportantDay subclasses
     * The file is saved as "list.json" in the current directory
     * Each important day is stored with its type, name, description, 
     * date and type-specific properties
     */
    public static void loadFromFile(){
        Gson myGson = new GsonBuilder().registerTypeAdapter(LocalDate.class, 
        new TypeAdapter<LocalDate>() {
            @Override
            public void write(JsonWriter jsonWriter,
            LocalDate localDate) throws IOException{
                jsonWriter.value(localDate.toString());
            }

            @Override
            public LocalDate read(JsonReader jsonReader) throws IOException{
                return LocalDate.parse(jsonReader.nextString());
            }
        }).create();

        File file = new File("./list.json");

        // handle missing file
        if(!file.exists()){
            System.out.println("No file found");
            return;
        }

        try (FileReader reader = new FileReader(file)) {
        JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
        importantDays = new ArrayList<>();

        // Iterate through the json array
        for (JsonElement element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            String type = obj.has("type") ? obj.get("type").getAsString() : "default";
            String name = obj.get("name").getAsString();
            String description = obj.has("description") ? obj.get("description").getAsString():"";
            LocalDate date = LocalDate.parse(obj.get("date").getAsString());

            // Create different types of important days
            switch (type) {
                case "Anniversary":
                    String location = obj.get("location").getAsString();
                    importantDays.add(new Anniversary(name, description, date, location));
                    break;
                case "Birthday":
                    String personName = obj.get("personName").getAsString();
                    importantDays.add(new Birthday(name, description, personName, date));
                    break;
                case "Occasion":
                    int frequency = obj.get("frequency").getAsInt();
                    importantDays.add(new Occasion(name, description, date, frequency));
                    break;
                default:
                    importantDays.add(new ImportantDay(name, description, date)); // Default case
                    break;
            }
        }

        System.out.println("Data read successfully!");
    } catch (IOException e) {
        System.out.println("Error reading data: " + e.getMessage());
    }
    }
}
