// This class stores static data on different Companies.
// It is stored in a separate object from the Shipping object to aviod
// unnessisary reading and processing.
// This class should be constructed only once in a program, and passed to any
// created Company objects.

import java.io.*;
import java.util.*;

public class CompanyData {
    private Map<String, Double> carbonIntensityMetrics;
    private double averageCarbonIntensityMetric;
    
    // Constructs the CompanyData instance from a file called companies.tsv
    // which holds the carbon emission data on companies.
    public CompanyData() throws FileNotFoundException {
        carbonIntensityMetrics = new HashMap<>();
        Scanner input = new Scanner(new File("companies.tsv"));
        double total = 0;
        int num = 0;
        while (input.hasNext()) {
            String name = input.next();
            double metric = input.nextDouble();
            carbonIntensityMetrics.put(name, metric);
            total += metric;
            num++;
        }
        averageCarbonIntensityMetric = total / num;
    }
    
    // This method returns the carbon intensity metric, which is
    // The kg of CO2 emitted by the company per dollar revenue.
    // If there is no company with the passed name, the average of
    // all stored companies is returned.
    // The passed string cannot be null.
    public double getCarbonIntensityMetric(String company) {
        if (carbonIntensityMetrics.containsKey(company)) {
            return carbonIntensityMetrics.get(company);
        }
        return averageCarbonIntensityMetric;
    }
}
