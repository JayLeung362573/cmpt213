package cmpt213.assignment.importantdaystracker.view;

import javax.swing.*;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import cmpt213.assignment.importantdaystracker.model.ImportantDay;
import cmpt213.assignment.importantdaystracker.control.AddImportantDay;
import cmpt213.assignment.importantdaystracker.control.LoadAndSave;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * This class represents a frame for adding new important days.
 * It provides a GUI interface with input fields for the name, description, date,
 * and type-specific information (location for Anniversary, person name for Birthday,
 * or frequency for Occasion). The frame includes a combo box for selecting the type
 * of important day and a date picker for selecting the date.
 * @author Jiawei Liang
 */
public class AddImportantDayFrame {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField specificField;
    private JComboBox<String> typeComboBox;
    private JLabel specificLabel;
    private DatePicker datePicker;

    /**
     * Constructor for AddImportantDayFrame class.
     * Creates a new frame for adding important days with a title and basic layout.
     */
    public AddImportantDayFrame() {
        frame = new JFrame("Add an Important Day");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400, 300);

        mainPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);

        mainPanel.setLayout(boxLayout);
        frame.add(mainPanel);

    }

    /**
     * Shows the frame by setting its visibility to true.
     * Makes the frame visible to the user after all components have been added.
     */
    public void showFrame() {
        frame.setVisible(true);
    }

    /**
     * Creates the type selection panel with a combo box for selecting the type of important day.
     * The combo box contains three options: Anniversary, Birthday, and Occasion.
     * When a type is selected, it triggers an update to the name field.
     */
    public void createType() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel typeLabel = new JLabel("Type:");
        String[] types = {"Anniversary", "Birthday", "Occasion"};
        typeComboBox = new JComboBox<>(types);

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                updateNameField();
            }
        });

        typeComboBox.setMaximumSize(new Dimension(200, 25));
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(typeLabel);
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(typeComboBox);

        mainPanel.add(panel);
    }

    /**
     * Creates the name input panel with a text field for entering the name of the important day.
     * The panel contains a label "Name:" and a text field limited to 200x25 pixels.
     * Adds rigid areas for spacing and adds the panel to the main panel.
     */
    public void createName(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 25));

        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(nameLabel);

        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(nameField);

        panel.add(Box.createRigidArea(new Dimension(0, 40)));

        mainPanel.add(panel);
    }
    
    /**
     * Creates the date input panel with a date picker component.
     * The panel contains a label "Date:" and a date picker limited to 200x25 pixels.
     * Uses DatePickerSettings to set the date format to "yyyy-MM-dd".
     * Adds rigid areas for spacing and adds the panel to the main panel.
     */
    public void createDate(){
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        datePickerSettings.setFormatForDatesCommonEra("yyyy-MM-dd"); // Date format (you can customize)

        datePicker = new DatePicker(datePickerSettings);
        datePicker.setMaximumSize(new Dimension(200, 25));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel dateLabel = new JLabel("Date:");

        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(dateLabel);
        panel.add(Box.createRigidArea(new Dimension(30, 0)));
        panel.add(datePicker);

        mainPanel.add(panel);
    }

    /**
     * Creates the description input panel with a text field for entering the description.
     * The panel contains a label "Description:" and a text field limited to 200x25 pixels.
     * Adds rigid areas for spacing and adds the panel to the main panel.
     */
    public void createDescription(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        descriptionField.setMaximumSize(new Dimension(200, 25));
        
        panel.add(Box.createRigidArea(new Dimension(20, 0)));
        panel.add(descriptionLabel);

        panel.add(descriptionField);

        panel.add(Box.createRigidArea(new Dimension(0, 40)));
        mainPanel.add(panel);
    }

    /**
     * Creates the specific input panel with a text field for entering specific information.
     * The panel contains a label that changes based on the selected type 
     * (Location/Person name/Frequency)
     * and a text field limited to 200x25 pixels.
     * Adds rigid areas for spacing and adds the panel to the main panel.
     */
    public void createSpecific(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        specificLabel = new JLabel("Location:");
        specificField = new JTextField();
        specificField.setMaximumSize(new Dimension(200, 25));
        
        panel.add(Box.createRigidArea(new Dimension(20, 00)));
        panel.add(specificLabel);

        panel.add(specificField);

        mainPanel.add(panel);
    }

    /**
     * Updates the specific label text based on the currently selected type in the type combo box.
     * Changes to "Person name:" for Birthday type, "Location:" for Anniversary type,
     * and "Frequency:" for Occasion type.
     */
    public void updateNameField(){
        String selectedType = (String) typeComboBox.getSelectedItem();
        if(selectedType.equals("Birthday")){
            specificLabel.setText("Person name:");
        }else if(selectedType.equals("Anniversary")){
            specificLabel.setText("Location:");
        }else if(selectedType.equals("Occasion")){
            specificLabel.setText("Frequency:");
        }
    }

    /**
     * Creates a panel with two buttons: Create and Cancel.
     * The Create button validates input and creates a new important day.
     * The Cancel button closes the frame without saving.
     */
    public void createTwoButtons(){
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String name = nameField.getText();
                String specificInput = specificField.getText();
                String specificType = specificLabel.getText();
                String description = descriptionField.getText();
                LocalDate date = datePicker.getDate();
                String type = typeComboBox.getSelectedItem().toString();

                // check if the name is empty
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        "Name cannot be empty.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                }

                // check if the datePicker is emtpy
                if (date == null) {
                    JOptionPane.showMessageDialog(frame,
                        "Date cannot be empty.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                }

                // check if the specific is empty
                if (specificInput.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, 
                        specificType + " cannot be empty.", 
                        "Input Error", 
                        JOptionPane.ERROR_MESSAGE);
                }

                if(type.equals("Occasion")){
                    try{
                        int frequency = Integer.parseInt(specificInput);
    
                        if (frequency <= 0) {
                            JOptionPane.showMessageDialog(frame, 
                                "Frequency must be a positive integer.", 
                                "Input Error", 
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(
                        frame, 
                        "Invalid INPUT. Please enter a positive integer.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                AddImportantDay newDayHandler = new AddImportantDay
                (name, type, date, description, specificInput);
                ImportantDay newDay = newDayHandler.getImportantDay();
                
                if (newDay != null) {
                    // show success create an important day message
                    JOptionPane.showMessageDialog(frame, 
                    "Important Day Created:\n" + newDay, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
                    LoadAndSave.importantDays.add(newDay);
                    
                    LoadAndSave.saveToFile();

                    // close frame after saving
                    frame.dispose();
                }
            }
        });

        JButton cancelbButton = new JButton("Cancel");
        cancelbButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(createButton);
        buttonPanel.add(cancelbButton);
        mainPanel.add(buttonPanel);
    }

}
