
/**
Course 1 Week 3 exercise
Process and test a number of information about weather in given CSV files
Uses Duke's simplified FileResource, DirectoryResource classes
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ParsingWeatherData {
    
    public CSVRecord smallestOfTwoTemp(CSVRecord current, CSVRecord smallest) {
        if (smallest == null) {
            smallest = current;
        } else {
            double currentTemp = Double.parseDouble(current.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallest.get("TemperatureF"));
            if (currentTemp < smallestTemp && currentTemp > -9999) {
                smallest = current;
            }
        }
        return smallest;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallest = null;
        for (CSVRecord current : parser) {
            smallest = smallestOfTwoTemp(current, smallest);
        }
        return smallest;
    }
    
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("smallest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("TimeEDT"));
    }
    
    public String fileWithColdestTemperature() {
        CSVRecord coldest = null;
        File result = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            coldest = smallestOfTwoTemp(current, coldest);
            if (coldest == current) {
                result = f;
            }
        }
        String temperatures = "";
        return "Coldest day was in file " + result + "\nColdest temperature on that day was " + coldest.get("TemperatureF");
    }
    
    public void testFileWithColdestTemperature() {
        System.out.println(fileWithColdestTemperature());
    }
    
    public CSVRecord smallestOfTwoHumid(CSVRecord current, CSVRecord smallest) {
        if (smallest == null) {
            smallest = current;
        } else {
            if (current.get("Humidity").length() < 3) {
             int currentHumid = Integer.parseInt(current.get("Humidity"));
             int smallestHumid = Integer.parseInt(smallest.get("Humidity"));
             if (currentHumid < smallestHumid) {
                smallest = current;
             }
            }
        }
        return smallest;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord current: parser) {
            lowest = smallestOfTwoHumid(current, lowest);
        }
        return lowest;
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowest = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            lowest = smallestOfTwoHumid(current, lowest);
        }
        return lowest;
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord lowest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double sum = 0.0;
        int count = 0;
        for (CSVRecord current : parser) {
            sum += Double.parseDouble(current.get("TemperatureF"));
            count += 1;
        }
        System.out.println("sum of temps: " + sum);
        System.out.println("count of temps: " + count);
        double average = sum / count;
        return average;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + average);
    }
    
    public CSVRecord highestOfTwoHumid(CSVRecord current, CSVRecord highest) {
        if (highest == null) {
            highest = current;
        } else {
            if (current.get("Humidity").length() < 3) {
             int currentHumid = Integer.parseInt(current.get("Humidity"));
             int highestHumid = Integer.parseInt(highest.get("Humidity"));
             if (currentHumid > highestHumid) {
                highest = current;
             }
            }
        }
        return highest;
    }
}
