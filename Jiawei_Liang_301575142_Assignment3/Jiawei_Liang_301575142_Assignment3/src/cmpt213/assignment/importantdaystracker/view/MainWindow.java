package cmpt213.assignment.importantdaystracker.view;
import cmpt213.assignment.importantdaystracker.model.ImportantDay;

import javax.swing.*;

import cmpt213.assignment.importantdaystracker.control.ImportantDaysTracker;
import cmpt213.assignment.importantdaystracker.control.LoadAndSave;

import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class represents the main window of the Important Days Tracker application.
 * It provides a GUI interface with buttons for filtering and displaying important days,
 * and functionality for adding new important days. The window includes a scrollable
 * list panel for displaying important days and buttons for different filtering options.
 * @author Jiawei Liang
 */
public class MainWindow {
    private static JPanel listPanel;
    private JFrame frame;
    private JPanel buttonPanel;
    private static JScrollPane scrollPane;
    private JPanel typePanel;
    private static List<ImportantDay> importantDays;

    /**
     * Constructor for MainWindow class.
     * Creates the main application window with a title, size, and layout.
     * Loads important days from file and sets up window closing behavior.
     */
    public MainWindow(){
        // main frame
        frame = new JFrame("Important Days Tracker");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBounds(500, 300, 550, 480);

        LoadAndSave.loadFromFile();
        importantDays = LoadAndSave.importantDays;

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LoadAndSave.saveToFile();
                frame.dispose();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // four buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton allButton = new JButton("ALL");
        JButton thisYearButton = new JButton("This year");
        JButton passedThisYearButton = new JButton("Passed this year");
        JButton upcomingThisYearButton = new JButton("Upcoming this year");
        
        allButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ImportantDaysTracker.listAllDays();
            }
        });

        passedThisYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ImportantDaysTracker.listPassedDaysThisYear();
            }
        });

        upcomingThisYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ImportantDaysTracker.listUpcomingDaysThisYear();
            }
        });

        buttonPanel.add(allButton);
        buttonPanel.add(thisYearButton);
        buttonPanel.add(passedThisYearButton);
        buttonPanel.add(upcomingThisYearButton);

        // panel for three type radiobox
        typePanel = new JPanel();
        typePanel.setLayout(new FlowLayout());

        // three type radiobox
        JRadioButton anniversaryRadioBox = new JRadioButton("Anniversary");
        JRadioButton birthdayRadioBox = new JRadioButton("Birthday");
        JRadioButton occasionRadioBox = new JRadioButton("Occasion");

        // a button group make sure that can be selected only one
        ButtonGroup typeButtonGroup = new ButtonGroup();
        typeButtonGroup.add(anniversaryRadioBox);
        typeButtonGroup.add(birthdayRadioBox);
        typeButtonGroup.add(occasionRadioBox);

        // add three type radiobox to the panel
        typePanel.add(anniversaryRadioBox);
        typePanel.add(birthdayRadioBox);
        typePanel.add(occasionRadioBox);

        thisYearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(anniversaryRadioBox.isSelected()){
                    ImportantDaysTracker.listByTypeThisYear("Anniversary");
                }else if(birthdayRadioBox.isSelected()){
                    ImportantDaysTracker.listByTypeThisYear("Birthday");
                }else if(occasionRadioBox.isSelected()){
                    ImportantDaysTracker.listByTypeThisYear("Occasion");
                }
                // }else{
                //     ImportantDaysTracker.listAllDays();
                // }
            }
        });

        // ADD button
        JPanel addButtonPanel = new JPanel();
        JButton addButton = new JButton("Add an Important Day");
        addButtonPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddFrame();
            }
        });

        // List panel inside JScrollPane
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(buttonPanel);
        mainPanel.add(typePanel);
        mainPanel.add(scrollPane);
        mainPanel.add(addButtonPanel);
 
        frame.add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Creates and displays the AddImportantDayFrame in a separate thread.
     * This method initializes the frame and sets up its components before making it visible.
    */
    public void showAddFrame(){
        SwingUtilities.invokeLater(() -> {
            AddImportantDayFrame addView = new AddImportantDayFrame();
            addView.createType();
            addView.createName();
            addView.createDate();
            addView.createDescription();
            addView.createSpecific();
            addView.createTwoButtons();
            addView.showFrame();
        });
    }

    /**
     * This displays different kinds of important days by providing a parameter
     * Displays each important day in a seperate panel, with a "remove" button
     * @param filteredDays input arrayList of ImportantDay
     */
    public static void displayFilteredDays(List<ImportantDay> filteredDays) {
        listPanel.removeAll(); 
        int index = 1;
        for (ImportantDay day : filteredDays) {
            JPanel dayPanel = new JPanel();
            dayPanel.setLayout(new BorderLayout());
            dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    
            // Index label
            JLabel dayNumberLabel = new JLabel("Important Day #" + index);
            dayNumberLabel.setFont(new Font("Arial", Font.BOLD, 16)); 
            dayPanel.add(dayNumberLabel, BorderLayout.NORTH);
            index++;
    
            // Text area for details
            JTextArea dayInfo = new JTextArea(day.toString());
            dayInfo.setWrapStyleWord(true);
            dayInfo.setLineWrap(true);
            dayInfo.setEditable(false);
            dayInfo.setPreferredSize(new Dimension(500, 70));
            dayPanel.add(dayInfo, BorderLayout.CENTER);
    
            // Add padding
            dayPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    
            // Remove button
            JPanel removeButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                importantDays.remove(day);
                displayFilteredDays(filteredDays);  
            });
    
            removeButtonPanel.add(removeButton);
            dayPanel.add(removeButtonPanel, BorderLayout.SOUTH);
            listPanel.add(dayPanel);
    
            // Add spacing between items
            listPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        // Refresh UI
        listPanel.revalidate();
        listPanel.repaint();
    
        // Ensure scroll bar is at the top
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(0));
    }
    
    /**
     * Displays the frame by setting its visibility to true.
     */
    public void showFrame(){
        frame.setVisible(true);
    }
}
