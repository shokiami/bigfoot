import java.io.*;
import java.util.*;

public class CompanyData{
    private Map<String, Integer> carbonIntensityMetrics; // kgCO2/$
    private double averageCarbonIntensityMetric;

    public CompanyData() throws FileNotFoundException {
        carbonIntensityMetrics = new HashMap<>();
        Scanner input = new Scanner(new File("companies.tsv"));
        while (input.hasNext()) {
            carbonIntensityMetrics.put(input.next(), input.nextInt());
        }
        double total = 0;
        int num = 0;
        for (String company : carbonIntensityMetrics.keySet()) {
            total += carbonIntensityMetrics.get(company);
            num++;
        }
        averageCarbonIntensityMetric = total / num;
    }

    public double getCarbonIntensityMetric(String company) {
        if (carbonIntensityMetrics.containsKey(company)) {
            return carbonIntensityMetrics.get(company);
        }
        return averageCarbonIntensityMetric;
    }
}
