/**
Course 1 Week 3 exercise
Process and test a number of information in given CSV files
Uses Duke's simplified FileResource class
 */
 
import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
            String mycountry = record.get("Country");
            String exports = record.get("Exports");
            String value = record.get("Value (dollars)");
            return mycountry + ": " + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                String mycountry = record.get("Country");
                System.out.println(mycountry);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                count += 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        //bigExporters(parser, "$999,999,999,999");
    }

}
