
/**
Course 1 Week 4 exercise
Process and test a number of information about birth data in given CSV files
Uses Duke's simplified FileResource, DirectoryResource classes
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNamesMiniProject {
    
        public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int countBoys = 0;
        int totalBoys = 0;
        int countGirls = 0;
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                countBoys += 1;
            }
            else {
                totalGirls += numBorn;
                countGirls += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("number of girls names: " + countGirls);
        System.out.println("total girls = " + totalGirls);
        System.out.println("number of boys names: " + countBoys);
        System.out.println("total boys = " + totalBoys);
     }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");        
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (!rec.get(0).equals(name)) {
                if (rec.get(1).equals(gender)) {
                    rank += 1;
                }
            }
            else {
                if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                return rank + 1;
               }
               }
        }
        return -1;
    }
    
    public void testgetRank() {
        System.out.println(getRank(1971, "Frank", "M"));
       }
       
    public String getName(int year, int rank, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int count = 1;
        for (File f : dr.selectedFiles()) {
         FileResource fr = new FileResource(f);
         for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (count == rank) {
                    return rec.get(0);
                   }
                else {
                    count += 1;
                   }
               }
           } }
           return "NO NAME";
        
       }
       
    public void testgetName() {
        System.out.println(getName(1982, 450, "M"));
       }
       
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String result = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + result + " if she was born in " + newYear);
       }
       
    public void testwhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
       }
       
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highest = 99999;
        int result = 0;
            for (File f : dr.selectedFiles()) {
                FileResource fr = new FileResource(f);
                String filename = f.getName();
                String stryear = filename.replaceAll("\\D+","");
                int year = Integer.parseInt(stryear);
                int current = getRank(year, name, gender);
                if (current < highest && current > 0) {
                    highest = current;
                    result = year;
                }
            }
        if (highest == 99999) {
            return -1;
        }
        else {
            return result;
        }
       }
       
    public void testyearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender) {
        int sum = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String filename = f.getName();
            String stryear = filename.replaceAll("\\D+","");
            int year = Integer.parseInt(stryear);
            int current = getRank(year, name, gender);
            sum += current;
            count += 1;
        }
        double sumres = Double.valueOf(sum);
        return sumres / count;
    }
    
    public void testgetAverageRank() {
        System.out.println(getAverageRank("Robert", "M"));
    }
}
