// This class stores static data on different Companies.
// It is stored in a separate object from the Shipping object to aviod
// unnessisary reading and processing.

import java.io.*;
import java.util.*;

public class CompanyData{
    private Map<String, Double> carbonIntensityMetrics; // kgCO2/$
    private double averageCarbonIntensityMetric;
    
    public CompanyData() throws FileNotFoundException {
        carbonIntensityMetrics = new HashMap<>();
        Scanner input = new Scanner(new File("companies.tsv"));
        while (input.hasNext()) {
            carbonIntensityMetrics.put(input.next(), input.nextDouble());
        }
        double total = 0;
        int num = 0;
        for (String company : carbonIntensityMetrics.keySet()) {
            total += carbonIntensityMetrics.get(company);
            num++;
        }
        averageCarbonIntensityMetric = total / num;
    }
    
    // this method returns the carbon intensity metric, which is
    // the kg of CO2 emitted by the company per dollar revenue.
    // if there is no company with the passed name, the average of
    // all stored companies is returned.
    // the passed string cannot be null
    public double getCarbonIntensityMetric(String company) {
        if (carbonIntensityMetrics.containsKey(company)) {
            return carbonIntensityMetrics.get(company);
        }
        return averageCarbonIntensityMetric;
    }
}
