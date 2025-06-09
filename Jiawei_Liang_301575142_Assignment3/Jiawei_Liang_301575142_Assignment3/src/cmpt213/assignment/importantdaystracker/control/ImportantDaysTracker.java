package cmpt213.assignment.importantdaystracker.control;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JOptionPane;

import cmpt213.assignment.importantdaystracker.model.ImportantDay;
import cmpt213.assignment.importantdaystracker.view.MainWindow;

/**
 * This class is for the main program of the important days tracker
 * @author Jiawei Liang
 */
public class ImportantDaysTracker{
    /**
     * This is the constructor of the ImportantDaysTracker class
     */
    public ImportantDaysTracker(){
        
    }

    /**
     * This method is for listing all the days in the list
     */
    public static void listAllDays(){
        List<ImportantDay> allDays = new ArrayList<>();

        // Filter important days by type and year
        for (ImportantDay day : LoadAndSave.importantDays) {
            allDays.add(day);
        }

        ImportantDaysTracker.sortDaysByDates(allDays);

        if (allDays.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, 
                "No items to show", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }

        MainWindow.displayFilteredDays(allDays);
    }

    /**
     * This orders the days by type and year, store them in a new arrayList: sameTypeThisYear
     * Pops up a dialog if no days to be shown
     * Display the filtered days
     * @param type type of important day, selected from the checkbox
     */
    public static void listByTypeThisYear(String type){
        LocalDate today = LocalDate.now();
        List<ImportantDay> sameTypeThisYear = new ArrayList<>();

        // Filter important days by type and year
        for (ImportantDay day : LoadAndSave.importantDays) {
            if (day.getType().equals(type) && day.getDate().getYear() == today.getYear()) {
                sameTypeThisYear.add(day);
            }
        }

        ImportantDaysTracker.sortDaysByDates(sameTypeThisYear);

        if (sameTypeThisYear.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, 
                "No important days to show.", 
                "EMPTY", 
                JOptionPane.ERROR_MESSAGE);
        }

        MainWindow.displayFilteredDays(sameTypeThisYear);
    }

    /**
     * This method is for listing the passed days this year
     * Pops up a dialog if no passed days to be shown
     */
    public static void listPassedDaysThisYear(){
        LocalDate today = LocalDate.now();
        List<ImportantDay> passDaysThisYear = new ArrayList<>();

        // Filter important days by type and year
        for (ImportantDay day : LoadAndSave.importantDays) {
            if (day.getDate().isBefore(today) && 
            day.getDate().getYear() == today.getYear()) {
                passDaysThisYear.add(day);
            }
        }

        ImportantDaysTracker.sortDaysByDates(passDaysThisYear);

        if (passDaysThisYear.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, 
                "No passed important days to show.", 
                "EMPTY", 
                JOptionPane.ERROR_MESSAGE);
        }else{
            MainWindow.displayFilteredDays(passDaysThisYear);
        }
    }

    /**
     * This method is for listing the upcoming days this year
     * Pops up a dialog if no upcoming days to be shown
     */
    public static void listUpcomingDaysThisYear(){
        LocalDate today = LocalDate.now();
        List<ImportantDay> upComingDaysThisYear = new ArrayList<>();

        // Filter important days by type and year
        for (ImportantDay day : LoadAndSave.importantDays) {
            if (day.getDate().isAfter(today) && 
            day.getDate().getYear() == today.getYear()) {
                upComingDaysThisYear.add(day);
            }
        }

        ImportantDaysTracker.sortDaysByDates(upComingDaysThisYear);

        if (upComingDaysThisYear.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, 
                "No upcoming important days to show.", 
                "EMPTY", 
                JOptionPane.ERROR_MESSAGE);
        }else{
            MainWindow.displayFilteredDays(upComingDaysThisYear);
        }
    }

    /**
     * This method sorts a list of ImportantDay objects by their dates in ascending order
     * @param importantDays The list of ImportantDay objects to be sorted
     */
    public static void sortDaysByDates(List<ImportantDay> importantDays){
        Collections.sort(importantDays, Comparator.comparing(ImportantDay::getDate));
    }

}

